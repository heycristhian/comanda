package br.com.heycristhian.comanda.infrastructure.database.persistence.springdata;

import br.com.heycristhian.comanda.infrastructure.database.schema.ClientSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<ClientSchema, Long> {
}
