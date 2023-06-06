package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.entidad.PlacaBase;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.regla.Usos;
import com.example.apptfg.singletonEntities.ListaComponentesSingleton;
import com.example.apptfg.singletonEntities.OrdenadorGeneradoSingleton;

public class VerComponenteActivity extends FatherView {
    private ConstraintLayout lCaja, lDiscoDuro, lDisipador, lFuente, lMemoria, lPlacaBase, lProcesador, lTarjetaGrafica;
    private Componente componente;
    private Ordenador ordenador;
    private Button botonModificar;
    private Enum<Usos> uso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_componente);
        darFuncionalidadMenu();
        setup();
        mostrarComponente(componente);
    }

    private void setup() {
        ordenador = OrdenadorGeneradoSingleton.getInstance().getOrdenador();
        lCaja = findViewById(R.id.cLayoutCaja);
        lDiscoDuro = findViewById(R.id.cLayoutDisco);
        lDisipador = findViewById(R.id.cLayoutDisipador);
        lFuente = findViewById(R.id.cLayoutFuente);
        lMemoria = findViewById(R.id.cLayoutMRam);
        lPlacaBase = findViewById(R.id.cLayoutPlacaBase);
        lProcesador = findViewById(R.id.cLayoutProcesador);
        lTarjetaGrafica = findViewById(R.id.cLayoutTarjetaGrafica);
        findViewById(R.id.btnVolverVistaComponentes).setOnClickListener(v -> finish());
        botonModificar = findViewById(R.id.btnModificarComponente);
        botonModificar.setOnClickListener(v -> modificar());
        Intent i = getIntent();
        componente = (Componente) i.getSerializableExtra("componente");
        uso = OrdenadorGeneradoSingleton.getInstance().getOrdenador().getUso();
        boolean modificando = i.getBooleanExtra("modificando", false);
        if(modificando)
            botonModificar.setVisibility(View.GONE);
    }

    private void modificar() {
        ListaComponentesSingleton.getInstance().inicializar(componente, this);
    }

    public void irModificarComponentes(){
        disiparCarga();
        Intent i = new Intent(this, ModificarComponenteActivity.class);
        i.putExtra("componente",componente);
        startActivity(i);
        finish();
    }

    public void terminarCarga(){
        disiparCarga();
    }

    public void carga(){
        mostrarCarga();
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
            botonModificar.setVisibility(View.GONE);
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
        txt = findViewById(R.id.txtFactorFormaPlacaBase);
        txt.setText(p.getFormato());
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
        txt.setText(f.getWattage() + "W");
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
        System.out.println(d.getFan_rpm());
        System.out.println(d.getRadiator_size());
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
        if(c.getPower_supply() == null)
            txt.setText("Sin fuente");
        else
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
        txt = findViewById(R.id.txtNombreDisco);
        txt.setText(d.getName());
        txt = findViewById(R.id.txtCacheDisco);
        txt.setText(d.getCache());
        txt = findViewById(R.id.txtCapacidadDisco);
        txt.setText(d.getCapacity());
        txt = findViewById(R.id.txtPrecioDisco);
        txt.setText(d.getPrice_usd() + "");
        txt = findViewById(R.id.txtTipoDisco);
        txt.setText(d.getType());
        txt = findViewById(R.id.txtFactorFormaDisco);
        txt.setText(d.getForm_factor());
    }

    private void mostrarMemoria() {
        MemoriaRam m = (MemoriaRam) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombreRam);
        txt.setText(m.getName());
        txt = findViewById(R.id.txtCapacidadRam);
        txt.setText(m.getCapacity() + "");
        txt = findViewById(R.id.txtLatenciaRam);
        txt.setText(m.getCas_latency());
        txt = findViewById(R.id.txtPrecioRam);
        txt.setText(m.getPrice_usd() + "");
        txt = findViewById(R.id.txtVelocidadRam);
        txt.setText(m.getSpeed() + "");
        txt = findViewById(R.id.txtFactorFormaRam);
        txt.setText(m.getForm_factor());
        txt = findViewById(R.id.txtNumModulosRam);
        txt.setText(m.getModules());
    }

    private void mostrarProcesador() {
        Procesador p = (Procesador) componente;
        TextView txt;
        txt = findViewById(R.id.txtNombreProcesador);
        txt.setText(p.getName());
        txt = findViewById(R.id.txtPrecioProcesador);
        txt.setText(p.getPrice() + "");
        txt = findViewById(R.id.txtSocketProcesador);
        txt.setText(p.getSocket());
        txt = findViewById(R.id.txtGigaherciosBaseProcesador);
        txt.setText(p.getCore_clock());
        txt = findViewById(R.id.txtGigaherciosOverclockedProcesador);
        txt.setText(p.getBoost_clock());
        txt = findViewById(R.id.txtGraficosIntegradosProcesador);
        if(p.getIntegrated_grafics() == null)
            txt.setText("Sin gráficos integrados");
        else
            txt.setText(p.getIntegrated_grafics());
    }
}