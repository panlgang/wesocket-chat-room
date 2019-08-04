package com.github.excellent.service;

import com.github.excellent.dao.LoginDao;
import com.github.excellent.entity.User;

import java.sql.SQLException;

/**
 * @auther plg
 * @date 2019/8/3 10:54
 */
public class Login {
    private LoginDao loginDao = new LoginDao();

    public boolean login(User user) throws SQLException {
        if(user == null){
            return false;
        }
        return loginDao.login(user);
    }
}
