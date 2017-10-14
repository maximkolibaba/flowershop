package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.business.MainBusinessService;
import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import com.accenture.flowershop.be.entity.flower.Flower;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("user")
public class UserRest {
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

//    @PathParam("username")
//    private String username;

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    @Autowired
    private MainBusinessService service;

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

    @POST
    @Path("/update_status")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateStatus(String orderId) {
        long id;
        try {
            id = Long.parseLong(orderId);
        } catch (Exception e) {
            return "";
        }
        Order order = service.updateOrderStatus(id);
        if (order == null) {
            return "";
        }
        JsonElement element = new Gson().toJsonTree(order);
        String status = element.getAsJsonObject().get("orderStatus").getAsString();
        element.getAsJsonObject().remove("orderStatus");
        element.getAsJsonObject().addProperty("orderStatus", Enum.valueOf(OrderStatus.class, status).getValue());
        return element.toString();
    }
}
