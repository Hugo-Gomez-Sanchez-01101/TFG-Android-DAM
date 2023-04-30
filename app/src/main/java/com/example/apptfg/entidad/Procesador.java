package com.example.apptfg.entidad;

public class Procesador {
    private String overClock, coreClock, numNucleos, grafica, nombre, shocket;
    private double precio;

    public Procesador(String overClock, String coreClock, String numNucleos, String grafica, String nombre, double precio, String shocket) {
        this.overClock = overClock;
        this.coreClock = coreClock;
        this.numNucleos = numNucleos;
        this.grafica = grafica;
        this.nombre = nombre;
        this.precio = precio;
        this.shocket = shocket;
    }

    public String getShocket() {
        return shocket;
    }

    public void setShocket(String shocket) {
        this.shocket = shocket;
    }

    public String getOverClock() {
        return overClock;
    }

    public void setOverClock(String overClock) {
        this.overClock = overClock;
    }

    public String getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(String coreClock) {
        this.coreClock = coreClock;
    }

    public String getNumNucleos() {
        return numNucleos;
    }

    public void setNumNucleos(String numNucleos) {
        this.numNucleos = numNucleos;
    }

    public String getGrafica() {
        return grafica;
    }

    public void setGrafica(String grafica) {
        this.grafica = grafica;
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
