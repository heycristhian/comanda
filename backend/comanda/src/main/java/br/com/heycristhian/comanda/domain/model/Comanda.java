package br.com.heycristhian.comanda.domain.model;

import java.time.Instant;

public abstract class Comanda {
    private Long id;
    private Long userCreatedId;
    private Instant createdAt;
    private Long userUpdatedId;
    private Instant updatedAt;

    protected Comanda() {
    }

    protected Comanda(Long userCreatedId, Instant createdAt, Long userUpdatedId, Instant updatedAt) {
        this.userCreatedId = userCreatedId;
        this.createdAt = createdAt;
        this.userUpdatedId = userUpdatedId;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(Long userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserUpdatedId() {
        return userUpdatedId;
    }

    public void setUserUpdatedId(Long userUpdatedId) {
        this.userUpdatedId = userUpdatedId;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
