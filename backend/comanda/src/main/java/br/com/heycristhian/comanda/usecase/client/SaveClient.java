package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.domain.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_NAME_MODEL;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.SAVING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.infrastructure.util.SecurityUtil.getLoggedId;

@Slf4j
@Service
public class SaveClient {

    private final ClientRepository clientRepository;

    public SaveClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(Client client) {
        client.setUserCreatedId(getLoggedId());

        log.info(SAVING_OBJECT_DATABASE, CLIENT_NAME_MODEL);
        return clientRepository.save(client);
    }
}
