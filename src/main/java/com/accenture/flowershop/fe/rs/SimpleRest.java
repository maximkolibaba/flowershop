package com.accenture.flowershop.fe.rs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/acc")
public class SimpleRest {
    @GET
    @Path("/logout")
    public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response)
            throws Exception {
        request.getSession(false).invalidate();
        response.sendRedirect("/index");
    }
}
