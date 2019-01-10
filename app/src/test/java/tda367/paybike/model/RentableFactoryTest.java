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
        String type = "Bike";
        String name = "TestBike1";
        double price= 55.05;
        Position position;
        boolean available = true;
        String owner= testUser.getName();
        Uri imageLink;
        String description;
        position = new Position("Testgatan", 55555, "Teststaden");
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
        String type = "Bike";
        String name= "TestBike2";
        double price = 56.05;
        Position position = new Position("Modelgatan", 44444, "Teststaden");
        boolean available = true;
        String owner= testUser.getName();
        Uri imageLink;
        String description;
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
