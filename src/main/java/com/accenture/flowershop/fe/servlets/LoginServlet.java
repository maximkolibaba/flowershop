package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.JsonUtils;
import com.accenture.flowershop.fe.Redirect;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.validation.constraints.AssertFalse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        JsonObject jsonObject = new Gson().fromJson(req.getReader(), JsonObject.class);

        //        // 1. get received JSON data from request
//        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
//        String json = "";
//        if(br != null){
//            json = br.readLine();
//        }


        User user = service.login(req.getParameter("login"), req.getParameter("password"));
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
//        resp.sendRedirect(user.getIsAdmin() ? "admin" : "/profile/info");
        resp.sendRedirect(user.getIsAdmin() ? "admin" : "/rest/" + user.getLogin() + "/info");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isAdmin = (Boolean) req.getSession(false).getAttribute("isAdmin");
        if (isAdmin != null) {
            resp.sendRedirect(isAdmin ? "admin" : "profile");
            return;
        }
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
