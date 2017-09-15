package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.access.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Component
@Path("/LoginRequest")
public class LoginRequest {
    @Context
    private Request request;

    @Autowired
    private UserDAO dao;

    @POST
    @Path("{login}")
    @Produces(MediaType.TEXT_HTML)
    public String uniqueLogin(@PathParam("login") String username) {
        return dao.getByLogin(username) == null ? "true" : "false";
    }

    @POST
    @Path("/isunique")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String uniqueLogin2(String username) {
        return dao.getByLogin(username) == null ? "true" : "false";
    }
}