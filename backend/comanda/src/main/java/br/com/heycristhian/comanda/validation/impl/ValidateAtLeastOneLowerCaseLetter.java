package br.com.heycristhian.comanda.validation.impl;

import br.com.heycristhian.comanda.util.RegexPattern;
import br.com.heycristhian.comanda.validation.ValidatePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateAtLeastOneLowerCaseLetter extends ValidatePassword {

    @Override
    protected boolean validate(String password) {
        log.info("Validating: ValidateAtLeastOneLowerCaseLetter");
        return !RegexPattern.containsLowerCaseLetter(password);
    }

    @Override
    protected String getErrorMessage() {
        return "A senha deve ter pelo menos 1 letra minúscula";
    }
}
