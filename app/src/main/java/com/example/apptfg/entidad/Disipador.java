package com.example.apptfg.entidad;

public class Disipador {
    private double price_usd;
    private String color;
    private String radiator_size;
    private String noise_level;
    private int rating;
    private String name;
    private String fan_rpm;
    private int rating_count;

    public Disipador(){}

    public Disipador(double price_usd, String color, String radiator_size, String noise_level, int rating, String name, String fan_rpm, int rating_count) {
        this.price_usd = price_usd;
        this.color = color;
        this.radiator_size = radiator_size;
        this.noise_level = noise_level;
        this.rating = rating;
        this.name = name;
        this.fan_rpm = fan_rpm;
        this.rating_count = rating_count;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public String getColor() {
        return color;
    }

    public String getRadiator_size() {
        return radiator_size;
    }

    public int getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String getFan_rpm() {
        return fan_rpm;
    }

    public int getRating_count() {
        return rating_count;
    }

    public String getNoise_level() {
        return noise_level;
    }

    @Override
    public String toString() {
        return "Disipador{" +
                "price_usd=" + price_usd +
                ", color='" + color + '\'' +
                ", radiator_size='" + radiator_size + '\'' +
                ", noise_level='" + noise_level + '\'' +
                ", rating=" + rating +
                ", name='" + name + '\'' +
                ", fan_rpm='" + fan_rpm + '\'' +
                ", rating_count=" + rating_count +
                '}';
    }
}
