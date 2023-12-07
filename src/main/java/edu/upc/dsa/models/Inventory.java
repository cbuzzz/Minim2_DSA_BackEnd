package edu.upc.dsa.models;

public class Inventory {
    String name;
    public String idItem;
    public String idPlayer;
    public Inventory() {
    }
    public Inventory(String name, String idItem, String idPlayer) {
        this.name = name;
        this.idItem = idItem;
        this.idPlayer = idPlayer;
    }
    public String getName() {
        return name;
    }
    public String getIdItem() {
        return idItem;
    }
    public String getIdPlayer() {
        return idPlayer;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }
    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }
}
