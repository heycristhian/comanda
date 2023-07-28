package br.com.heycristhian.comanda.usecase.user;

import br.com.heycristhian.comanda.controller.dto.request.UserRequest;
import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.mapper.UserMapper;
import br.com.heycristhian.comanda.repository.UserRepository;
import br.com.heycristhian.comanda.usecase.password.EncodePassword;
import br.com.heycristhian.comanda.validation.ValidatePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.heycristhian.comanda.util.MessagePattern.MAPPING_TO;
import static br.com.heycristhian.comanda.util.MessagePattern.UPDATING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_REQUEST_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.VALIDATING_PASSWORD;

@Slf4j
@Service
public class UpdateUser {

    private final UserRepository userRepository;
    private final EncodePassword encodePassword;
    private final List<ValidatePassword> validatesPassword;
    private final SearchUser searchUser;

    public UpdateUser(UserRepository userRepository, EncodePassword encodePassword, List<ValidatePassword> validatesPassword, SearchUser searchUser) {
        this.userRepository = userRepository;
        this.encodePassword = encodePassword;
        this.validatesPassword = validatesPassword;
        this.searchUser = searchUser;
    }

    public User execute(UserRequest userRequest, Long id) {
        var user = searchUser.byId(id);

        log.info(VALIDATING_PASSWORD);
        validatesPassword.forEach(validatePassword -> validatePassword.execute(userRequest.password()));

        log.info(MAPPING_TO, USER_REQUEST_NAME_ENTITY, USER_NAME_ENTITY);
        UserMapper.INSTANCE.toUser(userRequest, user, encodePassword);

        log.info(UPDATING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.save(user);
    }
}
