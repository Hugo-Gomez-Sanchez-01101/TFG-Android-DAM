package com.example.apptfg;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.EditText;

import com.example.apptfg.provider_tipe.ProviderType;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class TerminarRegistroActivity extends FatherView {
    EditText email;
    EditText contraseña1;
    EditText contraseña2;
    List<EditText> campos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminar_registro);
        setup();
    }

    private void setup() {
        Bundle bundle = getIntent().getExtras();
        email = findViewById(R.id.emailParaRegistroEditText);
        contraseña1 = findViewById(R.id.contraseña1EditText);
        contraseña2 = findViewById(R.id.contraseña2EditText);
        email.setText(bundle.getString("email"));
        email.setOnClickListener(v -> vaciarCampos(email));
        contraseña1.setOnClickListener(v -> vaciarCampos(contraseña1));
        contraseña2.setOnClickListener(v -> vaciarCampos(contraseña2));
        campos = new ArrayList<>();
        campos.add(email);
        campos.add(contraseña1);
        campos.add(contraseña2);
        findViewById(R.id.btnTerminrRegistro).setOnClickListener(v -> checkearCamposRellenos());
    }

    private void checkearCamposRellenos() {
        ColorStateList rojo = ColorStateList.valueOf(getResources().getColor(R.color.red_1));
        ColorStateList verde = ColorStateList.valueOf(getResources().getColor(R.color.verdeCorrecto));
        boolean todoOk = true;
        for (EditText e :
                campos) {
            if (e.getText().toString().equals("")) {
                e.setBackgroundTintList(rojo);
                todoOk = false;
            } else {
                e.setBackgroundTintList(verde);
            }
        }
        if (todoOk)
            comprobarContraseña();
        else
            mostrarToastCamposVacios();
    }

    private void comprobarContraseña() {
        if(contraseña1.getText().toString().equals(contraseña2.getText().toString()))
            registrar(email.getText().toString(), contraseña1.getText().toString());
        else
            mostrarToastContrasena();
    }

    private void registrar(String e, String c) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(e, c)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        irHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                    } else {
                        mostrarToastCamposIncompletos();
                    }
                });
    }

    private void irHome(String email, ProviderType proveedor) {
        Intent i = new Intent(this, ListaOrdenadoresActivity.class);
        i.putExtra("email", email);
        i.putExtra("proveedor", proveedor + "");
        startActivity(i);
    }

    private void vaciarCampos(EditText e){
        e.setText("");
    }
}