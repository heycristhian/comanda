package br.com.heycristhian.comanda.infrastructure.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "{string.not.blank}")
        @Email(message = "{valid.email}")
        String usuario,

        @NotBlank(message = "{string.not.blank}")
        String nomeCompleto,

        @NotBlank(message = "{string.not.blank}")
        String senha
) {
}
