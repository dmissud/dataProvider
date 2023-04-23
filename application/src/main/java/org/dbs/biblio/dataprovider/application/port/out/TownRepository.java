package org.dbs.biblio.dataprovider.application.port.out;

import org.dbs.biblio.dataprovider.domain.address.Town;

import java.util.Optional;

public interface TownRepository {
    Optional<Town> load(String nameTown);

    void save(Town town);
}
