package name.elegant.phono.server.control.json.readcrab;

import name.elegant.phono.client.dataobject.redcrab.PictureDO;
import name.elegant.phono.core.redcrab.dao.PictureDAO;
import name.elegant.phono.core.util.net.NetConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Author: Garry King
 * Date: 13-2-11 ÉÏÎç1:36
 * E-mail:flyhzq@sina.com
 */
@Controller
@RequestMapping("/json")
public class IndexJson {

    @Autowired
    private PictureDAO pictureDAO;

    @RequestMapping(value = "/indexImage.crab", method = RequestMethod.GET)
    @ResponseBody
    public Object testDoIt() throws IOException {
        List<PictureDO> picList = pictureDAO.queryAllPictureDO();
        return picList;
    }

}
