package com.example.apptfg.entidad;

import java.io.Serializable;

public class TarjetaGrafica extends Componente {;
    private double price_usd;
    private String memory;
    private String color;
    private String name;
    private double rating;
    private String chipset;
    private String boost_clock;
    private String core_clock;
    private double rating_count;
    private String length;

    public TarjetaGrafica(){}

    public TarjetaGrafica(double price_usd, String memory, String color, String name, double rating, String chipset, String boost_clock, String core_clock, double rating_count, String length) {
        this.price_usd = price_usd;
        this.memory = memory;
        this.color = color;
        this.name = name;
        this.rating = rating;
        this.chipset = chipset;
        this.boost_clock = boost_clock;
        this.core_clock = core_clock;
        this.rating_count = rating_count;
        this.length = length;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public String getMemory() {
        return memory;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public String getChipset() {
        return chipset;
    }

    public String getBoost_clock() {
        return boost_clock;
    }

    public String getCore_clock() {
        return core_clock;
    }

    public double getRating_count() {
        return rating_count;
    }

    public String getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "TarjetaGrafica{" +
                "price_usd=" + price_usd +
                ", memory='" + memory + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", chipset='" + chipset + '\'' +
                ", boost_clock='" + boost_clock + '\'' +
                ", core_clock='" + core_clock + '\'' +
                ", rating_count=" + rating_count +
                '}';
    }

    @Override
    public String getNombre() {
        return chipset;
    }

    @Override
    public double getPrecio() {
        return price_usd;
    }
}
