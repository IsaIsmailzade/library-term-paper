package library.servlets;

import library.dto.LoginUserDto;
import library.dto.UserDto;
import library.service.UserService;
import library.util.JspHelper;
import library.validator.LoginUserValidator;
import library.validator.ValidationResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static library.util.UrlPath.CATALOG;
import static library.util.UrlPath.LOGIN;

@WebServlet(LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final LoginUserValidator loginValidator = LoginUserValidator.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LoginUserDto loginUserDto = new LoginUserDto(email, password);

        ValidationResult validationResult = loginValidator.isValid(loginUserDto);

        if (!validationResult.isValid()) {
            req.setAttribute("errors", validationResult.getErrors());
            req.getRequestDispatcher(JspHelper.getPath("login"))
                    .forward(req, resp);
            return;
        }

        userService.login(email, password)
                .ifPresentOrElse(
                        user -> onLoginSuccess(user, req, resp),
                        () -> onLoginFail(req, resp)
                );
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect(CATALOG);
    }
}
