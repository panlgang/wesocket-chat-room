package com.github.excellent.utils;

import java.util.HashMap;
import java.util.Random;

/**
 * @auther plg
 * @date 2019/8/10 16:16
 */
public class RandomCar {
    private static HashMap<Integer,String> map = new HashMap<>();
    static{
        map.put(1, "布嘉迪");
        map.put(2, "兰博基尼");
        map.put(3, "柯尼赛格");
        map.put(4, "玛莎拉蒂");
        map.put(5, "劳斯莱斯");
        map.put(6, "宾利");
        map.put(7, "奔驰");
        map.put(8, "保时捷");
        map.put(9, "法拉力");
        map.put(10, "宝马");
        map.put(11, "奥迪");
        map.put(12, "捷豹");
        map.put(13, "凯迪拉克");
        map.put(14, "沃尔沃");
        map.put(15, "雷克萨斯");
        map.put(16, "英菲尼迪");
        map.put(17, "驱歌");
        map.put(18, "欧宝");
        map.put(19, "迈巴赫");
        map.put(20, "路虎");
    }
    static Random random = new Random();
    public static String getCar(){
        return map.get(random.nextInt(20));
    }

}
