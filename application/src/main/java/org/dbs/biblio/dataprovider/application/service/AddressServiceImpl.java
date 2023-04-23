package org.dbs.biblio.dataprovider.application.service;

import org.dbs.biblio.dataprovider.application.port.in.cmd.ReferenceAddress;
import org.dbs.biblio.dataprovider.application.port.out.AddressRepository;
import org.dbs.biblio.dataprovider.application.port.out.TownRepository;
import org.dbs.biblio.dataprovider.domain.address.Address;
import org.dbs.biblio.dataprovider.domain.address.Street;
import org.dbs.biblio.dataprovider.domain.address.Town;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements ReferenceAddress {
    private final AddressRepository addressRepository;
    private final TownRepository townRepository;

    public AddressServiceImpl(AddressRepository addressRepository, TownRepository townRepository) {
        this.addressRepository = addressRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void referenceAddress(ReferenceAddressCmd referenceAddressCmd) {
        Address address = retreiveOrBuildAddress(referenceAddressCmd.getCreateCityCmd().getCityName(),
                referenceAddressCmd.getNumber(),
                referenceAddressCmd.getRep(),
                referenceAddressCmd.getStreetName());
        enrichAddressWithPosition(address, referenceAddressCmd.getCreatePositionCmd());

        Town town = townRepository.load(referenceAddressCmd.getCreateCityCmd().getCityName())
                .orElse(new Town.TownBuilder()
                        .setCityName(referenceAddressCmd.getCreateCityCmd().getCityName())
                        .setCodeInsee(referenceAddressCmd.getCreateCityCmd().getCodeInsee())
                        .setCodePostal(referenceAddressCmd.getCreateCityCmd().getCodePostal())
                        .build());
        town.addStreet(new Street.StreetBuilder().setStreetName(referenceAddressCmd.getStreetName()).build());
        addressRepository.save(address);
        townRepository.save(town);

    }

    private Address retreiveOrBuildAddress(String nomCommune, int numero, String rep, String streetName) {
        return addressRepository.load(nomCommune, numero, rep, streetName)
                .orElse(new Address.AddressBuilder()
                        .setNomCommune(nomCommune)
                        .setStreet(new Street.StreetBuilder().setStreetName(streetName).build())
                        .setNumero(numero)
                        .setRep(rep)
                        .build());
    }

    private void enrichAddressWithPosition(Address address, ReferenceAddress.CreatePositionCmd createPositionCmd) {
        if (createPositionCmd != null) {
            address.updatePosition(createPositionCmd.getX(),
                    createPositionCmd.getY(),
                    createPositionCmd.getLat(),
                    createPositionCmd.getLon(),
                    createPositionCmd.getTypePosition());
        }
    }
}
