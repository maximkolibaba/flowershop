package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.business.MainBusinessService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
}
