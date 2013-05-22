package name.elegant.phono.core.common.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Garry King
 * Date: 13-5-9 下午11:17
 * E-mail:flyhzq@sina.com
 */
public class GroupSequence extends BaseDao implements Sequence {
    private final Lock lock = new ReentrantLock();
    /**
     * 当前可用主键号
     */
    private static AtomicLong value = new AtomicLong(0L);
    /**
     * 逻辑表名
     */
    private String logicTableName;
    /**
     * 分表数量
     */
    private long stepLength;

    @Override
    public void init() {
        if (value.get() == 0) {
            Long dbValue = queryCurrentUsedValue();
            if (dbValue == null)
                insertNewSequence(stepLength * 2);
            else if (dbValue == 0)
                updateSequence(stepLength * 2);
//TODO   logger record         else if (dbValue <= 0)
            dbValue = queryCurrentUsedValue();
            value.set(dbValue == null ? 0 : dbValue);
        }
    }

    @Override
    public long getNextValue() {
        if (value.get() == 0)
            init();
        return value.get();
    }

    public Long queryCurrentUsedValue() {
        return (Long) getSqlMapClientTemplate().queryForObject("Sequence.queryLastSequence", logicTableName);
    }

    public void insertNewSequence(long value) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logicTableName", logicTableName);
        map.put("value", value);
        getSqlMapClientTemplate().insert("Sequence.insertNewSequence", map);
    }

    public void updateSequence(long value) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logicTableName", logicTableName);
        map.put("value", value);
        //TODO 这里需要将上次获取的时间戳传入，不能比上次时间戳迟，如果迟了说明被其他线程更新了
        //还要看此条update语句 是否影响了1行，如果没有影响就说明被更新了
//        map.put("now", new Date());
        getSqlMapClientTemplate().update("Sequence.updateSequence", map);
    }

    public void setLogicTableName(String logicTableName) {
        this.logicTableName = logicTableName;
    }

    public void setStepLength(long stepLength) {
        this.stepLength = stepLength;
    }

    public static void main(String[] args) {
        Object a = 1L;
        a = null;
        Long b = (Long) a;
        System.out.println(b);
    }
}
