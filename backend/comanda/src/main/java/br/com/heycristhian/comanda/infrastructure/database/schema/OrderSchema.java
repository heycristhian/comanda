package br.com.heycristhian.comanda.infrastructure.database.schema;

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
public class OrderSchema extends ComandaSchema {

    private ProductSchema productSchema;
    private int quantity;
    private BigDecimal total;
    private BigDecimal totalEach;
    private List<ClientSchema> clientSchemas;
    private TableSchema tableSchema;
}
