package com.example.apptfg.entidad;

public class Caja {
    private double price_usd;
    private String side_panel_window;
    private String color;
    private String power_supply;
    private String internal_35_bays;
    private String name;
    private int rating;
    private String type;
    private String external_525_bays;
    private String form_fator;
    private int rating_count;

    public Caja(){}

    public Caja(double price_usd, String side_panel_window, String color, String power_supply, String internal_35_bays, String name, int rating, String type, String external_525_bays, String form_fator, int rating_count) {
        this.price_usd = price_usd;
        this.side_panel_window = side_panel_window;
        this.color = color;
        this.power_supply = power_supply;
        this.internal_35_bays = internal_35_bays;
        this.name = name;
        this.rating = rating;
        this.type = type;
        this.external_525_bays = external_525_bays;
        this.form_fator = form_fator;
        this.rating_count = rating_count;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public String getSide_panel_window() {
        return side_panel_window;
    }

    public String getColor() {
        return color;
    }

    public String getPower_supply() {
        return power_supply;
    }

    public String getInternal_35_bays() {
        return internal_35_bays;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getType() {
        return type;
    }

    public String getExternal_525_bays() {
        return external_525_bays;
    }

    public String getForm_fator() {
        return form_fator;
    }

    public int getRating_count() {
        return rating_count;
    }

    @Override
    public String toString() {
        return "Caja{" +
                "price_usd=" + price_usd +
                ", side_panel_window='" + side_panel_window + '\'' +
                ", color='" + color + '\'' +
                ", power_supply='" + power_supply + '\'' +
                ", internal_35_bays='" + internal_35_bays + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", type='" + type + '\'' +
                ", external_525_bays='" + external_525_bays + '\'' +
                ", form_fator='" + form_fator + '\'' +
                ", rating_count=" + rating_count +
                '}';
    }
}
