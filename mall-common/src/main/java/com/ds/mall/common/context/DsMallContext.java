package com.ds.mall.common.context;

import com.ds.mall.common.constant.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tb
 * @date 2019/1/7 10:57
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DsMallContext {
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    /**
     * 添加key/value到当前上下文中
     * @param key
     * @param value
     */
    public static void set(String key, Object value) {
        Map<String,Object> map = threadLocal.get();
        if(null == map){
            map = new ConcurrentHashMap<>();
            map.put(key,value);
            threadLocal.set(map);
        }else {
            map.put(key,value);
        }
    }

    public static Object get(String key) {
        Map<String,Object> map = threadLocal.get();
        if(null == map){
            map = new ConcurrentHashMap<>();
            threadLocal.set(map);
            return null;
        }
        return map.get(key);
    }
    public static void setClientId(String clientId) {
        set(Constants.CONTEXT_CLIENT_ID,clientId);
    }
    public static String getClientId() {
        return getByKey(Constants.CONTEXT_CLIENT_ID);
    }
    public static void setClientToken(String clientToken) {
        set(Constants.CONTEXT_CLIENT_TOKEN,clientToken);
    }
    public static String getToken(){
        return getByKey(Constants.CONTEXT_CLIENT_TOKEN);
    }
    public static void setClientName(String clientName) {
        set(Constants.CONTEXT_CLIENT_NAME,clientName);
    }
    public static String getClientName(){
        return getByKey(Constants.CONTEXT_CLIENT_NAME);
    }


    private static String getByKey(String key) {
        Object object = get(key);
        return objectToStr(object);
    }

    private static String objectToStr(Object object) {
        return null ==object?"":String.valueOf(object);
    }
}
