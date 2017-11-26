package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesDAO {

    public List<Movie> listAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Летят журавли", 1957));
            add(new Movie("Девчата", 1961));
            add(new Movie("Иван Васильевич меняет профессию", 1973));
        }};

    }
}
