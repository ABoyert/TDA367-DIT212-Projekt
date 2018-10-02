package tda367.paybike.model;

import android.net.Uri;

import java.util.Objects;


public class Bike implements Rentable {

    /* Instance variables */
    private String id,
            name,
            position,
            owner,
            description;
    private double price;
    private Position position;
    private boolean available;
    private Uri imagePath;


    /* Ensures that the name is between one and 50 characters long */
    private void checkName(String name) throws IllegalArgumentException {
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name needs to be at least one character long");
        } else if (name.length() > 50) {
            throw new IllegalArgumentException("Name is to long.");
        }
    }

    /* Ensures that the description id exists */
    private void checkDescription(String description) throws IllegalArgumentException {
        if (description.length() == 0) {
            throw new IllegalArgumentException("Description needs to be at least one character long");
        } else if (description.length() > 1000) {
            throw new IllegalArgumentException("Description is to long.");
        }
    }

    /* Constructor containing Id */
    public Bike(String name, double price, String position, boolean available, String owner, Uri imagePath, String description, String id) {
        checkName(name);
        checkDescription(description);

        this.name = name;
        this.price = price;
        this.position = position;
        this.available = available;
        this.owner = owner;
        this.imagePath = imagePath;
        this.description = description;
        this.id = id;
    }

    /* Constructor without an Id */
    public Bike(String name, double price, String position, boolean available, String owner, Uri imagePath, String description) {
        checkName(name);
        checkDescription(description);

        this.name = name;
        this.price = price;
        this.position = position;
        this.available = available;
        this.owner = owner;
        this.imagePath = imagePath;
        this.description = description;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
    public String getId(){
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
    public void setPosition(Position pos) {
        this.position = pos;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Uri getImagePath() {
        return imagePath;
    }

    @Override
    public void setImagePath(Uri imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Double.compare(bike.price, price) == 0 &&
                available == bike.available &&
                Objects.equals(id, bike.id) &&
                Objects.equals(name, bike.name) &&
                Objects.equals(position, bike.position) &&
                Objects.equals(owner, bike.owner) &&
                Objects.equals(imagePath, bike.imagePath) &&
                Objects.equals(description, bike.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, price, position, available, owner, imagePath, description);
    }

    @Override
    public String toString() {
        return "Bike Id: " + id + "\nName: " + name + "\nPosition: " + position +
                "\nOwner Id: " + owner + "\nDescription: " + description;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }
}
