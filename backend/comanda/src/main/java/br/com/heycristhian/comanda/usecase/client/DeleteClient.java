package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_NAME_MODEL;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.DELETING_OBJECT_DATABASE;

@Slf4j
@Service
public class DeleteClient {

    private final ClientRepository clientRepository;
    private final SearchClient searchClient;

    public DeleteClient(ClientRepository clientRepository, SearchClient searchClient) {
        this.clientRepository = clientRepository;
        this.searchClient = searchClient;
    }

    public void execute(Long id) {
        searchClient.byId(id);

        log.info(DELETING_OBJECT_DATABASE, CLIENT_NAME_MODEL);
        clientRepository.deleteById(id);
    }
}
