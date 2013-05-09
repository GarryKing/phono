package name.elegant.phono.core.redcrab.dao.impl;

import name.elegant.phono.client.dataobject.redcrab.PictureDO;
import name.elegant.phono.core.common.dao.BaseDao;
import name.elegant.phono.core.redcrab.dao.PictureDAO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Administrator
 * Date: 13-4-17
 * Time: ÏÂÎç11:33
 */
@Repository
public class PictureDAOImpl extends BaseDao implements PictureDAO {

    private static final int TABLE_PICTURE_NUMBER = 3;

    @Override
    public void insertNewPicture(PictureDO pictureDO) {

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PictureDO> queryAllPictureDO() {
        String tableNamePrefix = "picture_";
        List<PictureDO> list = new ArrayList<PictureDO>();
        for (int i = 0; i < TABLE_PICTURE_NUMBER; i++)
            list.addAll(getSqlMapClientTemplate().queryForList("PictureDAO.queryAllPictureDO", tableNamePrefix + "000" + i));
        return list.isEmpty() ? null : list;
    }
}
