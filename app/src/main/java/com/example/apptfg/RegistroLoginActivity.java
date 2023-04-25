package com.example.apptfg;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptfg.provider_tipe.ProviderType;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

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
        Intent i = new Intent(this, RecuperContraseñaActivity.class);
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

        if(email != null && proveedor != null) {
            findViewById(R.id.authLayout).setVisibility(View.INVISIBLE);
            irHome(email, ProviderType.valueOf(proveedor));
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
                        if(task.isSuccessful()){
                            irHome(task.getResult().getUser().getEmail(), ProviderType.BASIC);
                        } else{
                            mostrarToastError();
                        }
                    });
        }else{
            mostrarToastCamposVacios();
        }
    }

    private void irHome(String email, ProviderType proveedor){
        Intent i = new Intent(this, HomeActivity.class);
        i.putExtra("email",email);
        i.putExtra("proveedor",proveedor + "");
        guardarDatosUsuario(proveedor);
        startActivity(i);
    }


    private void guardarDatosUsuario(ProviderType proveedor) {
        SharedPreferences.Editor prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        prefs.putString("email", email);
        prefs.putString("proveedor",proveedor + "");

        prefs.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GOOGLE_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount account = null;
            try {
                account = task.getResult(ApiException.class);
                if(account != null) {
                    AuthCredential credetial = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    GoogleSignInAccount finalAccount = account;
                    FirebaseAuth.getInstance().signInWithCredential(credetial).addOnCompleteListener((it) -> {
                        if(it.isSuccessful()){
                            irHome(finalAccount.getEmail(), ProviderType.GOOGLE);
                        } else{
                            mostrarToastError();
                        }
                    });
                }
            } catch (ApiException e) {
                mostrarToastError();
            }
        }
    }
}
