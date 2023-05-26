package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.Ordenador;

import java.text.DecimalFormat;

public class OrdenadorGeneradoActivity extends FatherView {
    private Ordenador ordenador;
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
        if(ordenador.getTarjetaGrafica() == null)
            layoutGpu.setVisibility(View.GONE);
        TextView txtPrecio = findViewById(R.id.txtPrecioOrdenador);
        DecimalFormat formato = new DecimalFormat("#.##");
        String resultado = "PRECIO: " + formato.format(ordenador.getPrice());
        txtPrecio.setText(resultado);
    }

    private void verComponente(Componente componente){
        Intent i = new Intent(this, VistaComponenteActivity.class);
        i.putExtra("componente", componente);
        startActivity(i);
    }
}
