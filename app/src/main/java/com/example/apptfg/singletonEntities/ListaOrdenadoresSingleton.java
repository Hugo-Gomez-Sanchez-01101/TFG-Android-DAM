package com.example.apptfg.singletonEntities;

import com.example.apptfg.entidad.Ordenador;

import java.util.ArrayList;
import java.util.List;

public class ListaOrdenadoresSingleton {

    private static ListaOrdenadoresSingleton instance;
    private List<Ordenador> listaOrdenadores;
    private int contador = 1;

    private ListaOrdenadoresSingleton(){
        super();
    }

    public static ListaOrdenadoresSingleton getInstance() {
        if(instance == null){
            instance = new ListaOrdenadoresSingleton();
        }
        return instance;
    }

    public void inicializar(){
        listaOrdenadores = new ArrayList<>();
    }

    public List<Ordenador> getListaOrdenadores() {
        return listaOrdenadores;
    }

    public int añadirId(){
        return contador++;
    }
}
