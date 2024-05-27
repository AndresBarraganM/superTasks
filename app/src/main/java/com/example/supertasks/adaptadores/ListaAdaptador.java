package com.example.supertasks.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supertasks.R;
import com.example.supertasks.ventanas.ActivityEditarEventos;

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
    public int getItemCount() {
        return mDato.size();
    }

    @Override
    public ListaAdaptador.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lista_elementos_eventos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListaAdaptador.ViewHolder holder, final int position) {
        holder.bindData(mDato.get(position));
    }

    public void setItems(List<ListaEventos> items) {
        mDato = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconEditar;
        TextView nombreEvento, fechaEvento;

        ViewHolder(View itemView) {
            super(itemView);
            iconEditar = itemView.findViewById(R.id.btnEditar1);
            nombreEvento = itemView.findViewById(R.id.tituloPendiente);
            fechaEvento = itemView.findViewById(R.id.txtFechaPendiente);

            iconEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ListaEventos evento = mDato.get(position);
                        Intent intent = new Intent(contexto, ActivityEditarEventos.class);
                        intent.putExtra("nombreEvento", evento.getNombreEvento());
                        intent.putExtra("fechaEvento", evento.getFechaEvento());
                        contexto.startActivity(intent);
                    }
                }
            });
        }

        void bindData(final ListaEventos item) {
            nombreEvento.setText(item.getNombreEvento());
            fechaEvento.setText(item.getFechaEvento());
        }
    }
}
