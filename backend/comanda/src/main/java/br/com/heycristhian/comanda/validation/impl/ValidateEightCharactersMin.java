package br.com.heycristhian.comanda.validation.impl;

import br.com.heycristhian.comanda.validation.ValidatePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class ValidateEightCharactersMin extends ValidatePassword {

    @Override
    protected boolean validate(String password) {
        log.info("Validating: ValidateEightCharactersMin");
        return nonNull(password) && password.length() < 8;
    }

    @Override
    protected String getErrorMessage() {
        return "A senha deve ter pelo menos 8 caracteres";
    }
}
