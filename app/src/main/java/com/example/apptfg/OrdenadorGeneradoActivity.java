package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.Ordenador;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class OrdenadorGeneradoActivity extends FatherView {
    private Ordenador ordenador;
    private boolean ordenadorNuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenador_generado);
        darFuncionalidadMenu();
        setUp();
    }

    private void setUp() {
        findViewById(R.id.btniCaja).setOnClickListener(v -> verComponente(ordenador.getCaja()));
        findViewById(R.id.btniCpu).setOnClickListener(v -> verComponente(ordenador.getProcesador()));
        findViewById(R.id.btniDisipador).setOnClickListener(v -> verComponente(ordenador.getDisipador()));
        findViewById(R.id.btniPlacaBase).setOnClickListener(v -> verComponente(ordenador.getPlacaBase()));
        findViewById(R.id.btniRam).setOnClickListener(v -> verComponente(ordenador.getMemoriaRam()));
        findViewById(R.id.btniTarjetaGrafica).setOnClickListener(v -> verComponente(ordenador.getTarjetaGrafica()));
        findViewById(R.id.btniDiscoDuro).setOnClickListener(v -> verComponente(ordenador.getDiscoDuro()));
        findViewById(R.id.btniPsu).setOnClickListener(v -> verComponente(ordenador.getFuenteAlimentacion()));
        LinearLayout layoutGpu = findViewById(R.id.layoutGpu);
        Intent i = getIntent();
        this.ordenador = (Ordenador) i.getSerializableExtra("ordenador");
        ordenadorNuevo = (boolean) i.getSerializableExtra("tipo");
        if(ordenadorNuevo)
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> finish());
        else
            findViewById(R.id.btnBorrarOrdenador).setOnClickListener(v -> borrar());
        if(ordenador.getTarjetaGrafica() == null)
            layoutGpu.setVisibility(View.GONE);
        findViewById(R.id.btnGuardarOrdenador).setOnClickListener(v -> guardarOrdendor());
        TextView txtPrecio = findViewById(R.id.txtPrecioOrdenador);
        DecimalFormat formato = new DecimalFormat("#.##");
        String resultado = "PRECIO: " + formato.format(ordenador.getPrice());
        txtPrecio.setText(resultado);
    }

    private void guardarOrdendor() {
        mostrarCarga();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference userDocRef = firestore.collection("usuarios").document(uid);

        Map<String, Object> userData = new HashMap<>();
        Gson gson = new Gson();
        userData.put("listaOrdenadores", gson.toJson(ordenador));

        userDocRef.set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("AAAAAAAAAAA");
                        disiparCarga();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e);
                        disiparCarga();
                    }
                });
    }

    private void borrar() {

    }

    private void verComponente(Componente componente){
        Intent i = new Intent(this, VistaComponenteActivity.class);
        i.putExtra("componente", componente);
        startActivity(i);
    }
}
