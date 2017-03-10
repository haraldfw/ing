package com.wilhelmsen.orm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Created by Harald Floor Wilhelmsen on 06.03.2017.
 */

@Entity(name = "konto")
public class GoodAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    double balance;

    String owner;

    @Version
    private int version;

    @Version
    public int getVersion() {
        return version;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void pull(double amount) {
        this.balance -= amount;
    }

    public void add(double amount) {
        this.balance += amount;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
