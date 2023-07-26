package br.com.heycristhian.comanda.controller;

import br.com.heycristhian.comanda.controller.dto.request.UserRequest;
import br.com.heycristhian.comanda.controller.dto.response.UserResponse;
import br.com.heycristhian.comanda.mapper.UserMapper;
import br.com.heycristhian.comanda.usecase.user.SaveUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static br.com.heycristhian.comanda.util.MessagePattern.HTTP_RESPONSE;
import static br.com.heycristhian.comanda.util.MessagePattern.MAPPING_TO;
import static br.com.heycristhian.comanda.util.MessagePattern.STARTING_SAVE_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_RESPONSE_NAME_ENTITY;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final SaveUser saveUser;

    public UserController(SaveUser saveUser) {
        this.saveUser = saveUser;
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriBuilder) {
        log.info(STARTING_SAVE_OBJECT_DATABASE, USER_NAME_ENTITY);
        var user = saveUser.execute(userRequest);

        log.info(MAPPING_TO, USER_NAME_ENTITY, USER_RESPONSE_NAME_ENTITY);
        var userResponse = UserMapper.INSTANCE.toUserResponse(user);

        URI uri = uriBuilder.path("/api/v1/clients/{id}").buildAndExpand(user.getId()).toUri();

        log.info(HTTP_RESPONSE);
        return ResponseEntity.created(uri).body(userResponse);
    }
}
