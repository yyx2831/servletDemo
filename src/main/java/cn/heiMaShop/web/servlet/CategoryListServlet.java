package cn.heiMaShop.web.servlet;

import cn.heiMaShop.domain.Category;
import cn.heiMaShop.service.ProductService;
import cn.heiMaShop.utils.JedisPoolUtils;
import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryListServlet", urlPatterns = ("/categoryList"))
public class CategoryListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductService service = new ProductService();

        //先从缓存中查询categoryList 如果有直接使用 没有在从数据库中查询 存到缓存中
        //1、获得jedis对象 连接redis数据库
        Jedis jedis = JedisPoolUtils.getJedis();
        String categoryListJson = jedis.get("categoryListJson");
        //2、判断categoryListJson是否为空
        if (categoryListJson == null) {
            System.out.println("缓存没有数据 查询数据库");
            //准备分类数据
            List<Category> categoryList = service.findAllCategory();
            Gson gson = new Gson();
            categoryListJson = gson.toJson(categoryList);
            jedis.set("categoryListJson", categoryListJson);
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(categoryListJson);
    }
}
