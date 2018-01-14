package cn.yyx.ServletTest.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetCookieServlet", urlPatterns = "/GetCookieServlet" )
public class GetCookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得客户端携带的cookie的数据
        Cookie[] cookies = request.getCookies();

        Cookie cookie1 = new Cookie("name", "Jay");
        response.addCookie(cookie1);
        //通过cookie名称获得想要的cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //获得cookie的名称
                String name = cookie.getName();
                if (name.equals("name")) {
                    //获得该cookie的值
                    String value = cookie.getValue();
                    response.getWriter().println(value);
                    String path = cookie.getPath();
                    response.getWriter().println(path);
                }
            }
        }
    }
}
