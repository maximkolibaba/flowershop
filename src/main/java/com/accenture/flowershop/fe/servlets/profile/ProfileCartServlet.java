package com.accenture.flowershop.fe.servlets.profile;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;
import com.accenture.flowershop.fe.CartItem;
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

@WebServlet(urlPatterns = "/profile/cart")
public class ProfileCartServlet extends HttpServlet {
    @Autowired
    private OrderBusinessService orderService;

    @Autowired
    private FlowerBusinessService flowerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        Cart cart = (Cart) session.getAttribute("cart");

        if (req.getParameter("buttonOrder") != null) {
            User user = (User) session.getAttribute("user");
            Order order = orderService.createOrder(cart, user);
            if (order != null) {
                cart.clear();
                req.getSession(false).setAttribute("cart", cart);
                resp.sendRedirect("/profile/orders");
                return;
            }
        } else {
            String flowerName = req.getParameterNames().nextElement();
            if (flowerName != null) {
                flowerName = flowerName.substring(6); // TODO
            }
            for (CartItem item : cart) {
                if (req.getParameter("remove" + item.getFlower().getName()) != null) {
                    cart.removeFromCart(item);
                    flowerService.returnToStock(item.getFlower(), item.getAmount());
                    req.getSession(false).setAttribute("cart", cart);
                    break;
                }

            }
        }
        resp.sendRedirect("/profile/cart");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("../pages/profile/cart.jsp").forward(req, resp);
    }
}
