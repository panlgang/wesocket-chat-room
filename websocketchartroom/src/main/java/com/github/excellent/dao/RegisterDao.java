package com.github.excellent.dao;

import com.github.excellent.dao.base.BaseDao;
import com.github.excellent.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO层注册业务 ---- 向表中插入数据
 * @auther plg
 * @date 2019/8/3 10:49
 */
public class RegisterDao {
    private final BaseDao baseDao = BaseDao.getInstance() ;

    public boolean register(User user){
        String userName = user.getUserName();
        String password = user.getPassword();
        Connection connection = baseDao.getConnection();
        String sql = "insert into user (username,password) values(?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,DigestUtils.md5Hex(password));
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
       return false;
    }
}
