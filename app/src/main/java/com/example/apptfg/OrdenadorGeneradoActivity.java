package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.Ordenador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrdenadorGeneradoActivity extends FatherView {
    private Ordenador ordenador;
    private boolean ordenadorNuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenador_generado);
        darFuncionalidadMenu();
        setUp();
    }

    private void setUp() {
        findViewById(R.id.btniCaja).setOnClickListener(v -> verComponente(ordenador.getCaja()));
        findViewById(R.id.btniCpu).setOnClickListener(v -> verComponente(ordenador.getProcesador()));
        findViewById(R.id.btniDisipador).setOnClickListener(v -> verComponente(ordenador.getDisipador()));
        findViewById(R.id.btniPlacaBase).setOnClickListener(v -> verComponente(ordenador.getPlacaBase()));
        findViewById(R.id.btniRam).setOnClickListener(v -> verComponente(ordenador.getMemoriaRam()));
        findViewById(R.id.btniTarjetaGrafica).setOnClickListener(v -> verComponente(ordenador.getTarjetaGrafica()));
        findViewById(R.id.btniDiscoDuro).setOnClickListener(v -> verComponente(ordenador.getDiscoDuro()));
        findViewById(R.id.btniPsu).setOnClickListener(v -> verComponente(ordenador.getFuenteAlimentacion()));
        //findViewById(R.id.btnGuardarOrdenador).setOnClickListener(v -> guardarOrdenador());
        LinearLayout layoutGpu = findViewById(R.id.layoutGpu);

        Intent i = getIntent();
        this.ordenador = (Ordenador) i.getSerializableExtra("ordenador");
        ordenadorNuevo = (boolean) i.getSerializableExtra("tipo");
        if(ordenadorNuevo)
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> finish());
        else
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> borrar());

        if(ordenador.getTarjetaGrafica() == null)
            layoutGpu.setVisibility(View.GONE);

        TextView txtPrecio = findViewById(R.id.txtPrecioOrdenador);
        DecimalFormat formato = new DecimalFormat("#.##");
        String resultado = "PRECIO: " + formato.format(ordenador.getPrice());
        txtPrecio.setText(resultado);
    }

//    public void guardarOrdenador() {
//        try {
//            File archivo = new File("com.example.apptfg.listaPc");
//
//            // Verificar si el archivo existe, si no, crearlo
//            if (!archivo.exists()) {
//                archivo.createNewFile();
//            }
//
//            // Crear una instancia de Gson
//            Gson gson = new Gson();
//
//            // Convertir el objeto a formato JSON
//            String json = gson.toJson(ordenador);
//
//            // Escribir el JSON en el archivo
//            FileWriter writer = new FileWriter(archivo);
//            writer.write(json);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public List<Ordenador> leerObjetosDelArchivo() {
//        List<Ordenador> listaOrdenadores = new ArrayList<>();
//
//        try {
//            File archivo = new File(this.getFilesDir(), "listaOrdenadores.txt");
//
//            BufferedReader reader = new BufferedReader(new FileReader(archivo));
//
//            String linea;
//            while ((linea = reader.readLine()) != null) {
//                // Validar si la línea está vacía o no
//                if (linea.isEmpty()) {
//                    continue; // Saltar a la siguiente línea si está vacía
//                }
//
//                // Convertir la línea del archivo a un objeto Map
//                Gson gson = new Gson();
//                Type tipoMapa = new TypeToken<Map<String, String>>(){}.getType();
//                Map<String, String> objetoConId = gson.fromJson(linea, tipoMapa);
//
//                // Obtener el JSON del objeto a partir del Map
//                String json = objetoConId.values().iterator().next();
//
//                // Convertir el JSON a objeto Ordenador
//                Ordenador ordenador = gson.fromJson(json, Ordenador.class);
//
//                // Agregar el objeto Ordenador a la lista
//                listaOrdenadores.add(ordenador);
//            }
//
//            // Cerrar el archivo
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return listaOrdenadores;
//    }



    private void borrar() {

    }

    private void verComponente(Componente componente){
        Intent i = new Intent(this, VistaComponenteActivity.class);
        i.putExtra("componente", componente);
        startActivity(i);
    }
}
