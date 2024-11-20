package rami.generic.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import rami.generic.entities.base.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "dummy")
public class DummyEntity extends BaseEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String dummy;

    private Long email;

    private Long composeID1;

    private Long composeID2;
}
