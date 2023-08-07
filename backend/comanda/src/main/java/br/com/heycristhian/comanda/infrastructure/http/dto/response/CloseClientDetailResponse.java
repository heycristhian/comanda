package br.com.heycristhian.comanda.infrastructure.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CloseClientDetailResponse {
    private String portion;
    private String productName;
    private int quantity;
    private BigDecimal total;
    private Instant instant;
}
