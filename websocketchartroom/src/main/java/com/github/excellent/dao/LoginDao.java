package com.github.excellent.dao;

import com.github.excellent.dao.base.BaseDao;
import com.github.excellent.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO层登录业务 ------ 在数据库中查询
 * @auther plg
 * @date 2019/8/3 10:34
 */
public class LoginDao {
    private final BaseDao baseDao = BaseDao.getInstance();
    public boolean login(User user) throws SQLException {
        String userName = user.getUserName();
        String password = user.getPassword();
        Connection connection = baseDao.getConnection();
        String sql = "select * from USER where username = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,userName);
        statement.setString(2,DigestUtils.md5Hex(password));
        ResultSet set = statement.executeQuery();
        return set.next();
    }

}
