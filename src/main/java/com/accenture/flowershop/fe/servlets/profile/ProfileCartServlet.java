package com.accenture.flowershop.fe.servlets.profile;

import com.accenture.flowershop.be.entity.user.User;
import com.accenture.flowershop.fe.Cart;
import com.accenture.flowershop.fe.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownServiceException;

@WebServlet(urlPatterns = "/profile/cart")
public class ProfileCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        Cart cart = (Cart) session.getAttribute("cart");

        if (req.getParameter("buttonOrder") != null) {
            // TODO: and error "not enough flowers"
            User user = (User) session.getAttribute("user");
            BigDecimal priceToPay = cart.getTotal()
                    .multiply(new BigDecimal(1 - (double) (user.getDiscount()) / 100));
            if (user.getBalance().compareTo(priceToPay) == -1) {
                session.setAttribute("notEnoughMoney", true);
            } else {
                // TODO order this and redirect to order page
            }
        } else {
            for (CartItem item : cart.getItems()) {
                if (req.getParameter("remove" + item.getFlower().getName()) != null) {
                    cart.removeFromCart(item);
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
