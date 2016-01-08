package com.example.vesprada.controlpelicula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vesprada.controlpelicula.R;
import com.example.vesprada.controlpelicula.dao.ActorDAO;
import com.example.vesprada.controlpelicula.dao.Actor_PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.DirectorDAO;
import com.example.vesprada.controlpelicula.dao.GeneroDAO;
import com.example.vesprada.controlpelicula.dao.PeliculaDAO;
import com.example.vesprada.controlpelicula.dao.ProductorDAO;
import com.example.vesprada.controlpelicula.modelo.Actor;
import com.example.vesprada.controlpelicula.modelo.Actor_Pelicula;
import com.example.vesprada.controlpelicula.modelo.Director;
import com.example.vesprada.controlpelicula.modelo.Genero;
import com.example.vesprada.controlpelicula.modelo.Pelicula;
import com.example.vesprada.controlpelicula.modelo.Productor;

import java.util.ArrayList;

public class DetallePelicula extends AppCompatActivity {

    private TextView tvTituloDetalle;
    private ImageView ivPortadaDetalle;
    private TextView tvPuntuacionDetalle;
    private TextView tvAnyoDetalle;
    private TextView tvDuracionDetalle;
    private TextView tvGeneroDetalle;
    private TextView tvDirectorDetalle;
    private TextView tvProductorDetalle;
    private TextView tvActoresDetalle;
    private TextView tvSinopsisDetalle;
    private PeliculaDAO conectorPelicula = new PeliculaDAO(this);
    private GeneroDAO conectorGenero = new GeneroDAO(this);
    private ActorDAO conectorActor = new ActorDAO(this);
    private Actor_PeliculaDAO conectorActorPelicula = new Actor_PeliculaDAO(this);
    private ProductorDAO conectorProductor = new ProductorDAO(this);
    private DirectorDAO conectorDirector = new DirectorDAO(this);
    private int idPelicula;
    private ArrayList<Actor_Pelicula> listaActoresEnteros = new ArrayList<Actor_Pelicula>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallepelicula);

        tvTituloDetalle = (TextView)findViewById(R.id.tvTituloDetalle);
        ivPortadaDetalle = (ImageView)findViewById(R.id.ivPortadaDetalle);
        tvPuntuacionDetalle = (TextView)findViewById(R.id.tvPuntuacionDetalle);
        tvAnyoDetalle = (TextView)findViewById(R.id.tvAnyoDetalle);
        tvDuracionDetalle = (TextView)findViewById(R.id.tvDuracionDetalle);
        tvGeneroDetalle = (TextView)findViewById(R.id.tvGeneroDetalle);
        tvDirectorDetalle = (TextView)findViewById(R.id.tvDirectorDetalle);
        tvProductorDetalle = (TextView)findViewById(R.id.tvProductorDetalle);
        tvActoresDetalle = (TextView)findViewById(R.id.tvActoresDetalle);
        tvSinopsisDetalle = (TextView)findViewById(R.id.tvSinopsisDetalle);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idPelicula = extras.getInt("id_pelicula");
        }

        Pelicula pelicula = conectorPelicula.getPeliculaById(idPelicula);

        ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier(pelicula.portada, "drawable", this.getApplicationContext().getPackageName()));

        if (ivPortadaDetalle.getDrawable() == null) {
            ivPortadaDetalle.setImageResource(this.getApplicationContext().getResources().getIdentifier("pe_null", "drawable", this.getApplicationContext().getPackageName()));
        }

        tvTituloDetalle.setText(pelicula.nombre);
        tvPuntuacionDetalle.setText(String.valueOf(pelicula.puntuacion));
        tvAnyoDetalle.setText(String.valueOf(pelicula.anyo));
        tvDuracionDetalle.setText(String.valueOf(pelicula.duracion));

        Director director = conectorDirector.getDirectorById(pelicula.id_director);
        tvDirectorDetalle.setText(String.valueOf(director.nombre_completo));

        Genero genero = conectorGenero.getGeneroById(pelicula.id_genero);
        tvGeneroDetalle.setText(String.valueOf(genero.nombre));

        Productor productor = conectorProductor.getProductorById(pelicula.id_productor);
        tvProductorDetalle.setText(productor.nombre);

        tvSinopsisDetalle.setText(pelicula.sinopsis);


        listaActoresEnteros = conectorActorPelicula.getIdActorByIdPelicula(idPelicula);
        String listaActores = "";
        for (int i = 0; i < listaActoresEnteros.size(); i++){
            Actor actor = conectorActor.getActorById(listaActoresEnteros.get(i).id_actor);
            if (i < listaActoresEnteros.size()-1){
                listaActores = listaActores + actor.nombre_completo + ", ";
            }
            else{
                listaActores = listaActores + actor.nombre_completo;
            }
        }
        tvActoresDetalle.setText(listaActores);
    }
}