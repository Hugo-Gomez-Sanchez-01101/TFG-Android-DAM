package com.example.apptfg.entidad;

import java.io.Serializable;

public class PlacaBase implements Serializable {
    private int memoria_maxima;
    private double precio;
    private String puertos_almacenamiento;
    private String factor_forma_memoria;
    private String formato;
    private String puertos_usb;
    private String puertos_red;
    private String audio;
    private String socket;
    private String chipset;
    private String nombre;
    private int velocidad_max_memoria;

    public PlacaBase(int memoriaMaxima, double precio, String puertosAlmacenamiento, String factorFormaMemoria, String formato, String puertosUsb, String puertosRed, String audio, String shocket, String chipset, String nombre, int velocidadMaxima) {
        this.memoria_maxima = memoriaMaxima;
        this.precio = precio;
        this.puertos_almacenamiento = puertosAlmacenamiento;
        this.factor_forma_memoria = factorFormaMemoria;
        this.formato = formato;
        this.puertos_usb = puertosUsb;
        this.puertos_red = puertosRed;
        this.audio = audio;
        this.socket = shocket;
        this.chipset = chipset;
        this.nombre = nombre;
        this.velocidad_max_memoria = velocidadMaxima;
    }

    public int getMemoria_maxima() {
        return memoria_maxima;
    }

    public PlacaBase() {
    }

    public void setMemoria_maxima(int memoria_maxima) {
        this.memoria_maxima = memoria_maxima;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPuertos_almacenamiento() {
        return puertos_almacenamiento;
    }

    public void setPuertos_almacenamiento(String puertos_almacenamiento) {
        this.puertos_almacenamiento = puertos_almacenamiento;
    }

    public String getFactor_forma_memoria() {
        return factor_forma_memoria;
    }

    public void setFactor_forma_memoria(String factor_forma_memoria) {
        this.factor_forma_memoria = factor_forma_memoria;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getPuertos_usb() {
        return puertos_usb;
    }

    public void setPuertos_usb(String puertos_usb) {
        this.puertos_usb = puertos_usb;
    }

    public String getPuertos_red() {
        return puertos_red;
    }

    public void setPuertos_red(String puertos_red) {
        this.puertos_red = puertos_red;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidad_max_memoria() {
        return velocidad_max_memoria;
    }

    public void setVelocidad_max_memoria(int velocidad_max_memoria) {
        this.velocidad_max_memoria = velocidad_max_memoria;
    }

    @Override
    public String toString() {
        return "PlacaBase{" +
                "memoria_maxima=" + memoria_maxima +
                ", precio=" + precio +
                ", puertos_almacenamiento='" + puertos_almacenamiento + '\'' +
                ", factor_forma_memoria='" + factor_forma_memoria + '\'' +
                ", formato='" + formato + '\'' +
                ", puertos_usb='" + puertos_usb + '\'' +
                ", puertos_red='" + puertos_red + '\'' +
                ", audio='" + audio + '\'' +
                ", socket='" + socket + '\'' +
                ", chipset='" + chipset + '\'' +
                ", nombre='" + nombre + '\'' +
                ", velocidad_max_memoria=" + velocidad_max_memoria +
                '}';
    }
}
