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

    public void setColor(String color) {
        this.color = color;
    }

    public String getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(String revoluciones) {
        this.revoluciones = revoluciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivelRuido() {
        return nivelRuido;
    }

    public void setNivelRuido(String nivelRuido) {
        this.nivelRuido = nivelRuido;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
