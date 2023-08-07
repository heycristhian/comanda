package br.com.heycristhian.comanda.mother.user;

import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

public abstract class UserMother {
    public static User getUser(UserRequest userRequest) {
        User user = new User();
        user.setId(1L);
        user.setUserCreatedId(1L);
        user.setCreatedAt(Instant.now());
        user.setUserUpdatedId(1L);
        user.setUpdatedAt(Instant.now());
        user.setUsername(userRequest.usuario());
        user.setFullName(userRequest.nomeCompleto());
        user.setPassword(new BCryptPasswordEncoder().encode(userRequest.senha()));

        return user;
    }
}
