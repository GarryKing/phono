package name.elegant.phono.core.common.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Garry King
 * Date: 13-5-9 ����11:17
 * E-mail:flyhzq@sina.com
 */
public class GroupSequence extends BaseDao implements Sequence {
    private final Lock lock = new ReentrantLock();
    /**
     * ��ǰ����������
     */
    private static AtomicLong value = new AtomicLong(0L);
    /**
     * �߼�����
     */
    private String logicTableName;
    /**
     * �ֱ�����
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
        //TODO ������Ҫ���ϴλ�ȡ��ʱ������룬���ܱ��ϴ�ʱ����٣��������˵���������̸߳�����
        //��Ҫ������update��� �Ƿ�Ӱ����1�У����û��Ӱ���˵����������
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
