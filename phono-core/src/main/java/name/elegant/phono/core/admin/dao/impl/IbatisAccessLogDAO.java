package name.elegant.phono.core.admin.dao.impl;

import name.elegant.phono.client.dataobject.AccessLogDO;
import name.elegant.phono.core.admin.dao.AccessLogDAO;
import name.elegant.phono.core.admin.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Author: Garry King
 * Date: 12-12-13 ÉÏÎç12:40
 * E-mail:flyhzq@sina.com
 */
@Repository
public class IbatisAccessLogDAO extends BaseDao implements AccessLogDAO {

    @Override
    public void insertNewLog(AccessLogDO accessLogDO) {
        accessLogDO = new AccessLogDO();
        accessLogDO.setIp("127.0.0.1");
        accessLogDO.setAccessTime(new Date());
        accessLogDO.setRequestType(1);
        accessLogDO.setRequestUrl("/readAccessLog.jsp");
        accessLogDO.setStatusCode(400);
        accessLogDO.setReturnSize(1238748789L);
        accessLogDO.setAccessUtil("IE 9.0");
        accessLogDO.setLogFrom(1);
        getSqlMapClientTemplate().insert("IBatisAccessLogDAO.insertNewLog", accessLogDO);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AccessLogDO> queryAllAccessLogDO() {
        return (List<AccessLogDO>) getSqlMapClientTemplate().queryForList("IBatisAccessLogDAO.queryAllAccessLogDO");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<AccessLogDO> queryAccessLogDOByIp(String ip) {
        return (List<AccessLogDO>) getSqlMapClientTemplate().queryForList("IBatisAccessLogDAO.queryAccessLogDOByIp", ip);
    }
}
