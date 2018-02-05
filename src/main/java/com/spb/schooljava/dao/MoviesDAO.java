package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.util.Collection;

public abstract class MoviesDAO {

    public abstract Collection<Movie> listAllMovies();

    public abstract Movie getMovieById(int id);

    public abstract void addMovie(Movie movie);

    public abstract int newId();
}
