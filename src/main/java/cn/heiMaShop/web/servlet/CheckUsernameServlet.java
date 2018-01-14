package cn.heiMaShop.web.servlet;

import cn.heiMaShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CheckUsernameServlet")
public class CheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取用户名
        String username = request.getParameter("username");

        UserService service = new UserService();
        boolean isExist = service.checkUsername(username);

        String json = "{\"isExist\":"+isExist+"}";

        response.getWriter().write(json);
    }
}
