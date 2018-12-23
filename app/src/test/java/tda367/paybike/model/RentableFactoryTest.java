package tda367.paybike.model;


import android.net.Uri;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import static junit.framework.TestCase.assertNotNull;

public class RentableFactoryTest {

    RentableFactory rf = new RentableFactory();

    @Test
    public void TestCreateRentable() {
        RentableFactory rf = new RentableFactory();
        Rentable testBike1 = RentableFactory.createTestRentable("Bike");
        assertNotNull(testBike1);
    }

    @Test
    public void createRentableNoID(){
        User testUser = new User("t@t.com", "testpass", "Testare", "1");
        String type;
        String name;
        double price;
        Position position;
        boolean available;
        String owner;
        Uri imageLink;
        String description;

        String userOwenr = testUser.getName();
        position = new Position("Testgatan", 55555, "Teststaden");
        type = "Bike";
        name = "TestBike1";
        price = 55.05;
        available = true;
        owner = userOwenr;
        imageLink = null;
        description = "This is a testBike";

        //Create Rentable using the factory and instantiated variables
        Rentable testBike = RentableFactory.createRentableNoID(type, name, price, position, available, owner, imageLink, description);

        //Assert
        assertNotNull(testBike);
        assertEquals(testBike.getName(), name);
        assertEquals(testBike.getPosition(), position);

    }

    @Test
    public void addFactoryRentableTOModel(){
        PayBike payBike = PayBike.getInstance();

        User testUser = new User("t@t.com", "testpass", "Testare", "1");
        String type;
        String name;
        double price;
        Position position;
        boolean available;
        String owner;
        Uri imageLink;
        String description;

        position = new Position("Modelgatan", 44444, "Teststaden");
        type = "Bike";
        name = "TestBike2";
        price = 56.05;
        available = true;
        owner = testUser.getName();
        imageLink = null;
        description = "This is a testBike which is added to payBike";
        //Creates bike with factory
        Rentable testBike = RentableFactory.createRentableNoID(type, name, price, position, available, owner, imageLink, description);
        //Adds to model
        payBike.addModelRentable(testBike);
        //Find testBike and check
        assertEquals(findRentable(name).getName(), testBike.getName());
    }

    public Rentable findRentable(String name){
        PayBike payBike = PayBike.getInstance();
        for (Rentable rentable: payBike.getModelRentables()
             ) {
            if(rentable.getName().equals(name)){
                return rentable;
            }
        }
        return null;
    }


}
