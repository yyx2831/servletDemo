package cn.yyx.ServletTest.admin.service;

import cn.yyx.ServletTest.admin.dao.AdminProductDao;
import cn.yyx.ServletTest.admin.domain.Product;

import java.sql.SQLException;
import java.util.List;

public class AdminProductService {
    //查询所有的商品
    public List<Product> findAllProduct() throws SQLException {
        //因为没用复杂业务, 直接传递请求到dao层
        AdminProductDao dao = new AdminProductDao();
        return dao.findAllProduct();
    }
}
