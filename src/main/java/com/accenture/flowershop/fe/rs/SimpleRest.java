package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.business.user.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/acc")
public class SimpleRest {
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;
    @Autowired
    private UserBusinessService service;

    @GET
    @Path("/logout")
    public void logout() throws Exception {
        request.getSession(false).invalidate();
        response.sendRedirect("/index");
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public void register(String json) throws Exception {
        return;
    }
}
