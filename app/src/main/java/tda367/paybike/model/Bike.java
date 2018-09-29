package tda367.paybike.model;

import java.util.Date;


public class Bike implements Rentable {
    //private Date startDate;
    //private Date endDate;
    private String id;
    private String name;
    private double price;
    private String position;
    private boolean available;
    private String owner;
    private String imageLink;
    private String description;

    public Bike(String name, double price, String position, boolean available, String owner, String imageLink, String description, String id) {
        this.name = name;
        this.price = price;
        this.position = position;
        this.available = available;
        this.owner = owner;
        this.imageLink = imageLink;
        this.description = description;
        this.id = id;
    }

    // Without ID
    public Bike(String name, double price, String position, boolean available, String owner, String imageLink, String description) {
        this.name = name;
        this.price = price;
        this.position = position;
        this.available = available;
        this.owner = owner;
        this.imageLink = imageLink;
        this.description = description;
        this.id = id;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setOwner(String ownerID) {
        this.owner = ownerID;
    }

    @Override
    public String getOwner() {
        return owner;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*@Override
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
    }*/
}
