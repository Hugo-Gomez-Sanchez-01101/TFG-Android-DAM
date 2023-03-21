package com.example.apptfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        findViewById(R.id.btnListaOrdenadores).setOnClickListener(view -> verListaOrdenadores());
        findViewById(R.id.btnCerrarSesion).setOnClickListener(View -> cerrarSesion());
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

    private void cerrarSesion() {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        prefs.clear();
        prefs.apply();
        FirebaseAuth.getInstance().signOut();
        cerrarActividades();
    }

    private void cerrarActividades(){
        Intent intent = new Intent(getApplicationContext(), RegistroLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void verListaOrdenadores() {
        Intent i = new Intent(this,ListaOrdenadoresActivity.class);
        startActivity(i);
    }
}
