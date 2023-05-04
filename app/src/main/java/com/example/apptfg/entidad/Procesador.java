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

    public String getOverClock() {
        return overClock;
    }

    public String getCoreClock() {
        return coreClock;
    }

    public String getNumNucleos() {
        return numNucleos;
    }

    public String getGrafica() {
        return grafica;
    }

    public String getNombre() {
        return nombre;
    }

    public String getShocket() {
        return shocket;
    }

    public double getPrecio() {
        return precio;
    }
}
