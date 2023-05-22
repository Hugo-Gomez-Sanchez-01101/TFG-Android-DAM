package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptfg.entidad.Ordenador;

public class OrdenadorGeneradoActivity extends AppCompatActivity {
    private Ordenador ordenador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenador_generado);
        Intent i = getIntent();
        this.ordenador = (Ordenador) i.getSerializableExtra("ordenador");
    }
}
