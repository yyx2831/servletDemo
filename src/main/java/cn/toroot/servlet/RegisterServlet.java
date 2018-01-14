package cn.toroot.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String passworld = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        //如果输入的参数为空, 操作终止
        if ("".equals(userName) || "".equals(passworld) || "".equals(rePassword)) {
            req.setAttribute("error", "用户名和密码均不能为空!");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        //密码和重复密码必须一致
        if (!passworld.equals(rePassword)) {
            req.setAttribute("error", "两次密码必须一致!");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            return;
        }

        resp.sendRedirect("login.jsp");
    }
}
