package name.elegant.phono.core.admin.dao.impl;

import name.elegant.phono.core.admin.dao.BaseDao;
import name.elegant.phono.core.admin.dao.IpAddressDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Garry King
 * Date: 12-12-13 ÉÏÎç12:40
 * E-mail:flyhzq@sina.com
 */
@Repository
public class IbatisIpAddressDAO extends BaseDao implements IpAddressDAO {

    @Override
    public void insertNewIpAddress(String ip, String address) {
        Map<String, Object> map = new HashMap();
        map.put("ip", ip);
        map.put("address", address);
        getSqlMapClientTemplate().insert("IBatisIpAddressDAO.insertNewIpAddress", map);
    }

    @Override
    public String queryAddressByIp(String ip) {
        return (String) getSqlMapClientTemplate().queryForObject("IBatisIpAddressDAO.queryAddressByIp", ip);
    }

}
