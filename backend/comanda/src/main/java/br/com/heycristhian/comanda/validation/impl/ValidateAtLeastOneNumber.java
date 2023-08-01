package br.com.heycristhian.comanda.validation.impl;

import br.com.heycristhian.comanda.util.RegexPattern;
import br.com.heycristhian.comanda.validation.ValidatePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidateAtLeastOneNumber extends ValidatePassword {

    @Override
    protected boolean validate(String password) {
        log.info("Validating: ValidateAtLeastOneNumber");
        return !RegexPattern.containsNumber(password);
    }

    @Override
    protected String getErrorMessage() {
        return "A senha deve ter pelo menos 1 dígito";
    }
}
