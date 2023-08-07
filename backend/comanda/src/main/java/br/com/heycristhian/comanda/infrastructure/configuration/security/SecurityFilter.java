package br.com.heycristhian.comanda.infrastructure.configuration.security;

import br.com.heycristhian.comanda.domain.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.infrastructure.database.persistence.springdata.UserJpaRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Objects.nonNull;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final HandleToken handleToken;
    private final UserJpaRepository userJpaRepository;

    public SecurityFilter(HandleToken handleToken, UserJpaRepository userJpaRepository) {
        this.handleToken = handleToken;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = getToken(request);

        if (nonNull(tokenJWT)) {
            var username = handleToken.getSubject(tokenJWT);
            var user = userJpaRepository.findByUsername(username)
                    .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado para o username: " + username));

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (nonNull(authorizationHeader)) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
