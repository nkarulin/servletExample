package com.spb.schooljava.controllers;

import com.spb.schooljava.dao.InMemoryMoviesDAO;
import com.spb.schooljava.dao.MoviesDAO;
import com.spb.schooljava.models.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.spb.schooljava.controllers.MoviesController.checkLogin;

@WebServlet(name = "MoviePageController", urlPatterns = {"/movies/id/*"}, loadOnStartup = 1)
public class MoviePageController extends HttpServlet {

    private static Pattern ID_PATTERN = Pattern.compile(".*/movies/id/(.+)");

    private MoviesDAO moviesDAO = InMemoryMoviesDAO.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkLogin(request, response)) return;

        Movie movie = moviesDAO.getMovieById(id(request));
        request.setAttribute("movie", movie);
        request.getRequestDispatcher("/moviePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private int id(HttpServletRequest request) {

        Matcher matcher = ID_PATTERN.matcher(request.getRequestURL());
        if (!matcher.matches()) {
            throw new RuntimeException("No such id");
        }

        return Integer.parseInt(matcher.group(1));
    }
}
