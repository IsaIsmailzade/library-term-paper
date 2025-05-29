package library.servlets;

import library.dto.CreateUserDto;
import library.exception.ValidationException;
import library.service.UserService;
import library.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static library.util.UrlPath.REGISTER;

@WebServlet(REGISTER)
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateUserDto userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .phone(req.getParameter("phone"))
                .build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            req.setAttribute("name", userDto.getName());
            req.setAttribute("surname", userDto.getSurname());
            req.setAttribute("email", userDto.getEmail());
            req.setAttribute("password", userDto.getPassword());
            req.setAttribute("phone", userDto.getPhone());
            doGet(req, resp);
        }
    }
}
