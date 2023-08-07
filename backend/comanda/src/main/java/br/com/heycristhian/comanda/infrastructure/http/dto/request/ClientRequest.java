package br.com.heycristhian.comanda.infrastructure.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    @NotBlank(message = "{string.not.blank}")
    private String nome;

    @NotBlank(message = "{string.not.blank}")
    private String sobrenome;

    @NotBlank(message = "{string.not.blank}")
    @Pattern(regexp = "\\d{11}", message = "Deve ser um número de celular válido")
    private String celular;
}
