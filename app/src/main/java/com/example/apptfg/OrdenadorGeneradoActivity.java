package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.regla.Usos;
import com.example.apptfg.singletonEntities.OrdenadorGeneradoSingleton;

import java.text.DecimalFormat;

public class OrdenadorGeneradoActivity extends FatherView {
    private Ordenador ordenador;
    private boolean ordenadorNuevo;
    private Enum<Usos> uso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenador_generado);
        darFuncionalidadMenu();
        setUp();
    }

    private void setUp() {
        ordenador = OrdenadorGeneradoSingleton.getInstance().getOrdenador();
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
        ordenadorNuevo = (boolean) i.getSerializableExtra("tipo");
        uso =            (Enum<Usos>) i.getSerializableExtra("uso");

        if(ordenadorNuevo)
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> volver());
        else
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> borrar());

        if(ordenador.getTarjetaGrafica() == null)
            layoutGpu.setVisibility(View.GONE);

        TextView txtPrecio = findViewById(R.id.txtPrecioOrdenador);
        DecimalFormat formato = new DecimalFormat("#.##");
        String resultado = "PRECIO: " + formato.format(ordenador.getPrice());
        txtPrecio.setText(resultado);
    }

    private void volver() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
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
        Intent i = new Intent(this, VerComponenteActivity.class);
        i.putExtra("componente", componente);
        i.putExtra("uso", uso);
        startActivity(i);
    }
}
