package tda367.paybike.model;

import java.util.Date;

/**
 * Created by Oscar Orava Kilberg on 2018-09-19.
 */

public class Bike implements Rentable {

    private Date[] rentDate;
    private String id;
    private double price;
    private String[] position;
    private boolean available;

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;

    }


    @Override
    public double getPrice() {
        return price;
    }


    @Override
    public void setPosition(String pos[]) {
        this.position = pos;
    }

    @Override
    public String[] getPosition() {
        return position;
    }

    @Override
    public void setDate(Date start, Date end) {
        this.rentDate = new Date[]{start, end};
    }

    @Override
    public Date[] getRentingDate() {
        return rentDate;
    }


}
