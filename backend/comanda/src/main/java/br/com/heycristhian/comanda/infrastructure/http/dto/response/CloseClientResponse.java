package br.com.heycristhian.comanda.infrastructure.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CloseClientResponse {
    private String name;
    private List<CloseClientDetailResponse> closeClientDetailsResponse;
    private BigDecimal total;
}
