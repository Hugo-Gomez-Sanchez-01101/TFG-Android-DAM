package com.example.apptfg.entidad;

public class TarjetaGrafica {;
    private double price_usd;
    private String memory;
    private String color;
    private String name;
    private double rating;
    private String chipset;
    private String boost_clock;
    private String core_clock;
    private double rating_count;

    public TarjetaGrafica(){}

    public TarjetaGrafica(double price_usd, String memory, String color, String name, double rating, String chipset, String boost_clock, String core_clock, double rating_count) {
        this.price_usd = price_usd;
        this.memory = memory;
        this.color = color;
        this.name = name;
        this.rating = rating;
        this.chipset = chipset;
        this.boost_clock = boost_clock;
        this.core_clock = core_clock;
        this.rating_count = rating_count;
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
}
