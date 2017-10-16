package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.access.user.UserRepository;
import com.accenture.flowershop.be.entity.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import javax.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonString;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

@Component
@Path("/login")
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
    @Path("/is_unique")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginIsUnique(String username) {
        String isUnique = ((Boolean) (repository.findByLogin(username) == null)).toString();
        return "{\"unique\": " + isUnique + "}";
    }

    // FIXME
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String register(JsonElement json) {
//        JsonObject jsonObject = new Gson().toJsonTree(json).getAsJsonObject();
        return null;
    }
}