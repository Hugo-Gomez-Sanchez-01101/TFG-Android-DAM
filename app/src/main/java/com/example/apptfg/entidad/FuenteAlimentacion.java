package com.example.apptfg.entidad;

public class FuenteAlimentacion {
    private String color, certificacion, factorForma, modular, nombre;
    private double precio;
    private int wattage;

    public FuenteAlimentacion(String color, String certificacion, String factorForma, String modular, String nombre, double precio, int wattage) {
        this.color = color;
        this.certificacion = certificacion;
        this.factorForma = factorForma;
        this.modular = modular;
        this.nombre = nombre;
        this.precio = precio;
        this.wattage = wattage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public void setCertificacion(String certificacion) {
        this.certificacion = certificacion;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getWattage() {
        return wattage;
    }

    public void setWattage(int wattage) {
        this.wattage = wattage;
    }
}
