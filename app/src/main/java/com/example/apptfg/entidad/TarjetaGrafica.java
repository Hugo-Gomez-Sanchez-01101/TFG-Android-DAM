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

    public void setOverClock(String overClock) {
        this.overClock = overClock;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(String coreClock) {
        this.coreClock = coreClock;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getvRam() {
        return vRam;
    }

    public void setvRam(String vRam) {
        this.vRam = vRam;
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
}
