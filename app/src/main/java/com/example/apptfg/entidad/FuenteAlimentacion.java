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

    public String getCertificacion() {
        return certificacion;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public String getModular() {
        return modular;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getWattage() {
        return wattage;
    }

    @Override
    public String toString() {
        return "FuenteAlimentacion{" +
                "color='" + color + '\'' +
                ", certificacion='" + certificacion + '\'' +
                ", factorForma='" + factorForma + '\'' +
                ", modular='" + modular + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", wattage=" + wattage +
                '}';
    }
}
