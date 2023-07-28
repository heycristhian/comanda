package br.com.heycristhian.comanda.controller;

import br.com.heycristhian.comanda.controller.dto.request.UserRequest;
import br.com.heycristhian.comanda.controller.dto.response.UserResponse;
import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.mapper.UserMapper;
import br.com.heycristhian.comanda.usecase.user.DeleteUser;
import br.com.heycristhian.comanda.usecase.user.SaveUser;
import br.com.heycristhian.comanda.usecase.user.SearchUser;
import br.com.heycristhian.comanda.usecase.user.UpdateUser;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

import static br.com.heycristhian.comanda.util.MessagePattern.HTTP_RESPONSE;
import static br.com.heycristhian.comanda.util.MessagePattern.MAPPING_TO;
import static br.com.heycristhian.comanda.util.MessagePattern.STARTING_DELETE_OBJECT;
import static br.com.heycristhian.comanda.util.MessagePattern.STARTING_FIND_OBJECT;
import static br.com.heycristhian.comanda.util.MessagePattern.STARTING_SAVE_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.util.MessagePattern.STARTING_UPDATE_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_RESPONSE_NAME_ENTITY;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final SaveUser saveUser;
    private final SearchUser searchUser;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;

    public UserController(SaveUser saveUser, SearchUser searchUser, UpdateUser updateUser, DeleteUser deleteUser) {
        this.saveUser = saveUser;
        this.searchUser = searchUser;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponse> save(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriBuilder) {
        log.info(STARTING_SAVE_OBJECT_DATABASE, USER_NAME_ENTITY);
        var user = saveUser.execute(userRequest);

        log.info(MAPPING_TO, USER_NAME_ENTITY, USER_RESPONSE_NAME_ENTITY);
        var userResponse = UserMapper.INSTANCE.toUserResponse(user);

        URI uri = uriBuilder.path("/api/v1/clients/{id}").buildAndExpand(user.getId()).toUri();

        log.info(HTTP_RESPONSE);
        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        log.info(STARTING_FIND_OBJECT, USER_NAME_ENTITY);
        Page<User> users = searchUser.all(pageable);

        log.info(MAPPING_TO, USER_NAME_ENTITY, USER_RESPONSE_NAME_ENTITY);
        Page<UserResponse> usersResponse = users.map(UserMapper.INSTANCE::toUserResponse);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(usersResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        log.info(STARTING_FIND_OBJECT, USER_NAME_ENTITY);
        User user = searchUser.byId(id);

        log.info(MAPPING_TO, USER_NAME_ENTITY, USER_RESPONSE_NAME_ENTITY);
        var userResponse = UserMapper.INSTANCE.toUserResponse(user);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserRequest userRequest, @PathVariable Long id) {
        log.info(STARTING_UPDATE_OBJECT_DATABASE, USER_NAME_ENTITY);
        var user = updateUser.execute(userRequest, id);

        log.info(MAPPING_TO, USER_NAME_ENTITY, USER_RESPONSE_NAME_ENTITY);
        var userResponse = UserMapper.INSTANCE.toUserResponse(user);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info(STARTING_DELETE_OBJECT, USER_NAME_ENTITY);
        deleteUser.execute(id);

        log.info(HTTP_RESPONSE);
        return ResponseEntity.noContent().build();
    }
}
