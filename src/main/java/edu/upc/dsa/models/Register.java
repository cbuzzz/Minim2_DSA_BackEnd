package edu.upc.dsa.models;

public class Register {

    String username;
    String password;
    String confirm_password;
    String email;
    int coins;

    public Register() {
    }

    public Register(String username, String password, String confirm_password, String email, int coins) {
        this.username = username;
        this.password = password;
        this.confirm_password = confirm_password;
        this.email = email;
        this.coins = coins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
