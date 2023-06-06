package com.example.apptfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.example.apptfg.provider_tipe.ProviderType;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            comprobarContrasena();
        else
            mostrarToastCamposVacios();
    }

    private void comprobarContrasena() {
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
                        //crearListaOrdenadores();
                        guardarDatosUsuario(ProviderType.BASIC);
                        irHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                    } else {
                        mostrarToastCamposIncompletos();
                    }
                });
    }

    private void irHome(String email, ProviderType proveedor) {
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("email", email);
        i.putExtra("proveedor", proveedor + "");
        startActivity(i);
    }

//    private void crearListaOrdenadores() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//        DocumentReference userDocRef = firestore.collection("usuarios").document(uid);
//
//        Map<String, Object> listaOrdenadores = new HashMap<>();
//
//        userDocRef.set(listaOrdenadores, SetOptions.merge())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        System.out.println(e);
//                    }
//                });
//    }

    private void vaciarCampos(EditText e){
        e.setText("");
    }

    private void guardarDatosUsuario(ProviderType proveedor) {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        prefs.putString("email", email);
        prefs.putString("proveedor",proveedor + "");

        prefs.apply();
    }
}