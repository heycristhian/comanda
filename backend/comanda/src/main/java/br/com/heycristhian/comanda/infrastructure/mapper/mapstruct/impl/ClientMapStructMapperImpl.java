package br.com.heycristhian.comanda.infrastructure.mapper.mapstruct.impl;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.infrastructure.database.schema.ClientSchema;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.ClientRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.ClientResponse;
import br.com.heycristhian.comanda.infrastructure.mapper.ClientMapper;
import br.com.heycristhian.comanda.infrastructure.mapper.mapstruct.ClientMapStructMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapStructMapperImpl implements ClientMapper {

    private static final ClientMapStructMapper mapper = ClientMapStructMapper.INSTANCE;

    @Override
    public Client toClientReference(ClientRequest clientRequest) {
        return mapper.toClient(clientRequest);
    }

    @Override
    public ClientResponse toClientResponse(Client client) {
        return mapper.toClientResponse(client);
    }

    @Override
    public ClientSchema toClientSchema(Client client) {
        return mapper.toClientSchema(client);
    }

    @Override
    public Client toClientReference(ClientSchema clientSchema) {
        return mapper.toClient(clientSchema);
    }

    @Override
    public void toClientReference(Client newClient, Client currentClient) {
        mapper.toClientReference(newClient, currentClient);
    }
}
