package com.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.Objects;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent e){
       ServletContext context = e.getServletContext();
       if (Objects.nonNull(context)) {
           context.setAttribute("servletTimeInit", LocalDateTime.now());
       }
    }
}
