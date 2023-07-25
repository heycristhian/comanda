package br.com.heycristhian.comanda.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.Instant;

@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class ComandaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_created_id", nullable = false)
    private Long userCreatedId;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "user_updated_id")
    private Long userUpdatedId;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
        userUpdatedId = 1L;
    }
}
