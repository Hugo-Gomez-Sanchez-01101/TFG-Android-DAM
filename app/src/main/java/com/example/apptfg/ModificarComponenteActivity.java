package com.example.apptfg;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ModificarComponenteActivity extends FatherView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_componente);
        agregarComponenteLayout(5);
    }

    private void agregarComponenteLayout(int veces) {
        LayoutInflater inflater = LayoutInflater.from(this);
        ConstraintLayout contenedorLayout = findViewById(R.id.contenedorTarjetasLayout);

        for (int i = 0; i < veces; i++) {
            View componenteLayout = inflater.inflate(R.layout.tarjeta_componente_ram, contenedorLayout, false);
            contenedorLayout.addView(componenteLayout, 10);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            componenteLayout.setLayoutParams(layoutParams);
        }
    }

}