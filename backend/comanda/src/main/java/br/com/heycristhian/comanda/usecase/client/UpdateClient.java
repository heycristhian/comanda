package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.domain.repository.ClientRepository;
import br.com.heycristhian.comanda.infrastructure.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_NAME_MODEL;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.UPDATING_OBJECT_DATABASE;

@Slf4j
@Service
public class UpdateClient {

    private final ClientRepository clientRepository;
    private final SearchClient searchClient;
    private final ClientMapper clientMapper;

    public UpdateClient(ClientRepository clientRepository, SearchClient searchClient, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.searchClient = searchClient;
        this.clientMapper = clientMapper;
    }

    public Client execute(Long id, Client newClient) {
        var currentClient = searchClient.byId(id);

        this.clientMapper.toClientReference(newClient, currentClient);

        log.info(UPDATING_OBJECT_DATABASE, CLIENT_NAME_MODEL);
        return clientRepository.save(currentClient);
    }
}
