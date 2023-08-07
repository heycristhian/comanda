package br.com.heycristhian.comanda.mother.client;

import br.com.heycristhian.comanda.infrastructure.database.schema.ClientSchema;

public abstract class ClientSchemaMother {
    public static ClientSchema getClientSchema() {
        return ClientSchema.builder()
                .firstName("Cristhian")
                .lastName("Dias")
                .phone("18999999999")
                .build();
    }
}
