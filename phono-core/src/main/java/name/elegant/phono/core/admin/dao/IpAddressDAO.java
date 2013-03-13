package name.elegant.phono.core.admin.dao;

/**
 * Author: Garry King
 * Date: 12-12-14 ����2:43
 * E-mail:flyhzq@sina.com
 */
public interface IpAddressDAO {

    public void insertNewIpAddress(String ip, String address);

    public String queryAddressByIp(String ip);
}
