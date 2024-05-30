package com.example.supertasks.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.media.metrics.Event;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.supertasks.modelos.EventosGuardados;
import com.example.supertasks.modelos.Evento;

import com.example.supertasks.R;
import com.example.supertasks.ventanas.ActivityEditarEventos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListaAdaptador extends RecyclerView.Adapter<ListaAdaptador.ViewHolder> {
    private List<ListaEventos> mDato;
    private LayoutInflater mInflater;
    private Context contexto;
    private EventosGuardados eventoGuardado;

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
        ImageView iconEditar, btnBorrar;
        TextView nombreEvento, fechaEvento;

        Spinner editarPrioridad;

        ViewHolder(View itemView) {
            super(itemView);
            iconEditar = itemView.findViewById(R.id.btnEditar1);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
            nombreEvento = itemView.findViewById(R.id.tituloPendiente);
            fechaEvento = itemView.findViewById(R.id.txtFechaPendiente);
            editarPrioridad = itemView.findViewById(R.id.comboEditarPrioridad);

            iconEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ListaEventos evento = mDato.get(position);
                        Intent intent = new Intent(contexto, ActivityEditarEventos.class);
                        intent.putExtra("nombreEvento", evento.getNombreEvento());
                        intent.putExtra("descripcionEvento", evento.getDescripcion());
                        intent.putExtra("fechaEvento", evento.getFechaEvento());
                        Log.d("LISTA ADAPTADOR", "evento"+ evento.toString());
                        intent.putExtra("prioridadEvento", evento.getPrioridad());
                        contexto.startActivity(intent);
                    }
                }
            });

            btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ListaEventos evento = mDato.get(position);
                        Evento eventoEliminnar = new Evento();
                        eventoEliminnar.setNombre(evento.getNombreEvento());
                        eventoEliminnar.setDescripcion(evento.getDescripcion());
                        eventoEliminnar.setPrioridad(evento.getPrioridad());
                        String mensaje = eventoGuardado.eliminarEvento(eventoEliminnar);
                        Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show();
                        notifyItemRemoved(position);
                    }
                }
            });
        }

//        private Date convertStringToDate(String dateString) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                return sdf.parse(dateString);
//            } catch (ParseException e) {
//                e.printStackTrace();
//                return new Date();
//            }
//        }


        void bindData(final ListaEventos item) {
            nombreEvento.setText(item.getNombreEvento());
            fechaEvento.setText(item.getFechaEvento().toString());
        }
    }
}

