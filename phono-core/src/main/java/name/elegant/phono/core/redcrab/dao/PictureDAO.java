package name.elegant.phono.core.redcrab.dao;

import name.elegant.phono.client.dataobject.redcrab.PictureDO;

import java.util.List;

/**
 * User: Administrator
 * Date: 13-4-17
 * Time: ����11:24
 */
public interface PictureDAO {
    /**
     * �����µ�ͼƬ����
     *
     * @param pictureDO
     */
    public void insertNewPicture(PictureDO pictureDO);

    /**
     * ��ѯȫ��ͼƬ
     *
     * @return ͼƬ�����б�
     */
    public List<PictureDO> queryAllPictureDO();

}
