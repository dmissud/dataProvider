package org.dbs.biblio.dataprovider.application.port.out;

public interface FirstNameRepository {
    String FIRST_NAME = "FirstName";

    long sizeOfFirstName();

    String findFirstNameByPosition(int position);

}
