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





}

