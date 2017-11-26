package com.spb.schooljava.controllers;

import com.spb.schooljava.dao.MoviesDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MoviesController", urlPatterns = {"movies"}, loadOnStartup = 1)
public class MoviesController extends HttpServlet {

    public static final String LOGIN_ATTR = "login";

    MoviesDAO moviesDAO = new MoviesDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        if (login != null) {
            session.setAttribute("login", login);
        }

        if (session.getAttribute(LOGIN_ATTR) == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("movies", moviesDAO.listAllMovies());

            request.getRequestDispatcher("movies.jsp").forward(request, response);
        }

    }
}