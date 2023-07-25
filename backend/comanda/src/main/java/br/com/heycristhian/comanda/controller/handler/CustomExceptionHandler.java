package br.com.heycristhian.comanda.controller.handler;

import br.com.heycristhian.comanda.controller.dto.response.ExceptionResponse;
import br.com.heycristhian.comanda.controller.dto.response.FieldExceptionResponse;
import br.com.heycristhian.comanda.exception.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var response = ExceptionResponse.builder()
                .code(status.value())
                .status(status.toString())
                .message("Existe(m) campo(s) com erro")
                .correlationId(getCorrelationId())
                .fields(getFieldsExceptionResponse(ex))
                .build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = handleExceptionResponse(httpStatus, "Erro inesperado do sistema");

        log.error("Internal Server Error: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleException(DataIntegrityViolationException e) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var response = handleExceptionResponse(httpStatus, handleDataIntegrityViolationExceptionMessage(e));

        log.error("Bad Request: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleObjectNotFoundException(ObjectNotFoundException e) {
        var httpStatus = HttpStatus.NOT_FOUND;
        var response = handleExceptionResponse(httpStatus, e.getLocalizedMessage());

        log.error("Bad Request: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    private List<FieldExceptionResponse> getFieldsExceptionResponse(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> FieldExceptionResponse.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .parameter(error.getRejectedValue())
                        .build())
                .toList();
    }

    private ExceptionResponse handleExceptionResponse(HttpStatus httpStatus, String message) {
        return ExceptionResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .correlationId(getCorrelationId())
                .build();
    }

    private String handleDataIntegrityViolationExceptionMessage(DataIntegrityViolationException e) {
        final String regex = "\\[Duplicate entry '(.*?)' for key ('.*?')]";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(e.getLocalizedMessage());

        if (matcher.find()) {
            return "Dado duplicado no campo: " + matcher.group(2);
        }

        return "Os dados violam a integridade do banco de dados";
    }

    private String getCorrelationId() {
        return MDC.get("correlationId");
    }
}
