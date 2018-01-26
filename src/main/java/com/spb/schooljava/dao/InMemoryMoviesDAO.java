package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.util.ArrayList;
import java.util.List;


public class InMemoryMoviesDAO extends MoviesDAO {

    public static final InMemoryMoviesDAO INSTANCE = new InMemoryMoviesDAO();

    private List<Movie> movies;

    private InMemoryMoviesDAO() {
        movies = new ArrayList<Movie>() {
            {
                add(new Movie("Летят журавли", 1957, "letyat_zhuravli.jpg"));
                add(new Movie("Девчата", 1961, "devchyata.jpg"));
                add(new Movie("Иван Васильевич меняет профессию", 1973, "ivan.jpg"));
            }
        };
    }

    @Override
    public List<Movie> listAllMovies() {
        return movies;
    }

    @Override
    public Movie getMovieById(int id) {
        return movies.get(id);
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
}
