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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.nonNull(request)) {
            HttpSession session = request.getSession();
            RequestDispatcher dispatcher = null;
            if (Objects.isNull(session) || Objects.isNull(session.getAttribute("user"))) {
                dispatcher = request.getRequestDispatcher("/login.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("/user/hello.jsp");
            }
            if (Objects.nonNull(dispatcher)) {
                dispatcher.forward(request, response);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Objects.nonNull(request)) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            RequestDispatcher dispatcher = null;
            if (Users.getInstance().getUsers().contains(login) && !password.isEmpty()) {
                HttpSession session = request.getSession();
                if (Objects.nonNull(session)) {
                    session.setAttribute("user", login);
                }
                dispatcher = request.getRequestDispatcher("/user/hello.jsp");
            } else {
                dispatcher = request.getRequestDispatcher("/login.jsp");
            }
            if (Objects.nonNull(dispatcher)) {
                dispatcher.forward(request, response);
            }
        }
    }
}
