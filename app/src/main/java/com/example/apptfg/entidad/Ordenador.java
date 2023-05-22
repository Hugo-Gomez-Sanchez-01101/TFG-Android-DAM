package com.example.apptfg.entidad;

import java.io.Serializable;

public class Ordenador implements Serializable {
    private Caja caja;
    private DiscoDuro discoDuro;
    private Disipador disipador;
    private FuenteAlimentacion fuenteAlimentacion;
    private MemoriaRam memoriaRam;
    private PlacaBase placaBase;
    private Procesador procesador;
    private TarjetaGrafica tarjetaGrafica;

    public Ordenador(Caja caja, DiscoDuro discoDuro, Disipador disipador, FuenteAlimentacion fuenteAlimentacion, MemoriaRam memoriaRam, PlacaBase placaBasem, Procesador procesador, TarjetaGrafica tarjetaGrafica) {
        this.caja = caja;
        this.discoDuro = discoDuro;
        this.disipador = disipador;
        this.fuenteAlimentacion = fuenteAlimentacion;
        this.memoriaRam = memoriaRam;
        this.placaBase = placaBasem;
        this.procesador = procesador;
        this.tarjetaGrafica = tarjetaGrafica;
    }

    public Ordenador() {}

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public DiscoDuro getDiscoDuro() {
        return discoDuro;
    }

    public void setDiscoDuro(DiscoDuro discoDuro) {
        this.discoDuro = discoDuro;
    }

    public Disipador getDisipador() {
        return disipador;
    }

    public void setDisipador(Disipador disipador) {
        this.disipador = disipador;
    }

    public FuenteAlimentacion getFuenteAlimentacion() {
        return fuenteAlimentacion;
    }

    public void setFuenteAlimentacion(FuenteAlimentacion fuenteAlimentacion) {
        this.fuenteAlimentacion = fuenteAlimentacion;
    }

    public MemoriaRam getMemoriaRam() {
        return memoriaRam;
    }

    public void setMemoriaRam(MemoriaRam memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public PlacaBase getPlacaBase() {
        return placaBase;
    }

    public void setPlacaBase(PlacaBase placaBase) {
        this.placaBase = placaBase;
    }

    public Procesador getProcesador() {
        return procesador;
    }

    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    public TarjetaGrafica getTarjetaGrafica() {
        return tarjetaGrafica;
    }

    public void setTarjetaGrafica(TarjetaGrafica tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }

    @Override
    public String toString() {
        return "Ordenador{" +
                "caja=" + caja +
                ", discoDuro=" + discoDuro +
                ", disipador=" + disipador +
                ", fuenteAlimentacion=" + fuenteAlimentacion +
                ", memoriaRam=" + memoriaRam +
                ", placaBase=" + placaBase +
                ", procesador=" + procesador +
                ", tarjetaGrafica=" + tarjetaGrafica +
                '}';
    }
}
