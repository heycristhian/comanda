package br.com.heycristhian.comanda.usecase.user;

import br.com.heycristhian.comanda.domain.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.heycristhian.comanda.usecase.util.MessagePattern.SEARCHING_OBJECT_DATABASE;
import static br.com.heycristhian.comanda.usecase.util.MessagePattern.USER_NAME_ENTITY;

@Slf4j
@Service
public class SearchUser {

    private final UserRepository userRepository;

    public SearchUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> all() {
        log.info(SEARCHING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.findAll();
    }

    public User byId(Long id) {
        log.info(SEARCHING_OBJECT_DATABASE, USER_NAME_ENTITY);
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }
}
