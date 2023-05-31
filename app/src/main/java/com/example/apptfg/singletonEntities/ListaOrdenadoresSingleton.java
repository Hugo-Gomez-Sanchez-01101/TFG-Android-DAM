package com.example.apptfg.singletonEntities;

import com.example.apptfg.adaptador.OrdenadorTarjeta;
import java.util.ArrayList;
import java.util.List;

public class ListaOrdenadoresSingleton {

    private static ListaOrdenadoresSingleton instance;
    private List<OrdenadorTarjeta> listaOrdenadores;
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
        OrdenadorTarjeta n1 = new OrdenadorTarjeta(1,"mi Ordenador 1");
        listaOrdenadores.add(n1);
        OrdenadorTarjeta n2 = new OrdenadorTarjeta(2,"mi Ordenador 2");
        listaOrdenadores.add(n2);
        OrdenadorTarjeta n3 = new OrdenadorTarjeta(2,"mi Ordenador 3");
        listaOrdenadores.add(n3);
        OrdenadorTarjeta n4 = new OrdenadorTarjeta(2,"mi Ordenador 4");
        listaOrdenadores.add(n4);
        OrdenadorTarjeta n5 = new OrdenadorTarjeta(2,"mi Ordenador 5");
        listaOrdenadores.add(n5);
        OrdenadorTarjeta n6 = new OrdenadorTarjeta(2,"mi Ordenador 6");
        listaOrdenadores.add(n6);
        OrdenadorTarjeta n7 = new OrdenadorTarjeta(2,"mi Ordenador 7");
        listaOrdenadores.add(n7);
    }

    public List<OrdenadorTarjeta> getListaOrdenadores() {
        return listaOrdenadores;
    }

    public int añadirId(){
        return contador++;
    }
    public void borrar(OrdenadorTarjeta ordenador){
        listaOrdenadores.remove(ordenador);
    }
}
