package br.com.heycristhian.comanda.domain;

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
public class Table extends ComandaEntity {

    private int number;
    private List<Client> clients;
    private List<Order> orders;
    private boolean isOpen = true;

    public void addOrder(Order order) {
        if (isNull(this.orders)) {
            this.orders = new ArrayList<>();
        }

        this.orders.add(order);
    }
}


