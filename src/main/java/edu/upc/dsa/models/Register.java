package edu.upc.dsa.models;

public class Register {
    String username;
    String password;
    String telephoneNumber;
    String email;

    public Register() {
    }

    public Register(String username, String password, String telephoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username=username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber=telephoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email=email;
    }
}
