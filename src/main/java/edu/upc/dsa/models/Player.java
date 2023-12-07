package edu.upc.dsa.models;


public class Player {

    //private String id;
    private String username;
    private String password;
    private String telephoneNumber;
    private String email;

    //private int level;
    private int coins;
    //private List<Item> Inventario = new ArrayList<>();

    public Player() {

    }

    public Player(String username, String password, String telephoneNumber, String email) {
        this();
        this.setUsername(username);
        this.setPassword(password);
        this.setTelephoneNumber(telephoneNumber);
        this.setEmail(email);
        this.coins = 300;
    }

    //public String getId() {
    //    return id;
    //}

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

    //public int getLevel() {
    //    return level;
    //}

    public int getCoins() {
        return coins;
    }

    //public void setId(String id) {
    //    this.id=id;
    //}

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

    //public void setLevel(int level) {
    //    this.level=level;
    //}

    public void setCoins(int coins) {
        this.coins=coins;
    }

    //public List<Item> getInventario() {
    //    return Inventario;
    //}

    //public void setInventario(List<Item> inventario) {
    //    Inventario = inventario;
    //}

    public void updatePlayer(Player p) {
        this.setUsername(p.getUsername());
        this.setPassword(p.getPassword());
        this.setTelephoneNumber(p.getTelephoneNumber());
        this.setEmail(p.getEmail());
    }
}
