package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.util.Collection;
import java.util.HashMap;


public class InMemoryMoviesDAO extends MoviesDAO {

    public static final InMemoryMoviesDAO INSTANCE = new InMemoryMoviesDAO();

    private static int idCount = 0;

    private HashMap<Integer, Movie> movies;

    private InMemoryMoviesDAO() {
        movies = new HashMap<>();

        movies.put(idCount++, new Movie(idCount, "Летят журавли", 1957, "letyat_zhuravli.jpg"));
        movies.put(idCount++, new Movie(idCount, "Девчата", 1961, "devchyata.jpg"));
        movies.put(idCount++, new Movie(idCount, "Иван Васильевич меняет профессию", 1973, "ivan.jpg"));
    }

    @Override
    public Collection<Movie> listAllMovies() {
        return movies.values();
    }

    @Override
    public Movie getMovieById(int id) {
        return movies.get(id);
    }

    @Override
    public void addMovie(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    @Override
    public int newId() {
        return idCount++;
    }
}
