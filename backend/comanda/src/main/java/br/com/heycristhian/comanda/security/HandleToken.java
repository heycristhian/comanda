package br.com.heycristhian.comanda.security;

import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.exception.TokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class HandleToken {


    @Value(value = "${api.security.token.secret}")
    private String secret;

    @Value(value = "${api.name}")
    private String apiName;

    public String generate(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API " + apiName)
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getId())
                    .withExpiresAt(experationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new TokenException("Erro ao gerar Token JWT");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("API " + apiName)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new TokenException("Token JWT inválido ou expirado");
        }
    }

    private Instant experationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
