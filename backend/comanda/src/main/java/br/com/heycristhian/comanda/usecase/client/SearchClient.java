package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.domain.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_NAME_MODEL;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.SEARCHING_OBJECT_DATABASE;

@Slf4j
@Service
public class SearchClient {

    private final ClientRepository clientRepository;

    public SearchClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> all() {
        log.info(SEARCHING_OBJECT_DATABASE, CLIENT_NAME_MODEL);
        return clientRepository.findAll();
    }

    public Client byId(Long id) {
        log.info(SEARCHING_OBJECT_DATABASE, CLIENT_NAME_MODEL);
        return clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado para o id: " + id));
    }
}
