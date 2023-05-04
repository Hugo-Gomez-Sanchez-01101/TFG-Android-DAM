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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query;

import java.util.concurrent.ExecutionException;


public class Generador {
    private Reglas reglas;
    private String formFactorPlaca;
    private String forFactorRam;
    private String shocketCpu;
    private String velMaxRam;
    private String memMaxRam;
    private FirebaseFirestore db;

    public Generador(Reglas regla) {
        this.reglas = regla;
    }

    private void inicializarConexionBd() {
        db = FirebaseFirestore.getInstance();
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
        Query query = db.collection("placas_base")
                .whereLessThanOrEqualTo("precio", reglas.getPRECIO_MAX())
                .whereGreaterThanOrEqualTo("precio", reglas.getPRECIO_MIN())
                .orderBy("precio")
                .limit(1);


        ApiFuture<QuerySnapshot> future = (ApiFuture<QuerySnapshot>) query.get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = future.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        PlacaBase placaBase = null;
        for (DocumentSnapshot document : querySnapshot.getDocuments()) {
            placaBase = document.toObject(PlacaBase.class);
        }
        return null;
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

    public Ordenador generarOrdenador() {
        sacarInformacionCompatibilidades();
        TarjetaGrafica tarjetaGrafica = sacarGpu();
        FuenteAlimentacion fuenteAlimentacion = sacarPsu();
        Disipador disipador = sacarDisipador();
        DiscoDuro discoDuro = sacarDiscoDuro();
        Caja caja = sacarCaja();
        PlacaBase placaBase = sacarPlacaBase();
        Procesador procesador = sacarCpu();
        MemoriaRam memoriaRam = sacarMemoriaRam();
        Ordenador ordenador = new Ordenador(caja, discoDuro, disipador, fuenteAlimentacion, memoriaRam, placaBase, procesador, tarjetaGrafica);
        return ordenador;
    }
}
