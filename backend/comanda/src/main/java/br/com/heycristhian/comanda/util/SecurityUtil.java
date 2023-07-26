package br.com.heycristhian.comanda.util;

import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.exception.LoggedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.isNull;

public abstract class SecurityUtil {

    private SecurityUtil() {
    }

    public static Long getLoggedId() {
        var user = getUser();
        var id = user.getId();

        if (isNull(id)) {
            throw new LoggedException("Id do usuário logado encontra-se nulo");
        }

        return id;
    }

    public static String getLoggedUsername() {
        var user = getUser();
        var username = user.getUsername();

        if (isNull(username)) {
            throw new LoggedException("Username do usuário logado encontra-se nulo");
        }
        return username;
    }

    private static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
