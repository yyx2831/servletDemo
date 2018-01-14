package cn.yyx.ServletTest.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveCookieServlet", urlPatterns = "/RemoveCookieServlet")
public class RemoveCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //上传客户端保存 name=zhangsan的cookie信息
        Cookie name = new Cookie("name", "");
        //将path设置成与要删除
        name.setPath(null);
        //设置时间是0
        name.setMaxAge(0);

        response.addCookie(name);
    }
}
