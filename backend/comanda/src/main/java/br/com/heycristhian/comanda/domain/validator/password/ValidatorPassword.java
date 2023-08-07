package br.com.heycristhian.comanda.domain.validator.password;

import br.com.heycristhian.comanda.domain.exception.PasswordException;

public abstract class ValidatorPassword {

    protected abstract boolean validate(String password);

    protected abstract String getErrorMessage();

    //Design pattern: TEMPLATE METHOD
    public void execute(String password) {
        if (validate(password)) {
            throw new PasswordException(getErrorMessage());
        }
    }
}
