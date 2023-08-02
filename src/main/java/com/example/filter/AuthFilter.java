package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@WebFilter(
        urlPatterns = "/user/*"
)
public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) response).getSession();
            if (Objects.isNull(session.getAttribute("user"))) {
                ((HttpServletResponse) response).sendRedirect("/login.jsp");
            }

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }
        chain.doFilter(request, response);
    }
}