package tda367.paybike.model;

/**
 * Created by Oscar Orava Kilberg on 2018-09-20.
 *
 */

public class RentableFactory {

    public static Rentable createRentable(String type, String name, double price, String position, boolean available, String owner, String imageLink, String description, String id){


        if("Bike".equalsIgnoreCase(type))
            return new Bike(name, price, position, available, owner, imageLink, description, id);


        return null;


    }

    public static Rentable createRentableNoID(String type, String name, double price, String position, boolean available, String owner, String imageLink, String description){


        if("Bike".equalsIgnoreCase(type))
            return new Bike(name, price, position, available, owner, imageLink, description);


        return null;


    }

    public static Rentable createTestRentable(String type){


        if("Bike".equalsIgnoreCase(type))
            return new Bike("Test", 25.00, "Testgatan", true, "testOwner", "noImage", "this is a test bike", "123test");


        return null;


    }


}
