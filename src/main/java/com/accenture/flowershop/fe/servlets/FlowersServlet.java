package com.accenture.flowershop.fe.servlets;

import com.accenture.flowershop.be.access.flower.FlowerDAO;
import com.accenture.flowershop.be.access.flower.FlowerDAOImpl;
import com.accenture.flowershop.be.entity.flower.Flower;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/flowers")
public class FlowersServlet extends HttpServlet {
//    @Override
//    public void init() throws ServletException {
//        super.init();
//    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO is it good?
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        FlowerDAO dao = context.getBean(FlowerDAOImpl.class);
        String result = "";
        List<Flower> flowers = dao.getAll();

        req.setAttribute("flowers", flowers);
        req.getRequestDispatcher("/flowers.jsp").forward(req, resp);
    }
}
