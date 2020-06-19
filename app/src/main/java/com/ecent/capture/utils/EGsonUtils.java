package com.ecent.capture.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lxy on 2016/5/10.
 */
public final class EGsonUtils {

    private EGsonUtils(){}

    private static Gson gson = new GsonBuilder().setDateFormat(
            "yyyy-MM-dd HH:mm:ss").create();

    public static Gson getGson() {
        return gson;
    }

    public static  <T> T toObject(String json,Class<T> clz) {
        return fromJson(json, clz);
    }

    public static <T> List<T> toList(String json,Class<T> clz) {
        Type listType = type(List.class, clz);
        List<T> list = fromJson(json, listType);
        return list;
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, Type type) {
        if (TextUtils.isEmpty(json))
            return null;
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(String json) {
        if (TextUtils.isEmpty(json))
            return null;
        Type type = new TypeToken<T>(){}.getType();
        return gson.fromJson(json, type);
    }

    public static String obj2Json(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }

        };
    }

}
