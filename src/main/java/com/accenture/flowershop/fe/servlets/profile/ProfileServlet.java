package com.accenture.flowershop.fe.servlets.profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("buttonInfo") != null) {
            resp.sendRedirect("/profile/info");
        } else if (req.getParameter("buttonCatalog") != null) {
            resp.sendRedirect("/profile/catalog");
        } else if (req.getParameter("buttonOrders") != null) {
            resp.sendRedirect("/profile/orders");
        } else if (req.getParameter("buttonCart") != null) {
            resp.sendRedirect("/profile/cart");
        } else if (req.getParameter("buttonLogout") != null) {
            req.getSession(false).invalidate();
            resp.sendRedirect("index.jsp");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}