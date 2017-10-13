package com.accenture.flowershop.fe;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

public class JsonUtils {
    public static <T> String toJson(T obj) {
        try {
            Gson gson = new Gson();
            return URLEncoder.encode(gson.toJson(obj, obj.getClass()), "UTF-8");
        } catch (IOException ex) {
            return "";
        }
    }

    public static String mapToJson(Map<String, String> map) {
        Gson gson = new Gson();
        return gson.toJson(map);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            json = URLDecoder.decode(json, "UTF-8");
            Gson gson = new Gson();
            return gson.fromJson(json, tClass);
        } catch (IOException ex) {
            return null;
        }
    }

    public static void loadToResponse(String json, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadToResponse(Map<String, String> map, HttpServletResponse response) {
        JsonUtils.loadToResponse(JsonUtils.mapToJson(map), response);
    }
}
