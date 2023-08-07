package br.com.heycristhian.comanda.infrastructure.http.handler;

import br.com.heycristhian.comanda.infrastructure.http.dto.response.ExceptionResponse;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.FieldExceptionResponse;
import br.com.heycristhian.comanda.domain.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.domain.exception.PasswordException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.regex.Matcher;

import static br.com.heycristhian.comanda.usecase.util.RegexPattern.getMatcher;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleException(DataIntegrityViolationException e) {
        var matcher = getMatcherDuplicateEntry(e.getLocalizedMessage());
        String errorMessage;
        HttpStatus httpStatus;

        if (matcher.find()) {
            errorMessage = "Dado duplicado no campo: " + matcher.group(2);
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            errorMessage = "Os dados violam a integridade do banco de dados";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        var response = handleExceptionResponse(httpStatus, errorMessage);

        log.error(httpStatus + ": {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler({PropertyReferenceException.class, PasswordException.class})
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException e) {
        var httpStatus = HttpStatus.BAD_REQUEST;
        var response = handleExceptionResponse(httpStatus, e.getLocalizedMessage());

        log.error("BAD_REQUEST: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleObjectNotFoundException(ObjectNotFoundException e) {
        var httpStatus = HttpStatus.NOT_FOUND;
        var response = handleExceptionResponse(httpStatus, e.getLocalizedMessage());

        log.error("NOT_FOUND: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException e) {
        var httpStatus = HttpStatus.FORBIDDEN;
        var response = handleExceptionResponse(httpStatus, e.getLocalizedMessage());

        log.error("FORBIDDEN: {}", e.getLocalizedMessage());
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

    private Matcher getMatcherDuplicateEntry(String message) {
        final String regex = "\\[Duplicate entry '(.*?)' for key ('.*?')]";
        return getMatcher(regex, message);
    }

    private String getCorrelationId() {
        return MDC.get("correlationId");
    }
}
