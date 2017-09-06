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
        // button "Register"
        if (req.getParameter("buttonRegister") != null) {
            resp.sendRedirect("/register");
            return;
        }

        User user = service.login(req.getParameter("login"), req.getParameter("password"));
        boolean correctLogIn = user != null;

        // correctLogin служит для одноразового оповещения о неправильном входе
        // isLoggedIn хранится в течение всей сессии
        HttpSession session = req.getSession(false);
        session.setAttribute("correctLogIn", correctLogIn);
        session.setAttribute("isLoggedIn", correctLogIn);

        if (correctLogIn) {
            session.setAttribute("user", user);
            resp.sendRedirect("/profile/info");
        } else {
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean logged = (Boolean) req.getSession(false).getAttribute("isLoggedIn");
        if (logged == null || !logged) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("profile");
        }
    }
}
