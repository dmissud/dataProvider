package org.dbs.biblio.dataprovider.application.service;

import org.dbs.biblio.dataprovider.application.port.out.AddressRepository;
import org.dbs.biblio.dataprovider.application.port.out.TownRepository;
import org.dbs.biblio.dataprovider.domain.address.Address;
import org.dbs.biblio.dataprovider.domain.address.Street;
import org.dbs.biblio.dataprovider.domain.address.Town;
import org.dbs.biblio.dataprovider.exception.BusinessObjectAllReadyExist;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressTownRepositoryStub implements AddressRepository, TownRepository {
    private Address address;
    private Town town;
    public AddressTownRepositoryStub() {
        this.clear();
    }

    @Override
    public long sizeOfAdresse() {
        return 0;
    }

    @Override
    public long numberOfCity() {
        return 0;
    }

    @Override
    public Optional<Address> load(String nomCommune, int number, String rep, String streetName) {
        return Optional.ofNullable(address);
    }

    @Override
    public void save(Address address) {
        if (this.address== null) {
            this.address = address;
        } else {
            throw new BusinessObjectAllReadyExist(Address.class.getSimpleName(), address);
        }
    }

    public void clear() {
        this.address = null;
        this.town = null;
    }

    public boolean haveAddress(String nameTown, Street nameStreet, int number, String rep) {
        return Optional.ofNullable(this.address).filter(address -> address.is(nameTown, nameStreet, number, rep)).isPresent();
    }

    public boolean haveTown(String codePostal, String codeInsee, String nameTown) {
        return Optional.ofNullable(this.town).filter(town -> town.is(nameTown, codePostal, codeInsee)).isPresent();
    }

    @Override
    public Optional<Town> load(String nameTown) {
        return Optional.empty();
    }

    @Override
    public void save(Town town) {
        this.town = town;
    }

    public void reference(Address address) {
        this.address = address;
    }
}
