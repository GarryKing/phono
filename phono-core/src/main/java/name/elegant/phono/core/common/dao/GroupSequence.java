package name.elegant.phono.core.common.dao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Garry King
 * Date: 13-5-9 обнГ11:17
 * E-mail:flyhzq@sina.com
 */
public class GroupSequence {
    private final Lock lock = new ReentrantLock();
}
