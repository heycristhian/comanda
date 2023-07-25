package br.com.heycristhian.comanda.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}
