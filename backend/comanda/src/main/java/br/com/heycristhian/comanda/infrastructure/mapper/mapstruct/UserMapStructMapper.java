package br.com.heycristhian.comanda.infrastructure.mapper.mapstruct;

import br.com.heycristhian.comanda.domain.encoder.PasswordEncoder;
import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.infrastructure.database.schema.UserSchema;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapStructMapper {

    UserMapStructMapper INSTANCE = Mappers.getMapper(UserMapStructMapper.class);

    UserResponse toUserResponse(User user);

    @Mapping(target = "username", source = "userRequest.usuario")
    @Mapping(target = "fullName", source = "userRequest.nomeCompleto")
    @Mapping(target = "password", expression = "java(passwordEncoder.execute(userRequest.senha()))")
    User toUser(UserRequest userRequest, PasswordEncoder passwordEncoder);

    UserSchema toUserSchema(User user);

    User toUser(UserSchema userSchema);

    default void toUserReference(User newUser, User currentUser, PasswordEncoder passwordEncoder) {
        currentUser.setUsername(newUser.getUsername());
        currentUser.setFullName(newUser.getFullName());
        currentUser.setPassword(passwordEncoder.execute(newUser.getPassword()));
    }
}
