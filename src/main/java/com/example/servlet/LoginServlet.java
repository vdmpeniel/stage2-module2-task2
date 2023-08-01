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
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        if (Objects.isNull(session.getAttribute("user"))) {
            dispatcher = request.getRequestDispatcher("/login.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/user/hello.jsp");
        }
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher;
        if (Users.getInstance().getUsers().contains(login) && !password.isEmpty()){
            request.getSession().setAttribute("user", login);
            dispatcher = request.getRequestDispatcher("/user/hello.jsp");
        } else {
            dispatcher = request.getRequestDispatcher("/login.jsp");
        }
        dispatcher.forward(request, response);
    }
}
