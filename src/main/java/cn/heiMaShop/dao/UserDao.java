package cn.heiMaShop.dao;

import cn.heiMaShop.domain.User;
import cn.yyx.ServletTest.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDao {
    public int regist(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        int update = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
                user.getTelephone(), user.getBirthday(), user.getBirthday(), user.getSex(),
                user.getState(), user.getCode());
        return update;
    }

    public void active(String activeCode) {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update user set state = ? where code = ?";
        try {
            runner.update(sql, 1, activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long checkUsername(String username) {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from user where username=?";
        Long query = null;
        try {
            query = (Long)runner.query(sql, new ScalarHandler(), username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}
