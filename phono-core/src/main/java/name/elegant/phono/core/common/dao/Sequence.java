package name.elegant.phono.core.common.dao;

/**
 * Author: Garry King
 * Date: 13-5-22 下午9:06
 * E-mail:flyhzq@sina.com
 */
public interface Sequence {

    /**
     * 初始化主键分配的相关数据，包括开启本地缓存、主键分配表的初始化
     */
    public void init();

    /**
     * 获取对应表的下一个全局主键
     *
     * @return 主键
     */
    public long getNextValue();
}
