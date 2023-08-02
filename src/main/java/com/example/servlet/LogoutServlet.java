package com.example.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(
        value = "/logout"
)
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("./login.jsp");

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }

    }
}
