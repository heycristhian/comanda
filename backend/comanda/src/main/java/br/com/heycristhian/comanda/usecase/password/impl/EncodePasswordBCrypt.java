package br.com.heycristhian.comanda.usecase.password.impl;

import br.com.heycristhian.comanda.usecase.password.EncodePassword;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncodePasswordBCrypt implements EncodePassword {

    @Override
    public String execute(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
