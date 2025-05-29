package library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.service.BookService;
import library.util.JspHelper;

import java.io.IOException;

import static library.util.UrlPath.CATALOG;

@WebServlet(CATALOG)
public class CatalogServlet extends HttpServlet {

    BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("catalog"))
                .forward(req, resp);
    }
}
