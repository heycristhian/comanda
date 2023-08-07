package br.com.heycristhian.comanda.domain.encoder;

public interface PasswordEncoder {
    String execute(String password);
}
