package com.ecent.capture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SharedPreferences 数据存储工具类
 *
 * @author lxy
 */
public class ESPUtils {
    private static String PreferenceName = "shared_preferences";

    /**
     * 设置文件名
     *
     * @param preferenceName
     */
    public static void setPreferenceName(String preferenceName) {
        PreferenceName = preferenceName;
    }

    /**
     * 储存复杂的数据字段对象
     *
     * @param context
     * @param key
     * @param t
     * @return
     */
    public static <T> boolean setObjectToShare(Context context, String key, T t) {
        return setObjectToShare(context, PreferenceName, key, t);
    }

    /**
     * @param context
     * @param name
     * @param key
     * @param t
     * @return
     */
    public static <T> boolean setObjectToShare(Context context, String name,
                                               String key, T t) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name,
                    Context.MODE_PRIVATE);
            // 存储
            Editor editor = sp.edit();
            if (t == null) {
                editor.putString(key, "");
                editor.commit();
                return true;
            }
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos;

            oos = new ObjectOutputStream(toByte);
            oos.writeObject(t);
            // 对byte[]进行Base64编码
            String payCityMapBase64 = new String(Base64.encode(
                    toByte.toByteArray(), Base64.DEFAULT));

            editor.putString(key, payCityMapBase64);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 得到复杂的数据对象
     *
     * @param context
     * @param key
     * @return
     */
    public static <T> T getObjectFromShare(Context context, String key) {
        return getObjectFromShare(context, PreferenceName, key);
    }

    /**
     * 得到复杂的数据对象
     *
     * @param key
     * @param name
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectFromShare(Context context, String name,
                                           String key) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name,
                    Context.MODE_PRIVATE);
            String payCityMapBase64 = sp.getString(key, "");
            if (payCityMapBase64.length() == 0) {
                return null;
            }
            byte[] base64Bytes = Base64
                    .decode(payCityMapBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储字符串数据类型
     *
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 返回String类型数据，默认是“”；
     *
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    /**
     * 返回String类型数据，默认是“”；
     *
     * @param key
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 存储boolean数据类型
     *
     * @param key
     * @param value
     */
    public static void setboolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 返回boolean类型数据，默认是true；
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * * 返回boolean类型数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 存储int数据类型
     *
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 返回int类型数据，默认是true；
     *
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    /**
     * 返回int类型数据；
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    /**
     * 存储float数据类型
     *
     * @param key
     * @param value
     */
    public static void setFloat(Context context, String key, float value) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 返回float类型数据，默认是true；
     *
     * @param key
     * @return
     */
    public static float getFloat(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getFloat(key, 0);
    }

    /**
     * 存储long数据类型
     *
     * @param key
     * @param value
     */
    public static void setLong(Context context, String key, long value) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 返回long类型数据，默认是true；
     *
     * @param key
     * @return
     */
    public static long getLong(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getLong(key, -1);
    }

    public static long getLong(Context context, String key, long def) {
        SharedPreferences sp = context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
        return sp.getLong(key, def);
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PreferenceName, Context.MODE_PRIVATE);
    }

    public static Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    /**
     * 清除所有缓存
     *
     * @param context
     */
    public static void clearCache(Context context) {
        getEditor(context).clear().commit();
    }
}
