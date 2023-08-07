package br.com.heycristhian.comanda.infrastructure.encoder.password;

import br.com.heycristhian.comanda.domain.encoder.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderBCrypt implements PasswordEncoder {

    @Override
    public String execute(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
