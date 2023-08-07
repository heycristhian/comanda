package br.com.heycristhian.comanda.infrastructure.database.schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
@Data
@SuperBuilder
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientSchema extends ComandaSchema {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone", unique = true)
    private String phone;
}
