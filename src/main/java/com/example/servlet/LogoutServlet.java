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
        value = "/logout",
        initParams = {
                @WebInitParam(name = "LOGIN_JSP_PATH", value = "./login.jsp")
        }
)
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogoutServlet.class.getName());
    private String loginJspPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginJspPath = config.getInitParameter("LOGIN_JSP_PATH");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect(loginJspPath);

        } catch(Exception e) {
            logger.info("Error: " + e.getCause());
        }

    }
}
