package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.JsonUtils;
import com.accenture.flowershop.fe.Redirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.validation.constraints.AssertFalse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Autowired
    private UserBusinessService service;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = service.login(login, password);
        Boolean correctLogIn = user != null;

        HttpSession session = req.getSession(false);
        Cookie correctLoginCookie = new Cookie("correctLogIn", correctLogIn.toString());
        resp.addCookie(correctLoginCookie);
        session.setAttribute("correctLogIn", correctLogIn);

        if (!correctLogIn) {
            resp.sendRedirect("login");
            return;
        }
        session.setAttribute("user", user);
        session.setAttribute("isAdmin", user.getIsAdmin());
        resp.addCookie(new Cookie("user", JsonUtils.toJson(user)));
        resp.addCookie(new Cookie("isAdmin", user.getIsAdmin().toString()));
        resp.sendRedirect(user.getIsAdmin() ? "admin" : "/profile/info");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        if (!Redirect.fromAdminPage(req, resp)) {
//            resp.sendRedirect("admin");
//        }
        Boolean isAdmin = (Boolean) req.getSession(false).getAttribute("isAdmin");
        if (isAdmin != null) {
            resp.sendRedirect(isAdmin ? "admin" : "profile");
            return;
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
