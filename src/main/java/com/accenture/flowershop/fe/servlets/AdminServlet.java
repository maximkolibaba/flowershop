package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.business.MainBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
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

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Autowired
    private OrderBusinessService orderBusinessService;

    @Autowired
    private MainBusinessService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderBusinessService.getAllOrders();

        for (Order order : orders) {
            if (req.getParameter("s" + order.getId()) != null
                    || req.getParameter("c" + order.getId()) != null) {
                service.updateOrderStatus(order);
                break;
            }
//            if (req.getParameter("s" + order.getId()) != null) {
//                // ship
//                orderBusinessService.setStatus(order, OrderStatus.SHIPPED);
//                break;
//            } else if (req.getParameter("c" + order.getId()) != null) {
//                // complete
//                orderBusinessService.setStatus(order, OrderStatus.COMPLETED);
//                break;
//            }
        }
        resp.sendRedirect("/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allOrders", orderBusinessService.getAllOrders());
        req.getRequestDispatcher("pages/profile/admin.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
}
