package library.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import library.dto.CreateBookDto;
import library.exception.ValidationException;
import library.service.BookService;
import library.util.JspHelper;

import java.io.IOException;

import static library.util.UrlPath.ADMIN;

@WebServlet(ADMIN)
public class AdminPageServlet extends HttpServlet {

    BookService bookService = BookService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                addBook(req, resp);
            } else if ("delete".equals(action)) {
                deleteBook(req, resp);
            }
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        try {
            Long idParam = Long.parseLong(id);
            bookService.delete(idParam);
            req.setAttribute("delete", "Book was successfully deleted");
            req.getRequestDispatcher(JspHelper.getPath("adminPage"))
                    .forward(req, resp);
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        CreateBookDto createBookDto = CreateBookDto.builder()
                .title(req.getParameter("title"))
                .author(req.getParameter("author"))
                .description(req.getParameter("description"))
                .downloadFb2(req.getParameter("downloadFb2"))
                .downloadEpub(req.getParameter("downloadEpub"))
                .downloadPdf(req.getParameter("downloadPdf"))
                .downloadDocx(req.getParameter("downloadDocx"))
                .downloadMobi(req.getParameter("downloadMobi"))
                .read(req.getParameter("read"))
                .build();

        try {
            bookService.create(createBookDto);
            req.setAttribute("add", "Book was successfully added");
            req.getRequestDispatcher(JspHelper.getPath("adminPage"))
                    .forward(req, resp);
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("adminPage"))
                .forward(req, resp);
    }
}
