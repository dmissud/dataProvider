package org.dbs.biblio.dataprovider.application.port.out;

import org.dbs.biblio.dataprovider.domain.address.Address;

import java.util.Optional;

public interface AddressRepository {
    String ADRESSE_NAME = "Adresse";
    String CITY_NAME = "City";

    long sizeOfAdresse();
    long numberOfCity();

    Optional<Address> load(String nomCommune, int numero, String rep, String streetName);

    void save(Address address);
}
