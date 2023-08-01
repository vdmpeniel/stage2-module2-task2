package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (Objects.nonNull(request)) {
            HttpSession session = ((HttpServletRequest) response).getSession();
            if (Objects.nonNull(session)) {
                Object user = session.getAttribute("user");
                if (Objects.isNull(user)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    if (Objects.nonNull(dispatcher)) {
                        dispatcher.forward(request, response);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}