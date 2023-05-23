package com.example.apptfg.entidad;

import java.io.Serializable;

public class MemoriaRam implements Serializable {
    private double price_usd;
    private String color;
    private int rating;
    private String name;
    private String price_x_gb;
    private String form_factor;
    private int speed;
    private String modules;
    private int rating_count;
    private int cas_latencia;
    private int capacity;

    public MemoriaRam(){}

    public MemoriaRam(double price_usd, String color, int rating, String name, String price_x_gb, String form_factor, int speed, String modules, int rating_count, int cas_latencia, int capacity) {
        this.price_usd = price_usd;
        this.color = color;
        this.rating = rating;
        this.name = name;
        this.price_x_gb = price_x_gb;
        this.form_factor = form_factor;
        this.speed = speed;
        this.modules = modules;
        this.rating_count = rating_count;
        this.cas_latencia = cas_latencia;
        this.capacity = capacity;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public String getColor() {
        return color;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getPrice_x_gb() {
        return price_x_gb;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public int getSpeed() {
        return speed;
    }

    public String getModules() {
        return modules;
    }

    public int getRating_count() {
        return rating_count;
    }

    public int getCas_latencia() {
        return cas_latencia;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "MemoriaRam{" +
                "price_usd=" + price_usd +
                ", color='" + color + '\'' +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", price_x_gb='" + price_x_gb + '\'' +
                ", form_factor='" + form_factor + '\'' +
                ", speed=" + speed +
                ", modules='" + modules + '\'' +
                ", rating_count=" + rating_count +
                ", cas_latencia=" + cas_latencia +
                ", capacity=" + capacity +
                '}';
    }
}
