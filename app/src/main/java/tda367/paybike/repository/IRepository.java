package tda367.paybike.repository;

import android.net.Uri;

import java.time.LocalDateTime;

import tda367.paybike.model.Position;
import tda367.paybike.model.Rentable;

public interface IRepository {

    void updateUser();
    void updateRequests();
    void updateRentables();
    void addUser(String email, String password, String name);
    void addRequest(Rentable r, LocalDateTime from, LocalDateTime to, double price);
    void addRentable(String type, String name, double price,
                     Position position, boolean available,
                     Uri imagePath, String description);

}
