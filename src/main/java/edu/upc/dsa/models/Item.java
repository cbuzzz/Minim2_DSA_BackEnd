package edu.upc.dsa.models;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Item {
    int id;
    String name;

    String description;
    String type;
    double price;

    public Item() {

    }

    public Item(String name, String description, String type, double price) {
        this();
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.setPrice(price);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setType(String type) {
        this.type=type;
    }

    public void setPrice(double price) {
        this.price=price;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String toString() {
        return "Item [id="+id+", name="+name+", description="+description+", type="+type+", prize="+price+"]";
    }
}
