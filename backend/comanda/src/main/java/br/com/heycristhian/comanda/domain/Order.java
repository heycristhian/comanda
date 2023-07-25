package br.com.heycristhian.comanda.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Order extends ComandaEntity {

    private Product product;
    private int quantity;
    private BigDecimal total;
    private BigDecimal totalEach;
    private List<Client> clients;
    private Table table;
}
