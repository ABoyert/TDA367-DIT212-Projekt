package tda367.paybike.model;

import android.net.Uri;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 *
 *
 * Rentable is a interface implemented by all current and future object that can be rented in the application.
 *
 */

public interface Rentable {

    void setId(String id);
    String getId();
    void setPrice(Double price);
    double getPrice();
    void setPosition(Position pos);
    Position getPosition();
    void setAvailable(boolean available);
    boolean isAvailable();
    void setOwner(String ownerID);
    String getOwner();
    Uri getImagePath();
    void setImagePath(Uri imageLink);
    String getDescription();
    void setDescription(String description);
    String getName();
    void setName(String name);




}
