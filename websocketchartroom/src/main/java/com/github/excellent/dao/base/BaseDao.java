package com.github.excellent.dao.base;

import com.github.excellent.utils.JDBCUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 公有操作的提取
 */

public class BaseDao {
    private static BaseDao dao = new BaseDao();
    public static BaseDao getInstance(){
        return dao;
    }
    public Connection getConnection(){
        return JDBCUtils.getConnection();
    }

    public void close(ResultSet set,Statement statement,Connection connection){
         JDBCUtils.close(set,statement,connection);
    }

    public void close(Statement statement,Connection connection){
        close(null,statement,connection);
    }


}
