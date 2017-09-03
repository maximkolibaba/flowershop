package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import com.accenture.flowershop.be.business.user.UserBusinessServiceImpl;
import com.accenture.flowershop.be.entity.user.User;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().invalidate();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        UserBusinessService service = context.getBean(UserBusinessServiceImpl.class);

        User user = service.login(login, password);
        boolean isLoggedIn = user != null;

        req.getSession().setAttribute("isLoggedIn", isLoggedIn);

        if (isLoggedIn) {
            resp.sendRedirect("flowers");
        } else {
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
