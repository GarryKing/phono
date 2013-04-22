package name.elegant.phono.server.control.json;

import name.elegant.phono.core.admin.dao.IpAddressDAO;
import name.elegant.phono.core.util.net.NetConnection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Author: Garry King
 * Date: 13-2-11 ÉÏÎç1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/login")
public class Chatting {

    private static final String SIMI_KEY = "6f36844f-0096-44f7-b4c2-94a698b3363f";

    @RequestMapping(value = "/chatting.crab", method = RequestMethod.GET)
    @ResponseBody
    public void testDoIt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("gbk");
        response.setCharacterEncoding("gbk");
        String iSay = request.getParameter("iSay");
        iSay = URLEncoder.encode(iSay, "utf-8");
        String url = "http://sandbox.api.simsimi.com/request.p?key=" + SIMI_KEY + "&lc=ch&ft=1.0&text=" + iSay;
        String tmall = NetConnection.getURLContent(url).trim();
        response.getWriter().print(new String(tmall.getBytes("utf-8"), "gbk"));
    }

}
