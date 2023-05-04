package com.example.apptfg.entidad;

public class DiscoDuro {
    private String cache, capacidad, factorForma, interfaz, nombre, precioXgb, revoluciones;
    private double precio;

    public DiscoDuro(String cache, String capacidad, String factorForma, String interfaz, String nombre, String precioXgb, String revoluciones, double precio) {
        this.cache = cache;
        this.capacidad = capacidad;
        this.factorForma = factorForma;
        this.interfaz = interfaz;
        this.nombre = nombre;
        this.precioXgb = precioXgb;
        this.revoluciones = revoluciones;
        this.precio = precio;
    }

    public String getCache() {
        return cache;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrecioXgb() {
        return precioXgb;
    }

    public String getRevoluciones() {
        return revoluciones;
    }

    public double getPrecio() {
        return precio;
    }
}
