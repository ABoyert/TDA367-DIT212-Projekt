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
    public void setDate(Date start, Date end);
    public Date[] getRentingDate();
    public void setAvailable(boolean available);
    public boolean isAvailable();




}
