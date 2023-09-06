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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = ((HttpServletRequest) servletResponse);
            HttpServletResponse httpServletResponse = ((HttpServletResponse) servletResponse);

            HttpSession httpSession = httpServletRequest.getSession();
            if (Objects.nonNull(httpSession) && Objects.nonNull(httpSession.getAttribute("user"))) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            httpServletResponse.sendRedirect("/login.jsp");


        } catch(IOException | ServletException e) {
            logger.info("Error: " + e.getCause());
            throw e;
        }

    }
}