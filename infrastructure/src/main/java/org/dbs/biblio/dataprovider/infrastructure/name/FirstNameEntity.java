package org.dbs.biblio.dataprovider.infrastructure.name;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "first_name_entity")
public class FirstNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "sum", nullable = false)
    int sum;

}