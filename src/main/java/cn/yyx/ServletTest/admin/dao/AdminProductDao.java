package cn.yyx.ServletTest.admin.dao;

import cn.yyx.ServletTest.admin.domain.Product;
import cn.yyx.ServletTest.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminProductDao {
    public List<Product> findAllProduct() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String str = "select * from product";
        return runner.query(str, new BeanListHandler<Product>(Product.class));
    }
}
