package com.example.vesprada.controlpelicula;

public class PeliculaParser {

    private ArrayList<Pelicula> peliculas;
    private String nombrePelicula;
    private String puntuacionPelicula;
    private String imagenPelicula;
    private int estadoPelicula;

    public PeliculaParser(ArrayList<Pelicula> peliculas) throws Exception {
        this.peliculas = peliculas;

        peliculas.add(new Pelicula(nombrePelicula,puntuacionPelicula,imagenPelicula,estadoPelicula));
    }
}