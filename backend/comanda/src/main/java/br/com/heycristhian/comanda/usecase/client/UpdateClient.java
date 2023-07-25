package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.controller.dto.request.ClientRequest;
import br.com.heycristhian.comanda.domain.Client;
import br.com.heycristhian.comanda.mapper.ClientMapper;
import br.com.heycristhian.comanda.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.util.MessageUtil.*;

@Slf4j
@Service
public class UpdateClient {

    private final ClientRepository clientRepository;
    private final SearchClient searchClient;

    public UpdateClient(ClientRepository clientRepository, SearchClient searchClient) {
        this.clientRepository = clientRepository;
        this.searchClient = searchClient;
    }

    public Client execute(Long id, ClientRequest clientRequest) {
        var client = searchClient.byId(id);

        log.info(MAPPING_TO, CLIENT_REQUEST_NAME_ENTITY, CLIENT_NAME_ENTITY);
        ClientMapper.INSTANCE.toClient(client, clientRequest);

        log.info(UPDATING_OBJECT_DATABASE, CLIENT_NAME_ENTITY);
        return clientRepository.save(client);
    }
}
