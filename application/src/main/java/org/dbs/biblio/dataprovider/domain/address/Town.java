package org.dbs.biblio.dataprovider.domain.address;

import org.dbs.biblio.dataprovider.domain.exception.BusinessException;

import java.util.HashSet;
import java.util.Set;

public class Town {
    private final String cityName;
    private final String codeInsee;
    private final String codePostal;

    private final Set<Street> streets = new HashSet<>();

    public Town(String cityName, String codeInsee, String codePostal) {
        this.cityName = cityName;
        this.codeInsee = codeInsee;
        this.codePostal = codePostal;
    }

    public void addStreet(Street nomVoie) {
        this.streets.add(nomVoie);
    }

    public boolean is(String nameTown, String codePostal, String codeInsee) {
        return this.cityName.equals(nameTown)
                && this.codeInsee.equals(codeInsee)
                && this.codePostal.equals(codePostal);
    }

    public static class TownBuilder {
        private String cityName;
        private String codeInsee;
        private String codePostal;

        public TownBuilder setCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public TownBuilder setCodeInsee(String codeInsee) {
            this.codeInsee = codeInsee;
            return this;
        }

        public TownBuilder setCodePostal(String codePostal) {
            this.codePostal = codePostal;
            return this;
        }

        public Town build() {
            if (this.cityName != null && this.codeInsee != null && this.codePostal != null) {
                return new Town(cityName, codeInsee, codePostal);
            } else {
                throw new BusinessException("Invalid parameter for building a town");
            }
        }
    }
}
