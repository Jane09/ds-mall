package com.ds.mall.cache.parser;

import com.ds.mall.cache.annotation.CacheScope;

/**
 * @author tb
 * @date 2019/1/10 16:42
 */
public abstract class IKeyGenerator {
    private static final String UNDERLINE = "_";

    protected String getKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] args) {
        StringBuilder sb = new StringBuilder();
        key = buildKey(key,scope,parameterTypes,args);
        sb.append(key);
        if(scope.equals(CacheScope.user)){
            IUserKeyGenerator generator = getUserKeyGenerator();
            if(null != generator){
                sb.append(UNDERLINE)
                    .append(generator.getCurrentUserAccount());
            }
        }
        return sb.toString();
    }

    protected abstract String buildKey(String key, CacheScope scope, Class<?>[] parameterTypes, Object[] args);

    protected abstract IUserKeyGenerator getUserKeyGenerator();
}
