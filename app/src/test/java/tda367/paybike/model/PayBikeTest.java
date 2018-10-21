package tda367.paybike.model;

import android.net.Uri;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class PayBikeTest {

    static PayBike payBike;
    static User currentUser;
    static User targetUser;
    static Uri image;
    static Rentable testRentable;
    static Rentable testRentable2;
    static List<Rentable> rentableList = new ArrayList<>();

    @BeforeClass
    public static void init() {
        payBike = PayBike.getInstance();
        PayBike.setCurrentUser(new User("test@test.com", "password", "Testare", "0"));
        currentUser = PayBike.getCurrentUser();
        targetUser = new User("test@t.com", "password", "Testare", "1");
        image = Uri.parse("Image");
        testRentable = RentableFactory.createRentable("Bike", "Cykeltest", 25.00, new Position("Testgatan 1", 34351, "Miami"), true, targetUser.getUserID(), image, "En test", "id1234");
        testRentable2 = RentableFactory.createRentable("Bike", "Cykeltest2", 2.00, new Position("Testgatana 1", 54351, "Miami"), true, targetUser.getUserID(), image, "En test2", "id5678");
        rentableList.add(testRentable);
        rentableList.add(testRentable2);
        payBike.setModelRentables(rentableList);
    }

    @Test
    public void addRequest(){
        payBike.addRequest(currentUser, testRentable, LocalDateTime.of(2020, 12, 24, 11,0), LocalDateTime.of(2020, 12, 24, 12, 0), 10);
        assertFalse(payBike.getModelRequests().isEmpty());
    }

    @Test
    public void getRentableFromId() {
        assertEquals(testRentable2, payBike.getRentableFromId("id5678"));
    }

    @Test
    public void addModelRentable() {
        payBike.addModelRentable(testRentable);
        payBike.addModelRentable(testRentable);
        payBike.addModelRentable(testRentable2);
        /* Size should still be 2 */
        assertEquals(rentableList.size(), payBike.getModelRentables().size());
    }

    @Test
    public void addModelUser() {
        User newUser = new User("test1@test.com", "password", "Testare", "1");
        User newUser2 = new User("test2@test.com", "password", "Testare", "2");

        PayBike.addModelUser(newUser);
        PayBike.addModelUser(newUser2);
        PayBike.addModelUser(newUser2);

        /* Should only be 2 users in model */
        assertEquals(2, payBike.getModelUsers().size());
    }

    //TODO kolla så inte flera av samma request kan göras, kolla så inte en bike med samma ID av redan existerande bike läggs till

}