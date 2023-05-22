package com.example.apptfg.algoritmo;

import android.content.Context;

import com.example.apptfg.PreciosActivity;
import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.gestor.GestorFirebase;
import com.example.apptfg.regla.Reglas;
import com.example.apptfg.regla.Usos;

public class Generador{
    private Reglas reglas;
    private Ordenador ordenador;
    private PlacaBase placaBaseMain;
    private PreciosActivity preciosActivity;

    public Generador(Enum<Usos> uso, int minimoEstablecidoUsuario, int maximoEstablecidoUsuario, PreciosActivity preciosActivity) {
        this.preciosActivity = preciosActivity;
        reglas = new Reglas(uso ,minimoEstablecidoUsuario,maximoEstablecidoUsuario);
        GestorFirebase.getInstance().setReglas(reglas);
        ordenador = new Ordenador();
    }

    public void comenzar() {
        reglas.rellenarCamos(new Reglas.ReglasCallback() {
            @Override
            public void onReglasObtenidas(Reglas reglas) {
                sacarPlaca();
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    public void setReglas(Reglas reglas){
        this.reglas = reglas;
    }

    /**
     * obtains a motherboard that is the base to build a pc, then it calls the continuarGenerando() method
     */
    public void sacarPlaca() {
        GestorFirebase.getInstance().sacarPlacaBase(new GestorFirebase.PlacaBaseCallback() {
            @Override
            public void onPlacaBaseObtenida(PlacaBase placaBase) {
                ordenador.setPlacaBase(placaBase);
                placaBaseMain = placaBase;
                sacarCpu();
            }
            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    /**
     * Obtains a cpu compatible with the motherboard
      */
    private void sacarCpu() {
        if(reglas.getPRECIO_MAX_GPU() == 0)
            GestorFirebase.getInstance().sacarCpuConGrafica(placaBaseMain, new GestorFirebase.ProcesadorCallback() {
                @Override
                public void onProcesadorObtenido(Procesador procesador) {
                    ordenador.setProcesador(procesador);
                    ordenador.setTarjetaGrafica(null);
                    sacarPsu();
                }

                @Override
                public void onError(String errorMessage) {
                    preciosActivity.mostrarError();
                }
            });
        else
            GestorFirebase.getInstance().sacarCpuNormal(placaBaseMain, new GestorFirebase.ProcesadorCallback() {
                @Override
                public void onProcesadorObtenido(Procesador procesador) {
                    ordenador.setProcesador(procesador);
                    sacarGpu();
                }

                @Override
                public void onError(String errorMessage) {
                    preciosActivity.mostrarError();
                }
            });
    }

    private void sacarGpu() {
        GestorFirebase.getInstance().sacarGpu(new GestorFirebase.GpuCallback() {
            @Override
            public void onGpuObtenida(TarjetaGrafica tarjetaGrafica) {
                ordenador.setTarjetaGrafica(tarjetaGrafica);
                sacarPsu();
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarPsu() {
        GestorFirebase.getInstance().sacarPsu(new GestorFirebase.PsuCallback() {
            @Override
            public void onPsuObtenida(FuenteAlimentacion fuenteAlimentacion) {
                ordenador.setFuenteAlimentacion(fuenteAlimentacion);
                sacarDisipador();
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarDisipador() {
        GestorFirebase.getInstance().sacarDisipador(new GestorFirebase.DisipadorCallback() {
            @Override
            public void onCoolerObtenido(Disipador disipador) {
                ordenador.setDisipador(disipador);
                sacarDiscoDuro();
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarDiscoDuro() {
        GestorFirebase.getInstance().sacarDiscoDuro(new GestorFirebase.DiscoDuroCallback() {
            @Override
            public void onDiscoDuroObtenido(DiscoDuro discoDuro) {
                ordenador.setDiscoDuro(discoDuro);
                sacarCaja();
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarCaja() {
        GestorFirebase.getInstance().sacarCaja(placaBaseMain, new GestorFirebase.CajaCallback() {
            @Override
            public void onCajaObtenida(Caja caja) {
                ordenador.setCaja(caja);
                sacarMemoriaRam();
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarMemoriaRam() {
        GestorFirebase.getInstance().sacarMemoriaRam(placaBaseMain, new GestorFirebase.RamCallback() {
            @Override
            public void onRamObtenida(MemoriaRam ram) {
                ordenador.setMemoriaRam(ram);
                System.out.println(ordenador);
                preciosActivity.terminarGenerar(ordenador);
            }

            @Override
            public void onError(String errorMessage) {
                preciosActivity.mostrarError();
            }
        });
    }


}