package library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static library.util.UrlPath.*;

@WebServlet(LOGOUT)
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectPath;
        if (req.getSession().getAttribute("user") != null) {
            redirectPath = LOGIN;
        } else if (req.getSession().getAttribute("admin") != null) {
            redirectPath = ADMIN_LOGIN;
        } else {
            redirectPath = "/";
        }

        req.getSession().invalidate();
        resp.sendRedirect(redirectPath);
    }
}
