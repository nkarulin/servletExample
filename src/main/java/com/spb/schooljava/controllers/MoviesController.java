package com.spb.schooljava.controllers;

import com.spb.schooljava.dao.H2DAO;
import com.spb.schooljava.dao.MoviesDAO;
import com.spb.schooljava.models.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@WebServlet(name = "MoviesController", urlPatterns = {"/movies"}, loadOnStartup = 1)
@MultipartConfig
public class MoviesController extends HttpServlet {

    public static final String LOGIN_ATTR = "login";

    static MoviesDAO moviesDAO = H2DAO.INSTANCE;

    //list movies
    //GET/movies/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkLogin(request, response)) return;

        request.setAttribute("movies", moviesDAO.listAllMovies());
        request.getRequestDispatcher("/movies.jsp").forward(request, response);
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
            //add nothing
            response.sendRedirect("movies");
        }

        Movie movie = new Movie(moviesDAO.newId(), title, Integer.parseInt(year));

        uploadImage(request).ifPresent(movie::setImage);

        moviesDAO.addMovie(movie);

        response.sendRedirect("movies");
    }

    private Optional<String> uploadImage(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">

        if (filePart == null) {
            return Optional.empty();
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();

        File file = new File(request.getServletContext().getRealPath("/") + "/pics/" + fileName);
        System.err.println(file.getAbsolutePath());
        Files.copy(fileContent, file.toPath());

        return Optional.of(fileName);

        // ... (do your job here)
    }

    static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String login = request.getParameter(LOGIN_ATTR);
        if (login != null) {
            session.setAttribute(LOGIN_ATTR, login);
        }

        if (session.getAttribute(LOGIN_ATTR) == null) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return false;
        } else {
            return true;
        }
    }
}