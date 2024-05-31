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

    public ListaAdaptador(List<ListaEventos> itemList, Context contexto, EventosGuardados eventosGuardados) {
        this.mInflater = LayoutInflater.from(contexto);
        this.contexto = contexto;
        this.mDato = itemList;
        this.eventoGuardado = eventosGuardados;
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
                        String nombreEvento = evento.getNombreEvento();
                        String descripcionEvento = evento.getDescripcion();
                        int prioridadEvento = evento.getPrioridad();
                        Log.d("LISTA EVENTOS", "Evento a borrar - Nombre: " + nombreEvento + ", Descripción: " + descripcionEvento + ", Prioridad: " + prioridadEvento);
                        if (eventoGuardado != null) {
                            Evento eventoEliminar = new Evento();
                            eventoEliminar.setNombre(nombreEvento);
                            eventoEliminar.setDescripcion(descripcionEvento);
                            eventoEliminar.setPrioridad(prioridadEvento);

                            // Llamar al método eliminarEvento con el evento a eliminar
                            String mensaje = eventoGuardado.eliminarEvento(eventoEliminar);
                            Log.d("LISTA EVENTOS", "BORRAR EVENTO - " + mensaje);
                            Toast.makeText(contexto, mensaje, Toast.LENGTH_LONG).show();

                            // Eliminar el evento de la lista y notificar al adaptador
                            mDato.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Log.e("ListaAdaptador", "El objeto eventoGuardado es nulo");
                            Toast.makeText(contexto, "Error: No se pudo eliminar el evento", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }


        void bindData(final ListaEventos item) {
            nombreEvento.setText(item.getNombreEvento());
            fechaEvento.setText(item.getFechaEvento().toString());
        }
    }
}

