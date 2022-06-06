package com.example.meteorologyapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meteorologyapp.Modelos.Entidades.EstadoMetereologico;

import java.util.LinkedList;

public class AdaptadorTablaRV extends RecyclerView.Adapter<AdaptadorTablaRV.TablaRVViewHolder> {

    private Activity context;
    private LinkedList<EstadoMetereologico> estadosMetereologicos;
    private String variable;

    public AdaptadorTablaRV(Activity context, LinkedList<EstadoMetereologico> estadosMetereologicos, String variable) {
        this.context = context;
        this.estadosMetereologicos = estadosMetereologicos;
        this.variable = variable;
    }

    @NonNull
    @Override
    public TablaRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        TablaRVViewHolder tablaRVViewHolder = new TablaRVViewHolder(view);
        return tablaRVViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TablaRVViewHolder holder, int position) {
        final EstadoMetereologico estadoMetereologico = estadosMetereologicos.get(position);
        holder.tv_fecha.setText(estadoMetereologico.getFecha());
        if(variable.equals("humedad")){
            holder.tv_variable.setText(estadoMetereologico.getHumedad() + "");
        }else if(variable.equals("precipitacion")){
            holder.tv_variable.setText(estadoMetereologico.getPrecipitacion() + "");
        }else if(variable.equals("direccion")){
            holder.tv_variable.setText(estadoMetereologico.getDireccionViento());
        }else if (variable.equals("velocidad")){
            holder.tv_variable.setText(estadoMetereologico.getVelocidadViento() + "");
        }else{
            holder.tv_variable.setText(estadoMetereologico.getTemperatura()+"");
        }

    }

    @Override
    public int getItemCount() {
        return estadosMetereologicos.size();
    }

    public class TablaRVViewHolder extends RecyclerView.ViewHolder {

        TextView tv_fecha, tv_variable;

        public TablaRVViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_fecha = itemView.findViewById(R.id.tv_fecha);
            tv_variable = itemView.findViewById(R.id.tv_variable);
        }
    }
}
