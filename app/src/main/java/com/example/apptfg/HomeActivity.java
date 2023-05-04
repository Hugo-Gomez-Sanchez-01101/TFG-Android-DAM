package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.algoritmo.Generador;
import com.example.apptfg.regla.Reglas;
import com.example.apptfg.regla.Usos;

public class HomeActivity extends FatherView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        darFuncionalidadMenu();
        setup();
    }

    private void setup() {
        findViewById(R.id.btnGenerarPc).setOnClickListener(V -> generarOrdenador());
    }

    private void generarOrdenador() {
        Reglas reglas = new Reglas(Usos.OFIMATICA,200,500);
        Generador generador = new Generador(reglas);
    }

}

