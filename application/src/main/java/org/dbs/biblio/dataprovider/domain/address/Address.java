package org.dbs.biblio.dataprovider.domain.address;


public class Address {
    private final String cityName;
    private final Street street;
    private final int number;
    private final String rep;
    private AddressPosition addressPosition;
    
    private Address(String cityName, Street street, int number, String rep) {
        this.cityName = cityName;
        this.street = street;
        this.number = number;
        this.rep = rep;
        this.addressPosition = null;
    }

    public boolean is(String city, Street street, int numero, String rep) {

        return this.cityName.equals(city)
                && this.street.equals(street)
                && this.number == numero
                && (this.rep == null || this.rep.equals(rep));
    }

    public boolean isAt(AddressPosition position) {
        return this.addressPosition.equals(position);
    }

    public void updatePosition(float x, float y, float lat, float lon, String typePosition) {
        this.addressPosition = new AddressPosition.AddressPositionBuilder()
                .setX(x)
                .setY(y)
                .setLat(lat)
                .setLon(lon)
                .setTypePosition(typePosition)
                .build();
    }

    public static class AddressBuilder {
        private String nomCommune;
        private Street street;
        private int numero;
        private String rep;

        public AddressBuilder setNomCommune(String nomCommune) {
            this.nomCommune = nomCommune;
            return this;
        }

        public AddressBuilder setStreet(Street street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setNumero(int numero) {
            this.numero = numero;
            return this;
        }

        public AddressBuilder setRep(String rep) {
            this.rep = rep;
            return this;
        }

        public Address build() {
            return new Address(nomCommune, street, numero, rep);
        }
    }
}
