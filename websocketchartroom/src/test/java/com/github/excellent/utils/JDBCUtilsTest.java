package com.github.excellent.utils;
import org.junit.Test;

import java.sql.*;

/**
 * @auther plg
 * @date 2019/7/31 10:55
 */
public class JDBCUtilsTest {
    @Test
    public void test(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
           connection = JDBCUtils.getConnection();
           String sql = "select * from user where id = ? and username = ?";
           statement = connection.prepareStatement(sql);
           statement.setInt(1,1);
           statement.setString(2,"zs");
           set = statement.executeQuery();
           while(set.next()){
               int id = set.getInt("id");
               String username = set.getString("username");
               String password = set.getString("password");
               System.out.println(id + "--" + username + "--" +  password);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(set,statement,connection);
        }
    }
}
