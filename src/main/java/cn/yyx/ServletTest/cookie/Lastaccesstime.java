package cn.yyx.ServletTest.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "Lastaccesstime", urlPatterns = "/Lastaccesstime")
public class Lastaccesstime extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String currentTime = simpleDateFormat.format(date);

        //1.创建Cookie 记录当前的最新访问时间
        Cookie lastAccessTime = new Cookie("lastAccessTime", currentTime);
        lastAccessTime.setMaxAge(600000);
        response.addCookie(lastAccessTime);

        //2.获得客户端携带cookie ---- lastAccessTime
        String lastTime = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie coo : cookies) {
                if ("lastAccessTime".equals(coo.getName())) {
                    lastTime = coo.getValue();
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        if (lastTime == null) {
            response.getWriter().println("您是第一次访问");
        } else {
            response.getWriter().println("您上次访问的时间是:" + lastTime);
        }
    }
}
