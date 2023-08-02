package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@WebFilter(
        urlPatterns = "/user/*",
        initParams = {
                @WebInitParam(name = "LOGIN_JSP_PATH", value = "/login.jsp")
        }
)
public class AuthFilter implements Filter {
    private static final Logger logger = Logger.getLogger(AuthFilter.class.getName());
    private String loginJspPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        loginJspPath = filterConfig.getInitParameter("LOGIN_JSP_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) response).getSession();

            if (Objects.isNull(session.getAttribute("user"))) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(loginJspPath);
                dispatcher.forward(request, response);
            }

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }
        chain.doFilter(request, response);
    }
}