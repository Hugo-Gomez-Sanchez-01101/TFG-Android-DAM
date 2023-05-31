package com.example.apptfg.entidad;

import java.io.Serializable;

public abstract class Componente implements Serializable {
    public abstract String getNombre();
    public abstract double getPrecio();
}
