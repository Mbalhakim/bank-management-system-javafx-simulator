package org.example.bankmanagementsystemjavasim.models;

public class Customer {
    private int customerId;
    private String fullName;
    private String email;
    private String pin;

    public Customer(String fullName, String email, String pin) {
        this.fullName = fullName;
        this.email = email;
        this.pin = pin;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
