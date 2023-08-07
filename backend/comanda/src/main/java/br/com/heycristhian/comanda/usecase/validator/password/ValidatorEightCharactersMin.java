package br.com.heycristhian.comanda.usecase.validator.password;

import br.com.heycristhian.comanda.domain.validator.password.ValidatorPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class ValidatorEightCharactersMin extends ValidatorPassword {

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
