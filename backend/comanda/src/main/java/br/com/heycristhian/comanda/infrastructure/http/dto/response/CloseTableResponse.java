package br.com.heycristhian.comanda.infrastructure.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CloseTableResponse {

    private UUID tableId;
    private List<CloseClientResponse> closeClientsResponse;

}
