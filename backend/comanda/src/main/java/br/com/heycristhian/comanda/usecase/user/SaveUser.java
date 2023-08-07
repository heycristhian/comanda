package br.com.heycristhian.comanda.usecase.user;

import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.domain.repository.UserRepository;
import br.com.heycristhian.comanda.domain.validator.password.ValidatorPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.SAVING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.USER_NAME_ENTITY;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.VALIDATING_PASSWORD;
import static br.com.heycristhian.comanda.infrastructure.util.SecurityUtil.getLoggedId;

@Slf4j
@Service
public class SaveUser {

    private final UserRepository userRepository;
    private final List<ValidatorPassword> validatesPassword;


    public SaveUser(UserRepository userRepository, List<ValidatorPassword> validatesPassword) {
        this.userRepository = userRepository;
        this.validatesPassword = validatesPassword;
    }

    public User execute(User user) {
        log.info(VALIDATING_PASSWORD);
        validatesPassword.forEach(validatePassword -> validatePassword.execute(user.getPassword()));

        user.setUserCreatedId(getLoggedId());

        log.info(SAVING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.save(user);
    }
}
