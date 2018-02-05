package com.spb.schooljava.dao;

import com.spb.schooljava.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class H2DAO extends MoviesDAO {

    public static final H2DAO INSTANCE = new H2DAO();

    private final Connection conn;

    private final PreparedStatement sqlListAllMovies;
    private final PreparedStatement sqlgetMovieById;
    private final PreparedStatement sqlAddMovie;

    private final PreparedStatement sqlNewId;


    private H2DAO() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            sqlListAllMovies = conn.prepareStatement("SELECT * FROM MOVIES");
            sqlgetMovieById = conn.prepareStatement("SELECT * FROM movies WHERE id=?");
            sqlAddMovie = conn.prepareStatement("INSERT INTO movies VALUES (?, ?, ?, ?)");

            sqlNewId = conn.prepareStatement("SELECT movies_id.NEXTVAL");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    synchronized public Collection<Movie> listAllMovies() {
        try {
            ResultSet rs = sqlListAllMovies.executeQuery();
            ArrayList<Movie> moviesList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                String image = rs.getString("image");

                moviesList.add(new Movie(id, title, year, image));
            }

            return moviesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    synchronized public Movie getMovieById(int id) {
        try {
            sqlgetMovieById.setInt(1, id);
            ResultSet rs = sqlListAllMovies.executeQuery();

            rs.next();

            String title = rs.getString("title");
            int year = rs.getInt("year");
            String image = rs.getString("image");

            return new Movie(id, title, year, image);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    synchronized public void addMovie(Movie movie) {
        try {
            sqlAddMovie.setInt(1, movie.getId());
            sqlAddMovie.setString(2, movie.getTitle());
            sqlAddMovie.setInt(3, movie.getYear());
            sqlAddMovie.setString(4, movie.getImage());

            sqlAddMovie.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int newId() {
        try {
            ResultSet rs = sqlNewId.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
