package com.example.apptfg.entidad;

import com.example.apptfg.regla.Usos;

import java.io.Serializable;

public class Ordenador implements Serializable {
    private String nombre;
    private Enum<Usos> uso;
    private Caja caja;
    private DiscoDuro discoDuro;
    private Disipador disipador;
    private FuenteAlimentacion fuenteAlimentacion;
    private MemoriaRam memoriaRam;
    private PlacaBase placaBase;
    private Procesador procesador;
    private TarjetaGrafica tarjetaGrafica;

    public Ordenador(Caja caja, DiscoDuro discoDuro, Disipador disipador, FuenteAlimentacion fuenteAlimentacion, MemoriaRam memoriaRam, PlacaBase placaBase, Procesador procesador, TarjetaGrafica tarjetaGrafica, Enum<Usos> uso) {
        this.caja = caja;
        this.discoDuro = discoDuro;
        this.disipador = disipador;
        this.fuenteAlimentacion = fuenteAlimentacion;
        this.memoriaRam = memoriaRam;
        this.placaBase = placaBase;
        this.procesador = procesador;
        this.tarjetaGrafica = tarjetaGrafica;
        this.uso = uso;
    }

    public double getPrice(){
        double total = 0;
        total += caja.getPrice_usd();
        total += discoDuro.getPrice_usd();
        total += disipador.getPrice_usd();
        total += fuenteAlimentacion.getPrice_usd();
        total += memoriaRam.getPrice_usd();
        total += placaBase.getPrecio();
        total += procesador.getPrice();
        try {
            total += tarjetaGrafica.getPrice_usd();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        return total;
    }

    public Ordenador() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Enum<Usos> getUso() {
        return uso;
    }

    public void setUso(Enum<Usos> uso) {
        this.uso = uso;
    }

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
