package br.com.heycristhian.comanda.usecase.user;

import br.com.heycristhian.comanda.domain.repository.UserRepository;
import br.com.heycristhian.comanda.usecase.util.MessagePattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeleteUser {
    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(Long id) {
        log.info(MessagePattern.DELETING_OBJECT_DATABASE, MessagePattern.USER_NAME_ENTITY);
        userRepository.deleteById(id);
    }
}
