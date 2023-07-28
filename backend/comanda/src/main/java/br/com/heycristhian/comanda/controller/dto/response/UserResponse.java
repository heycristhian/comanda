package br.com.heycristhian.comanda.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        Long id,

        @JsonProperty(value = "usuario")
        String username,

        @JsonProperty(value = "nomeCompleto")
        String fullname
) {
}
