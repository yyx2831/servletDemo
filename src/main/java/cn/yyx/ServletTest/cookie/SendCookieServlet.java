package cn.yyx.ServletTest.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SendCookieServlet", urlPatterns = "/SendCookieServlet")
public class SendCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 创建cookie对象
        Cookie cookie = new Cookie("name", "JayKer");

        //1.1 为cookie设置持久化时间 ---- cookie信息在硬盘上保存的时间
        cookie.setMaxAge(600);
        //1.2 为cookie设置携带的路径
        cookie.setPath("/");

        //2、将cookie中存储的信息发送到客户端---头
        response.addCookie(cookie);
    }
}
