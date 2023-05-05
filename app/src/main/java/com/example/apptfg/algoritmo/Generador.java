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
import com.example.apptfg.regla.Reglas;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

public class Generador {
    private Reglas reglas;
    private String formFactorPlaca;
    private String forFactorRam;
    private String shocketCpu;
    private String velMaxRam;
    private String memMaxRam;

    private Ordenador ordenador;

    public Generador(Reglas regla) {
        this.reglas = regla;
        ordenador = new Ordenador();
    }


    private void sacarInformacionCompatibilidades() {

    }

    private MemoriaRam sacarMemoriaRam() {

        return null;
    }

    private Procesador sacarCpu() {

        return null;
    }

    private void sacarPlacaBase() {
        Gson gson = new Gson();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        db.collection("placas_base")
                .whereLessThanOrEqualTo("precio", reglas.getPRECIO_MAX())
                .whereGreaterThanOrEqualTo("precio", reglas.getPRECIO_MIN())
                .orderBy("precio")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNN");
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            PlacaBase placaBase = document.toObject(PlacaBase.class);
                            //PlacaBase placaBase = gson.fromJson(document.getData().toString(), PlacaBase.class);
                            ordenador.setPlacaBase(placaBase);
                        } else {
                            System.out.println("No se encontró ningún documento que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        System.out.println("hubo un error al realizar la consulta");
                    }

                });
    }

    private Caja sacarCaja() {

        return null;
    }

    private DiscoDuro sacarDiscoDuro() {

        return null;
    }

    private Disipador sacarDisipador() {

        return null;
    }

    private FuenteAlimentacion sacarPsu() {

        return null;
    }

    private TarjetaGrafica sacarGpu() {
        TarjetaGrafica t = null;

        return t;
    }

    public void generarOrdenador() {
        sacarInformacionCompatibilidades();
        sacarPlacaBase();
//        TarjetaGrafica tarjetaGrafica = sacarGpu();
//        FuenteAlimentacion fuenteAlimentacion = sacarPsu();
//        Disipador disipador = sacarDisipador();
//        DiscoDuro discoDuro = sacarDiscoDuro();
//        Caja caja = sacarCaja();
//        Procesador procesador = sacarCpu();
//        MemoriaRam memoriaRam = sacarMemoriaRam();
//        Ordenador ordenador = new Ordenador(caja, discoDuro, disipador, fuenteAlimentacion, memoriaRam, placaBase, procesador, tarjetaGrafica);
//        return ordenador;
    }
}
