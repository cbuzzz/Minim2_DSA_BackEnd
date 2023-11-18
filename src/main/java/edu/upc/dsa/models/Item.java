package edu.upc.dsa.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Item {
    int id;
    String name;
    String description;
    String type;
    double price;
    String url;
    public static Map<String,Item> Tienda = new HashMap<>();

    public Item() {
        this.id = new Random().nextInt();
    }

    public Item(int id, String name, String description, String type, double price) {
        this();
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.setPrice(price);
    }

    public int getId() {
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

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
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

    public void setPrice(double prize) {
        this.price=price;
    }

    public void setUrl(String url) {
        this.url=url;
    }

    public String toString() {
        return "Item [id="+id+", name="+name+", description="+description+", type="+type+", prize="+price+", url="+url+"]";
    }
}
