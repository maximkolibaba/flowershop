package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Autowired
    private UserBusinessService service;

    @Override
    public void init() throws ServletException {
//        super.init(config);
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");

        if (req.getParameter("buttonRegister") != null) {
            resp.sendRedirect("/register");
            return;
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = service.login(login, password);
        Boolean correctLogIn = user != null;

        HttpSession session = req.getSession(false);
        Cookie correctLoginCookie = new Cookie("correctLogIn", correctLogIn.toString());
        resp.addCookie(correctLoginCookie);
        session.setAttribute("correctLogIn", correctLogIn);

        if (correctLogIn) {
            session.setAttribute("user", user);
            session.setAttribute("isAdmin", user.getIsAdmin());
            String str;
            str = URLEncoder.encode(new Gson().toJson(user, User.class), "UTF-8");
            resp.addCookie(new Cookie("user", str));
            str = user.getIsAdmin().toString();
            resp.addCookie(new Cookie("isAdmin", str));
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
