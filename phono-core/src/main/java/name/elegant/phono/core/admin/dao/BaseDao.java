package name.elegant.phono.core.admin.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Author: Garry King
 * Date: 13-2-11 обнГ10:08
 * E-mail:flyhzq@sina.com
 */
public class BaseDao extends SqlMapClientDaoSupport {

    @Resource
    private SqlMapClient sqlMapClient;

    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClient(sqlMapClient);
    }
}
