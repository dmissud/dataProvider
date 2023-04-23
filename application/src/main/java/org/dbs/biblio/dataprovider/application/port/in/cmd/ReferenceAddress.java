package org.dbs.biblio.dataprovider.application.port.in.cmd;

import lombok.Getter;
import org.dbs.biblio.dataprovider.application.common.SelfValidating;

@FunctionalInterface
public interface ReferenceAddress {
    void referenceAddress(ReferenceAddressCmd referenceAddressCmd);

    @Getter
    class CreatePositionCmd extends SelfValidating<CreatePositionCmd> {
        private final float x;
        private final float y;
        private final float lon;
        private final float lat;
        private final String typePosition;

        public CreatePositionCmd(float x, float y, float lon, float lat, String typePosition) {
            this.x = x;
            this.y = y;
            this.lon = lon;
            this.lat = lat;
            this.typePosition = typePosition;
        }
    }

    @Getter
    class CreateCityCmd extends SelfValidating<CreateCityCmd> {
        private final String codePostal;
        private final String codeInsee;
        private final String cityName;

        public CreateCityCmd(String codePostal, String codeInsee, String cityName) {
            this.codePostal = codePostal;
            this.codeInsee = codeInsee;
            this.cityName = cityName;
        }
    }

    @Getter
    class ReferenceAddressCmd extends SelfValidating<ReferenceAddressCmd> {
        private final int number;
        private final String rep;
        private final String streetName;
        private final CreatePositionCmd createPositionCmd;
        private final CreateCityCmd createCityCmd;

        public ReferenceAddressCmd(int number, String rep, String streetName, CreatePositionCmd createPositionCmd, CreateCityCmd createCityCmd) {
            this.streetName = streetName;
            this.number = number;
            this.rep = rep;
            this.createPositionCmd = createPositionCmd;
            this.createCityCmd = createCityCmd;
        }
    }
}
