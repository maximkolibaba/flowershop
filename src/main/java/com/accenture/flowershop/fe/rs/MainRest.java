package com.accenture.flowershop.fe.rs;

import com.accenture.flowershop.be.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("/main")
public class MainRest {
    @Context
    private HttpServletRequest request;

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String asString() {
        String username = ((User) request.getSession(false).getAttribute("user")).getLogin();
        return "<html><title>hi :)</title>" + "<body><h1>hi " + username + " :)</h1></body>" + "</html>";
    }

    @Path("/jsonchik")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> asJSON() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "man");
        map.put("wow", "nice");
        return map;
    }
}
