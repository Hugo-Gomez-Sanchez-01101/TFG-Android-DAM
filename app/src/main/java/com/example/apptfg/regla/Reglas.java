package com.example.apptfg.regla;

import android.security.keystore.UserPresenceUnavailableException;

public class Reglas {
    private String    MAX_CPU_INTEL;
    private String    MIN_CPU_INTEL;
    private String    MAX_CPU_AMD;
    private String    MIN_CPU_AMD;

    //porcentajes
    private double    PRECIO_MAX_RAM;
    private double    PRECIO_MAX_CPU;
    private double    PRECIO_MAX_PLACA;
    private double    PRECIO_MAX_COOLER;
    private double    PRECIO_MAX_DISCO;
    private double    PRECIO_MAX_PSU;
    private double    PRECIO_MAX_CAJA;
    private double    PRECIO_MAX_GPU;

    //Precio en euros
    private double    PRECIO_MAX_CATEGORIA;

    public Reglas(Usos uso){
        switch (uso){
            case GAMING:
                rellenarGaming();
                break;
            case OFIMATICA:
                rellenarOfimatica();
                break;
            case GAMING_PROFESIONAL:
                rellenarGamingProfesional();
                break;
            case EDICION:
                rellenarEdicion();
                break;
        }
    }

    public void rellenarGamingProfesional(){
        this.MAX_CPU_INTEL =        "Intel Core i9";
        this.MIN_CPU_INTEL =        "Intel Core i7";
        this.MAX_CPU_AMD =          "AMD Ryzen 9";
        this.MIN_CPU_AMD =          "AMD Ryzen 7";
        this.PRECIO_MAX_RAM =       0.00;
        this.PRECIO_MAX_CPU =       0.00;
        this.PRECIO_MAX_PLACA =     0.00;
        this.PRECIO_MAX_COOLER =    0.00;
        this.PRECIO_MAX_DISCO =     0.00;
        this.PRECIO_MAX_PSU =       0.00;
        this.PRECIO_MAX_CAJA =      0.00;
        this.PRECIO_MAX_GPU =       0.00;
        this.PRECIO_MAX_CATEGORIA = 0.00;
    }

    public void rellenarEdicion(){
        this.MAX_CPU_INTEL =        "";
        this.MIN_CPU_INTEL =        "";
        this.MAX_CPU_AMD =          "";
        this.MIN_CPU_AMD =          "";
        this.PRECIO_MAX_RAM =       0.00;
        this.PRECIO_MAX_CPU =       0.00;
        this.PRECIO_MAX_PLACA =     0.00;
        this.PRECIO_MAX_COOLER =    0.00;
        this.PRECIO_MAX_DISCO =     0.00;
        this.PRECIO_MAX_PSU =       0.00;
        this.PRECIO_MAX_CAJA =      0.00;
        this.PRECIO_MAX_GPU =       0.00;
        this.PRECIO_MAX_CATEGORIA = 0.00;
    }

    public void rellenarGaming(){
        this.MAX_CPU_INTEL =        "Intel Core i7";
        this.MIN_CPU_INTEL =        "Intel Core i5";
        this.MAX_CPU_AMD =          "AMD Ryzen 7";
        this.MIN_CPU_AMD =          "AMD Ryzen 5";
        this.PRECIO_MAX_RAM =       8.15;
        this.PRECIO_MAX_CPU =       26.08;
        this.PRECIO_MAX_PLACA =     14.67;
        this.PRECIO_MAX_COOLER =    5.43;
        this.PRECIO_MAX_DISCO =     6.52;
        this.PRECIO_MAX_PSU =       7.60;
        this.PRECIO_MAX_CAJA =      7.60;
        this.PRECIO_MAX_GPU =       23.91;
        this.PRECIO_MAX_CATEGORIA = 920;
    }

    public void rellenarOfimatica(){
        this.MAX_CPU_INTEL =        "Intel Core i5";
        this.MIN_CPU_INTEL =        "Intel Core i3";
        this.MAX_CPU_AMD =          "AMD Ryzen 5";
        this.MIN_CPU_AMD =          "AMD Ryzen 3";
        this.PRECIO_MAX_RAM =       9.27;
        this.PRECIO_MAX_CPU =       31.95;
        this.PRECIO_MAX_PLACA =     21.64;
        this.PRECIO_MAX_COOLER =    8.24;
        this.PRECIO_MAX_DISCO =     8.24;
        this.PRECIO_MAX_PSU =       10.30;
        this.PRECIO_MAX_CAJA =      10.30;
        this.PRECIO_MAX_GPU =       0.00;
        this.PRECIO_MAX_CATEGORIA = 485.00;
    }

    public String getMAX_CPU_INTEL() {
        return MAX_CPU_INTEL;
    }

    public void setMAX_CPU_INTEL(String MAX_CPU_INTEL) {
        this.MAX_CPU_INTEL = MAX_CPU_INTEL;
    }

    public String getMIN_CPU_INTEL() {
        return MIN_CPU_INTEL;
    }

    public void setMIN_CPU_INTEL(String MIN_CPU_INTEL) {
        this.MIN_CPU_INTEL = MIN_CPU_INTEL;
    }

    public String getMAX_CPU_AMD() {
        return MAX_CPU_AMD;
    }

    public void setMAX_CPU_AMD(String MAX_CPU_AMD) {
        this.MAX_CPU_AMD = MAX_CPU_AMD;
    }

    public String getMIN_CPU_AMD() {
        return MIN_CPU_AMD;
    }

    public void setMIN_CPU_AMD(String MIN_CPU_AMD) {
        this.MIN_CPU_AMD = MIN_CPU_AMD;
    }

    public double getPRECIO_MAX_RAM() {
        return PRECIO_MAX_RAM;
    }

    public void setPRECIO_MAX_RAM(double PRECIO_MAX_RAM) {
        this.PRECIO_MAX_RAM = PRECIO_MAX_RAM;
    }

    public double getPRECIO_MAX_CPU() {
        return PRECIO_MAX_CPU;
    }

    public void setPRECIO_MAX_CPU(double PRECIO_MAX_CPU) {
        this.PRECIO_MAX_CPU = PRECIO_MAX_CPU;
    }

    public double getPRECIO_MAX_PLACA() {
        return PRECIO_MAX_PLACA;
    }

    public void setPRECIO_MAX_PLACA(double PRECIO_MAX_PLACA) {
        this.PRECIO_MAX_PLACA = PRECIO_MAX_PLACA;
    }

    public double getPRECIO_MAX_COOLER() {
        return PRECIO_MAX_COOLER;
    }

    public void setPRECIO_MAX_COOLER(double PRECIO_MAX_COOLER) {
        this.PRECIO_MAX_COOLER = PRECIO_MAX_COOLER;
    }

    public double getPRECIO_MAX_DISCO() {
        return PRECIO_MAX_DISCO;
    }

    public void setPRECIO_MAX_DISCO(double PRECIO_MAX_DISCO) {
        this.PRECIO_MAX_DISCO = PRECIO_MAX_DISCO;
    }

    public double getPRECIO_MAX_PSU() {
        return PRECIO_MAX_PSU;
    }

    public void setPRECIO_MAX_PSU(double PRECIO_MAX_PSU) {
        this.PRECIO_MAX_PSU = PRECIO_MAX_PSU;
    }

    public double getPRECIO_MAX_CAJA() {
        return PRECIO_MAX_CAJA;
    }

    public void setPRECIO_MAX_CAJA(double PRECIO_MAX_CAJA) {
        this.PRECIO_MAX_CAJA = PRECIO_MAX_CAJA;
    }

    public double getPRECIO_MAX_GPU() {
        return PRECIO_MAX_GPU;
    }

    public void setPRECIO_MAX_GPU(double PRECIO_MAX_GPU) {
        this.PRECIO_MAX_GPU = PRECIO_MAX_GPU;
    }

    public double getPRECIO_MAX_CATEGORIA() {
        return PRECIO_MAX_CATEGORIA;
    }

    public void setPRECIO_MAX_CATEGORIA(double PRECIO_MAX_CATEGORIA) {
        this.PRECIO_MAX_CATEGORIA = PRECIO_MAX_CATEGORIA;
    }
}
