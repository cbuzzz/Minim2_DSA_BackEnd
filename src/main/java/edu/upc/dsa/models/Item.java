package edu.upc.dsa.models;

public class Item {
    int id;
    String name;
    String description;
    String type;
    double prize;
    String url;

    public Item() {
    }

    public Item(int id, String name, String description, String type, double prize) {
        this();
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setType(type);
        this.setPrize(prize);
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

    public double getPrize() {
        return prize;
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

    public void setPrize(double prize) {
        this.prize=prize;
    }

    public void setUrl(String url) {
        this.url=url;
    }

    public String toString() {
        return "Item [id="+id+", name="+name+", description="+description+", type="+type+", prize="+prize+", url="+url+"]";
    }
}
