package br.com.heycristhian.comanda.mother;

import br.com.heycristhian.comanda.domain.Client;

public abstract class ClientMother {
    public static Client getClient() {
        return Client.builder()
                .firstName("Cristhian")
                .lastName("Dias")
                .phone("18999999999")
                .build();
    }
}
