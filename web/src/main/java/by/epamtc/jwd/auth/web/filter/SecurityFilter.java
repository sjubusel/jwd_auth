package by.epamtc.jwd.auth.web.filter;

import by.epamtc.jwd.auth.web.util.constant.CommandPaths;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epamtc.jwd.auth.web.util.constant.AppAttributes.*;

public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if ((session != null)
                && (session.getAttribute(SESSION_AUTH_DATA) != null)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + CommandPaths.LOGIN_GET);
        }
    }
}
