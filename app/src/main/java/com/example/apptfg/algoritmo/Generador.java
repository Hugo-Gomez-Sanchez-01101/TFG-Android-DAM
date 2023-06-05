package com.example.apptfg.algoritmo;

import com.example.apptfg.PreciosActivity;
import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.Componente;
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

import org.checkerframework.checker.compilermsgs.qual.CompilerMessageKey;

public class Generador{
    private Reglas reglas;
    private Ordenador ordenador;
    private PlacaBase placaBaseMain;
    private int regenerar;
    private PreciosActivity preciosActivity;

    public Generador(Enum<Usos> uso, int minimoEstablecidoUsuario, int maximoEstablecidoUsuario, PreciosActivity preciosActivity) {
        this.preciosActivity = preciosActivity;
        reglas = new Reglas(uso ,minimoEstablecidoUsuario,maximoEstablecidoUsuario);
        GestorFirebase.getInstance().setReglas(reglas);
        ordenador = new Ordenador();
        regenerar = 1;
    }

    public void comenzar() {
        reglas.rellenarCamos(new Reglas.ReglasCallback() {
            @Override
            public void onReglasObtenidas(Reglas reglas) {
                sacarPlaca();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
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
        GestorFirebase.getInstance().sacarPlacaBase(null, new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                PlacaBase placaBase = (PlacaBase) componente;
                ordenador.setPlacaBase(placaBase);
                placaBaseMain = placaBase;
                sacarCpu();
            }
            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }

    /**
     * Obtains a cpu compatible with the motherboard
      */
    private void sacarCpu() {
        if(reglas.getPRECIO_MAX_GPU() == 0)
            GestorFirebase.getInstance().sacarCpuConGrafica(placaBaseMain, new GestorFirebase.ComponenteCallback() {
                @Override
                public void onComponenteObtenido(Componente componente) {
                    Procesador procesador = (Procesador) componente;
                    ordenador.setProcesador(procesador);
                    ordenador.setTarjetaGrafica(null);
                    sacarPsu();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    preciosActivity.mostrarError();
                }
            });
        else
            GestorFirebase.getInstance().sacarCpuNormal(placaBaseMain, new GestorFirebase.ComponenteCallback() {
                @Override
                public void onComponenteObtenido(Componente componente) {
                    Procesador procesador = (Procesador) componente;
                    ordenador.setProcesador(procesador);
                    sacarGpu();
                }

                @Override
                public void onError(String errorMessage) {
                    System.out.println(errorMessage);
                    preciosActivity.mostrarError();
                }
            });
    }

    private void sacarGpu() {
        GestorFirebase.getInstance().sacarGpu(new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                TarjetaGrafica tarjetaGrafica = (TarjetaGrafica) componente;
                ordenador.setTarjetaGrafica(tarjetaGrafica);
                sacarPsu();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarPsu() {
        GestorFirebase.getInstance().sacarPsu(new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                FuenteAlimentacion fuenteAlimentacion = (FuenteAlimentacion) componente;
                ordenador.setFuenteAlimentacion(fuenteAlimentacion);
                sacarDisipador();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarDisipador() {
        GestorFirebase.getInstance().sacarDisipador(new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                Disipador disipador = (Disipador) componente;
                ordenador.setDisipador(disipador);
                sacarDiscoDuro();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarDiscoDuro() {
        GestorFirebase.getInstance().sacarDiscoDuro(new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                DiscoDuro discoDuro = (DiscoDuro) componente;
                ordenador.setDiscoDuro(discoDuro);
                sacarCaja();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }

    private void sacarCaja() {
        GestorFirebase.getInstance().sacarCaja(placaBaseMain, new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                Caja caja = (Caja) componente;
                ordenador.setCaja(caja);
                sacarMemoriaRam();
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                if(regenerar == 1)
                    regenerar();
                else
                    preciosActivity.mostrarError();
            }
        });
    }

    private void sacarMemoriaRam() {
        GestorFirebase.getInstance().sacarMemoriaRam(placaBaseMain, new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                MemoriaRam ram = (MemoriaRam) componente;
                ordenador.setMemoriaRam(ram);
                System.out.println(ordenador);
                preciosActivity.terminarGenerar(ordenador);
            }

            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }

    private void regenerar() {
        ordenador = new Ordenador();
        GestorFirebase.getInstance().sacarPlacaBase("ATX",new GestorFirebase.ComponenteCallback() {
            @Override
            public void onComponenteObtenido(Componente componente) {
                PlacaBase placaBase = (PlacaBase) componente;
                ordenador.setPlacaBase(placaBase);
                placaBaseMain = placaBase;
                sacarCpu();
            }
            @Override
            public void onError(String errorMessage) {
                System.out.println(errorMessage);
                preciosActivity.mostrarError();
            }
        });
    }
}