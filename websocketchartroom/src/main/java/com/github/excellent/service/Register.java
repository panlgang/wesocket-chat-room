package com.github.excellent.service;

import com.github.excellent.dao.RegisterDao;
import com.github.excellent.entity.User;

import java.sql.SQLException;

/**
 * @auther plg
 * @date 2019/8/3 10:54
 */
public class Register {
    private RegisterDao registerDao = new RegisterDao();
    public boolean regitser(User user) throws SQLException {
        if(user == null){
            return false;
        }
        return registerDao.register(user);
    }
}
