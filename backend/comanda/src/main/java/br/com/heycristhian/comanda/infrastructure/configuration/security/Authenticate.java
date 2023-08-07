package br.com.heycristhian.comanda.infrastructure.configuration.security;

import br.com.heycristhian.comanda.domain.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.infrastructure.database.persistence.springdata.UserJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Authenticate implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    public Authenticate(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não cadastrado"));
    }
}
