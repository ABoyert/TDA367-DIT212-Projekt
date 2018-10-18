package tda367.paybike.model;

import java.util.IllegalFormatException;
import java.util.Objects;

/*
 * This class models Positions
 *
 * It's current limitation is to only model Swedish addresses
 */
public class Position {

    private static final String COUNTRY = "Sweden";

    private final String street;
    private final Integer zipCode;
    private final  String city;

    /* Verifies that the given zipcode has five digits */
    private void checkZipCode(Integer zipCode) throws IllegalArgumentException {
        if (!(zipCode / 10000 < 9 && zipCode / 10000 > 1)) {
            throw new IllegalArgumentException("Zipcode is invalid.");
        }
    }

    /* Verifies that street is not an empty String */
    private void checkStreet(String street) throws IllegalArgumentException {
        if (street == "" || containsInvalidChar(street)) {
            throw new IllegalArgumentException("Street needs to be at least one character long.");
        }
    }

    /* Verifies that city is not an empty String */
    private void checkCity(String city) throws IllegalArgumentException {
        if (city == "" || containsInvalidChar(city)) {
            throw new IllegalArgumentException("City needs to be at least one character long.");
        }
    }

    /**
     * Models Swedish address, hence all positions will have country automatically set to Sweden
     *
     * @param street Describes the street address, cannot be empty string
     * @param zipCode Valid Swedish zipcode with 5 digits
     * @param city Cannot be empty string
     */
    public Position(String street, Integer zipCode, String city) {
        checkStreet(street);
        checkZipCode(zipCode);
        checkCity(city);
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    /* Getters since all variables are final */

    public String getStreet() {
        return street;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return COUNTRY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(street, position.street) &&
                Objects.equals(zipCode, position.zipCode) &&
                Objects.equals(city, position.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zipCode, city);
    }

    @Override
    public String toString() {
        return street + "," + zipCode + "," + city;
    }

    private boolean containsInvalidChar(String s) {
        return s.indexOf(',') == -1 ? false : true;
    }

    /*
    *  Takes a toString() representation of a Position and parses to a new Position object
    *  Street, zipcode and city should be separated with a comma
    *  Example: "Mainstreet 2,45643,Stockholm"
    */
    public static Position parseString(String position) throws IllegalArgumentException {
        String street;
        Integer zipCode;
        String city;

        String[] split = position.split(",", -1);
        if (split.length == 3) {
            street = split[0];
            zipCode = Integer.parseInt(split[1]);
            city = split[2];
            return new Position(street, zipCode, city);
        } else {
            throw new IllegalArgumentException("Position could not be parsed due to Illegal Format");
        }
    }

}
