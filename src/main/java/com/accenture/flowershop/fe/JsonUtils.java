package com.accenture.flowershop.fe;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class JsonUtils {
    public static <T> String toJson(T obj) {
        try {
            Gson gson = new Gson();
            return URLEncoder.encode(gson.toJson(obj, obj.getClass()), "UTF-8");
        } catch (IOException ex) {
            return "";
        }
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
}
