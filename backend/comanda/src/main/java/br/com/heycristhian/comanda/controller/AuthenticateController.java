package br.com.heycristhian.comanda.controller;

import br.com.heycristhian.comanda.controller.dto.request.LoginRequest;
import br.com.heycristhian.comanda.controller.dto.response.AuthResponse;
import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.security.HandleToken;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    private final AuthenticationManager authenticationManager;
    private final HandleToken handleToken;

    public AuthenticateController(AuthenticationManager authenticationManager, HandleToken handleToken) {
        this.authenticationManager = authenticationManager;
        this.handleToken = handleToken;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var token = handleToken.generate((User) authentication.getPrincipal());
        var authResponse = new AuthResponse(token, "Bearer");

        return ResponseEntity.ok(authResponse);
    }
}
