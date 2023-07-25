package br.com.heycristhian.comanda.mapper;

import br.com.heycristhian.comanda.controller.dto.request.ClientRequest;
import br.com.heycristhian.comanda.controller.dto.response.ClientResponse;
import br.com.heycristhian.comanda.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    @Mapping(target = "userCreatedId", constant = "1L")
    @Mapping(target = "firstName", source = "nome")
    @Mapping(target = "lastName", source = "sobrenome")
    @Mapping(target = "phone", source = "celular")
    Client toClient(ClientRequest clientRequest);

    default void toClient(Client client, ClientRequest clientRequest) {
        client.setFirstName(clientRequest.getNome());
        client.setLastName(clientRequest.getSobrenome());
        client.setPhone(clientRequest.getCelular());
    }

    @Mapping(target = "nome", source = "firstName")
    @Mapping(target = "sobrenome", source = "lastName")
    @Mapping(target = "celular", source = "phone")
    ClientResponse toClientResponse(Client client);
}
