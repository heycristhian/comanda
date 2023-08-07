package br.com.heycristhian.comanda.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Order extends Comanda {

    private Product product;
    private int quantity;
    private BigDecimal total;
    private BigDecimal totalEach;
    private List<Client> clients;
    private Table table;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalEach() {
        return totalEach;
    }

    public void setTotalEach(BigDecimal totalEach) {
        this.totalEach = totalEach;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
