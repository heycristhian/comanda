package br.com.heycristhian.comanda.infrastructure.mapper;

import br.com.heycristhian.comanda.domain.model.User;
import br.com.heycristhian.comanda.infrastructure.database.schema.UserSchema;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.UserRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.UserResponse;

public interface UserMapper {
    UserResponse toUserResponse(User user);

    User toUser(UserRequest userRequest);

    UserSchema toUserSchema(User user);

    User toUser(UserSchema userSchema);

    void toUserReference(User newUser, User currentUser);
}
