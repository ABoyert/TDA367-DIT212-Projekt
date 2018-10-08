package tda367.paybike.model;

public class Position {

    final private String street;
    final private Integer zipCode;
    final private String city;
    final private String country;

    public Position(String street, Integer zipCode, String city, String country) {
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

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
