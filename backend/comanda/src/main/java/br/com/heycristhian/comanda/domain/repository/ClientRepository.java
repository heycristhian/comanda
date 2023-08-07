package br.com.heycristhian.comanda.domain.repository;

import br.com.heycristhian.comanda.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    void deleteById(Long id);

    Client save(Client client);

    List<Client> findAll();

    Optional<Client> findById(Long id);
}
