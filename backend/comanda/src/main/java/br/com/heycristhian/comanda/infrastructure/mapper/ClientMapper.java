package br.com.heycristhian.comanda.infrastructure.mapper;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.infrastructure.database.schema.ClientSchema;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.ClientRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.ClientResponse;

public interface ClientMapper {

    Client toClientReference(ClientRequest clientRequest);

    ClientResponse toClientResponse(Client client);

    ClientSchema toClientSchema(Client client);

    Client toClientReference(ClientSchema clientSchema);

    void toClientReference(Client newClient, Client currentClient);
}
