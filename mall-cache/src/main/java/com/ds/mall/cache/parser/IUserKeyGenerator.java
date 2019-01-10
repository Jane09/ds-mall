package com.ds.mall.cache.parser;

/**
 * @author tb
 * @date 2019/1/10 16:45
 */
@FunctionalInterface
public interface IUserKeyGenerator {

    String getCurrentUserAccount();
}
