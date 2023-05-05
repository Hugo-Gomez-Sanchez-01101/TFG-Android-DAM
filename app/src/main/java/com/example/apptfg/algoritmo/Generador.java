package com.example.apptfg.algoritmo;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.regla.Reglas;
import com.google.firebase.auth.FirebaseAuth;
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

    public Generador(Reglas regla) {
        this.reglas = regla;
    }


    private void sacarInformacionCompatibilidades() {

    }

    private MemoriaRam sacarMemoriaRam() {

        return null;
    }

    private Procesador sacarCpu() {

        return null;
    }

    private PlacaBase sacarPlacaBase() {
        class PlacaBaseWrapper {
            private PlacaBase placaBase;
            public PlacaBase getPlacaBase() {
                return placaBase;
            }

            public void setPlacaBase(PlacaBase placaBase) {
                this.placaBase = placaBase;
            }
        }
        PlacaBaseWrapper placaBaseWrapper = new PlacaBaseWrapper();
        Gson gson = new Gson();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("placas_base")
                .whereLessThanOrEqualTo("precio", reglas.getPRECIO_MAX())
                .whereGreaterThanOrEqualTo("precio", reglas.getPRECIO_MIN())
                .orderBy("precio")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            PlacaBase placaBase = document.toObject(PlacaBase.class);
                            //PlacaBase placaBase = gson.fromJson(document.getData().toString(), PlacaBase.class);
                            placaBaseWrapper.setPlacaBase(placaBase);
                        } else {
                            System.out.println("No se encontró ningún documento que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        System.out.println("hubo un error al realizar la consulta");
                    }

                });
        return placaBaseWrapper.getPlacaBase();
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
        PlacaBase placaBase = sacarPlacaBase();
        System.out.println(placaBase);
        System.out.println("vfvvvfddvdfvfdAAAAAAAAAAAAAAAAAAa");
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
