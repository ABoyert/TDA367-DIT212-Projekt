package tda367.paybike.model;

import java.util.Date;


public class Bike implements Rentable {
    private Date startDate;
    private Date endDate;
    private String id;
    private double price;
    private String position;
    private boolean available;

    public Bike(String id, double price, String position){
        this.id = id;
        this.price = price;
        this.position = position;
        this.setAvailable(false);
    }

    public Bike(String id, double price, String position, boolean available){
        this.id = id;
        this.price = price;
        this.position = position;
        this.available = available;
    }

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
    public void setPosition(String pos) {
        this.position = pos;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setEndDate(Date startDate) {
        this.endDate = endDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }
}
