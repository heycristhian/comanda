package br.com.heycristhian.comanda.usecase.validator.password;

import br.com.heycristhian.comanda.usecase.util.RegexPattern;
import br.com.heycristhian.comanda.domain.validator.password.ValidatorPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidatorAtLeastOneSpecialCharacter extends ValidatorPassword {

    @Override
    protected boolean validate(String password) {
        log.info("Validating: ValidateAtLeastOneSpecialCharacter");
        return !RegexPattern.containsSpecialCharacter(password);
    }

    @Override
    protected String getErrorMessage() {
        return "A senha deve ter pelo menos 1 caracter especial";
    }
}
