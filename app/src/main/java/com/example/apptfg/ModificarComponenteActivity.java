package com.example.apptfg;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptfg.adaptador.AdaptadorComponentesViewHolder;
import com.example.apptfg.entidad.Caja;
import com.example.apptfg.entidad.Componente;
import com.example.apptfg.entidad.DiscoDuro;
import com.example.apptfg.entidad.Disipador;
import com.example.apptfg.entidad.FuenteAlimentacion;
import com.example.apptfg.entidad.MemoriaRam;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.entidad.Procesador;
import com.example.apptfg.entidad.TarjetaGrafica;
import com.example.apptfg.singletonEntities.ListaComponentesSingleton;
import com.example.apptfg.singletonEntities.OrdenadorGeneradoSingleton;

public class ModificarComponenteActivity extends FatherView {
    private Ordenador ordenador;
    private Componente componente;
    private RecyclerView recyclerViewUser;
    private AdaptadorComponentesViewHolder adaptadorComponentesViewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_componente);
        darFuncionalidadMenu();
        setup();
        setUpRecycler();
    }

    private void setup() {
        ordenador = OrdenadorGeneradoSingleton.getInstance().getOrdenador();
        Intent i = getIntent();
        componente = (Componente) i.getSerializableExtra("componente");
        findViewById(R.id.btnVolverModificarComponente).setOnClickListener(v -> volver());
    }

    private void volver() {
        Intent i = new Intent(this, VerComponenteActivity.class);
        i.putExtra("componente", componente);
        startActivity(i);
        finish();
    }

    public void setUpRecycler(){
        recyclerViewUser = findViewById(R.id.recyclerViewComponentes);
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));
        for (Componente c:
             ListaComponentesSingleton.getInstance().getListaComponentesSingleton()) {
            System.out.println(c);
        }
        adaptadorComponentesViewHolder = new AdaptadorComponentesViewHolder(this, ListaComponentesSingleton.getInstance().getListaComponentesSingleton());
        recyclerViewUser.setAdapter(adaptadorComponentesViewHolder);
    }

    public void recibirComponente(Componente componente){
        if(componente instanceof Procesador){
            Procesador c = (Procesador) componente;
            ordenador.setProcesador(c);
        } else if(componente instanceof MemoriaRam){
            MemoriaRam c = (MemoriaRam) componente;
            ordenador.setMemoriaRam(c);
        } else if(componente instanceof DiscoDuro) {
            DiscoDuro c = (DiscoDuro) componente;
            ordenador.setDiscoDuro(c);
        } else if(componente instanceof Caja) {
            Caja c = (Caja) componente;
            ordenador.setCaja(c);
        } else if(componente instanceof Disipador) {
            Disipador c = (Disipador) componente;
            ordenador.setDisipador(c);
        } else if(componente instanceof FuenteAlimentacion) {
            FuenteAlimentacion c = (FuenteAlimentacion) componente;
            ordenador.setFuenteAlimentacion(c);
        } else if(componente instanceof TarjetaGrafica) {
            TarjetaGrafica c = (TarjetaGrafica) componente;
            ordenador.setTarjetaGrafica(c);
            System.out.println("MODIFICAR: " + ordenador.getTarjetaGrafica());
        }
        Intent i = new Intent(this, VerComponenteActivity.class);
        i.putExtra("componente", componente);
        startActivity(i);
        finish();
    }

    public void verComponente(int posicion) {
        Intent i = new Intent(this, VerComponenteActivity.class);
        i.putExtra("modificando", true);
        Componente componente = ListaComponentesSingleton.getInstance().getListaComponentesSingleton().get(posicion);
        i.putExtra("componente", componente);
        startActivity(i);
    }

//    private void agregarComponenteLayout(int veces) {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        LinearLayout contenedorLayout = findViewById(R.id.contenedorTarjetasLayout);
//
//        for (int i = 0; i < veces; i++) {
//            View componenteLayout = inflater.inflate(R.layout.tarjeta_componente, contenedorLayout, false);
//            contenedorLayout.addView(componenteLayout);
//
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//            componenteLayout.setLayoutParams(layoutParams);
//        }
//    }
}