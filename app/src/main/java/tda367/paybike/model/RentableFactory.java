package tda367.paybike.model;

import android.net.Uri;

/**
 * Created by Oscar Orava Kilberg on 2018-09-20.
 *
 *
 * RentableFactory abtracts the creeation of Rentable objects.
 *
 */

public class RentableFactory {

    public static Rentable createRentable(String type, String name, double price, Position position, boolean available, String owner, Uri imageLink, String description, String id){


        if("Bike".equalsIgnoreCase(type))
            return new Bike(name, price, position, available, owner, imageLink, description, id);


        return null;


    }

    public static Rentable createRentableNoID(String type, String name, double price, Position position, boolean available, String owner, Uri imageLink, String description){


        if("Bike".equalsIgnoreCase(type))
            return new Bike(name, price, position, available, owner, imageLink, description);


        return null;


    }

    public static Rentable createTestRentable(String type){

        if("Bike".equalsIgnoreCase(type))
            return new Bike("Test", 25.00, new Position("Testgatan 1", 53422, "Vara"), true, "testOwner", null, "this is a test bike", "123test");

        return null;

    }


}
