package com.example.apptfg.singletonEntities;

import com.example.apptfg.OrdenadorGeneradoActivity;
import com.example.apptfg.entidad.Ordenador;

public class OrdenadorGeneradoSingleton {
    private static OrdenadorGeneradoSingleton ordenadorGeneradoActivity;
    private Ordenador ordenador;

    private OrdenadorGeneradoSingleton() {}

    public static OrdenadorGeneradoSingleton getInstance(){
        if(ordenadorGeneradoActivity == null){
            ordenadorGeneradoActivity = new OrdenadorGeneradoSingleton();
        }
        return ordenadorGeneradoActivity;
    }

    public Ordenador getOrdenador(){
        return ordenador;
    }

    public void setOrdenador(Ordenador ordenador) {
        this.ordenador = ordenador;
    }
}
