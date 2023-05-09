package com.example.apptfg.algoritmo;

import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.exception.MuñonException;
import com.example.apptfg.gestor.GestorFirebase;
import com.example.apptfg.regla.Reglas;
import com.example.apptfg.regla.Usos;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Generador.
 */
public class Generador {
    private Reglas reglas;
    private Ordenador ordenador;
    private GestorFirebase gestorFirestore;


    public Generador(Usos uso, int minimoEstablecidoUsuario, int maximoEstablecidoUsuario) {
        reglas = new Reglas(uso ,minimoEstablecidoUsuario,maximoEstablecidoUsuario);
        ordenador = new Ordenador();
        inicializarGestor();
    }

    public void inicializarGestor(){
        reglas.rellenarCamos(new Reglas.ReglasCallback() {
            @Override
            public void onReglasObtenidas(Reglas reglas) {
                gestorFirestore = new GestorFirebase(reglas);
                sacarPlaca();
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    /**
     * obtains a motherboard that is the base to build a pc, then it calls the continuarGenerando() method
     */
    public void sacarPlaca() {
        gestorFirestore.sacarPlacaBase(new GestorFirebase.PlacaBaseCallback() {
            @Override
            public void onPlacaBaseObtenida(PlacaBase placaBase) {
                ordenador.setPlacaBase(placaBase);
                sacarCpu();
            }
            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    /**
     * Obtains a cpu compatible with the motherboard
      */
    private void sacarCpu() {
        if(reglas.getPRECIO_MAX_GPU() == 0)
            gestorFirestore.sacarCpuConGrafica(new GestorFirebase.ProcesadorCallback() {
                @Override
                public void onProcesadorObtenido(Procesador procesador) {
                    ordenador.setProcesador(procesador);
                    ordenador.setTarjetaGrafica(null);
                    sacarPsu();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                }
            });
        else
            gestorFirestore.sacarCpuNormal(new GestorFirebase.ProcesadorCallback() {
                @Override
                public void onProcesadorObtenido(Procesador procesador) {
                    ordenador.setProcesador(procesador);
                    sacarGpu();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                }
            });
    }

    private void sacarGpu() {
        gestorFirestore.sacarGpu(new GestorFirebase.GpuCallback() {
            @Override
            public void onGpuObtenida(TarjetaGrafica tarjetaGrafica) {
                ordenador.setTarjetaGrafica(tarjetaGrafica);
                sacarPsu();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    private void sacarPsu() {
        gestorFirestore.sacarPsu(new GestorFirebase.PsuCallback() {
            @Override
            public void onPsuObtenida(FuenteAlimentacion fuenteAlimentacion) {
                ordenador.setFuenteAlimentacion(fuenteAlimentacion);
                sacarDisipador();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    private void sacarDisipador() {
        gestorFirestore.sacarDisipador(new GestorFirebase.DisipadorCallback() {
            @Override
            public void onCoolerObtenido(Disipador disipador) {
                ordenador.setDisipador(disipador);
                sacarDiscoDuro();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    private void sacarDiscoDuro() {
        gestorFirestore.sacarDiscoDuro(new GestorFirebase.DiscoDuroCallback() {
            @Override
            public void onDiscoDuroObtenido(DiscoDuro discoDuro) {
                ordenador.setDiscoDuro(discoDuro);
                sacarCaja();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    private void sacarCaja() {
        gestorFirestore.sacarCaja(new GestorFirebase.CajaCallback() {
            @Override
            public void onCajaObtenida(Caja caja) {
                ordenador.setCaja(caja);
                sacarMemoriaRam();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }

    private void sacarMemoriaRam() {
        gestorFirestore.sacarMemoriaRam(new GestorFirebase.RamCallback() {
            @Override
            public void onRamObtenida(MemoriaRam ram) {
                ordenador.setMemoriaRam(ram);
                System.out.println(ordenador);
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
            }
        });
    }
}