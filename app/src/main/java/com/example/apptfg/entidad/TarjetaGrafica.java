package com.example.apptfg.entidad;

public class TarjetaGrafica {
    private String overClock, chipset, color, coreClock, ancho, vRam, nombre;
    private double precio;

    public TarjetaGrafica(String overClock, String chipset, String color, String coreClock, String ancho, String vRam, String nombre, double precio) {
        this.overClock = overClock;
        this.chipset = chipset;
        this.color = color;
        this.coreClock = coreClock;
        this.ancho = ancho;
        this.vRam = vRam;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getOverClock() {
        return overClock;
    }

    public String getChipset() {
        return chipset;
    }

    public String getColor() {
        return color;
    }

    public String getCoreClock() {
        return coreClock;
    }

    public String getAncho() {
        return ancho;
    }

    public String getvRam() {
        return vRam;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}
