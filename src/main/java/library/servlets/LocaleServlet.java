package library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static library.util.UrlPath.LOCALE;
import static library.util.UrlPath.LOGIN;

@WebServlet(LOCALE)
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String language = req.getParameter("lang");

        req.getSession().setAttribute("lang", language);

        String prevPage = req.getHeader("referer");
        String page = prevPage != null ? prevPage : LOGIN;

        page = page.replaceAll("(\\?|&)lang=[^&]*", "");

        page = page.replaceAll("[&?]+$", "");

        String separator = page.contains("?") ? "&" : "?";
        resp.sendRedirect(page + separator + "lang=" + language);
    }
}
