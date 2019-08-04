package com.github.excellent.utils;

import com.github.excellent.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

/**
 * @auther plg
 * @date 2019/7/31 10:45
 */
public class CommonUtilsTest {
    @Test
    public void testloadProperties(){
        Properties properties = CommonUtils.loadProperties("datasource.properties");
        Assert.assertNotNull(properties);
    }

    @Test
    public void testObject2Json(){
        User user = new User();
        user.setId(0);
        user.setUserName("Jack");
        user.setPassword("root");
        System.out.println(CommonUtils.object2Json(user));
    }

    @Test
    public void testJson2Object(){
        System.out.println(CommonUtils.Json2Object("{\"id\":0,\"userName\":\"Jack\",\"password\":\"root\"}",User.class));
    }
}
