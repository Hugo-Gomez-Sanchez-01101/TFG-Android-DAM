package com.example.apptfg.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.apptfg.ModificarComponenteActivity;
import com.example.apptfg.R;
import com.example.apptfg.entidad.Componente;
import com.example.apptfg.singletonEntities.ListaComponentesSingleton;
import java.util.List;

public class AdaptadorComponentesViewHolder extends RecyclerView.Adapter<AdaptadorComponentesViewHolder.ViewHolder>{
    private List<Componente> listaComponentes;
    private Context context;

    public AdaptadorComponentesViewHolder(Context context, List<Componente> listaComponentes) {
        this.context = context;
        this.listaComponentes = listaComponentes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Contructor:
        private Button btnSeleccionar;
        private Button btnVer;
        private TextView nombre;
        private TextView precio;
        public ViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.txtCartaNombre);
            precio = v.findViewById(R.id.txtCartaPrecio);
            btnSeleccionar = v.findViewById(R.id.btnSeleccionarComponente);
            btnVer = v.findViewById(R.id.btnVerComponente);
        }
    }


    @NonNull
    @Override
    public AdaptadorComponentesViewHolder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjeta_componente, parent, false);
        AdaptadorComponentesViewHolder.ViewHolder viewHolder = new AdaptadorComponentesViewHolder.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(AdaptadorComponentesViewHolder.ViewHolder holder, int position) {
        //holder es una clase que contiene los atributos del viewHolderXML
        //onBindViewHolder sirve como controlador para la vista
        holder.nombre.setText(ListaComponentesSingleton.getInstance().getListaComponentesSingleton().get(position).getNombre());
        holder.precio.setText(ListaComponentesSingleton.getInstance().getListaComponentesSingleton().get(position).getPrecio() + "");
        holder.btnVer.setOnClickListener(v -> verComponente(position));
        holder.btnSeleccionar.setOnClickListener(v -> seleccionar(position));
    }

    private void seleccionar(int position) {
        Componente componente = ListaComponentesSingleton.getInstance().getListaComponentesSingleton().get(position); // Obtén el objeto Componente según la posición del adaptador
        ModificarComponenteActivity activity = (ModificarComponenteActivity) context;
        activity.recibirComponente(componente); // Envía el objeto Componente a la actividad ModificarComponenteActivity
    }

    private void verComponente(int id) {
        ModificarComponenteActivity activity = (ModificarComponenteActivity) context;
        activity.verComponente(id);
    }

    private void eliminar(AdaptadorComponentesViewHolder.ViewHolder holder, int id){
//        Toast.makeText(holder.itemView.getContext(), "Eliminando ordenador ", Toast.LENGTH_SHORT).show();
//        ListaComponentesSingleton.getInstance().borrar(listaComponentes.get(id));
//        notifyDataSetChanged();
    }
    private void ver(AdaptadorComponentesViewHolder.ViewHolder holder, int id) {
//        Intent i = new Intent(holder.itemView.getContext(), OrdenadorGeneradoActivity.class);
//        i.putExtra("id", id);
//        holder.itemView.getContext().startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listaComponentes.size();
    }
}
