package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.business.MainBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.be.entity.order.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("admin")
public class AdminRest {
    @Autowired
    private MainBusinessService service;

    @POST
    @Consumes(MediaType.TEXT_HTML)
    @Path("/update_order")
    public void updateOrderStatus(String orderId) {
        if (!StringUtils.isEmpty(orderId)) {
            service.updateOrderStatus(Long.parseLong(orderId.substring(1)));
        }
    }

    // TODO could return JsonObject
    // cool datetime
    // new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault()).format(order.getCreateDate())
    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllOrders() {
        List<Order> orders = service.getAllOrders();
        Collections.sort(orders);
        JsonArray array = new Gson().toJsonTree(orders).getAsJsonArray();
        for (JsonElement element : array) {
            String status = element.getAsJsonObject().get("orderStatus").getAsString();
            element.getAsJsonObject().remove("orderStatus");
            element.getAsJsonObject().addProperty("orderStatus", Enum.valueOf(OrderStatus.class, status).getValue());

            JsonElement user = element.getAsJsonObject().get("user");
            user.getAsJsonObject().remove("isAdmin");
            user.getAsJsonObject().remove("firstName");
            user.getAsJsonObject().remove("lastName");
            user.getAsJsonObject().remove("password");
            user.getAsJsonObject().remove("address");
            user.getAsJsonObject().remove("balance");
            user.getAsJsonObject().remove("discount");
        }
        return array.toString();
    }
}
