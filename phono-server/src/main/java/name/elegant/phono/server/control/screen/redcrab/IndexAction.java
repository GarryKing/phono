package name.elegant.phono.server.control.screen.redcrab;

import name.elegant.phono.client.dataobject.redcrab.PictureDO;
import name.elegant.phono.core.redcrab.dao.PictureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * User: Administrator
 * Date: 13-4-17
 * Time: ÏÂÎç11:51
 */
@Controller
@RequestMapping("/index.html")
public class IndexAction {

    @Autowired
    private PictureDAO pictureDAO;

    @RequestMapping
    public ModelAndView showPicture(HttpServletRequest request, HttpServletResponse response) {
        List<PictureDO> picList = pictureDAO.queryAllPictureDO();
        return new ModelAndView("index", "picList", picList);
    }
}
