package br.com.heycristhian.comanda.infrastructure.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "{valid.email}")
        @NotBlank(message = "{string.not.blank}")
        String username,

        @NotBlank(message = "{string.not.blank}")
        String password) {
}
