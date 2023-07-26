package br.com.heycristhian.comanda.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "{string.not.blank}")
        @Email(message = "{valid.email}")
        @JsonProperty(value = "usuario")
        String username,

        @NotBlank(message = "{string.not.blank}")
        @JsonProperty(value = "nomeCompleto")
        String fullname,

        @NotBlank(message = "{string.not.blank}")
        @JsonProperty(value = "senha")
        String password
) {
}
