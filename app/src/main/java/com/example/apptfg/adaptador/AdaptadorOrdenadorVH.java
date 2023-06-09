package com.example.apptfg.adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptfg.ListaOrdenadoresActivity;
import com.example.apptfg.VerOrdenadorActivity;
import com.example.apptfg.R;
import com.example.apptfg.entidad.Ordenador;
import com.example.apptfg.singletonEntities.ListaOrdenadoresSingleton;
import com.example.apptfg.singletonEntities.OrdenadorGeneradoSingleton;

import java.util.List;

public class AdaptadorOrdenadorVH extends RecyclerView.Adapter<AdaptadorOrdenadorVH.ViewHolder> {

    private List<Ordenador> listaOrdenadores;
    private ListaOrdenadoresActivity activity;

    public AdaptadorOrdenadorVH(List<Ordenador> listaOrdenadores, ListaOrdenadoresActivity activity) {
        this.listaOrdenadores = listaOrdenadores;
        this.activity = activity;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Contructor:
        private Button btnEliminar;
        private Button btnVer;
        private TextView uso;
        private TextView nombre;

        public ViewHolder(View v) {
            super(v);
            uso = v.findViewById(R.id.usoPc);
            nombre = v.findViewById(R.id.nombrePc);
            btnEliminar = v.findViewById(R.id.btnEliminarOrdenador);
            btnVer = v.findViewById(R.id.btnVerOrdenador);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ordenador_view_holder, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder es una clase que contiene los atributos del viewHolderXML
        //onBindViewHolder sirve como controlador para la vista
        holder.uso.setText(listaOrdenadores.get(position).getUso().toString());
        holder.nombre.setText(listaOrdenadores.get(position).getNombre());
        holder.btnEliminar.setOnClickListener(view -> eliminar(holder, position));
        holder.btnVer.setOnClickListener(view -> ver(holder, position));
    }

    private void eliminar(ViewHolder holder, int posicion) {
        ListaOrdenadoresSingleton.getInstance().getListaOrdenadores().remove(listaOrdenadores.get(posicion));
        activity.mostrarBorrado();
        notifyDataSetChanged();
    }

    private void ver(ViewHolder holder, int position) {
        Intent i = new Intent(holder.itemView.getContext(), VerOrdenadorActivity.class);
        i.putExtra("nuevo", false);
        OrdenadorGeneradoSingleton.getInstance().setOrdenador(ListaOrdenadoresSingleton.getInstance().getListaOrdenadores().get(position));
        holder.itemView.getContext().startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listaOrdenadores.size();
    }

}