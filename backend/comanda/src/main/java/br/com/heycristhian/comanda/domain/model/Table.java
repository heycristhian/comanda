package br.com.heycristhian.comanda.domain.model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Table extends Comanda {

    private int number;
    private List<Client> clients;
    private List<Order> orders;
    private boolean isOpen;

    public void addOrder(Order order) {
        if (isNull(this.orders)) {
            this.orders = new ArrayList<>();
        }

        this.orders.add(order);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}


