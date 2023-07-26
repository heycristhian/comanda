package br.com.heycristhian.comanda.mapper;

import br.com.heycristhian.comanda.controller.dto.request.UserRequest;
import br.com.heycristhian.comanda.controller.dto.response.UserResponse;
import br.com.heycristhian.comanda.domain.User;
import br.com.heycristhian.comanda.usecase.password.EncodePassword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toUserResponse(User user);

    @Mapping(target = "password", expression = "java(encodePassword.execute(userRequest.password()))")
    User toUser(UserRequest userRequest, EncodePassword encodePassword);
}
