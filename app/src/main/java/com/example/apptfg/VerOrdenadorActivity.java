package com.example.apptfg;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.regla.Usos;
import com.example.apptfg.singletonEntities.ListaOrdenadoresSingleton;
import com.example.apptfg.singletonEntities.OrdenadorGeneradoSingleton;

import java.text.DecimalFormat;

public class VerOrdenadorActivity extends FatherView {
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
        findViewById(R.id.btnGuardarOrdenador).setOnClickListener(v -> showPopup());
        LinearLayout layoutGpu = findViewById(R.id.layoutGpu);

        Intent i = getIntent();
        ordenadorNuevo = (boolean) i.getSerializableExtra("nuevo");
        uso = OrdenadorGeneradoSingleton.getInstance().getOrdenador().getUso();

        if (ordenadorNuevo)
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> volver());
        else
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> borrar());

        if (ordenador.getTarjetaGrafica() == null)
            layoutGpu.setVisibility(View.GONE);

        TextView txtPrecio = findViewById(R.id.txtPrecioOrdenador);
        DecimalFormat formato = new DecimalFormat("#.##");
        String resultado = "PRECIO: " + formato.format(ordenador.getPrice());
        txtPrecio.setText(resultado);
    }

    private void guardarOrdenador(String nombre) {
        ordenador.setNombre(nombre);
        ListaOrdenadoresSingleton.getInstance().getListaOrdenadores().add(ordenador);
        mostrarToastGuardado();
        Context context = getApplicationContext();
        Intent i = new Intent(context, ListaOrdenadoresActivity.class);
        startActivity(i);
        finish();
    }

    private void borrar() {
        ListaOrdenadoresSingleton.getInstance().getListaOrdenadores().remove(ordenador);
        mostrarToastBorrado();
        Intent i = new Intent(this, ListaOrdenadoresActivity.class);
        startActivity(i);
        finish();
    }

    private void volver() {
        Intent i = new Intent(this, UsosActivity.class);
        Ordenador ordenador = new Ordenador();
        OrdenadorGeneradoSingleton.getInstance().setOrdenador(ordenador);
        startActivity(i);
        finish();
    }

    private void verComponente(Componente componente) {
        Intent i = new Intent(this, VerComponenteActivity.class);
        i.putExtra("componente", componente);
        startActivity(i);
    }

    private void showPopup() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = dialogView.findViewById(R.id.editTextNamePopup);
        Button cancelar = dialogView.findViewById(R.id.buttonCancelPopup);
        Button aceptar = dialogView.findViewById(R.id.buttonAcceptPopup);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();
        cancelar.setOnClickListener(v -> alertDialog.dismiss());
        aceptar.setOnClickListener(v ->{
            String name = editTextName.getText().toString();
            guardarOrdenador(name);
        });

    }
}
