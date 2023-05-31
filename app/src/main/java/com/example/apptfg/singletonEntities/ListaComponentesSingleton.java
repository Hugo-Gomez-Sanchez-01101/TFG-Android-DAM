package com.example.apptfg.singletonEntities;

import com.example.apptfg.adaptador.ComponenteTarjeta;
import com.example.apptfg.adaptador.OrdenadorTarjeta;
import com.example.apptfg.entidad.Componente;

import java.util.ArrayList;
import java.util.List;

public class ListaComponentesSingleton {
    private static ListaComponentesSingleton instance;
    private List<Componente> listaComponentes;

    private ListaComponentesSingleton(){
        super();
    }

    public static ListaComponentesSingleton getInstance() {
        if(instance == null){
            instance = new ListaComponentesSingleton();
        }
        return instance;
    }

    public void inicializar(){
        listaComponentes = new ArrayList<>();
    }

    public List<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public void setListaComponentes(List<Componente> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

}
