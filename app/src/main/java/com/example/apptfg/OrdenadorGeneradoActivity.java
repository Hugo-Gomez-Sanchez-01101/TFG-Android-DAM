package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.apptfg.entidad.Ordenador;

public class OrdenadorGeneradoActivity extends AppCompatActivity {
    private Ordenador ordenador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenador_generado);
        LinearLayout layoutGpu = findViewById(R.id.layoutGpu);
        Intent i = getIntent();
        this.ordenador = (Ordenador) i.getSerializableExtra("ordenador");
        if(ordenador.getTarjetaGrafica() == null)
            layoutGpu.setVisibility(View.GONE);
    }
}
