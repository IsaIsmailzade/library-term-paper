package library.filter;

import library.dto.AdminDto;
import library.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static library.util.UrlPath.*;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(REGISTER, LOGIN, LOCALE, ADMIN_LOGIN);
    private static final Set<String> PUBLIC_RESOURCES = Set.of("/css/", "/js/", "/images/");

    private boolean isPublicResource(String requestURI) {
        return PUBLIC_RESOURCES.stream().anyMatch(requestURI::startsWith);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(requestURI) || isUserLoggedIn(servletRequest) || isAdminLoggedIn(servletRequest) || isPublicResource(requestURI)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletResponse) servletResponse).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
        }
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return  user != null;
    }

    private boolean isAdminLoggedIn(ServletRequest servletRequest) {
        AdminDto admin = (AdminDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("admin");
        return  admin != null;
    }

    private boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }

}
