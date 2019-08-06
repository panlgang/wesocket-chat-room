package com.github.excellent.utils;

import java.util.Collections;

/**
 * @auther plg
 * @date 2019/8/6 21:57
 */
public class TestSubString {
    public static void main(String[] args) {
        String str = "0-1-2-3-";
         for(String s : str.split("-")){
             System.out.println(s + "1");
         }
    }
}
