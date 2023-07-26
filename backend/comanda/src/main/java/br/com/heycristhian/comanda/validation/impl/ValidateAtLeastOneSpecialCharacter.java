package br.com.heycristhian.comanda.validation.impl;

import br.com.heycristhian.comanda.exception.PasswordException;
import br.com.heycristhian.comanda.util.RegexPattern;
import br.com.heycristhian.comanda.validation.ValidatePassword;
import org.springframework.stereotype.Component;

@Component
public class ValidateAtLeastOneSpecialCharacter implements ValidatePassword {

    @Override
    public void execute(String password) {
        if (!RegexPattern.containsSpecialCharacter(password)) {
            throw new PasswordException("A senha deve ter pelo menos 1 caracter especial");
        }
    }
}
