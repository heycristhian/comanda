package br.com.heycristhian.comanda.infrastructure.mapper.mapstruct.impl;

import br.com.heycristhian.comanda.domain.encoder.PasswordEncoder;
import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.infrastructure.database.schema.UserSchema;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.UserResponse;
import br.com.heycristhian.comanda.infrastructure.mapper.UserMapper;
import br.com.heycristhian.comanda.infrastructure.mapper.mapstruct.UserMapStructMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapStructMapperImpl implements UserMapper {

    private static final UserMapStructMapper mapper = UserMapStructMapper.INSTANCE;

    private final PasswordEncoder passwordEncoder;

    public UserMapStructMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return mapper.toUserResponse(user);
    }

    @Override
    public User toUser(UserRequest userRequest) {
        return mapper.toUser(userRequest, this.passwordEncoder);
    }

    @Override
    public UserSchema toUserSchema(User user) {
        return mapper.toUserSchema(user);
    }

    @Override
    public User toUser(UserSchema userSchema) {
        return mapper.toUser(userSchema);
    }

    @Override
    public void toUserReference(User newUser, User currentUser) {
        mapper.toUserReference(newUser, currentUser, this.passwordEncoder);
    }
}
