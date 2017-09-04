package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.business.user.UserRegisterResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Autowired
    private UserBusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String address = req.getParameter("address");

        List<UserRegisterResult> results = service.register(login, password, firstName, lastName, address);

        if (results.contains(UserRegisterResult.SUCCESS)) {
            resp.sendRedirect("successfulRegister.html");
            return;
        }

        req.getSession().setAttribute("noLogin", results.contains(UserRegisterResult.NO_LOGIN));
        req.getSession().setAttribute("noPassword", results.contains(UserRegisterResult.NO_PASSWORD));
        req.getSession().setAttribute("loginIsUsed", results.contains(UserRegisterResult.LOGIN_IS_USED));
        req.getSession().setAttribute("notFullInfo", results.contains(UserRegisterResult.INCOMPLETE_USER_INFO));

        resp.sendRedirect("register");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}
