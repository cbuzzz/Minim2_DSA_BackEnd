package edu.upc.dsa.models;

public class Player {
    private int id;
    private String username;
    private String password;
    private String confirm_password;
    private String email;
    private int level;
    private int coins;

    public Player() {
    }

    public Player(String username, String password, String email, int coins) {
        this();
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setCoins(coins);
    }

    public void playerRegister (String username,String password,String confirm_password,String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setConfirm_password(confirm_password);
        this.setEmail(email);
        this.setCoins(0);
        this.setLevel(1);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return level;
    }

    public int getCoins() {
        return coins;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password=confirm_password;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setLevel(int level) {
        this.level=level;
    }

    public void setCoins(int coins) {
        this.coins=coins;
    }
}
