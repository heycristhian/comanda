package br.com.heycristhian.comanda.mapper;

import br.com.heycristhian.comanda.mother.ClientMother;
import br.com.heycristhian.comanda.mother.ClientRequestMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientMapperTest {

    private ClientMapper fixture;

    @BeforeEach
    void setup() {
        this.fixture = ClientMapper.INSTANCE;
    }

    @Test
    void mustMapFromClientRequestToClient() {
        //given:
        var clientRequest = ClientRequestMother.getClientRequest();

        //when:
        var client = fixture.toClient(clientRequest);

        //then:
        assertEquals(clientRequest.getNome(), client.getFirstName());
        assertEquals(clientRequest.getSobrenome(), client.getLastName());
        assertEquals(clientRequest.getCelular(), client.getPhone());
    }

    @Test
    void mustMapFromClientToClientResponse() {
        //given:
        var client = ClientMother.getClient();

        //when:
        var clientResponse = fixture.toClientResponse(client);

        //then:
        assertEquals(client.getId(), clientResponse.getId());
        assertEquals(client.getFirstName(), clientResponse.getNome());
        assertEquals(client.getLastName(), clientResponse.getSobrenome());
        assertEquals(client.getPhone(), clientResponse.getCelular());
    }
}