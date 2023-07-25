package br.com.heycristhian.comanda.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
@Data
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class Client extends ComandaEntity {

    public Client() {
        super();
    }

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone", unique = true)
    private String phone;
}
