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
import static br.com.heycristhian.comanda.util.MessagePattern.SAVING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.MessagePattern.USER_REQUEST_NAME_ENTITY;
import static br.com.heycristhian.comanda.util.SecurityUtil.getLoggedId;

@Slf4j
@Service
public class SaveUser {

    private final UserRepository userRepository;
    private final List<ValidatePassword> validatesPassword;
    private final EncodePassword encodePassword;

    public SaveUser(UserRepository userRepository, List<ValidatePassword> validatesPassword, EncodePassword encodePassword) {
        this.userRepository = userRepository;
        this.validatesPassword = validatesPassword;
        this.encodePassword = encodePassword;
    }

    public User execute(UserRequest userRequest) {
        log.info("Validating password");
        validatesPassword.forEach(validatePassword -> validatePassword.execute(userRequest.password()));

        log.info(MAPPING_TO, USER_REQUEST_NAME_ENTITY, USER_NAME_ENTITY);
        var user = UserMapper.INSTANCE.toUser(userRequest, encodePassword);
        user.setUserCreatedId(getLoggedId());

        log.info(SAVING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.save(user);
    }
}
