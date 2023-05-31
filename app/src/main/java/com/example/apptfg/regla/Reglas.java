package com.example.apptfg.regla;

import com.example.apptfg.algoritmo.Generador;
import com.example.apptfg.entidad.PlacaBase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;
import java.util.Map;

public class Reglas {
    private String MAX_CPU_INTEL;
    private String MIN_CPU_INTEL;
    private String MAX_CPU_AMD;
    private String MIN_CPU_AMD;

    //porcentajes
    private double PRECIO_MAX_RAM;
    private double PRECIO_MIN_RAM;
    private double PRECIO_MAX_CPU;
    private double PRECIO_MIN_CPU;
    private double PRECIO_MAX_PLACA;
    private double PRECIO_MIN_PLACA;
    private double PRECIO_MAX_COOLER;
    private double PRECIO_MIN_COOLER;
    private double PRECIO_MAX_DISCO;
    private double PRECIO_MIN_DISCO;
    private double PRECIO_MAX_PSU;
    private double PERCIO_MIN_PSU;
    private double PRECIO_MAX_CAJA;
    private double PRECIO_MIN_CAJA;
    private double PRECIO_MAX_GPU;
    private double PRECIO_MIN_GPU;

    private String uso;

    //Precio en euros
    private double PRECIO_MAX;
    private double PRECIO_MIN;
    private double maximoEstablecidoUsuario;

    public interface ReglasCallback {
        void onReglasObtenidas(Reglas reglas);

        void onError(String errorMessage);
    }

    public Reglas(Enum<Usos> uso, double minimoEstablecidoUsuario, double maximoEstablecidoUsuario) {
        PRECIO_MIN = minimoEstablecidoUsuario;
        this.maximoEstablecidoUsuario = maximoEstablecidoUsuario;
        this.uso = uso.toString().toLowerCase(Locale.ROOT);
    }

    public void rellenarCamos(ReglasCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("reglas")
                .document(uso)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> data = documentSnapshot.getData();
                        PRECIO_MAX = (Long) data.get("PRECIO_MAX");
                        if (maximoEstablecidoUsuario < PRECIO_MAX)
                            PRECIO_MAX = maximoEstablecidoUsuario;


                        MAX_CPU_AMD = (String) data.get("MAX_CPU_AMD");
                        MAX_CPU_INTEL = (String) data.get("MAX_CPU_INTEL");
                        MIN_CPU_AMD = (String) data.get("MIN_CPU_AMD");
                        MIN_CPU_INTEL = (String) data.get("MIN_CPU_INTEL");

                        double porcentajeCooler = (double) data.get("PORCENTAJE_MAX_COOLER");
                        PRECIO_MAX_COOLER = sacarPrecioDePorcentaje(porcentajeCooler, PRECIO_MAX);
                        PRECIO_MIN_COOLER = sacarPrecioDePorcentaje(porcentajeCooler, PRECIO_MIN);

                        double porcentajeCpu = (double) data.get("PORCENTAJE_MAX_CPU");
                        PRECIO_MAX_CPU = sacarPrecioDePorcentaje(porcentajeCpu, PRECIO_MAX);
                        PRECIO_MIN_CPU = sacarPrecioDePorcentaje(porcentajeCpu, PRECIO_MIN);

                        double porcentajeDisco = (double) data.get("PORCENTAJE_MAX_DISCO");
                        PRECIO_MAX_DISCO = sacarPrecioDePorcentaje(porcentajeDisco, PRECIO_MAX);
                        PRECIO_MIN_DISCO = sacarPrecioDePorcentaje(porcentajeDisco, PRECIO_MIN);

                        double porcentajeGpu;
                        try {
                            porcentajeGpu = (double) data.get("PORCENTAJE_MAX_GPU");
                        }catch (ClassCastException e){
                            porcentajeGpu = (long) data.get("PORCENTAJE_MAX_GPU");
                        }
                        PRECIO_MAX_GPU = sacarPrecioDePorcentaje(porcentajeGpu, PRECIO_MAX);
                        PRECIO_MIN_GPU = sacarPrecioDePorcentaje(porcentajeGpu, PRECIO_MIN);

                        double porcentajePlaca = (double) data.get("PORCENTAJE_MAX_PLACA");
                        PRECIO_MAX_PLACA = sacarPrecioDePorcentaje(porcentajePlaca, PRECIO_MAX);
                        PRECIO_MIN_PLACA = sacarPrecioDePorcentaje(porcentajePlaca, PRECIO_MIN);

                        double porcentajePsu = (double) data.get("PORCENTAJE_MAX_PSU");
                        PRECIO_MAX_PSU = sacarPrecioDePorcentaje(porcentajePsu, PRECIO_MAX);
                        PERCIO_MIN_PSU = sacarPrecioDePorcentaje(porcentajePsu, PRECIO_MIN);

                        double porcentajeRam = (double) data.get("PORCENTAJE_MAX_RAM");
                        PRECIO_MAX_RAM = sacarPrecioDePorcentaje(porcentajeRam, PRECIO_MAX);
                        PRECIO_MIN_RAM = sacarPrecioDePorcentaje(porcentajeRam, PRECIO_MIN);

                        double porcentajeCaja = (double) data.get("PORCENTAJE_MAX_CAJA");
                        PRECIO_MAX_CAJA = sacarPrecioDePorcentaje(porcentajeCaja, PRECIO_MAX);
                        PRECIO_MIN_CAJA = sacarPrecioDePorcentaje(porcentajeCaja, PRECIO_MIN);

                        callback.onReglasObtenidas(this);
                    }
                });
    }

    private double sacarPrecioDePorcentaje(double porcentaje, double precioMax) {
        double resultado;
        try {
            resultado = porcentaje * precioMax / 100;
        } catch (ArithmeticException e) {
            resultado = 0;
        }
        return resultado;
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

    public double getPRECIO_MIN_RAM() {
        return PRECIO_MIN_RAM;
    }

    public double getPRECIO_MIN_CPU() {
        return PRECIO_MIN_CPU;
    }

    public double getPRECIO_MIN_PLACA() {
        return PRECIO_MIN_PLACA;
    }

    public double getPRECIO_MIN_COOLER() {
        return PRECIO_MIN_COOLER;
    }

    public double getPRECIO_MIN_DISCO() {
        return PRECIO_MIN_DISCO;
    }

    public double getPERCIO_MIN_PSU() {
        return PERCIO_MIN_PSU;
    }

    public double getPRECIO_MIN_CAJA() {
        return PRECIO_MIN_CAJA;
    }

    public double getPRECIO_MIN_GPU() {
        return PRECIO_MIN_GPU;
    }

    public String getUso() {
        return uso;
    }
}
