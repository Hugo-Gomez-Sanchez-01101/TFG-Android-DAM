package com.example.apptfg.entidad;

public class Disipador {
    private String color;
    private String revoluciones;
    private String nombre;
    private String nivelRuido;
    private double precio;

    public Disipador(String color, String revoluciones, String nombre, String nivelRuido, double precio) {
        this.color = color;
        this.revoluciones = revoluciones;
        this.nombre = nombre;
        this.nivelRuido = nivelRuido;
        this.precio = precio;
    }

    public String getColor() {
        return color;
    }

    public String getRevoluciones() {
        return revoluciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivelRuido() {
        return nivelRuido;
    }

    public double getPrecio() {
        return precio;
    }
}
