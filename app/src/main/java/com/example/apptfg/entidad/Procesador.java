package com.example.apptfg.entidad;

import java.io.Serializable;

public class Procesador extends Componente {
    private String integrated_grafics;
    private double price;
    private String name;
    private String socket;
    private String core_clock;
    private String core_count;
    private String boost_clock;

    public Procesador(String integrated_grafics, double price, String name, String socket, String core_clock, String core_count, String boost_clock) {
        this.integrated_grafics = integrated_grafics;
        this.price = price;
        this.name = name;
        this.socket = socket;
        this.core_clock = core_clock;
        this.core_count = core_count;
        this.boost_clock = boost_clock;
    }

    public Procesador(){}
    public String getIntegrated_grafics() {
        return integrated_grafics;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSocket() {
        return socket;
    }

    public String getCore_clock() {
        return core_clock;
    }

    public String getCore_count() {
        return core_count;
    }

    public String getBoost_clock() {
        return boost_clock;
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "integrated_grafics='" + integrated_grafics + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", socket='" + socket + '\'' +
                ", core_clock='" + core_clock + '\'' +
                ", core_count='" + core_count + '\'' +
                ", boost_clock='" + boost_clock + '\'' +
                '}';
    }

    @Override
    public String getNombre() {
        return name;
    }

    @Override
    public double getPrecio() {
        return price;
    }
}
