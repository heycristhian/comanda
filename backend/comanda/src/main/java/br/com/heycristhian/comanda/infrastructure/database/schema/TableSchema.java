package br.com.heycristhian.comanda.infrastructure.database.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class TableSchema extends ComandaSchema {

    private int number;
    private List<ClientSchema> clientSchemas;
    private List<OrderSchema> orderSchemas;
    private boolean isOpen = true;

    public void addOrder(OrderSchema orderSchema) {
        if (isNull(this.orderSchemas)) {
            this.orderSchemas = new ArrayList<>();
        }

        this.orderSchemas.add(orderSchema);
    }
}


