package tda367.paybike.model;

import android.media.audiofx.AudioEffect;
import android.net.Uri;

import java.util.Objects;


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
}
