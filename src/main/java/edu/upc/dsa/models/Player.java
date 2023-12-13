package edu.upc.dsa.models;
import edu.upc.dsa.exceptions.NoCoinsForBuyException;
import edu.upc.dsa.CRUD.util.RandomUtils;

import java.util.List;
import java.util.ArrayList;


public class Player {

    private String id;
    private String username;
    private String password;
    private String telephoneNumber;
    private String email;
    private int coins;

    public Player() {
    }

    public Player(String id, String username, String password, String telephoneNumber, String email, int coins) {
        this();
        this.id = id;
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.coins = 500;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getCoins() {
        return coins;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber=telephoneNumber;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setCoins(int coins) {
        this.coins=coins;
    }

    public void purchaseItem(Item item) throws NoCoinsForBuyException {
        if (this.coins >= item.getPrice()) {
            this.coins -= item.getPrice();
        } else {
            throw new NoCoinsForBuyException();
        }
    }
}
