package com.example.apptfg;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.adaptador.AdaptadorOrdenadorVH;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.singletonEntities.ListaOrdenadoresSingleton;

import java.util.List;


public class ListaOrdenadoresActivity extends FatherView {
    private RecyclerView recyclerViewUser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordenadores);
        darFuncionalidadMenu();
        setupRecicler();
    }

    private void setupRecicler() {
        recyclerViewUser = findViewById(R.id.recyclerViewOrdenador);
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.VERTICAL,
                        false));
        List<Ordenador> listaOrdenadores = ListaOrdenadoresSingleton.getInstance().getListaOrdenadores();
        AdaptadorOrdenadorVH adaptadorOrdenadorVH = new AdaptadorOrdenadorVH(listaOrdenadores);
        recyclerViewUser.setAdapter(adaptadorOrdenadorVH);
    }


    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewUser.getAdapter().notifyDataSetChanged();
    }
}
