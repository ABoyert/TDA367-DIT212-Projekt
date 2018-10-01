package tda367.paybike.model;

import java.util.Date;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public interface Rentable {

    public void setId(String id);
    public String getId();
    public void setPrice(Double price);
    public double getPrice();
    public void setPosition(String pos);
    public String getPosition();
    /*public void setStartDate(Date startDate);
    public Date getStartDate();
    public void setEndDate(Date startDate);
    public Date getEndDate();*/
    public void setAvailable(boolean available);
    public boolean isAvailable();
    public void setOwner(String ownerID);
    public String getOwner();
    public String getImageLink();
    public void setImageLink(String imageLink);
    public String getDescription();
    public void setDescription(String description);
    public String getName();
    public void setName(String name);




}
