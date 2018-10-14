package tda367.paybike.model;

import android.net.Uri;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PayBikeTest {

    @Test
    public void addRequest(){
        PayBike payBike = PayBike.getInstance();
        User currentUser = payBike.getCurrentUser();
        User targetUser = new User("test@t.com", "123", "Testare", "1");
        Uri image = Uri.parse("Image");
        Rentable testRentable = RentableFactory.createRentableNoID("Bike", "Cykeltest", 25.00, "Testland", true, targetUser.getUserID(), image, "En test");
        payBike.addRequest(currentUser, testRentable, LocalDateTime.of(2020, 12, 24, 11,0), LocalDateTime.of(2020, 12, 24, 12, 0));
        assert !payBike.getModelRequests().isEmpty();
    }

}