package br.com.heycristhian.comanda.security;

import br.com.heycristhian.comanda.exception.ObjectNotFoundException;
import br.com.heycristhian.comanda.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Authenticate implements UserDetailsService {

    private final UserRepository userRepository;

    public Authenticate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não cadastrado"));
    }
}
