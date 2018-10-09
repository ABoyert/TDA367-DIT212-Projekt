package tda367.paybike.model;

import android.net.Uri;


public class Bike implements Rentable {
    private String id;
    private String name;
    private double price;
    private String position;
    private boolean available;
    private String owner;
    private Uri imagePath;
    private String description;

    public Bike(String name, double price, String position, boolean available, String owner, Uri imagePath, String description, String id) {
        this.name = name;
        this.price = price;
        this.position = position;
        this.available = available;
        this.owner = owner;
        this.imagePath = imagePath;
        this.description = description;
        this.id = id;
    }

    // Without ID
    public Bike(String name, double price, String position, boolean available, String owner, Uri imagePath, String description) {
        this.name = name;
        this.price = price;
        this.position = position;
        this.available = available;
        this.owner = owner;
        this.imagePath = imagePath;
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

    public Uri getImagePath() {
        return imagePath;
    }

    public void setImagePath(Uri imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
