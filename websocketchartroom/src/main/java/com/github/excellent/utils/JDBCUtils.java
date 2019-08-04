package com.github.excellent.utils;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 基于Druid数据库连接池
 * @auther plg
 * @date 2019/7/31 10:47
 */
public class JDBCUtils {
    private static DruidDataSource ds;
    static{
        Properties properties = CommonUtils.loadProperties("datasource.properties");
        try {
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("获取数据源失败...");
        }
    }

    public static DruidPooledConnection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(ResultSet set, Statement statement,Connection connection){
        if(set != null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement statement,Connection connection){
        close(null,statement,connection);
    }
}

