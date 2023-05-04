package com.example.apptfg.entidad;

public class Ordenador {
    private Caja caja;
    private DiscoDuro discoDuro;
    private Disipador disipador;
    private FuenteAlimentacion fuenteAlimentacion;
    private MemoriaRam memoriaRam;
    private PlacaBase placaBasem;
    private Procesador procesador;
    private TarjetaGrafica tarjetaGrafica;

    public Ordenador(Caja caja, DiscoDuro discoDuro, Disipador disipador, FuenteAlimentacion fuenteAlimentacion, MemoriaRam memoriaRam, PlacaBase placaBasem, Procesador procesador, TarjetaGrafica tarjetaGrafica) {
        this.caja = caja;
        this.discoDuro = discoDuro;
        this.disipador = disipador;
        this.fuenteAlimentacion = fuenteAlimentacion;
        this.memoriaRam = memoriaRam;
        this.placaBasem = placaBasem;
        this.procesador = procesador;
        this.tarjetaGrafica = tarjetaGrafica;
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

    public PlacaBase getPlacaBasem() {
        return placaBasem;
    }

    public void setPlacaBasem(PlacaBase placaBasem) {
        this.placaBasem = placaBasem;
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
}
