package com.accenture.flowershop.fe.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("{username}")
public class UserRest {
    @PathParam("username")
    private String username;

    @GET
    public void info() {

    }
}
