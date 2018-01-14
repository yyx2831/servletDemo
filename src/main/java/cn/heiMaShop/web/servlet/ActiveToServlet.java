package cn.heiMaShop.web.servlet;

import cn.heiMaShop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActiveToServlet", urlPatterns = "/active")
public class ActiveToServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得激活码
        String activeCode = request.getParameter("activeCode");

        UserService service = new UserService();

        service.active(activeCode);

        //跳转到登陆页面
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
