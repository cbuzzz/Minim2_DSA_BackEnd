package edu.upc.dsa.models;

import java.util.List;

public class UserInformation {
    String username;
    String password;
    String telephoneNumber;
    String email;
    //double coins;

    public UserInformation(){};

    public UserInformation(String username,String password,String telephoneNumber,String email){
        this.setUsername(username);
        this.setPassword(password);
        this.setTelephoneNumber(telephoneNumber);
        this.setEmail(email);
        //this.setCoins(coins);

    }

    public UserInformation(Player p){
        this.setUsername(p.getUsername());
        this.setPassword(p.getPassword());
        this.setTelephoneNumber(p.getTelephoneNumber());
        this.setEmail(p.getEmail());
        //this.setCoins(p.getCoins());
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
/*
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public int getCoins(){
        return this.coins;
    }

 */
}