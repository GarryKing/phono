package name.elegant.phono.core.common.dao;

/**
 * Author: Garry King
 * Date: 13-5-22 ����9:06
 * E-mail:flyhzq@sina.com
 */
public interface Sequence {

    /**
     * ��ʼ�����������������ݣ������������ػ��桢���������ĳ�ʼ��
     */
    public void init();

    /**
     * ��ȡ��Ӧ�����һ��ȫ������
     *
     * @return ����
     */
    public long getNextValue();
}
