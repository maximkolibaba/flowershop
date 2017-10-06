package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.fe.servlets.LoginServlet;
import com.accenture.flowershop.fe.servlets.profile.ProfileInfoServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("{username}")
public class UserRest {
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @PathParam("username")
    private String username;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

//    @GET
//    public void info() throws Exception {
//        // working tipa
//        request.getRequestDispatcher("/pages/profile/info.jsp").forward(request, response);
//    }

    @GET
    @Path("/logout")
    public void logout() throws Exception {
        request.getSession(false).invalidate();
        response.sendRedirect("/index");
    }

    @GET
    @Path("/info")
    public void info() throws Exception {
//        new ProfileInfoServlet().doGet(request, response);
        request.getRequestDispatcher("/pages/profile/info.jsp").forward(request, response);
    }

    @GET
    @Path("/catalog")
    public void catalog() throws Exception {
        List<Flower> flowers = flowerBusinessService.getAllFlowers();
        request.setAttribute("flowers", flowers);
        request.getRequestDispatcher("s/pages/profile/catalog.jsp").forward(request, response);
    }
}
