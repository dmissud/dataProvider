package org.dbs.biblio.dataprovider.application.port.out;

import org.dbs.biblio.dataprovider.domain.IsbnBook;

import java.util.Set;

public interface IsbnStockRepository {
    String ISBN_STOCK = "IsbnStock";

    long sizeOfStock();

    Set<IsbnBook> findIsbnsByPage(int pos, int sizeOfPage);

    IsbnBook findIsbnsByPosition(int position);
}
