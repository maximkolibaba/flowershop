package com.accenture.flowershop.fe.rs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

@Path("{username}")
public class UserRest {
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @PathParam("username")
    private String username;

    @GET
    public void info() throws Exception {
        // working tipa
        request.getRequestDispatcher("/pages/profile/info.jsp").forward(request, response);
    }

    @GET
    @Path("/logout")
    public void logout() throws Exception {
        request.getSession(false).invalidate();
        response.sendRedirect("/index");
    }
}
