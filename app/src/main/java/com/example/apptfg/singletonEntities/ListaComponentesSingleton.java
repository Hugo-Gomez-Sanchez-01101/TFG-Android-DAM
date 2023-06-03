package com.example.apptfg.singletonEntities;

import com.example.apptfg.VerComponenteActivity;
import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.gestor.GestorFirebase;
import com.example.apptfg.regla.Usos;

import java.util.ArrayList;
import java.util.List;

public class ListaComponentesSingleton {
    private static ListaComponentesSingleton instance;
    private List<Componente> listaComponentesSingleton;

    private ListaComponentesSingleton(){
        super();
    }

    public static ListaComponentesSingleton getInstance() {
        if(instance == null){
            instance = new ListaComponentesSingleton();
        }
        return instance;
    }

    public void inicializar(Ordenador ordenador, Componente componente, Enum<Usos> uso, VerComponenteActivity vistaComponenteActivity){
        vistaComponenteActivity.carga();
        listaComponentesSingleton = new ArrayList<>();
        if(componente instanceof Procesador && uso != Usos.OFIMATICA) {
            GestorFirebase.getInstance().sacarListaCpuNormal(ordenador.getPlacaBase(), new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                }
            });
        } else if (componente instanceof Procesador && uso == Usos.OFIMATICA){
            GestorFirebase.getInstance().sacarListaCpuConGrafica(ordenador.getPlacaBase(), new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        } else if(componente instanceof MemoriaRam){
            GestorFirebase.getInstance().sacarListaRam(ordenador.getPlacaBase(), new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        } else if(componente instanceof DiscoDuro) {
            GestorFirebase.getInstance().sacarListaDiscoDuro(new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        } else if(componente instanceof Caja) {
            GestorFirebase.getInstance().sacarListaCaja(ordenador.getPlacaBase(), new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        } else if(componente instanceof Disipador) {
            GestorFirebase.getInstance().sacarListaDisipador(new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        } else if(componente instanceof FuenteAlimentacion) {
            GestorFirebase.getInstance().sacarListaPsu(ordenador.getPlacaBase(), new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        } else if(componente instanceof TarjetaGrafica) {
            GestorFirebase.getInstance().sacarListaGpu(new GestorFirebase.ListaComponentesCallback() {
                @Override
                public void onListaComponentesObtenidos(List<Componente> listaComponentes) {
                    listaComponentesSingleton = listaComponentes;
                    vistaComponenteActivity.irModificarComponentes();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    vistaComponenteActivity.terminarCarga();
                }
            });
        }
    }

    public List<Componente> getListaComponentesSingleton() {
        return listaComponentesSingleton;
    }
}
