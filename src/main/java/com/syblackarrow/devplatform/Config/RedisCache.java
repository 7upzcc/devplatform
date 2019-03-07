package com.syblackarrow.devplatform.Config;

import com.syblackarrow.devplatform.Utils.SpringContextHolder;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id; // cache instance id
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
        ValueOperations opsForValue =  getRedisTemplate().opsForValue();
        opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
    }

    @Override
    public Object getObject(Object key) {
        ValueOperations opsForValue =  getRedisTemplate().opsForValue();
        return opsForValue.get(key);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        getRedisTemplate().delete(key);
        return null;
    }

    @Override
    public void clear() {
        getRedisTemplate().execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    public RedisTemplate getRedisTemplate(){
        return SpringContextHolder.getBean("redisTemplate") ;
    }
}
