package br.com.heycristhian.comanda.infrastructure.mapper.mapstruct;

import br.com.heycristhian.comanda.domain.model.Client;
import br.com.heycristhian.comanda.infrastructure.database.schema.ClientSchema;
import br.com.heycristhian.comanda.infrastructure.http.dto.request.ClientRequest;
import br.com.heycristhian.comanda.infrastructure.http.dto.response.ClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapStructMapper {

    ClientMapStructMapper INSTANCE = Mappers.getMapper(ClientMapStructMapper.class);

    @Mapping(target = "firstName", source = "nome")
    @Mapping(target = "lastName", source = "sobrenome")
    @Mapping(target = "phone", source = "celular")
    Client toClient(ClientRequest clientRequest);

    @Mapping(target = "nome", source = "firstName")
    @Mapping(target = "sobrenome", source = "lastName")
    @Mapping(target = "celular", source = "phone")
    ClientResponse toClientResponse(Client client);

    ClientSchema toClientSchema(Client client);

    Client toClient(ClientSchema clientSchema);

    default void toClientReference(Client newClient, Client currentClient) {
        currentClient.setFirstName(newClient.getFirstName());
        currentClient.setLastName(newClient.getLastName());
        currentClient.setPhone(newClient.getPhone());
    }
}
