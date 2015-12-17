package com.example.vesprada.controlpelicula.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.R;

import java.util.ArrayList;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    private ArrayList<Pelicula> peliculas;

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPortada;
        private TextView tvNombrePelicula;
        private TextView tvPuntuacionPelicula;
        private TextView tvEstadoPelicula;

        public PeliculaViewHolder(View itemView) {
            super(itemView);

            ivPortada = (ImageView) itemView.findViewById(R.id.ivPortada);
            tvNombrePelicula = (TextView) itemView.findViewById(R.id.tvNombrePelicula);
            tvPuntuacionPelicula = (TextView) itemView.findViewById(R.id.tvPuntuacionPelicula);
            tvEstadoPelicula = (TextView) itemView.findViewById(R.id.tvEstadoPelicula);
        }

        public void bindPelicula(Pelicula pelicula) {

            ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier(pelicula.portada, "drawable", itemView.getContext().getPackageName()));

            if (ivPortada.getDrawable() == null) {
                ivPortada.setImageResource(itemView.getContext().getResources().getIdentifier("pe_null", "drawable", itemView.getContext().getPackageName()));
            }

            tvNombrePelicula.setText(pelicula.nombre);
            tvPuntuacionPelicula.setText((int) pelicula.puntuacion);
            tvEstadoPelicula.setText(pelicula.estado);
        }
    }

    public PeliculaAdapter (ArrayList<Pelicula> peliculas){
        this.peliculas = peliculas;
    }

    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);

        PeliculaViewHolder pvh = new PeliculaViewHolder(itemView);

        return pvh;
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder viewHolder, int pos){

        Pelicula item = peliculas.get(pos);

        viewHolder.bindPelicula(item);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }
}
