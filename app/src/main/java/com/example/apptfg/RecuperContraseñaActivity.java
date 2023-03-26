package com.example.apptfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperContraseñaActivity extends FatherView {
    private EditText emailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuper_contrasena);
        setup();
    }

    private void setup(){
        findViewById(R.id.btnRecuperar).setOnClickListener(v -> validarCapos());
        emailEditText = findViewById(R.id.emailRecuperarEditText);
    }

    private void validarCapos() {
        String email = emailEditText.getText().toString();
        if(!email.equals("")){
            mandarEmail(email);
        } else{
            mostrarToastCamposVacios();
        }
    }

    private void mandarEmail(String email) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            mostrarToastCorreoEnviado();
                            finish();
                        }else
                            mostrarToastError();
                    }
                });
    }
}