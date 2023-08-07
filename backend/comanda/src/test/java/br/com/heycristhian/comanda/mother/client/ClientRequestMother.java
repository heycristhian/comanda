package br.com.heycristhian.comanda.mother.client;

import br.com.heycristhian.comanda.infrastructure.http.dto.request.ClientRequest;

public abstract class ClientRequestMother {
    public static ClientRequest getClientRequest() {
        return ClientRequest.builder()
                .nome("Cristhian")
                .sobrenome("Dias")
                .celular("18999999999")
                .build();
    }
}
