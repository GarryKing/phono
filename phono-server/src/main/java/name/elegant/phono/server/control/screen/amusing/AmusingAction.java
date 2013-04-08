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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: Garry King
 * Date: 13-2-11 上午1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/amusing/index.crab")
public class AmusingAction {

    @Resource
    private IpAddressDAO ipAddressDAO;

    private final int ACCESS_LOG_SECTION_IP = 0;
    private String logsContent;

    @RequestMapping
    public ModelAndView execute() {
        Map<String, IpLogVO> ipMap = new HashMap<String, IpLogVO>();
        String accessContent = getLogsContent();
        String[] lines = accessContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] sections = lines[i].split(" ");
            String ip = sections[ACCESS_LOG_SECTION_IP];
            if (ip == null || !ip.contains("."))
                continue;
            String address = this.queryAddress(ip);
            ipMap.put(ip, new IpLogVO(ip, address, 0));
            break;
        }
        return new ModelAndView("amusing/index", "ipMap", ipMap);
    }


    private String queryAddress(String tempIp) {
        if (tempIp == null || !tempIp.contains("."))
            return "";
        String address = null;
        address = ipAddressDAO.queryAddressByIp(tempIp);
        if (address == null) {
            String query = NetConnection.getURLContent("http://www.cha086.com/app/ip/" + tempIp).split("class=\"last\"")[1];
            address = NetConnection.getSubStringWithTag(query, "：", "</td>");
            ipAddressDAO.insertNewIpAddress(tempIp, address);
        }
        return address;
    }

    public String getLogsContent() {
        String logsContent = "";
        String name = "";
        String fileName = "";
        try {
            name = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        if (name.contains("TAOBAO")) {
            fileName = "C:\\Documents and Settings\\zeqing.hzq\\桌面\\history ali log\\access.log";
        } else if (name.contains("Garry")) {
            fileName = "E:\\360data\\重要数据\\桌面\\log\\access.log";
        } else {
            fileName = "/var/log/apache2/access.log";
        }
        for (int i = 0; i < 3; i++) {
            logsContent = FileReaderUtil.readTextFile(fileName + (i == 0 ? "" : "." + i));
        }
        return logsContent;
    }
}
