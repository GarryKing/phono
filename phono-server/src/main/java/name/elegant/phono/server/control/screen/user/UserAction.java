package name.elegant.phono.server.control.screen.user;

import name.elegant.phono.core.admin.dao.IpAddressDAO;
import name.elegant.phono.core.util.net.NetConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Garry King
 * Date: 13-2-11 上午1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/user")
public class UserAction {

    @Resource
    private IpAddressDAO ipAddressDAO;

    @RequestMapping("/login.html")
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
        String currIp = request.getRemoteAddr();
        String address = ipAddressDAO.queryAddressByIp(currIp);
        if (address == null || address.equals("")) {
            String query = NetConnection.getURLContent("http://www.cha086.com/app/ip/" + currIp).split("class=\"last\"")[1];
            address = NetConnection.getSubStringWithTag(query, "：", "</td>");
            ipAddressDAO.insertNewIpAddress(currIp, address);
        }
        String result = "您好，来自" + address + "的朋友，您的ip是：" + currIp;
        return new ModelAndView("user/login", "result", result);
    }

}
