package org.dbs.biblio.dataprovider.application.service;

import org.dbs.biblio.dataprovider.application.common.LogExecutionTime;
import org.dbs.biblio.dataprovider.application.port.in.query.DataDescription;
import org.dbs.biblio.dataprovider.application.port.in.query.DataProviderDescriptionQuery;
import org.dbs.biblio.dataprovider.application.port.out.IsbnStockRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DataProviderQueryImpl implements DataProviderDescriptionQuery {
    private final IsbnStockRepository isbnStockRepository;

    public DataProviderQueryImpl(IsbnStockRepository isbnStockRepository) {
        this.isbnStockRepository = isbnStockRepository;
    }

    @Override
    @LogExecutionTime(vApi = "DataProvider", vType = "Query", vMethod = "descriptionOfDataProvide")
    public Set<DataDescription> descriptionOfDataProvide() {
        Set<DataDescription> dataDescriptions = new HashSet<>();
        dataDescriptions.add(DataDescription.builder()
                .dataName(IsbnStockRepository.ISBN_STOCK)
                .sizeOf(this.isbnStockRepository.sizeOfStock())
                .build());
        return dataDescriptions;
    }

}
