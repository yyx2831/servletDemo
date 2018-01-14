package cn.yyx.ServletTest.oneDay;

import javax.servlet.*;
import java.io.IOException;

public class QuickStratServlet implements Servlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        //1.获得servlet的name---<servlet-name>abc</servlet-name>
        String servletName = servletConfig.getServletName();
        System.out.println(servletName );

        //2.获得该servlet的初始化的参数
        String initPar = servletConfig.getInitParameter("url");
        System.out.println(initPar);

        //3.获得ServletContext对象
        ServletContext servletContext = servletConfig.getServletContext();

        System.out.println("init runing");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("QuickStratServlet running....");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
