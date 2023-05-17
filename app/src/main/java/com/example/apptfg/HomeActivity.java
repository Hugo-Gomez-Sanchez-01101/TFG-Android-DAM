package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Spinner;
import com.example.apptfg.gestor.GestorFirebase;
import com.example.apptfg.regla.Usos;

public class HomeActivity extends FatherView {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        darFuncionalidadMenu();
        iniciarGestor();
        setup();
    }

    private void iniciarGestor() {
        GestorFirebase.getInstance();
    }

    private void setup() {
        findViewById(R.id.buttonGaming).setOnClickListener(v -> obtenerValoresPorTipo(Usos.GAMING));
        findViewById(R.id.buttonGamingP).setOnClickListener(v -> obtenerValoresPorTipo(Usos.GAMING_PROFESIONAL));
        findViewById(R.id.buttonEdicion).setOnClickListener(v -> obtenerValoresPorTipo(Usos.EDICION));
        findViewById(R.id.buttonOfimatica).setOnClickListener(v -> obtenerValoresPorTipo(Usos.OFIMATICA));
    }

    private void obtenerValoresPorTipo(Enum<Usos> uso) {
        GestorFirebase.getInstance().obtenerMaximoMinimo(uso, new GestorFirebase.MinimoMaximosCallback() {
            @Override
            public void onValoresObtenidos(long[] minimoMaximo) {
                irPreciosActivity(minimoMaximo, uso);
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
    }

    private void irPreciosActivity(long[] minimoMaximo, Enum<Usos> uso) {
        Intent i = new Intent(this, PreciosActivity.class);
        i.putExtra("minimo", (int)minimoMaximo[0]);
        i.putExtra("maximo", (int)minimoMaximo[1]);
        i.putExtra("uso", uso);
        startActivity(i);
        finish();
    }
}

