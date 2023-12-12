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

    String id;
    String name;
    String description;
    String type;
    double price;

    //private BufferedImage imagen;

    public Item() {

    }

    public Item(String id, String name, String description, String type, double price) {
        this();
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.setPrice(price);
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id=id;
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


    @Override
    public String toString() {
        return "Item [id="+id+", name="+name+", description="+description+", type="+type+", prize="+price+"]";
    }
}
