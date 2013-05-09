package name.elegant.phono.core.redcrab.dao;

import name.elegant.phono.client.dataobject.redcrab.PictureDO;

import java.util.List;

/**
 * User: Administrator
 * Date: 13-4-17
 * Time: 下午11:24
 */
public interface PictureDAO {
    /**
     * 插入新的图片对象
     *
     * @param pictureDO
     */
    public void insertNewPicture(PictureDO pictureDO);

    /**
     * 查询全部图片
     *
     * @return 图片对象列表
     */
    public List<PictureDO> queryAllPictureDO();

}
