package com.example.apptfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.gestor.GestorFirebase;
import com.example.apptfg.singletonEntities.ListaOrdenadoresSingleton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroLoginActivity extends FatherView {
    private final int GOOGLE_SIGN_IN = 100;
    EditText email;
    EditText c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);
        setup();
        sesion();
    }

    private void setup() {
        email = findViewById(R.id.emailEditText);
        c = findViewById(R.id.contrasenaEditText);
        email.setOnClickListener(v -> email.setHint(""));
        c.setOnClickListener(v -> c.setHint(""));
        findViewById(R.id.btnRegistrar).setOnClickListener(v -> irTerminarRegistro());
        findViewById(R.id.btnAcceder).setOnClickListener(v -> acceder());
        findViewById(R.id.btnRegistroGoogle).setOnClickListener(v -> regitrarConGoogle());
        findViewById(R.id.lblRecuperarContraseña).setOnClickListener(v -> irRecuperarContraseña());
    }

    private void irRecuperarContraseña() {
        Intent i = new Intent(this, RecuperContrasenaActivity.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        findViewById(R.id.authLayout).setVisibility(View.VISIBLE);
    }

    private void sesion() {
        SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        String email = prefs.getString("email", null);
        String proveedor = prefs.getString("proveedor", null);

        if (email != null) {
            findViewById(R.id.authLayout).setVisibility(View.INVISIBLE);
            irHome();
        }
    }

    private void regitrarConGoogle() {
        GoogleSignInOptions googleConf;
        googleConf = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        GoogleSignInClient googleClient;
        googleClient = GoogleSignIn.getClient(this, googleConf);
        googleClient.signOut();
        startActivityForResult(googleClient.getSignInIntent(), GOOGLE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                if (account != null) {
                    AuthCredential credetial = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    GoogleSignInAccount finalAccount = account;
                    FirebaseAuth.getInstance().signInWithCredential(credetial).addOnCompleteListener((it) -> {
                        if (it.isSuccessful()) {
                            crearUsuario();
                            irHome();
                        } else {
                            mostrarToastError();
                        }
                    });
                }
            } catch (ApiException e) {
                mostrarToastError();
            }
        }
    }

    private void crearUsuario() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference userDocRef = firestore.collection("usuarios").document(uid);

        Map<String, List<Ordenador>> mapa = new HashMap<>();
        userDocRef.set(mapa)
                .addOnSuccessListener(aVoid -> System.out.println("Lista de ordenadores creada"))
                .addOnFailureListener(System.out::println);
    }

    private void irTerminarRegistro() {
        Intent i = new Intent(this, TerminarRegistroActivity.class);
        i.putExtra("email", email.getText().toString());
        startActivity(i);
    }

    private void acceder() {
        if (!email.getText().toString().isEmpty() && !c.getText().toString().isEmpty()) {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email.getText().toString(), c.getText().toString())
                    .addOnCompleteListener((task) -> {
                        if (task.isSuccessful()) {
                            irHome();
                        } else {
                            mostrarToastError();
                        }
                    });
        } else {
            mostrarToastCamposVacios();
        }
    }

    private void saveUserData() {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        prefs.putString("email", email);
        prefs.apply();
    }

    private void irHome() {
        Intent i = new Intent(this, HomeActivity.class);
        saveUserData();
        inicializarListaOrdenadores(i);
    }


    private void inicializarListaOrdenadores(Intent i) {
        ListaOrdenadoresSingleton.getInstance().inicializar();
        startActivity(i);
        finish();
    }
}
