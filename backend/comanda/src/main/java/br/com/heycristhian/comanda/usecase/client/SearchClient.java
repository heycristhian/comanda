package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.domain.Client;
import br.com.heycristhian.comanda.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.util.MessageUtil.CLIENT_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessageUtil.SEARCHING_OBJECT_DATABASE;

@Slf4j
@Service
public class SearchClient {

    private final ClientRepository clientRepository;

    public SearchClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> all(Pageable pageable) {
        log.info(SEARCHING_OBJECT_DATABASE, CLIENT_NAME_ENTITY);
        return clientRepository.findAll(pageable);
    }

    public Client byId(Long id) {
        log.info(SEARCHING_OBJECT_DATABASE, CLIENT_NAME_ENTITY);
        return clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado para o id: " + id));
    }
}
