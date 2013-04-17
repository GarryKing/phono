package name.elegant.phono.core.admin.control;

import name.elegant.phono.core.admin.dao.IpAddressDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
