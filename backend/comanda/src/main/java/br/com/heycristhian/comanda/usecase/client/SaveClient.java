package br.com.heycristhian.comanda.usecase.client;

import br.com.heycristhian.comanda.controller.dto.request.ClientRequest;
import br.com.heycristhian.comanda.domain.Client;
import br.com.heycristhian.comanda.mapper.ClientMapper;
import br.com.heycristhian.comanda.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.util.MessagePattern.CLIENT_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.CLIENT_REQUEST_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.MAPPING_TO;
import static br.com.heycristhian.comanda.util.MessagePattern.SAVING_OBJECT_DATABASE;

@Slf4j
@Service
public class SaveClient {

    private final ClientRepository clientRepository;

    public SaveClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(ClientRequest clientRequest) {
        log.info(MAPPING_TO, CLIENT_REQUEST_NAME_ENTITY, CLIENT_NAME_ENTITY);
        var client = ClientMapper.INSTANCE.toClient(clientRequest);

        log.info(SAVING_OBJECT_DATABASE, CLIENT_NAME_ENTITY);
        return clientRepository.save(client);
    }
}
