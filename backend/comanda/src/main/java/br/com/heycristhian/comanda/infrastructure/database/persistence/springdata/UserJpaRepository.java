package br.com.heycristhian.comanda.infrastructure.database.persistence.springdata;

import br.com.heycristhian.comanda.infrastructure.database.schema.UserSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserSchema, Long> {
    Optional<UserSchema> findByUsername(String username);
}
