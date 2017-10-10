package com.accenture.flowershop.fe.servlets.profile;

import com.accenture.flowershop.be.business.MainBusinessService;
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
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/profile/catalog")
public class ProfileCatalogServlet extends HttpServlet {
    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private MainBusinessService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        Cart cart = new Cart((Cart) session.getAttribute("cart"));
        Map<Long, Integer> orderedFlowers = req.getParameterMap().entrySet().stream()
                .filter(map -> !map.getValue()[0].equals("0"))
                .collect(Collectors.toMap(p -> Long.parseLong(p.getKey()), p -> Integer.parseInt(p.getValue()[0])));

        // TODO: [REFACTOR] better to make function which will take ids of flowers and fill the cart in
        List<Flower> flowers = (List<Flower>) service.getFlowers(orderedFlowers.keySet());
        for (Flower flower : flowers) {
            int amount = orderedFlowers.get(flower.getId());
            cart.addToCart(new CartItem(service.orderFlower(flower, amount), amount));
        }

        session.setAttribute("cart", cart);
        resp.sendRedirect("/profile/cart");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("flowers", flowerBusinessService.getAllFlowers());
        req.getRequestDispatcher("../pages/profile/catalog.jsp").forward(req, resp);
    }
}
