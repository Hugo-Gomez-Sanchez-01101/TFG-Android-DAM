package com.example.apptfg.entidad;

import java.io.Serializable;

public class DiscoDuro implements Serializable {
    private double price_usd;
    private String cache;
    private String name;
    private int rating;
    private String price_gb;
    private String type;
    private String interfaze;
    private String form_factor;
    private String capacity;
    private int rating_count;

    public DiscoDuro(){}

    public DiscoDuro(double price_usd, String cache, String name, int rating, String price_gb, String type, String interfaze, String form_factor, String capacity, int rating_count) {
        this.price_usd = price_usd;
        this.cache = cache;
        this.name = name;
        this.rating = rating;
        this.price_gb = price_gb;
        this.type = type;
        this.interfaze = interfaze;
        this.form_factor = form_factor;
        this.capacity = capacity;
        this.rating_count = rating_count;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public String getCache() {
        return cache;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getPrice_gb() {
        return price_gb;
    }

    public String getType() {
        return type;
    }

    public String getInterfaze() {
        return interfaze;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public String getCapacity() {
        return capacity;
    }

    public int getRating_count() {
        return rating_count;
    }

    @Override
    public String toString() {
        return "DiscoDuro{" +
                "price_usd=" + price_usd +
                ", cache='" + cache + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", price_gb='" + price_gb + '\'' +
                ", type='" + type + '\'' +
                ", interfaze='" + interfaze + '\'' +
                ", form_factor='" + form_factor + '\'' +
                ", capacity='" + capacity + '\'' +
                ", rating_count=" + rating_count +
                '}';
    }
}
