package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.util.List;

public abstract class MoviesDAO {

    public abstract List<Movie> listAllMovies();

    public abstract Movie getMovieById(int id);

    public abstract void addMovie(Movie movie);
}
