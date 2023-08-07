package br.com.heycristhian.comanda.mother.client;

import br.com.heycristhian.comanda.domain.model.Client;

public abstract class ClientMother {
    public static Client getClient() {
        Client client = new Client();
        client.setFirstName("Cristhian");
        client.setLastName("Silva");
        client.setPhone("18999999999");

        return client;
    }
}
