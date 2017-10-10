package com.accenture.flowershop.fe.servlets.profile;

import com.accenture.flowershop.be.business.MainBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
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
import java.util.List;

@WebServlet(urlPatterns = "/profile/orders")
public class ProfileOrdersServlet extends HttpServlet {
    @Autowired
    private MainBusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderString = req.getParameterNames().nextElement();
        if (!StringUtils.isEmpty(orderString)) {
            long orderId = Long.parseLong(orderString.substring(1));
            switch (orderString.charAt(0)) {
                case 'p':
                case 'd':
                    service.updateOrderStatus(orderId);
                    break;
                case 'c':
                    service.cancelOrder(orderId);
                    break;
            }
        }

        resp.sendRedirect("/profile/orders");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = service.getUserOrders((User) req.getSession(false).getAttribute("user"));
        req.getSession(false).setAttribute("orders", orders);
        req.getRequestDispatcher("../pages/profile/orders.jsp").forward(req, resp);
    }
}
