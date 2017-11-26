package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kodoo on 26.11.17.
 */
public class InMemoryMoviesDAO extends MoviesDAO {

    private List<Movie> movies;

    public InMemoryMoviesDAO() {
        movies = new ArrayList<Movie>() {{
            add(new Movie("Летят журавли", 1957));
            add(new Movie("Девчата", 1961));
            add(new Movie("Иван Васильевич меняет профессию", 1973));
        }};
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
