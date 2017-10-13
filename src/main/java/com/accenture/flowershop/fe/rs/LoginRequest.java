package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.access.user.UserRepository;
import com.accenture.flowershop.be.entity.user.User;
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
    private UserRepository repository;

    @POST
    @Path("{login}")
    @Produces(MediaType.TEXT_HTML)
    public String uniqueLogin(@PathParam("login") String username) {
//        return dao.getByLogin(username) == null ? "true" : "false";
        return null;
    }

    @POST
    @Path("/IsUnique")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String uniqueLogin2(String username) {
        return repository.findByLogin(username) == null ? "true" : "false";
    }
}