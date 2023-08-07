package br.com.heycristhian.comanda.infrastructure.http.handler;

import br.com.heycristhian.comanda.infrastructure.http.dto.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class DefaultExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var response = handleExceptionResponse(httpStatus, "Erro inesperado do sistema");

        log.error("INTERNAL_SERVER_ERROR: {}", e.getLocalizedMessage());
        return ResponseEntity.status(httpStatus).body(response);
    }


    private ExceptionResponse handleExceptionResponse(HttpStatus httpStatus, String message) {
        return ExceptionResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .correlationId(getCorrelationId())
                .build();
    }

    private String getCorrelationId() {
        return MDC.get("correlationId");
    }
}
