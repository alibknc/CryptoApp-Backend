package com.project.cryptoapp.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="record")
public class Record {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="IP")
    private String ip;

    @Column(name="symbol")
    private String symbol;

    @Column(name="price")
    private double price;

    @Column(name="time")
    private Date time;

    public Record(int id, String ip, String symbol, double price, Date time) {
        this.id = id;
        this.ip = ip;
        this.symbol = symbol;
        this.price = price;
        this.time = time;
    }

    public Record() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
