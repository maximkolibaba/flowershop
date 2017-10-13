package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.MainBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Autowired
    private MainBusinessService service;

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = service.login(JsonUtils.jsonToMap(req.getReader().readLine()));
//        HttpSession session = req.getSession(false);

        Map<String, String> data = new HashMap<>();
        if (user != null) {
//            session.setAttribute("user", user);
//            session.setAttribute("isAdmin", user.getIsAdmin());
            data.put("redirect", user.getIsAdmin() ? "/admin" : "/profile/info");
            // TODO user cookies
//        resp.addCookie(new Cookie("user", JsonUtils.toJson(user)));
//        resp.addCookie(new Cookie("isAdmin", user.getIsAdmin().toString()));
        }
        JsonUtils.loadToResponse(data, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (isAdmin != null) {
                resp.sendRedirect(isAdmin ? "admin" : "profile");
                return;
            }
        }
        req.getRequestDispatcher("login.html").forward(req, resp);
    }
}
