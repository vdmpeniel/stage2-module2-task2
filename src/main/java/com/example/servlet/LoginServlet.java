package com.example.servlet;

import com.example.Users;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@WebServlet(
        value = "/login"
)
public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            HttpSession session = httpServletRequest.getSession();
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
                httpServletResponse.sendRedirect("/login.jsp");
            } else {
                httpServletResponse.sendRedirect("/user/hello.jsp");
            }

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        try {
            String login = httpServletRequest.getParameter("login");
            String password = httpServletRequest.getParameter("password");
            if (!password.isEmpty() && Users.getInstance().getUsers().contains(login)) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user", login);
                httpServletResponse.sendRedirect("/user/hello.jsp");

            } else {
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(httpServletRequest, httpServletResponse);
            }

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }
    }
}
