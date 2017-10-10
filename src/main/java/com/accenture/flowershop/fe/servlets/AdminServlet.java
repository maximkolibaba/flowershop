package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.MainBusinessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Autowired
    private MainBusinessService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameterNames().nextElement();

        if (!StringUtils.isEmpty(orderId)) {
            service.updateOrderStatus(Long.parseLong(orderId));
        }

        resp.sendRedirect("/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allOrders", service.getAllOrders());
        req.getRequestDispatcher("pages/profile/admin.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
