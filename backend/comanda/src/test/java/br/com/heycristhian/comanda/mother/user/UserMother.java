package br.com.heycristhian.comanda.mother.user;

import br.com.heycristhian.comanda.controller.dto.request.UserRequest;
import br.com.heycristhian.comanda.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

public abstract class UserMother {
    public static User getUser(UserRequest userRequest) {
        return User.builder()
                .id(1L)
                .userCreatedId(1L)
                .createdAt(Instant.now())
                .userUpdatedId(1L)
                .updatedAt(Instant.now())
                .username(userRequest.username())
                .fullname(userRequest.fullname())
                .password(new BCryptPasswordEncoder().encode(userRequest.password()))
                .build();
    }
}
