package com.ds.mall.cache.parser;

import com.ds.mall.cache.annotation.CacheScope;

import java.util.regex.Pattern;

/**
 * @author tb
 * @date 2019/1/10 16:49
 */
public class DefaultKeyGenerator extends AbstractKeyGenerator {

    private static final String BLOCK_LEFT = "{";
    private static final String BLOCK_RIGHT = "}";
    private static final Pattern REG = Pattern.compile("\\d+\\.?[\\w]*");

    @Override
    protected String buildKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] args) {
        return null;
    }

    @Override
    protected IUserKeyGenerator getUserKeyGenerator() {
        return new IUserKeyGenerator() {
            @Override
            public String getCurrentUserAccount() {
                return null;
            }
        };
    }
}
