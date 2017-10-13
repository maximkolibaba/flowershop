package com.accenture.flowershop.fe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

public class JsonUtils {
    public static <T> String toJson(T obj) {
        try {
            return URLEncoder.encode(new Gson().toJson(obj, obj.getClass()), "UTF-8");
        } catch (IOException ex) {
            return "";
        }
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return new Gson().fromJson(URLDecoder.decode(json, "UTF-8"), tClass);
        } catch (IOException ex) {
            return null;
        }
    }

    public static String mapToJson(Map<String, String> map) {
        return new Gson().toJson(map);
    }

    public static Map<String, String> jsonToMap(String json) {
        Type type = new TypeToken<Map<String,String>>(){}.getType();
        return new Gson().fromJson(json, type);
    }

    public static void loadToResponse(Map<String, String> map, HttpServletResponse response) {
        JsonUtils.loadToResponse(JsonUtils.mapToJson(map), response);
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
}
