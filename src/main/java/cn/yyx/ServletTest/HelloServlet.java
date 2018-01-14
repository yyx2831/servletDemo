package cn.yyx.ServletTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    public HelloServlet() {
        System.out.println("HelloServlet Construcotor method");
    }

    // 重写init
    @Override
    public void init() throws ServletException {
        System.out.println("HelloServlet init method");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet service method");
    }

    //重写destroy

    @Override
    public void destroy() {
        System.out.println("HelloServlet destroy method");
    }
}
