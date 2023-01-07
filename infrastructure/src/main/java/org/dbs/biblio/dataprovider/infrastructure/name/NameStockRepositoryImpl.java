package org.dbs.biblio.dataprovider.infrastructure.name;

import org.dbs.biblio.dataprovider.application.port.out.FirstNameRepository;
import org.dbs.biblio.dataprovider.application.port.out.LastNameRepository;
import org.springframework.stereotype.Repository;

@Repository
public class NameStockRepositoryImpl implements FirstNameRepository, LastNameRepository {

    private final FirstNameEntityRepository firstNameEntityRepository;
    private final LastNameEntityRepository lastNameEntityRepository;

    public NameStockRepositoryImpl(FirstNameEntityRepository firstNameEntityRepository, LastNameEntityRepository lastNameEntityRepository) {
        this.firstNameEntityRepository = firstNameEntityRepository;
        this.lastNameEntityRepository = lastNameEntityRepository;
    }

    @Override
    public long sizeOfFirstName() {
        return this.firstNameEntityRepository.count();
    }

    @Override
    public String findFirstNameByPosition(int position) {
        return null;
    }

    @Override
    public long sizeOfLastName() {
        return this.lastNameEntityRepository.count();
    }

    @Override
    public String findLastNameByPosition(int position) {
        return null;
    }
}
