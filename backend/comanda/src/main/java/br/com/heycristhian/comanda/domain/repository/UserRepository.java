package br.com.heycristhian.comanda.domain.repository;

import br.com.heycristhian.comanda.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void deleteById(Long id);

    User save(User user);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    Optional<User> findById(Long id);
}
