package cn.heiMaShop.web.servlet;

import cn.heiMaShop.domain.Category;
import cn.heiMaShop.domain.Product;
import cn.heiMaShop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductService service = new ProductService();

        //准备热门商品---List<Product>
        List<Product> hotProductList = service.findHotProductList();

        //准备最新商品---List<Product>
        List<Product> newProductList = service.findNewProductList();


        request.setAttribute("hotProductList", hotProductList);
        request.setAttribute("newProductList", newProductList);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
