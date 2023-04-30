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

    public void setColor(String color) {
        this.color = color;
    }

    public String getCosLatencia() {
        return cosLatencia;
    }

    public void setCosLatencia(String cosLatencia) {
        this.cosLatencia = cosLatencia;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

    public String getModulos() {
        return modulos;
    }

    public void setModulos(String modulos) {
        this.modulos = modulos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrecioXgb() {
        return precioXgb;
    }

    public void setPrecioXgb(String precioXgb) {
        this.precioXgb = precioXgb;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
