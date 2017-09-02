package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        UserBusinessService service = context.getBean(UserBusinessServiceImpl.class);

        if (service.login(login, password) == null) {
            req.getRequestDispatcher("login.jsp").include(req, resp);
        } else {
            req.getRequestDispatcher("flowers").forward(req, resp);
        }
    }
}
