package tda367.paybike.model;

/*
 * This class models Positions
 *
 * It's current limitation is to only model Swedish addresses
 */
public class Position {

    final private String street;
    final private Integer zipCode;
    final private String city;
    final private String country;

    /* Verifies that the given zipcode has five digits */
    private void checkZipCode(Integer zipCode) throws IllegalArgumentException {
        if (!(zipCode % 1000 < 9)) {
            throw new IllegalArgumentException("Zipcode is invalid.");
        }
    }

    /* Verifies that street is not an empty String */
    private void checkStreet(String street) throws IllegalArgumentException {
        if (street == "") {
            throw new IllegalArgumentException("Street needs to be at least one character long.");
        }
    }

    /* Verifies that city is not an empty String */
    private void checkCity(String city) throws IllegalArgumentException {
        if (city == "") {
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
        checkCity(city)
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        country = "Sweden";
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
        return country;
    }

}
