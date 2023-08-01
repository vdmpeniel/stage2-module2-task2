package com.example.servlet;

import com.example.Users;
import com.example.filter.AuthFilter;

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AuthFilter.class.getName());


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher dispatcher = null;
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
                dispatcher = request.getRequestDispatcher("/login.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("/user/hello.jsp");
            }
            dispatcher.forward(request, response);

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            RequestDispatcher dispatcher = null;
            if (Users.getInstance().getUsers().contains(login) && !password.isEmpty()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", login);
                dispatcher = request.getRequestDispatcher("/user/hello.jsp");

            } else {
                dispatcher = request.getRequestDispatcher("/login.jsp");
            }
            dispatcher.forward(request, response);

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }
    }
}
