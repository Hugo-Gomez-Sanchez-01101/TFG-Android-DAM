package com.example.apptfg;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FatherView extends AppCompatActivity {
    protected void mostrarToastError() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_error_login_registro, (ViewGroup) findViewById(R.id.toastErrorLoginRegistro));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    protected void mostrarToastContraseña() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_contrasenas_iguales, (ViewGroup) findViewById(R.id.toastContrasenaDif));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    protected void mostrarToastCamposVacios() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_campos_vacios, (ViewGroup) findViewById(R.id.toastCamposVacios));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }

    protected void mostrarToastCorreoEnviado() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.toast_correo_enviado, (ViewGroup) findViewById(R.id.toastCorreoEnviado));
        Toast t = new Toast(getApplicationContext());
        t.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        t.setDuration(Toast.LENGTH_SHORT);
        t.setView(view);
        t.show();
    }
}
