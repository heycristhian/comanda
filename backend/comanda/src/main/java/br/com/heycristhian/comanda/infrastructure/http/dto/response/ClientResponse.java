package br.com.heycristhian.comanda.infrastructure.http.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String nome;
    private String sobrenome;
    private String celular;
}
