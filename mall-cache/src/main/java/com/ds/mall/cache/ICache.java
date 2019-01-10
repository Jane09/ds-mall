package com.ds.mall.cache;

import com.ds.mall.cache.annotation.CacheNode;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/10 17:01
 */
public interface ICache<T> {

    T get(String key);

    List<CacheNode> getByPrefix(String prefix);

    List<CacheNode> getAll();

    boolean isEnabled();

    void set(String key, T value, int expireMs);

    void set(String key, T value, int expireMs,String desc);

    Long remove(String key);

    Long remove(String... keys);

    Long removeByPrefix(String prefix);

    String setSys(String key);
}
