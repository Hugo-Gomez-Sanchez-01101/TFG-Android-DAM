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

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

    public String getInterfaz() {
        return interfaz;
    }

    public void setInterfaz(String interfaz) {
        this.interfaz = interfaz;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecioXgb() {
        return precioXgb;
    }

    public void setPrecioXgb(String precioXgb) {
        this.precioXgb = precioXgb;
    }

    public String getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(String revoluciones) {
        this.revoluciones = revoluciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
