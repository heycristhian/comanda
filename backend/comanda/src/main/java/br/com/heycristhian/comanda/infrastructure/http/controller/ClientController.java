package br.com.heycristhian.comanda.infrastructure.http.controller;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.ClientRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.ClientResponse;
import br.com.heycristhian.comanda.infrastructure.mapper.ClientMapper;
import br.com.heycristhian.comanda.usecase.client.DeleteClient;
import br.com.heycristhian.comanda.usecase.client.SaveClient;
import br.com.heycristhian.comanda.usecase.client.SearchClient;
import br.com.heycristhian.comanda.usecase.client.UpdateClient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_NAME_MODEL;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_REQUEST_NAME_MODEL;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.CLIENT_RESPONSE_NAME_ENTITY;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.HTTP_RESPONSE;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.MAPPING_TO;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.STARTING_DELETE_OBJECT;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.STARTING_FIND_OBJECT;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.STARTING_SAVE_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.STARTING_UPDATE_OBJECT_DATABASE;

@Slf4j
@RestController
@Tag(name = "CLIENTE", description = "Endpoints relacionado a manter cliente")
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final SaveClient saveClient;
    private final SearchClient searchClient;
    private final UpdateClient updateClient;
    private final DeleteClient deleteClient;
    private final ClientMapper clientMapper;

    public ClientController(SaveClient saveClient, SearchClient searchClient, UpdateClient updateClient, DeleteClient deleteClient, ClientMapper clientMapper) {
        this.saveClient = saveClient;
        this.searchClient = searchClient;
        this.updateClient = updateClient;
        this.deleteClient = deleteClient;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientResponse> save(@RequestBody @Valid ClientRequest clientRequest, UriComponentsBuilder uriBuilder) {
        log.info(STARTING_SAVE_OBJECT_DATABASE, CLIENT_NAME_MODEL);

        log.info(MAPPING_TO, CLIENT_REQUEST_NAME_MODEL, CLIENT_NAME_MODEL);
        var client = this.clientMapper.toClientReference(clientRequest);

        client = saveClient.execute(client);

        log.info(MAPPING_TO, CLIENT_NAME_MODEL, CLIENT_RESPONSE_NAME_ENTITY);
        var clientResponse = this.clientMapper.toClientResponse(client);

        URI uri = uriBuilder.path("/api/v1/clients/{id}").buildAndExpand(client.getId()).toUri();

        log.info(HTTP_RESPONSE);
        return ResponseEntity.created(uri).body(clientResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll(Pageable pageable) {
        log.info(STARTING_FIND_OBJECT, CLIENT_NAME_MODEL);
        List<Client> clients = searchClient.all();

        log.info(MAPPING_TO, CLIENT_NAME_MODEL, CLIENT_RESPONSE_NAME_ENTITY);
        List<ClientResponse> clientResponse = clients
                .stream()
                .map(this.clientMapper::toClientResponse)
                .toList();

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(clientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        log.info(STARTING_FIND_OBJECT, CLIENT_NAME_MODEL);
        var client = searchClient.byId(id);

        log.info(MAPPING_TO, CLIENT_NAME_MODEL, CLIENT_RESPONSE_NAME_ENTITY);
        var clientResponse = this.clientMapper.toClientResponse(client);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(clientResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
        log.info(STARTING_UPDATE_OBJECT_DATABASE, CLIENT_NAME_MODEL);

        log.info(MAPPING_TO, CLIENT_REQUEST_NAME_MODEL, CLIENT_NAME_MODEL);
        var client = clientMapper.toClientReference(clientRequest);

        client = updateClient.execute(id, client);

        log.info(MAPPING_TO, CLIENT_NAME_MODEL, CLIENT_RESPONSE_NAME_ENTITY);
        var clientResponse = this.clientMapper.toClientResponse(client);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info(STARTING_DELETE_OBJECT, CLIENT_NAME_MODEL);
        deleteClient.execute(id);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.noContent().build();
    }
}
