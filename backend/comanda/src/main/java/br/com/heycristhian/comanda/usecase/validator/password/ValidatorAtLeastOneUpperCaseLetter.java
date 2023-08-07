package br.com.heycristhian.comanda.usecase.validator.password;

import br.com.heycristhian.comanda.usecase.util.RegexPattern;
import br.com.heycristhian.comanda.domain.validator.password.ValidatorPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ValidatorAtLeastOneUpperCaseLetter extends ValidatorPassword {

    @Override
    protected boolean validate(String password) {
        log.info("Validating: ValidateAtLeastOneUpperCaseLetter");
        return !RegexPattern.containsUpperCaseLetter(password);
    }

    @Override
    protected String getErrorMessage() {
        return "A senha deve ter pelo menos 1 letra maiúscula";
    }
}
