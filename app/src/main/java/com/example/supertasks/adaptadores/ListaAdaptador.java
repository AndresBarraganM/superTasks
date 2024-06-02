package com.example.supertasks.adaptadores;

import static com.example.supertasks.MainActivity.eventosLocales;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.metrics.Event;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.supertasks.modelos.EventosGuardados;
import com.example.supertasks.modelos.Evento;

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
        ImageView iconEditar, btnBorrar;
        TextView nombreEvento, fechaEvento;
        FrameLayout framePrioridad;
        Spinner editarPrioridad;

        ViewHolder(View itemView) {
            super(itemView);
            iconEditar = itemView.findViewById(R.id.btnEditar1);
            btnBorrar = itemView.findViewById(R.id.btnBorrar);
            nombreEvento = itemView.findViewById(R.id.tituloPendiente);
            fechaEvento = itemView.findViewById(R.id.txtFechaPendiente);
            framePrioridad = itemView.findViewById(R.id.framePrioridad);
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
                        Log.d("LISTA ADAPTADOR", "evento" + evento.toString());
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
                        int id_evento = evento.getId_evento();
                        Log.d("LISTA EVENTOS", "Evento a borrar - Nombre: " + nombreEvento + ", Descripción: " + descripcionEvento + ", Prioridad: " + prioridadEvento + ", ID" + evento.getId_evento());
                        if (eventosLocales != null) {


                            // Llamar al método eliminarEvento con el evento a eliminar
                            String mensaje = eventosLocales.eliminarEvento(id_evento);
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
            int color = obtenerColorPrioridad(item.getPrioridad());
            framePrioridad.setBackgroundResource(color);
        }

        // Metodo para cambiar el color de la prioridad
        private int obtenerColorPrioridad(int prioridad) {
            switch (prioridad) {
                case 2: // Alta
                    return R.drawable.frame_nivel_prioridad_alta;
                case 1: // Media
                    return R.drawable.frame_nivel_prioridad_media;
                case 0: // Baja
                    return R.drawable.frame_nivel_prioridad_baja;
                default:
                    return R.drawable.frame_nivel_prioridad_default; // Por si acaso
            }
        }
    }
}

