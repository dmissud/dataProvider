package org.dbs.biblio.dataprovider.application.port.out;

public interface LastNameRepository {
    String LAST_NAME = "LastName";

    long sizeOfLastName();

    String findLastNameByPosition(int position);

}
