package br.com.heycristhian.comanda.security;

import br.com.heycristhian.comanda.usecase.user.SearchUser;
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
    private final SearchUser searchUser;

    public SecurityFilter(HandleToken handleToken, SearchUser searchUser) {
        this.handleToken = handleToken;
        this.searchUser = searchUser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = getToken(request);

        if (nonNull(tokenJWT)) {
            var username = handleToken.getSubject(tokenJWT);
            var user = searchUser.byUsername(username);

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
