package com.easy.common.redis.service;

import lombok.AllArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
public class RedisLockService {

    private final RedissonClient redissonClient;

    /**
     * 获取锁的方法
     *
     * @param lockKey   锁键
     * @param waitTime  等待时间
     * @param leaseTime 租约时间
     * @return 是否获取到锁
     */
    public boolean acquireLock(String lockKey, long waitTime, long leaseTime) {
        // 获取指定锁键对应的 Redisson 锁
        RLock lock = redissonClient.getLock(lockKey);
        try {
            // 尝试以指定等待时间和租约时间获取锁
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程中断
            Thread.currentThread().interrupt();
            // 返回获取锁失败
            return false;
        }
    }

    /**
     * 释放锁的方法
     *
     * @param lockKey 锁键
     */
    public void releaseLock(String lockKey) {
        // 获取指定锁键对应的 Redisson 锁
        RLock lock = redissonClient.getLock(lockKey);
        // 如果当前线程持有该锁
        if (lock.isHeldByCurrentThread()) {
            // 释放锁
            lock.unlock();
        }
    }

}