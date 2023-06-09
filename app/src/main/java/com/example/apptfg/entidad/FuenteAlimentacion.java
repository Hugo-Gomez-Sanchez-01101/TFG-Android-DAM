package com.example.apptfg.entidad;

import java.io.Serializable;

public class FuenteAlimentacion extends Componente {
    private String efficiency_rating;
    private double price_usd;
    private int wattage;
    private String modular;
    private String color;
    private int rating;
    private String name;
    private String form_factor;
    private int rating_count;

    public FuenteAlimentacion(){}

    public FuenteAlimentacion(String efficiency_rating, double price_usd, int wattage, String modular, String color, int rating, String name, String form_factor, int rating_count) {
        this.efficiency_rating = efficiency_rating;
        this.price_usd = price_usd;
        this.wattage = wattage;
        this.modular = modular;
        this.color = color;
        this.rating = rating;
        this.name = name;
        this.form_factor = form_factor;
        this.rating_count = rating_count;
    }

    public String getEfficiency_rating() {
        return efficiency_rating;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public int getWattage() {
        return wattage;
    }

    public String getModular() {
        return modular;
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

    public String getForm_factor() {
        return form_factor;
    }

    public int getRating_count() {
        return rating_count;
    }

    @Override
    public String toString() {
        return "FuenteAlimentacion{" +
                "efficiency_rating='" + efficiency_rating + '\'' +
                ", price_usd=" + price_usd +
                ", wattage=" + wattage +
                ", modular=" + modular +
                ", color='" + color + '\'' +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", form_factor='" + form_factor + '\'' +
                ", rating_count=" + rating_count +
                '}';
    }

    @Override
    public String getNombre() {
        return name;
    }

    @Override
    public double getPrecio() {
        return price_usd;
    }
}
