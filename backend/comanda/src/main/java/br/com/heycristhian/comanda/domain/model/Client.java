package br.com.heycristhian.comanda.domain.model;

import java.time.Instant;

public class Client extends Comanda {
    private String firstName;
    private String lastName;
    private String phone;

    public Client() {
    }

    public Client(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Client(Long userCreatedId, Instant createdAt, Long userUpdatedId, Instant updatedAt, String firstName, String lastName, String phone) {
        super(userCreatedId, createdAt, userUpdatedId, updatedAt);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
