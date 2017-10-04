package com.accenture.flowershop.fe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirect {
    public static boolean fromAdminPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
        if (isAdmin != null) {
            if (!isAdmin) {
                response.sendRedirect("/profile");
                return true;
            } else {
                return false;
            }
        }
        response.sendRedirect("/index");
        return true;
    }

    public static boolean fromUserPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean isAdmin = (Boolean) request.getSession(false).getAttribute("isAdmin");
        if (isAdmin == null) {
            response.sendRedirect("/index");
            return true;
        } else if (isAdmin) {
            response.sendRedirect("/admin");
            return true;
        }
        return false;
    }
}
