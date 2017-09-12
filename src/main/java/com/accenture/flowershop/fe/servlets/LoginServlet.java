package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Autowired
    private UserBusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("buttonRegister") != null) {
            resp.sendRedirect("/register");
            return;
        }

        User user = service.login(req.getParameter("login"), req.getParameter("password"));
        boolean correctLogIn = user != null;

        HttpSession session = req.getSession(false);
        session.setAttribute("correctLogIn", correctLogIn);

        if (correctLogIn) {
            session.setAttribute("user", user);
            session.setAttribute("isAdmin", user.getIsAdmin());
            if (user.getIsAdmin()) {
                resp.sendRedirect("admin");
            } else {
                resp.sendRedirect("/profile/info");
            }
        } else {
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Boolean isAdmin = (Boolean) req.getSession(false).getAttribute("isAdmin");
            if (isAdmin) {
                resp.sendRedirect("admin");
            } else {
                resp.sendRedirect("profile");
            }
        } catch (NullPointerException ex) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
