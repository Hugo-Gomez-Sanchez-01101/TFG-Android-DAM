package com.example.apptfg.regla;

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
    private double    PRECIO_MAX;
    private double    PRECIO_MIN;

    public Reglas(Usos uso, double minimoEstablecidoUsuario, double maximoEstablecidoUsuario){
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
        PRECIO_MIN = minimoEstablecidoUsuario;
        if(maximoEstablecidoUsuario < PRECIO_MAX)
            PRECIO_MAX = maximoEstablecidoUsuario;
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
        this.PRECIO_MAX = 0.00;
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
        this.PRECIO_MAX = 0.00;
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
        this.PRECIO_MAX = 920;
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
        this.PRECIO_MAX = 485.00;
    }

    public String getMAX_CPU_INTEL() {
        return MAX_CPU_INTEL;
    }

    public String getMIN_CPU_INTEL() {
        return MIN_CPU_INTEL;
    }

    public String getMAX_CPU_AMD() {
        return MAX_CPU_AMD;
    }

    public String getMIN_CPU_AMD() {
        return MIN_CPU_AMD;
    }

    public double getPRECIO_MAX_RAM() {
        return PRECIO_MAX_RAM;
    }

    public double getPRECIO_MAX_CPU() {
        return PRECIO_MAX_CPU;
    }

    public double getPRECIO_MAX_PLACA() {
        return PRECIO_MAX_PLACA;
    }

    public double getPRECIO_MAX_COOLER() {
        return PRECIO_MAX_COOLER;
    }

    public double getPRECIO_MAX_DISCO() {
        return PRECIO_MAX_DISCO;
    }

    public double getPRECIO_MAX_PSU() {
        return PRECIO_MAX_PSU;
    }

    public double getPRECIO_MAX_CAJA() {
        return PRECIO_MAX_CAJA;
    }

    public double getPRECIO_MAX_GPU() {
        return PRECIO_MAX_GPU;
    }

    public double getPRECIO_MAX() {
        return PRECIO_MAX;
    }

    public double getPRECIO_MIN() {
        return PRECIO_MIN;
    }
}
