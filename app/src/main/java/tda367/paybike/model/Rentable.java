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
    public void setPosition(Position pos);
    public Position getPosition();
    /*public void setStartDate(Date startDate);
    public Date getStartDate();
    public void setEndDate(Date startDate);
    public Date getEndDate();*/
    public void setAvailable(boolean available);
    public boolean isAvailable();
    public void setOwner(String ownerID);
    public String getOwner();
    public void setName(String name);
    public String getName();
    public void setDescription(String name);
    public String getDescription();
    public String getImageLink();
    public void setImageLink(String imageLink);





}
