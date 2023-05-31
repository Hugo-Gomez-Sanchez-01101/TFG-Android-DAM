package com.example.apptfg.adaptador;

public class OrdenadorTarjeta {
    //Esta clase tiene que ser reemplazada por la clase Ordenador
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public OrdenadorTarjeta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
