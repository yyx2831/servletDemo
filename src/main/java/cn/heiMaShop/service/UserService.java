package cn.heiMaShop.service;

import cn.heiMaShop.dao.UserDao;
import cn.heiMaShop.domain.User;

import java.sql.SQLException;

public class UserService {
    public boolean regist(User user) {

        UserDao dao = new UserDao();
        int row = 0;
        try {
            row = dao.regist(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row>0?true:false;
    }

    //激活
    public void active(String activeCode) {
        UserDao dao = new UserDao();
        dao.active(activeCode);
    }

    public boolean checkUsername(String username) {
        UserDao dao = new UserDao();
        Long isExist =dao.checkUsername(username);
        return isExist>0?true:false;
    }
}
