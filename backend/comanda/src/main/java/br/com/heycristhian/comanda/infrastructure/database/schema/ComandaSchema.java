package br.com.heycristhian.comanda.infrastructure.database.schema;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.Instant;

import static br.com.heycristhian.comanda.infrastructure.util.SecurityUtil.getLoggedId;

@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class ComandaSchema implements Serializable {

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
        userUpdatedId = getLoggedId();
    }
}
