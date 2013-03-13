package name.elegant.phono.core.admin.control;

import name.elegant.phono.client.dataobject.AccessLogDO;
import name.elegant.phono.core.admin.dao.AccessLogDAO;
import name.elegant.phono.core.admin.dao.IpAddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-13 ÉÏÎç1:33
 * E-mail:flyhzq@sina.com
 */
public class DAOServlet extends HttpServlet {

    @Autowired
    private IpAddressDAO ipAddressDAO;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("GBK");
        request.setCharacterEncoding("GBK");
//        ApplicationContext context = new ClassPathXmlApplicationContext(
//                "spring/persistence.xml");
//        IpAddressDAO ipAddressDAO = (IpAddressDAO) context.getBean("iBatisIpAddressDAO");
        String address = ipAddressDAO.queryAddressByIp("127.0.0.1");
        response.getWriter().println("127.0.0.1 = " + address);
    }

}
