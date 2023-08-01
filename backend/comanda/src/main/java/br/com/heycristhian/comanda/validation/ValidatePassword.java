package br.com.heycristhian.comanda.validation;

import br.com.heycristhian.comanda.exception.PasswordException;

public abstract class ValidatePassword {

    protected abstract boolean validate(String password);

    protected abstract String getErrorMessage();

    //Design pattern: TEMPLATE METHOD
    public void execute(String password) {
        if (validate(password)) {
            throw new PasswordException(getErrorMessage());
        }
    }
}
