package edu.upc.dsa.models;

public class PurchasedItem {
    int idItem;
    int idPlayer;

    public PurchasedItem() {
    }

    public PurchasedItem(int idItem, int idPlayer) {
        this.setIdItem(idItem);
        this.setIdPlayer(idPlayer);
    }

    public int getIdItem() {
        return idItem;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdItem(int idItem) {
        this.idItem=idItem;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer=idPlayer;
    }
}
