package com.example.apptfg.singletonEntities;

import com.example.apptfg.adaptador.OrdenadorTarjeta;
import com.example.apptfg.entidad.Ordenador;

import java.util.ArrayList;
import java.util.List;

public class ListaOrdenadoresSingleton {

    private static ListaOrdenadoresSingleton instance;
    private List<Ordenador> ordenadores;
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
        //
    }

    public List<Ordenador> getOrdenadores() {
        return ordenadores;
    }

    public int añadirId(){
        return contador++;
    }
    public void borrar(OrdenadorTarjeta ordenador){
        ordenadores.remove(ordenador);
    }
}
