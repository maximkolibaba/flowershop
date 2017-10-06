package com.accenture.flowershop.fe.servlets.profile;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
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
import java.util.List;

@WebServlet(urlPatterns = "/profile/catalog")
public class ProfileCatalogServlet extends HttpServlet {
    @Autowired
    private FlowerBusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        Cart cart = new Cart((Cart) session.getAttribute("cart"));
        List<Flower> flowers = service.getAllFlowers();

        for (Flower flower : flowers) {
            long id = flower.getId();
            String amountStr = req.getParameter(String.valueOf(id));
            Integer amount;
            if (amountStr.length() != 0 && (amount = Integer.parseInt(amountStr)) > 0) {
                cart.addToCart(new CartItem(service.order(flower, amount), amount));
            }
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("/profile/cart");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flowers", service.getAllFlowers());
        req.getRequestDispatcher("../pages/profile/catalog.jsp").forward(req, resp);
    }
}
