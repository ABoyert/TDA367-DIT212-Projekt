package tda367.paybike.model;

import android.net.Uri;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PayBikeTest {

    @Test
    public void addRequest(){
        PayBike payBike = PayBike.getInstance();
        payBike.getModelUsers().clear();
        payBike.getModelRequests().clear();
        assertTrue (payBike.getModelUsers().isEmpty());
        User loggedIn = new User("current@t.com", "87654321", "Testcurrent", "0");
        PayBike.addModelUser(loggedIn);
        payBike.updateCurrentUser(loggedIn.getUserID());
        User currentUser = PayBike.getCurrentUser();
        User targetUser = new User("test@t.com", "12345678", "Testare", "1");
        PayBike.addModelUser(targetUser);
        assertFalse (payBike.getModelUsers().isEmpty());
        Uri image = Uri.parse("Image");
        Rentable testRentable = RentableFactory.createRentableNoID("Bike", "Cykeltest", 25.00, "Testland", true, targetUser.getUserID(), image, "En test");
        payBike.addRequest(currentUser, testRentable, LocalDateTime.of(2020, 12, 24, 11,0), LocalDateTime.of(2020, 12, 24, 12, 0));
        assertFalse (payBike.getModelRequests().isEmpty());
    }

    @Test
    public void loginTests(){
        PayBike payBike = PayBike.getInstance();
        User testUser = new User("test@test.com", "testpass", "testname", "1" );
        PayBike.addModelUser(testUser);
        //assertFalse (payBike.checkLogin("test@failtest.com", "testpass"));
        //assertFalse (payBike.checkLogin("test@test.com", "testpassfail"));
        //assertFalse (payBike.checkLogin("test@testfail.com", "testpassfail"));
        //assertTrue (payBike.checkLogin("test@test.com", "testpass"));

    }

}