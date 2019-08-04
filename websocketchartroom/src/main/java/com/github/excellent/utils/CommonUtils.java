package com.github.excellent.utils;

/**
 * @auther plg
 * @date 2019/7/31 10:40
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 封装一些公共的工具方法
 */
public class CommonUtils {
    private static final Gson GSON = new GsonBuilder().create();
    private CommonUtils(){

    }

    public static Properties loadProperties(String fileName){
        if(fileName == null || fileName.length() == 0){
            return null;
        }
        Properties properties = new Properties();
        InputStream in = CommonUtils.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


    public static  String object2Json(Object obj){
        return GSON.toJson(obj);
    }
    public static Object Json2Object(String str,Class<?> cls){
        return GSON.fromJson(str,cls);
    }
}
