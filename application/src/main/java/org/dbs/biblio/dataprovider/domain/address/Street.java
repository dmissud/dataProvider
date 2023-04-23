package org.dbs.biblio.dataprovider.domain.address;

import java.util.Objects;

public class Street {
    private final String streetName;

    public Street(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Street street = (Street) o;

        return Objects.equals(streetName, street.streetName);
    }

    @Override
    public int hashCode() {
        return streetName != null ? streetName.hashCode() : 0;
    }

    public static class StreetBuilder {
        private String streetName;

        public StreetBuilder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Street build() {
            return new Street(streetName);
        }
    }
}
