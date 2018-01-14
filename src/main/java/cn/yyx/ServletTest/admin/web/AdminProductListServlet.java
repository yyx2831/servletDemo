package cn.yyx.ServletTest.admin.web;

import cn.yyx.ServletTest.admin.domain.Product;
import cn.yyx.ServletTest.admin.service.AdminProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminProductListServlet", urlPatterns = "/adminProductList")
public class AdminProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //传递请求service层
        AdminProductService service = new AdminProductService();
        List<Product> productList = null;
        try {
            productList = service.findAllProduct();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //将productList放到request域
        request.setAttribute("productList", productList);

        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
    }
}
