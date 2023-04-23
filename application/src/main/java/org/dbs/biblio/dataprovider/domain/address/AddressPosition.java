package org.dbs.biblio.dataprovider.domain.address;

import org.dbs.biblio.dataprovider.domain.exception.BusinessException;

public class AddressPosition {
    public static final String NAN = "Nan";
    private final float x;
    private final float y;
    private final float lon;
    private final float lat;
    private final String typePosition;

    private AddressPosition(float x, float y, float lon, float lat, String typePosition) {
        this.x = x;
        this.y = y;
        this.lon = lon;
        this.lat = lat;
        this.typePosition = typePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressPosition position = (AddressPosition) o;

        if (Float.compare(position.x, x) != 0) return false;
        if (Float.compare(position.y, y) != 0) return false;
        if (Float.compare(position.lon, lon) != 0) return false;
        if (Float.compare(position.lat, lat) != 0) return false;
        return typePosition.equals(position.typePosition);
    }

    @Override
    public int hashCode() {
        int result = (x != 0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != 0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (lon != 0.0f ? Float.floatToIntBits(lon) : 0);
        result = 31 * result + (lat != 0.0f ? Float.floatToIntBits(lat) : 0);
        result = 31 * result + typePosition.hashCode();
        return result;
    }

    public static class AddressPositionBuilder {
        private float x;
        private boolean xOk = false;
        private float y;
        private boolean yOk = false;
        private float lon;
        private boolean lonOk = false;
        private float lat;
        private boolean latOk = false;
        private String typePosition;
        private boolean typePositionOk = false;

        public AddressPositionBuilder setX(float x) {
            this.x = x;
            this.xOk = true;
            return this;
        }

        public AddressPositionBuilder setY(float y) {
            this.y = y;
            this.yOk = true;
            return this;
        }

        public AddressPositionBuilder setLon(float lon) {
            this.lon = lon;
            this.lonOk = true;
            return this;
        }

        public AddressPositionBuilder setLat(float lat) {
            this.lat = lat;
            this.latOk = true;
            return this;
        }

        public AddressPositionBuilder setTypePosition(String typePosition) {
            this.typePosition = typePosition;
            if (this.typePosition != null) {
                this.typePositionOk = true;
            }
            return this;
        }

        public AddressPosition build() {
            if (Boolean.FALSE.equals(this.xOk && this.yOk && this.latOk && this.lonOk)) {
                throw new BusinessException(String.format("Invalid Position builder parameter x(%s) y(%s) lat(%s) lon(%s)", xOk, yOk, latOk, lonOk));
            }
            if (Boolean.FALSE.equals(this.typePositionOk)) {
                this.typePosition = NAN;
            }
            return new AddressPosition(x, y, lon, lat, typePosition);
        }
    }
}
