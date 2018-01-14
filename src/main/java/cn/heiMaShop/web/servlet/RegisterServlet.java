package cn.heiMaShop.web.servlet;

import cn.heiMaShop.domain.User;
import cn.heiMaShop.service.UserService;
import cn.heiMaShop.utils.CommonsUtils;
import cn.heiMaShop.utils.MailUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Map;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //获得表单数据
        Map parameterMap = request.getParameterMap();
        User user = new User();
        try {
            //自己指定一个类型转换器(将String转成Date)
            ConvertUtils.register(new Converter() {
                @Override
                public <T> T convert(Class<T> aClass, Object o) {
                    //将string转成date
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parse = null;
                    try {
                        parse = format.parse(o.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return (T) parse;
                }
            }, Date.class);
            //映射封装
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //  private String uid;
        user.setUid(CommonsUtils.getUUID());
        //  private String telephone;
        user.setTelephone(null);
        //  private int state;  //是否激活
        user.setState(0);
        //  private String code;    //激活码
        String uuid = CommonsUtils.getUUID();
        user.setCode(uuid);

        //将user传递给service
        UserService service = new UserService();
        boolean isRegisterSuccess = service.regist(user);

        //是否注册成功
        if (isRegisterSuccess) {
            //发送激活邮件
            String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
                    + "<a href='http://localhost:8080/HeimaShop/active?activeCode=" + uuid + "'>"
                    + "http://localhost:8080/HeimaShop/active?activeCode=" + uuid + "</a>";
            try {
                MailUtils.sendMail(user.getEmail(), emailMsg);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            //重定向到注册成功页面
            response.sendRedirect(request.getContextPath() + "/registerSuccess.jsp");
        } else {
            //重定向到注册失败页面
            response.sendRedirect(request.getContextPath() + "/registerFail.jsp");
        }
    }
}
