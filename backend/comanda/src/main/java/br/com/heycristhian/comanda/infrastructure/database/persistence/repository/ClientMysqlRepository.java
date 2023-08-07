package br.com.heycristhian.comanda.infrastructure.database.persistence.repository;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.domain.repository.ClientRepository;
import br.com.heycristhian.comanda.infrastructure.database.persistence.springdata.ClientJpaRepository;
import br.com.heycristhian.comanda.infrastructure.mapper.ClientMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ClientMysqlRepository implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;

    public ClientMysqlRepository(ClientJpaRepository clientJpaRepository, ClientMapper clientMapper) {
        this.clientJpaRepository = clientJpaRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public void deleteById(Long id) {
        clientJpaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Client save(Client client) {
        var clientSchema = clientJpaRepository.save(
                this.clientMapper.toClientSchema(client)
        );

        return this.clientMapper.toClientReference(clientSchema);
    }

    @Override
    public List<Client> findAll() {
        return clientJpaRepository.findAll()
                .stream()
                .map(clientMapper::toClientReference)
                .toList();
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientJpaRepository.findById(id)
                .map(clientMapper::toClientReference);
    }
}
