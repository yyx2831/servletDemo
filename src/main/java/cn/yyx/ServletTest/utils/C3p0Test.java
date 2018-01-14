package cn.yyx.ServletTest.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.SQLException;

public class C3p0Test {
    @Test
    public void test() {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        try {
            runner.update("insert into user values(?,?,?,?,?,?,?,?,?,?)", "yyx", null, null, null, null, null, null, null, null, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
