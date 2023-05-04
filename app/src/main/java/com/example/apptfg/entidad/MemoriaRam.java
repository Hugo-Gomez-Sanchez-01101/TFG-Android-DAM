package com.example.apptfg.entidad;

public class MemoriaRam {
    private String color, cosLatencia, factorForma, modulos, name, precioXgb;
    private double precio;
    private int capacidad, velocidad;

    public MemoriaRam(String color, String cosLatencia, String factorForma, String modulos, String name, String precioXgb, double precio, int capacidad, int velocidad) {
        this.color = color;
        this.cosLatencia = cosLatencia;
        this.factorForma = factorForma;
        this.modulos = modulos;
        this.name = name;
        this.precioXgb = precioXgb;
        this.precio = precio;
        this.capacidad = capacidad;
        this.velocidad = velocidad;
    }

    public String getColor() {
        return color;
    }

    public String getCosLatencia() {
        return cosLatencia;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public String getModulos() {
        return modulos;
    }

    public String getName() {
        return name;
    }

    public String getPrecioXgb() {
        return precioXgb;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getVelocidad() {
        return velocidad;
    }
}
