package br.com.heycristhian.comanda.usecase.user;

import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.domain.repository.UserRepository;
import br.com.heycristhian.comanda.domain.validator.password.ValidatorPassword;
import br.com.heycristhian.comanda.infrastructure.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.UPDATING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.USER_NAME_ENTITY;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.VALIDATING_PASSWORD;

@Slf4j
@Service
public class UpdateUser {

    private final UserRepository userRepository;
    private final List<ValidatorPassword> validatesPassword;
    private final SearchUser searchUser;
    private final UserMapper userMapper;

    public UpdateUser(UserRepository userRepository, List<ValidatorPassword> validatesPassword, SearchUser searchUser, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.validatesPassword = validatesPassword;
        this.searchUser = searchUser;
        this.userMapper = userMapper;
    }


    public User execute(User newUser, Long id) {
        log.info(VALIDATING_PASSWORD);
        validatesPassword.forEach(validatePassword -> validatePassword.execute(newUser.getPassword()));

        var currentUser = searchUser.byId(id);
        this.userMapper.toUserReference(newUser, currentUser);

        log.info(UPDATING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.save(currentUser);
    }
}
