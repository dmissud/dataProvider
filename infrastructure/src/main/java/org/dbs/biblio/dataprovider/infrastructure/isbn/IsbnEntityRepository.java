package org.dbs.biblio.dataprovider.infrastructure.isbn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IsbnEntityRepository extends JpaRepository<IsbnEntity, String> {
}
