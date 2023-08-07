package br.com.heycristhian.comanda.infrastructure.database.persistence.repository;

import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.domain.repository.UserRepository;
import br.com.heycristhian.comanda.infrastructure.database.persistence.springdata.UserJpaRepository;
import br.com.heycristhian.comanda.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class UserMysqlRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    public UserMysqlRepository(UserJpaRepository userJpaRepository, UserMapper userMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        var userSchema = userJpaRepository.save(
                this.userMapper.toUserSchema(user)
        );

        return this.userMapper.toUser(userSchema);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username)
                .map(this.userMapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(this.userMapper::toUser)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(this.userMapper::toUser);
    }
}
