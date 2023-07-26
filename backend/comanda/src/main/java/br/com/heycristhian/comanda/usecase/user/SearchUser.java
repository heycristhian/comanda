package br.com.heycristhian.comanda.usecase.user;

import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.heycristhian.comanda.util.MessageUtil.SEARCHING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.util.MessageUtil.USER_NAME_ENTITY;

@Slf4j
@Service
public class SearchUser {

    private final UserRepository userRepository;

    public SearchUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User byUsername(String username) {
        log.info(SEARCHING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("Username não encontrado"));
    }
}
