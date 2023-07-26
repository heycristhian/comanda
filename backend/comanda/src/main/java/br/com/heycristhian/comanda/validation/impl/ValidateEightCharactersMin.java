package br.com.heycristhian.comanda.validation.impl;

import br.com.heycristhian.comanda.exception.PasswordException;
import br.com.heycristhian.comanda.validation.ValidatePassword;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class ValidateEightCharactersMin implements ValidatePassword {

    @Override
    public void execute(String password) {
        if (nonNull(password) && password.length() < 8) {
            throw new PasswordException("A senha deve ter pelo menos 8 caracteres");
        }
    }
}
