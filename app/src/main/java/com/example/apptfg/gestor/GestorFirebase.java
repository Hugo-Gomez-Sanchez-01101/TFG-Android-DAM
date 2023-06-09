package com.example.apptfg.gestor;

import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.regla.Reglas;
import com.example.apptfg.regla.Usos;
import com.example.apptfg.singletonEntities.ListaOrdenadoresSingleton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The type Gestor firebase.
 */
public class GestorFirebase {
    private Reglas reglas;
    private static GestorFirebase gestorFirebase;

    public void setReglas(Reglas reglas) {
        this.reglas = reglas;
    }

    private GestorFirebase() {
    }

    public static GestorFirebase getInstance() {
        if (gestorFirebase == null) {
            gestorFirebase = new GestorFirebase();
        }
        return gestorFirebase;
    }

    public interface MinimoMaximosCallback {
        void onValoresObtenidos(long[] minimoMaximo);

        void onError(String errorMessage);
    }

    public interface ListaComponentesCallback {
        void onListaComponentesObtenidos(List<Componente> listaComponentes);

        void onError(String errorMessage);
    }

    public interface ListaOrdenadoresCallback{
        void onListaOrenadoresObtenida(List<Ordenador> listaOrdenadores);
        void onError(String errorMessage);
    }

    public interface ComponenteCallback {
        void onComponenteObtenido(Componente componente);

        void onError(String errorMessage);
    }

    public interface OrdenadoresActualizadosCallback{
        void onListaActualizada(String okMessage);
        void onError(String errorMessage);
    }

    public void sacarListaRam(PlacaBase placaBase, ListaComponentesCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("memory_ram")
                .whereEqualTo("form_factor", placaBase.getFactor_forma_memoria())
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_RAM())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_RAM())
                .limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(MemoriaRam.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningúna lista memoria que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta lista memoria");
                    }
                });
    }

    public void sacarListaCpuNormal(PlacaBase placaBase, ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("procesadores")
                .whereEqualTo("socket", placaBase.getSocket())
                .whereLessThanOrEqualTo("price", reglas.getPRECIO_MAX_CPU())
                .whereGreaterThanOrEqualTo("price", reglas.getPRECIO_MIN_CPU())
                .orderBy("price")
                .limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(Procesador.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningún lista cpuNormal que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta lista cpu");
                    }

                });
    }

    public void sacarListaCpuConGrafica(PlacaBase placaBase, ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("procesadores")
                .whereEqualTo("socket", placaBase.getSocket())
                .whereNotEqualTo("integrated_graphics", null)
                .limit(10)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> documentos = queryDocumentSnapshots.getDocuments();
                        ordenarPorPrecio(documentos);
                        List<Componente> lista = new ArrayList<>();
                        for (DocumentSnapshot d:
                             documentos) {
                            lista.add(d.toObject(Procesador.class));
                        }
                        callback.onListaComponentesObtenidos(lista);
                    } else {
                        callback.onError("no se ha encontrado ninguna lista cpu con grafica");
                    }
                });
    }

    public void sacarListaGpu(ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("video-card")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_GPU())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_GPU())
                .orderBy("price_usd")
                .limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(TarjetaGrafica.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningún lista gpu que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta lista gpu");
                    }
                });
    }

    public void sacarListaPsu( ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("power_supply")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_PSU())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_PSU())
                .orderBy("price_usd")
                .limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(FuenteAlimentacion.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningún lista psu que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta lista psu");
                    }
                });
    }

    public void sacarListaDisipador(ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("cpu-cooler")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_COOLER())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_COOLER())
                .orderBy("price_usd")
                .limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(DiscoDuro.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningún lista disipador que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta lista disipador");
                    }
                });
    }
    public void sacarListaDiscoDuro(ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("internal-hard-drive")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_DISCO())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_DISCO())
                .orderBy("price_usd")
                .limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(DiscoDuro.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningúna lista disco que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta lista disco");
                    }
                });
    }

    public void sacarListaCaja(PlacaBase placaBase, ListaComponentesCallback callback){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<Componente> lista = new ArrayList<>();
                            for (DocumentSnapshot d:
                                 documents) {
                                lista.add(d.toObject(Caja.class));
                            }
                            callback.onListaComponentesObtenidos(lista);
                        } else {
                            callback.onError("No se encontró ningúna lista caja que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta caja");
                    }
                });
    }

    /**
     * Sacar placa base.
     *
     * @param callback the callback
     */
    public void sacarPlacaBase(String formato, ComponenteCallback callback) {
        if(formato == null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("placas_base")
                    .whereLessThanOrEqualTo("precio", reglas.getPRECIO_MAX_PLACA())
                    .whereGreaterThanOrEqualTo("precio", reglas.getPRECIO_MIN_PLACA())
                    .orderBy("precio")
                    .limit(1)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot snapshot = task.getResult();
                            if (snapshot != null && !snapshot.isEmpty()) {
                                DocumentSnapshot document = snapshot.getDocuments().get(0);
                                PlacaBase placaBase = document.toObject(PlacaBase.class);
                                callback.onComponenteObtenido(placaBase);
                            } else {
                                callback.onError("No se encontró ningúna placa base que cumpliera las condiciones de la consulta");
                            }
                        } else {
                            callback.onError("Hubo un error al realizar la consulta");
                        }
                    });
        } else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("placas_base")
                    .whereEqualTo("formato", formato)
                    .whereLessThanOrEqualTo("precio", reglas.getPRECIO_MAX_PLACA())
                    .whereGreaterThanOrEqualTo("precio", reglas.getPRECIO_MIN_PLACA())
                    .orderBy("precio")
                    .limit(1)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot snapshot = task.getResult();
                            if (snapshot != null && !snapshot.isEmpty()) {
                                DocumentSnapshot document = snapshot.getDocuments().get(0);
                                PlacaBase placaBase = document.toObject(PlacaBase.class);
                                callback.onComponenteObtenido(placaBase);
                            } else {
                                callback.onError("No se encontró ningúna placa base que cumpliera las condiciones de la consulta");
                            }
                        } else {
                            callback.onError("Hubo un error al realizar la consulta");
                        }
                    });
        }
    }

    /**
     * Sacar cpu con grafica.
     *
     * @param callback the callback
     */
    public void sacarCpuConGrafica(PlacaBase placaBase, ComponenteCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("procesadores")
                .whereEqualTo("socket", placaBase.getSocket())
                .whereNotEqualTo("integrated_graphics", null)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        List<DocumentSnapshot> documentos = queryDocumentSnapshots.getDocuments();
                        List<DocumentSnapshot> docsFiltrados = new ArrayList<>(documentos);
                        for (DocumentSnapshot documento : documentos) {
                            double precio = documento.getDouble("price");
                            if (precio < reglas.getPRECIO_MIN_CPU() || precio > reglas.getPRECIO_MAX_CPU()) {
                                docsFiltrados.remove(documento);
                            }
                        }
                        ordenarPorPrecio(docsFiltrados);
                        if (docsFiltrados.size() != 0) {
                            Procesador procesador = docsFiltrados.get(docsFiltrados.size() / 2).toObject(Procesador.class);
                            callback.onComponenteObtenido(procesador);
                        } else {
                            callback.onError("no se ha encontrado ninguna cpu con grafica 1");
                        }
                    } else {
                        callback.onError("no se ha encontrado ninguna cpu con grafica");
                    }
                });
    }

    /**
     * Sacar cpu normal.
     *
     * @param callback the callback
     */
    public void sacarCpuNormal(PlacaBase placaBase, ComponenteCallback callback) {
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
                            callback.onComponenteObtenido(procesador);
                        } else {
                            callback.onError("No se encontró ningún cpuNormal que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta cpu");
                    }

                });
    }

    /**
     * Sacar gpu.
     *
     * @param callback the callback
     */
    public void sacarGpu(ComponenteCallback callback) {
        System.out.println("precio max gpu: " + reglas.getPRECIO_MAX_GPU());
        System.out.println("precio min gpu: " + reglas.getPRECIO_MIN_GPU());
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
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            TarjetaGrafica tarjetaGrafica = document.toObject(TarjetaGrafica.class);
                            callback.onComponenteObtenido(tarjetaGrafica);
                        } else {
                            callback.onError("No se encontró ningún gpu que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta gpu");
                    }
                });
    }

    /**
     * Sacar psu.
     *
     * @param callback the callback
     */
    public void sacarPsu(ComponenteCallback callback) {
        System.out.println("precio max psu: " + reglas.getPRECIO_MAX_PSU());
        System.out.println("precio min psu: " + reglas.getPRECIO_MIN_PSU());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("power_supply")
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_PSU())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_PSU())
                .orderBy("price_usd")
                .limit(1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            FuenteAlimentacion fuenteAlimentacion = document.toObject(FuenteAlimentacion.class);
                            callback.onComponenteObtenido(fuenteAlimentacion);
                        } else {
                            callback.onError("No se encontró ningún psu que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta psu");
                    }
                });
    }

    /**
     * Sacar disipador.
     *
     * @param callback the callback
     */
    public void sacarDisipador(ComponenteCallback callback) {
        System.out.println("precio max disipador: " + reglas.getPRECIO_MAX_COOLER());
        System.out.println("precio min disipador: " + reglas.getPRECIO_MIN_COOLER());
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
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            Disipador disipador = document.toObject(Disipador.class);
                            callback.onComponenteObtenido(disipador);
                        } else {
                            callback.onError("No se encontró ningún disipador que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta disipador");
                    }
                });
    }

    /**
     * Sacar disco duro.
     *
     * @param callback the callback
     */
    public void sacarDiscoDuro(ComponenteCallback callback) {
        System.out.println("precio max disco: " + reglas.getPRECIO_MAX_DISCO());
        System.out.println("precio min disco: " + reglas.getPRECIO_MIN_DISCO());
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
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot document = snapshot.getDocuments().get(0);
                            DiscoDuro discoDuro = document.toObject(DiscoDuro.class);
                            callback.onComponenteObtenido(discoDuro);
                        } else {
                            callback.onError("No se encontró ningún disco que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta disco");
                    }
                });
    }

    /**
     * Sacar caja.
     *
     * @param callback the callback
     */
    public void sacarCaja(PlacaBase placaBase, ComponenteCallback callback) {
        System.out.println("precio max caja: " + reglas.getPRECIO_MAX_CAJA());
        System.out.println("precio min caja: " + reglas.getPRECIO_MIN_CAJA());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
                        if (snapshot != null && !snapshot.isEmpty()) {
                            DocumentSnapshot
                                    document = snapshot.getDocuments().get(0);
                            Caja caja = document.toObject(Caja.class);
                            callback.onComponenteObtenido(caja);
                        } else {
                            callback.onError("No se encontró ningúna CAJAAAA que cumpliera las condiciones de la consulta");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta caja");
                    }
                });
    }

    public void sacarMemoriaRam(PlacaBase placaBase, ComponenteCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        System.out.println("velocidad max memoria: " + placaBase.getVelocidad_max_memoria());
        System.out.println("precio max memoria: " + reglas.getPRECIO_MAX_RAM());
        System.out.println("precio min memoria: " + reglas.getPRECIO_MIN_RAM());
        db.collection("memory_ram")
                .whereEqualTo("form_factor", placaBase.getFactor_forma_memoria())
                .whereLessThanOrEqualTo("price_usd", reglas.getPRECIO_MAX_RAM())
                .whereGreaterThanOrEqualTo("price_usd", reglas.getPRECIO_MIN_RAM())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot snapshot = task.getResult();
                        if (snapshot != null && !snapshot.isEmpty()) {
                            List<DocumentSnapshot> documents = snapshot.getDocuments();
                            List<DocumentSnapshot> listaFiltrada = new ArrayList<>(documents);
                            for (DocumentSnapshot document : documents
                            ) {
                                if ((Long) document.get("speed") > placaBase.getVelocidad_max_memoria()) {
                                    listaFiltrada.remove(document);
                                    System.out.println("removida");
                                }
                            }
                            if (listaFiltrada.size() != 0) {
                                ordenarPorPrecio(listaFiltrada);
                                DocumentSnapshot document = listaFiltrada.get(0);
                                MemoriaRam memoriaRam = document.toObject(MemoriaRam.class);
                                callback.onComponenteObtenido(memoriaRam);
                            } else {
                                callback.onError("No se encontró ningúna memoria que cumpliera las condiciones de la consulta 1");
                            }
                        } else {
                            callback.onError("No se encontró ningúna memoria que cumpliera las condiciones de la consulta 2");
                        }
                    } else {
                        callback.onError("hubo un error al realizar la consulta memoria");
                    }
                });
    }


    private void ordenarPorPrecio(List<DocumentSnapshot> documents) {
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

    public void obtenerMaximoMinimo(Enum<Usos> u, MinimoMaximosCallback minimoMaximosCallback) {
        String uso = u.toString().toLowerCase(Locale.ROOT);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("reglas")
                .document(uso)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> data = documentSnapshot.getData();
                        long[] valores = new long[2];
                        valores[1] = (long) data.get("PRECIO_MAX");
                        valores[0] = (long) data.get("PRECIO_MIN");
                        minimoMaximosCallback.onValoresObtenidos(valores);
                    }
                });
    }

    public void sacarListaOrdenadores(ListaOrdenadoresCallback callback) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("usuarios")
                .document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()){
                            Gson gson = new Gson();
                            String json = document.get("listaOrdenadores", String.class);
                            JsonArray listaJson = gson.fromJson(json, JsonArray.class);
                            List<Ordenador> listaOrdenadores = new ArrayList<>();
                            for (JsonElement j: listaJson
                                 ) {
                                listaOrdenadores.add(gson.fromJson(j, Ordenador.class));
                            }
                            if (listaOrdenadores != null && !listaOrdenadores.isEmpty()) {
                                callback.onListaOrenadoresObtenida(listaOrdenadores);
                            } else{
                                listaOrdenadores = new ArrayList<>();
                                callback.onListaOrenadoresObtenida(listaOrdenadores);
                            }
                        } else {
                            callback.onError("no existe el usuario");
                        }
                    } else {
                        callback.onError("Se ha producido un error al hacer la consulta de la lista de ordenadores");
                    }
                });
    }

    public void actualizarListaOrdenadores(OrdenadoresActualizadosCallback callback){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            Gson gson = new Gson();
            String listaJson = gson.toJson(ListaOrdenadoresSingleton.getInstance().getListaOrdenadores());
            db.collection("usuarios").document(userId)
                    .update("listaOrdenadores", listaJson)
                    .addOnSuccessListener(aVoid -> {
                        callback.onListaActualizada("Lista Actualizada");
                    })
                    .addOnFailureListener(e -> {
                        callback.onError("Error al actualizar la lista");
                    });
        } else {
            callback.onError("no se ha encontrado el usuario");
        }
    }
}