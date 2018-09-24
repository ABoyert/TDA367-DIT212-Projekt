package tda367.paybike.model;

/**
 * Created by Oscar Orava Kilberg on 2018-09-20.
 */

public class RentableFactory {

    int i;

    public static Rentable createRentable(String type, String id, double price, String position){


        if("Bike".equalsIgnoreCase(type))return new Bike(id, price, position );


        return null;


    }

}
