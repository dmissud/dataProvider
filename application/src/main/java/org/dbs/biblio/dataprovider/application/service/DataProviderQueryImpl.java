package org.dbs.biblio.dataprovider.application.service;

import org.dbs.biblio.dataprovider.application.common.LogExecutionTime;
import org.dbs.biblio.dataprovider.application.port.in.query.DataDescription;
import org.dbs.biblio.dataprovider.application.port.in.query.DataProviderDescriptionQuery;
import org.dbs.biblio.dataprovider.application.port.out.FirstNameRepository;
import org.dbs.biblio.dataprovider.application.port.out.IsbnStockRepository;
import org.dbs.biblio.dataprovider.application.port.out.LastNameRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DataProviderQueryImpl implements DataProviderDescriptionQuery {
    private final IsbnStockRepository isbnStockRepository;
    private final FirstNameRepository firstNameRepository;
    private final LastNameRepository lastNameRepository;

    public DataProviderQueryImpl(IsbnStockRepository isbnStockRepository,
                                 FirstNameRepository firstNameRepository,
                                 LastNameRepository lastNameRepository) {
        this.firstNameRepository = firstNameRepository;
        this.isbnStockRepository = isbnStockRepository;
        this.lastNameRepository = lastNameRepository;
    }

    @Override
    @LogExecutionTime(vApi = "DataProvider", vType = "Query", vMethod = "descriptionOfDataProvide")
    public Set<DataDescription> descriptionOfDataProvide() {
        Set<DataDescription> dataDescriptions = new HashSet<>();
        enrichWithIsbn(dataDescriptions);
        enrichWithFirstName(dataDescriptions);
        enrichWithLastName(dataDescriptions);
        return dataDescriptions;
    }

    private void enrichWithFirstName(Set<DataDescription> dataDescriptions) {
        dataDescriptions.add(DataDescription.builder()
                .dataName(FirstNameRepository.FIRST_NAME)
                .sizeOf(this.firstNameRepository.sizeOfFirstName())
                .build());
    }

    private void enrichWithIsbn(Set<DataDescription> dataDescriptions) {
        dataDescriptions.add(DataDescription.builder()
                .dataName(IsbnStockRepository.ISBN_STOCK)
                .sizeOf(this.isbnStockRepository.sizeOfStock())
                .build());
    }

    private void enrichWithLastName(Set<DataDescription> dataDescriptions) {
        dataDescriptions.add(DataDescription.builder()
                .dataName(LastNameRepository.LAST_NAME)
                .sizeOf(this.lastNameRepository.sizeOfLastName())
                .build());
    }
}
