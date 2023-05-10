package com.example.apptfg.gestor;

import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.regla.Reglas;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Gestor firebase.
 */
public class GestorFirebase {
    private Reglas reglas;
    private PlacaBase placaBase;

    /**
     * Instantiates a new Gestor firebase.
     *
     * @param reglas the reglas
     */
    public GestorFirebase(Reglas reglas) {
        this.reglas = reglas;
    }

    /**
     * The interface Placa base callback.
     */
    public interface PlacaBaseCallback {
        /**
         * On placa base obtenida.
         *
         * @param placaBase the placa base
         */
        void onPlacaBaseObtenida(PlacaBase placaBase);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    /**
     * The interface Procesador callback.
     */
    public interface ProcesadorCallback {
        /**
         * On procesador obtenido.
         *
         * @param procesador the procesador
         */
        void onProcesadorObtenido(Procesador procesador);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    /**
     * The interface Gpu callback.
     */
    public interface GpuCallback {
        /**
         * On gpu obtenida.
         *
         * @param tarjetaGrafica the tarjeta grafica
         */
        void onGpuObtenida(TarjetaGrafica tarjetaGrafica);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    /**
     * The interface Psu callback.
     */
    public interface PsuCallback {
        /**
         * On psu obtenida.
         *
         * @param fuenteAlimentacion the fuente alimentacion
         */
        void onPsuObtenida(FuenteAlimentacion fuenteAlimentacion);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    /**
     * The interface Disipador callback.
     */
    public interface DisipadorCallback {
        /**
         * On cooler obtenido.
         *
         * @param disipador the disipador
         */
        void onCoolerObtenido(Disipador disipador);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    /**
     * The interface Disco duro callback.
     */
    public interface DiscoDuroCallback {
        /**
         * On disco duro obtenido.
         *
         * @param discoDuro the disco duro
         */
        void onDiscoDuroObtenido(DiscoDuro discoDuro);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    /**
     * The interface Caja callback.
     */
    public interface CajaCallback {
        /**
         * On caja obtenida.
         *
         * @param caja the caja
         */
        void onCajaObtenida(Caja caja);

        /**
         * On error.
         *
         * @param errorMessage the error message
         */
        void onError(String errorMessage);
    }

    public interface RamCallback {
        void onRamObtenida(MemoriaRam ram);
        void onError(String errorMessage);
    }

    /**
     * Sacar placa base.
     *
     * @param callback the callback
     */
    public void sacarPlacaBase(PlacaBaseCallback callback) {
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
                            this.placaBase = placaBase;
                            callback.onPlacaBaseObtenida(placaBase);
                        } else {
                            callback.onError("No se encontró ningún documento que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("Hubo un error al realizar la consulta");
                    }
                });
    }

    /**
     * Sacar cpu con grafica.
     *
     * @param callback the callback
     */
    public void sacarCpuConGrafica(ProcesadorCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("procesadores")
                .whereEqualTo("socket", placaBase.getSocket())
                .whereNotEqualTo("integrated_grafics", null)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documentos = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot documento : documentos) {
                        double precio = documento.getDouble("price");
                        if (!(precio >= reglas.getPRECIO_MIN_CPU()) || !(precio <= reglas.getPRECIO_MAX_CPU())) {
                            documentos.remove(documento);
                        }
                    }
                    ordenarPorPrecio(documentos);

                    if(documentos.size() != 0){
                        Procesador procesador = documentos.get(documentos.size() / 2).toObject(Procesador.class);
                        callback.onProcesadorObtenido(procesador);
                    }
                });
    }

    /**
     * Sacar cpu normal.
     *
     * @param callback the callback
     */
    public void sacarCpuNormal(ProcesadorCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("procesadores")
                .whereEqualTo("socket", placaBase.getSocket())
                .whereLessThanOrEqualTo("price", reglas.getPRECIO_MAX_CPU())
                .whereGreaterThanOrEqualTo("price", reglas.getPRECIO_MIN_CPU())
                .orderBy("price")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            Procesador procesador = document.toObject(Procesador.class);
                            callback.onProcesadorObtenido(procesador);
                        } else {
                            System.out.println("No se encontró ningún documento que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        System.out.println("hubo un error al realizar la consulta");
                    }

                });
    }

    /**
     * Sacar gpu.
     *
     * @param callback the callback
     */
    public void sacarGpu(GpuCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("video-card")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_GPU())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_GPU())
                .orderBy("price_usd")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        TarjetaGrafica tarjetaGrafica = document.toObject(TarjetaGrafica.class);
                        callback.onGpuObtenida(tarjetaGrafica);
                    }
                });
    }

    /**
     * Sacar psu.
     *
     * @param callback the callback
     */
    public void sacarPsu(PsuCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("power_supply")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_PSU())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPERCIO_MIN_PSU())
                .orderBy("price_usd")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        FuenteAlimentacion fuenteAlimentacion = document.toObject(FuenteAlimentacion.class);
                        callback.onPsuObtenida(fuenteAlimentacion);
                    }
                });
    }

    /**
     * Sacar disipador.
     *
     * @param callback the callback
     */
    public void sacarDisipador(DisipadorCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("cpu-cooler")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_COOLER())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_COOLER())
                .orderBy("price_usd")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        Disipador disipador = document.toObject(Disipador.class);
                        callback.onCoolerObtenido(disipador);
                    }
                });
    }

    /**
     * Sacar disco duro.
     *
     * @param callback the callback
     */
    public void sacarDiscoDuro(DiscoDuroCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("internal-hard-drive")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_DISCO())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_DISCO())
                .orderBy("price_usd")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        DiscoDuro discoDuro = document.toObject(DiscoDuro.class);
                        callback.onDiscoDuroObtenido(discoDuro);
                    }
                });
    }

    /**
     * Sacar caja.
     *
     * @param callback the callback
     */
    public void sacarCaja(CajaCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        System.out.println(reglas.getPRECIO_MAX_CAJA());
        System.out.println(reglas.getPRECIO_MIN_CAJA());
        db.collection("case")
                .whereEqualTo("form_factor", placaBase.getFormato())
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_CAJA())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_CAJA())
                .orderBy("price_usd")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        DocumentSnapshot document = snapshot.getDocuments().get(0);
                        System.out.println(document.getData());
                        Caja caja = document.toObject(Caja.class);
                        callback.onCajaObtenida(caja);
                    }
                });
    }

    public void sacarMemoriaRam(RamCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("memory_ram")
                .whereEqualTo("form_factor", placaBase.getFactor_forma_memoria())
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_RAM())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_RAM())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        List<DocumentSnapshot> documents = snapshot.getDocuments();
                        for (DocumentSnapshot document: documents
                             ) {
                            if((Long)document.get("speed") > placaBase.getVelocidad_max_memoria())
                                documents.remove(document);
                        }
                        System.out.println("tamaño" + documents.size());
                        if(documents.size() > 1)
                            ordenarPorPrecio(documents);
                        DocumentSnapshot document = documents.get(0);
                        System.out.println(document.getData());
                        MemoriaRam memoriaRam = document.toObject(MemoriaRam.class);
                        callback.onRamObtenida(memoriaRam);
                    }
                });
    }


    private void ordenarPorPrecio(List<DocumentSnapshot> documents){
        Collections.sort(documents, new Comparator<DocumentSnapshot>() {
            @Override
            public int compare(DocumentSnapshot doc1, DocumentSnapshot doc2) {
                Double price1 = doc1.getDouble("price");
                Double price2 = doc2.getDouble("price");
                if (price1 != null && price2 != null) {
                    return Double.compare(price2, price1); // Orden descendente
                }
                return 0;
            }
        });
    }

}
