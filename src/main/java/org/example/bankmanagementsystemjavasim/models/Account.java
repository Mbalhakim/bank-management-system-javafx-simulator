package org.example.bankmanagementsystemjavasim.models;

public class Account {
    private int accountId;
    private int customerId;
    private double balance;
    private String type;
    private String createdAt;

    public Account(int accountId, int customerId, double balance, String type, String createdAt) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
        this.type = type;
        this.createdAt = createdAt;
    }

    public Account(int customerId, double balance, String type) {
        this.customerId = customerId;
        this.balance = balance;
        this.type = type;
    }

    public int getAccountId() { return accountId; }
    public int getCustomerId() { return customerId; }
    public double getBalance() { return balance; }
    public String getType() { return type; }
    public String getCreatedAt() { return createdAt; }

    public void setBalance(double balance) { this.balance = balance; }
}
