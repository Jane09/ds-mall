package com.ds.mall.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @author tb
 * @date 2019/1/7 11:35
 */
public final class EntityUtils {


    private static final String CREATE_BY = "createBy";
    private static final String CREATE_IP = "createIp";
    private static final String CREATE_AT = "createAt";
    private static final String UPDATE_BY = "updateBy";
    private static final String UPDATE_IP = "updateIp";
    private static final String UPDATE_AT = "updateAt";

    private static final String[] CREATE_FIELDS = {CREATE_BY,CREATE_AT,CREATE_IP};
    private static final String[] UPDATE_FIELDS = {UPDATE_BY,UPDATE_AT,UPDATE_IP};

    public static void main(String[] args) {
        System.out.println(decode("好"));
    }

    public static <T> void create(T entity){
        String createBy;
        String createAt;
        String createIp;
        HttpServletRequest request = getRequest();
        if(null != request) {
            createBy = request.getHeader("");
        }


    }

    public static <T> void update(T entity) {
        String updateBy;
        String updateAt;
        String updateIp;
        HttpServletRequest request = getRequest();
    }

    private static HttpServletRequest getRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(attributes != null && attributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes)attributes).getRequest();
        }
        return null;
    }

    private static String decode(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException e) {
            //do nothing
        }
        return value;
    }


    private static String getValueDef(String key,HttpServletRequest request, String def) {
        String value = request.getHeader(key);
        return StringUtils.isBlank(value)?def:value;
    }
}
