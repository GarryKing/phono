package name.elegant.phono.core.admin.dao;

import name.elegant.phono.client.dataobject.AccessLogDO;

import java.util.List;

public interface AccessLogDAO {

    public void insertNewLog(AccessLogDO accessLogDO);

    public List<AccessLogDO> queryAllAccessLogDO();

    public List<AccessLogDO> queryAccessLogDOByIp(String ip);

}