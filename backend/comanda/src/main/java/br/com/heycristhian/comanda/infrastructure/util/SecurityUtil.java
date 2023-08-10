package br.com.heycristhian.comanda.infrastructure.util;

import br.com.heycristhian.comanda.domain.exception.LoggedException;
import br.com.heycristhian.comanda.infrastructure.database.schema.UserSchema;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.isNull;

public interface SecurityUtil {
    static Long getLoggedId() {
        var user = getUser();
        var id = user.getId();

        if (isNull(id)) {
            throw new LoggedException("Id do usuário logado encontra-se nulo");
        }

        return id;
    }

    private static UserSchema getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserSchema) authentication.getPrincipal();
    }
}
