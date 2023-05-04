package com.example.apptfg.entidad;

public class PlacaBase {
    private String audio, chipset, factorForma, nombre, puertosAlmacenamiento, puertosRed, puertosUsb, shocket;
    private double precio;
    private int memoriaMaxima, velocidadMaxima;

    public PlacaBase(String audio, String chipset, String factorForma, String nombre, String puertosAlmacenamiento, String puertosRed, String puertosUsb, String shocket, double precio, int memoriaMaxima, int velocidadMaxima) {
        this.audio = audio;
        this.chipset = chipset;
        this.factorForma = factorForma;
        this.nombre = nombre;
        this.puertosAlmacenamiento = puertosAlmacenamiento;
        this.puertosRed = puertosRed;
        this.puertosUsb = puertosUsb;
        this.shocket = shocket;
        this.precio = precio;
        this.memoriaMaxima = memoriaMaxima;
        this.velocidadMaxima = velocidadMaxima;
    }

    public String getAudio() {
        return audio;
    }

    public String getChipset() {
        return chipset;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuertosAlmacenamiento() {
        return puertosAlmacenamiento;
    }

    public String getPuertosRed() {
        return puertosRed;
    }

    public String getPuertosUsb() {
        return puertosUsb;
    }

    public String getShocket() {
        return shocket;
    }

    public double getPrecio() {
        return precio;
    }

    public int getMemoriaMaxima() {
        return memoriaMaxima;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }
}
