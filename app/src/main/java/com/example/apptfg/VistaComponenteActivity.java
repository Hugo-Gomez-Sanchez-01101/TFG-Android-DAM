package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;

public class VistaComponenteActivity extends FatherView {
    private ConstraintLayout lCaja, lDiscoDuro, lDisipador, lFuente, lMemoria, lPlacaBase, lProcesador, lTarjetaGrafica;
    private Componente componente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_componente);
        darFuncionalidadMenu();
        setup();
        Intent i = getIntent();
        componente = (Componente) i.getSerializableExtra("componente");
        mostrarComponente(componente);

    }

    private void setup() {
        lCaja = findViewById(R.id.cLayoutCaja);
        lDiscoDuro = findViewById(R.id.cLayoutDisco);
        lDisipador = findViewById(R.id.cLayoutDisipador);
        lFuente = findViewById(R.id.cLayoutFuente);
        lMemoria = findViewById(R.id.cLayoutMRam);
        lPlacaBase = findViewById(R.id.cLayoutPlacaBase);
        lProcesador = findViewById(R.id.cLayoutProcesador);
        lTarjetaGrafica = findViewById(R.id.cLayoutTarjetaGrafica);
        findViewById(R.id.btnVolverVistaComponentes).setOnClickListener(v -> finish());
    }

    private void mostrarComponente(Object componente) {
        if(componente instanceof Procesador){
            lProcesador.setVisibility(View.VISIBLE);
            mostrarProcesador();
        } else if(componente instanceof MemoriaRam){
            lMemoria.setVisibility(View.VISIBLE);
            mostrarMemoria();
        } else if(componente instanceof DiscoDuro) {
            lDiscoDuro.setVisibility(View.VISIBLE);
            mostrarDisco();
        } else if(componente instanceof Caja) {
            lCaja.setVisibility(View.VISIBLE);
            mostrarCaja();
        } else if(componente instanceof Disipador) {
            lDisipador.setVisibility(View.VISIBLE);
            motrarDisipador();
        } else if(componente instanceof FuenteAlimentacion) {
            lFuente.setVisibility(View.VISIBLE);
            mostrarFuete();
        } else if(componente instanceof PlacaBase) {
            lPlacaBase.setVisibility(View.VISIBLE);
            motrarPlaca();
        } else if(componente instanceof TarjetaGrafica) {
            lTarjetaGrafica.setVisibility(View.VISIBLE);
            mostrarTarjeta();
        }
    }

    private void mostrarTarjeta() {
        TarjetaGrafica t = (TarjetaGrafica) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombreTarjetaGrafica);
        txt.setText(t.getName());
        txt = findViewById(R.id.txtChipsetTarjetaGrafica);
        txt.setText(t.getChipset());
        txt = findViewById(R.id.txtColorTarjetaGrafica);
        txt.setText(t.getColor());
        txt = findViewById(R.id.txtMemoriaTarjetaGrafica);
        txt.setText(t.getMemory());
        txt = findViewById(R.id.txtTamañoTarjetaGrafica);
        txt.setText(t.getLength());
        txt = findViewById(R.id.txtGigaherciosBaseTarjetaGrafica);
        txt.setText(t.getCore_clock());
        txt = findViewById(R.id.txtGigaherciosOverclockedTarjetaGrafica);
        txt.setText(t.getCore_clock());
        txt = findViewById(R.id.txtPrecioTarjetaGrafica);
        txt.setText(t.getPrice_usd() + "");
    }

    private void motrarPlaca() {
        PlacaBase p = (PlacaBase) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombrePlacaBase);
        txt.setText(p.getNombre());
        txt = findViewById(R.id.txtAudioPlacaBase);
        txt.setText(p.getAudio());
        txt = findViewById(R.id.txtChipsetPlacaBase);
        txt.setText(p.getChipset());
        txt = findViewById(R.id.txtPrecioPlacaBase);
        txt.setText(p.getPrecio() + "");
        txt = findViewById(R.id.txtSocketPlacaBase);
        txt.setText(p.getSocket());
        txt = findViewById(R.id.txtPuertosUsbPlacaBase);
        txt.setText(p.getPuertos_usb());
        txt = findViewById(R.id.txtFactorFormaMemoriaPlacaBase);
        txt.setText(p.getFactor_forma_memoria());
        txt = findViewById(R.id.txtVelocidadMaxRamPlacaBase);
        txt.setText(p.getVelocidad_max_memoria() + "");
    }

    private void mostrarFuete() {
        FuenteAlimentacion f = (FuenteAlimentacion) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombrePsu);
        txt.setText(f.getName());
        txt = findViewById(R.id.txtColorPsu);
        txt.setText(f.getColor());
        txt = findViewById(R.id.txtEficienciaPsu);
        txt.setText(f.getEfficiency_rating());
        txt = findViewById(R.id.txtModularPsu);
        txt.setText(f.getModular());
        txt = findViewById(R.id.txtPotenciaPsu);
        txt.setText(f.getWattage());
        txt = findViewById(R.id.txtPrecioPsu);
        txt.setText(f.getPrice_usd() + "");
        txt = findViewById(R.id.txtFactorFormaPsu);
        txt.setText(f.getForm_factor());
    }

    private void motrarDisipador() {
        Disipador d = (Disipador) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombreDisipador);
        txt.setText(d.getName());
        txt = findViewById(R.id.txtColorDisipador);
        txt.setText(d.getColor());
        txt = findViewById(R.id.txtPrecioDisipador);
        txt.setText(d.getPrice_usd() + "");
        txt = findViewById(R.id.txtRevolucionesDisipador);
        txt.setText(d.getFan_rpm());
        txt = findViewById(R.id.txtTamañoDisipador);
        txt.setText(d.getRadiator_size());
        txt = findViewById(R.id.txtNivelRuidoDisipador);
        txt.setText(d.getNoise_level());
    }

    private void mostrarCaja() {
        Caja c = (Caja) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombreCaja);
        txt.setText(c.getName());
        txt = findViewById(R.id.txtColorCaja);
        txt.setText(c.getColor());
        txt = findViewById(R.id.txtFuenteCaja);
        txt.setText(c.getPower_supply());
        txt = findViewById(R.id.txtPrecioCaja);
        txt.setText(c.getPrice_usd() + "");
        txt = findViewById(R.id.txtTipoCaja);
        txt.setText(c.getType());
        txt = findViewById(R.id.txtFactorFormaCaja);
        txt.setText(c.getForm_factor());
    }

    private void mostrarDisco() {
        DiscoDuro d = (DiscoDuro) componente;
        TextView txt;
    }

    private void mostrarMemoria() {
    }

    private void mostrarProcesador() {
    }
}