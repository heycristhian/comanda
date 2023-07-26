package br.com.heycristhian.comanda.controller;

import br.com.heycristhian.comanda.controller.dto.request.ClientRequest;
import br.com.heycristhian.comanda.controller.dto.response.ClientResponse;
import br.com.heycristhian.comanda.domain.Client;
import br.com.heycristhian.comanda.mapper.ClientMapper;
import br.com.heycristhian.comanda.usecase.client.DeleteClient;
import br.com.heycristhian.comanda.usecase.client.SaveClient;
import br.com.heycristhian.comanda.usecase.client.SearchClient;
import br.com.heycristhian.comanda.usecase.client.UpdateClient;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static br.com.heycristhian.comanda.util.MessageUtil.*;

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

    public ClientController(SaveClient saveClient, SearchClient searchClient, UpdateClient updateClient, DeleteClient deleteClient) {
        this.saveClient = saveClient;
        this.searchClient = searchClient;
        this.updateClient = updateClient;
        this.deleteClient = deleteClient;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClientResponse> save(@RequestBody @Valid ClientRequest clientRequest, UriComponentsBuilder uriBuilder) {
        log.info(STARTING_SAVE_OBJECT_DATABASE, CLIENT_NAME_ENTITY);
        var client = saveClient.execute(clientRequest);

        log.info(MAPPING_TO, CLIENT_NAME_ENTITY, CLIENT_RESPONSE_NAME_ENTITY);
        var clientResponse = ClientMapper.INSTANCE.toClientResponse(client);

        URI uri = uriBuilder.path("/api/v1/clients/{id}").buildAndExpand(client.getId()).toUri();

        log.info(HTTP_RESPONSE);
        return ResponseEntity.created(uri).body(clientResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponse>> findAll(Pageable pageable) {
        log.info(STARTING_FIND_OBJECT, CLIENT_NAME_ENTITY);
        Page<Client> client = searchClient.all(pageable);

        log.info(MAPPING_TO, CLIENT_NAME_ENTITY, CLIENT_RESPONSE_NAME_ENTITY);
        Page<ClientResponse> clientResponse = client
                .map(ClientMapper.INSTANCE::toClientResponse);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(clientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Long id) {
        log.info(STARTING_FIND_OBJECT, CLIENT_NAME_ENTITY);
        var client = searchClient.byId(id);

        log.info(MAPPING_TO, CLIENT_NAME_ENTITY, CLIENT_RESPONSE_NAME_ENTITY);
        var clientResponse = ClientMapper.INSTANCE.toClientResponse(client);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(clientResponse);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClientResponse> update(@PathVariable Long id, @Valid @RequestBody ClientRequest clientRequest) {
        log.info(STARTING_UPDATE_OBJECT_DATABASE, CLIENT_NAME_ENTITY);
        var client = updateClient.execute(id, clientRequest);

        log.info(MAPPING_TO, CLIENT_NAME_ENTITY, CLIENT_RESPONSE_NAME_ENTITY);
        var clientResponse = ClientMapper.INSTANCE.toClientResponse(client);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(clientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info(STARTING_DELETE_OBJECT, CLIENT_NAME_ENTITY);
        deleteClient.execute(id);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.noContent().build();
    }
}
