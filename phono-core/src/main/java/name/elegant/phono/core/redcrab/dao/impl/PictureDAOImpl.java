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

    @Override
    public List<PictureDO> queryAllPictureDO() {
        String tableNamePrefix = "picture_";
        List<PictureDO> list = new ArrayList<PictureDO>();
        list.addAll(getSqlMapClientTemplate().queryForList("PictureDAO.queryAllPictureDO", tableNamePrefix + "0001"));
        list.addAll(getSqlMapClientTemplate().queryForList("PictureDAO.queryAllPictureDO", tableNamePrefix + "0002"));
        list.addAll(getSqlMapClientTemplate().queryForList("PictureDAO.queryAllPictureDO", tableNamePrefix + "0003"));
        list.addAll(getSqlMapClientTemplate().queryForList("PictureDAO.queryAllPictureDO", tableNamePrefix + "0000"));
        return list.isEmpty() ? null : list;
    }
}
