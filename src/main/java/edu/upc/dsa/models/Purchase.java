package edu.upc.dsa.models;

public class Purchase {
    String idUser;
    String idItem;

    public Purchase(){}

    public Purchase(String idUser, String idItem) {
        this.idUser = idUser;
        this.idItem = idItem;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getidItem() {
        return idItem;
    }

    public void setidItem(String idItem) {
        this.idItem = idItem;
    }
}