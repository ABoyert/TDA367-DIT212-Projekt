package tda367.paybike.model;

import android.net.Uri;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public interface Rentable {

    void setId(String id);
    String getId();
    void setPrice(Double price);
    double getPrice();
    void setPosition(String pos);
    String getPosition();
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
