package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.access.user.UserDAO;
import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/RestLoginRequest/*")
public class RestLoginRequest extends HttpServlet{
    @Autowired
    private UserBusinessService service;

    @Autowired
    private UserDAO dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        boolean flag = dao.getByLogin(username) == null;
        resp.setContentType("text/plain");
        resp.getWriter().write(flag ? "yes" : "no");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        boolean flag = dao.getByLogin(username) == null;
        resp.setContentType("text/plain");
        resp.getWriter().write(flag ? "yes" : "no");
    }
}
