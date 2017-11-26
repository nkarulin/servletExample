package com.spb.schooljava.controllers;

import com.spb.schooljava.dao.InMemoryMoviesDAO;
import com.spb.schooljava.dao.MoviesDAO;
import com.spb.schooljava.models.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MoviesController", urlPatterns = {"movies/*"}, loadOnStartup = 1)
public class MoviesController extends HttpServlet {

    public static final String LOGIN_ATTR = "login";

    static MoviesDAO moviesDAO = new InMemoryMoviesDAO();

    //list movies
    //GET/movies/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkLogin(request, response)) return;

        request.setAttribute("movies", moviesDAO.listAllMovies());
        request.getRequestDispatcher("movies.jsp").forward(request, response);
    }

    //add a movie
    //POST/movies/
    //params: title, year
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!checkLogin(request, response)) return;

        String title = request.getParameter("title");
        String year = request.getParameter("year");

        if (title == null || year == null) {
            doGet(request, response);
        }

        Movie movie = new Movie(title, Integer.parseInt(year));
        moviesDAO.addMovie(movie);

        doGet(request, response);
    }

    private boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN_ATTR);
        if (login != null) {
            session.setAttribute(LOGIN_ATTR, login);
        }

        if (session.getAttribute(LOGIN_ATTR) == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}