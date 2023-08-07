package br.com.heycristhian.comanda.infrastructure.http.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponse(
        Long id,

        @JsonProperty(value = "usuario")
        String username,

        @JsonProperty(value = "nomeCompleto")
        String fullName
) {
}
