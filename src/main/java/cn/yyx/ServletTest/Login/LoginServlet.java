package cn.yyx.ServletTest.Login;

import cn.yyx.ServletTest.register.User;
import cn.yyx.ServletTest.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login1")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        //1.获取用户账号密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.调用一个业务方法进行该用户查询
        User login = login(username, password);
        //3.通过user是否为null判断用户名密码是否正确
        if (login != null) {
            //用户名和密码正确
            //登陆成功, 跳转导网站的首页
            response.sendRedirect(request.getContextPath());
        } else {
            //用户名或密码错误
            //跳回当前login.jsp
            //使用转发, 转发导login.jsp, 向resquest域种存储错误信息
            request.setAttribute("loginInfo", "用户名或者密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        //验证码效验
        //获得页面输入的验证码
        String checkCode_client = request.getParameter("checkCode");
        //获得生成图片的文字的验证码
        String checkCode_session = (String) request.getSession().getAttribute("checkcode_session");
        //对比页面的和生成图片的文字的验证码是否一致
        if (!checkCode_client.equals(checkCode_session)) {
            request.setAttribute("loginInfo", "您的验证码不正确");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        //获得页面的用户名和密码进行数据库的校验
        //......
    }

    public User login(String username, String passworld) {
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from user where username=? and password=?";
        User user = null;
        try {
            user = runner.query(sql, new BeanHandler<User>(User.class), username, passworld);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
