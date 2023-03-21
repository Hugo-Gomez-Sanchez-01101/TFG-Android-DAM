package com.example.apptfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        guardarDatosUsuario();
        setupMenu();
    }

    private void setupMenu() {
        findViewById(R.id.iCasa).setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaOrdenadoresActivity.class);
            startActivity(intent);

        });
        findViewById(R.id.iOrdenador).setOnClickListener(v ->{
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } );

        findViewById(R.id.iUsuario).setOnClickListener(v ->{
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        });
    }

    private void guardarDatosUsuario() {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        Bundle bundle = getIntent().getExtras();
        String email = bundle.getString("email");
        String proveedor = bundle.getString("proveedor");
        prefs.putString("email", email);
        prefs.putString("proveedor", proveedor);
        prefs.apply();
    }
}

