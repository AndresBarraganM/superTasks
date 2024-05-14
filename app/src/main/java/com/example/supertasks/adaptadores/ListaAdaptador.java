package com.example.supertasks.adaptadores;

// Esta clase permite vincular el recycler view utilizado en @layout

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supertasks.R;

import java.util.List;
public class ListaAdaptador extends RecyclerView.Adapter<ListaAdaptador.ViewHolder> {
    private List<ListaEventos> mDato;
    private LayoutInflater mInflater;
    private Context contexto;

    public ListaAdaptador(List<ListaEventos> itemList, Context contexto) {
        this.mInflater = LayoutInflater.from(contexto);
        this.contexto = contexto;
        this.mDato = itemList;
    }

    @Override
    public int getItemCount() {return mDato.size();}

    @Override
    public ListaAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_eventos, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ListaAdaptador.ViewHolder holder, final int position) {
        holder.bindData(mDato.get(position));
    }

    public void setItems(List<ListaEventos> items) {mDato = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconEditar;
        TextView nombreEvento, fechaEvento;
        ViewHolder(View itemView) {
            super(itemView);
            iconEditar = itemView.findViewById(R.id.btnEditar1);
            nombreEvento = itemView.findViewById(R.id.tituloPendiente);
            fechaEvento = itemView.findViewById(R.id.txtFechaPendiente);
        }

        void bindData(final ListaEventos item) {
            nombreEvento.setText(item.getNombreEvento());
            fechaEvento.setText(item.getFechaEvento());
        }
    }
}