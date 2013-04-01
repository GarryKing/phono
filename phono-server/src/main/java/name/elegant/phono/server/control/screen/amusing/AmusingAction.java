package name.elegant.phono.server.control.screen.amusing;

import name.elegant.phono.core.admin.dao.IpAddressDAO;
import name.elegant.phono.core.util.net.NetConnection;
import name.elegant.phono.core.util.text.FileReaderUtil;
import name.elegant.phono.server.vo.amusing.IpLogVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: Garry King
 * Date: 13-2-11 ÉÏÎç1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/amusing/index.html")
public class AmusingAction {

    @Resource
    private IpAddressDAO ipAddressDAO;

    private final int ACCESS_LOG_SECTION_IP = 0;

    @RequestMapping
    public ModelAndView execute() {
        Map<String, IpLogVO> ipMap = new HashMap<String, IpLogVO>();
        String accessContent = FileReaderUtil.readTextFile("/var/log/apache2/access.log");
        accessContent += FileReaderUtil.readTextFile("/var/log/apache2/access.log.1");
        accessContent += FileReaderUtil.readTextFile("/var/log/apache2/access.log.2");
        accessContent += FileReaderUtil.readTextFile("/var/log/apache2/access.log.3");
        String[] lines = accessContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] sections = lines[i].split(" ");
            String ip = sections[ACCESS_LOG_SECTION_IP];
            String address = this.queryAddress(ip);
            ipMap.put(ip, new IpLogVO(ip, address, 0));
        }
        return new ModelAndView("asuming/index", "ipMap", ipMap);
    }


    private String queryAddress(String tempIp) {
        String address = null;
        address = ipAddressDAO.queryAddressByIp(tempIp);
        if (address == null) {
            String query = NetConnection.getURLContent("http://www.cha086.com/app/ip/" + tempIp).split("class=\"last\"")[1];
            address = NetConnection.getSubStringWithTag(query, "£º", "</td>");
            ipAddressDAO.insertNewIpAddress(tempIp, address);
        }
        return address;
    }

}
