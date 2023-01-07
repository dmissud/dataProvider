package org.dbs.biblio.dataprovider.infrastructure.name;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LastNameEntityRepository extends JpaRepository<LastNameEntity, UUID> {
}