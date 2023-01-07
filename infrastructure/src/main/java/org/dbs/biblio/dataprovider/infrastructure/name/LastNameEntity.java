package org.dbs.biblio.dataprovider.infrastructure.name;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "last_name_entity")
public class LastNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patronyme", nullable = false)
    private String patronyme;

    @Column(name = "count", nullable = false)
    int count;
}