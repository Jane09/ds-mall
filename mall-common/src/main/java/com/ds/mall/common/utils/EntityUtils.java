package com.ds.mall.common.utils;

import com.ds.mall.common.constant.Constants;
import com.ds.mall.common.context.DsMallContext;
import com.ds.mall.common.service.Mid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author tb
 * @date 2019/1/7 11:35
 */
public final class EntityUtils {

    private static final String USER_HOST = "userHost";
    private static final String CREATE_BY = "createBy";
    private static final String CREATE_IP = "createIp";
    private static final String CREATE_AT = "createAt";
    private static final String UPDATE_BY = "updateBy";
    private static final String UPDATE_IP = "updateIp";
    private static final String UPDATE_AT = "updateAt";
    private static final String ID = "id";

    private static final String[] CREATE_FIELDS = {CREATE_BY,CREATE_AT,CREATE_IP};
    private static final String[] UPDATE_FIELDS = {UPDATE_BY,UPDATE_AT,UPDATE_IP};

    public static void main(String[] args) {
//        System.out.println(decode("好@&"));
    }

    public static <T> void createAndUpdate(T entity) {
        create(entity);
        update(entity);
    }

    private static <T> void create(T entity){
        String createBy = null;
        String createIp = "";
        HttpServletRequest request = getRequest();
        if(null != request) {
            createBy = StringUtils.trimToEmpty(request.getHeader(Constants.CLIENT_ID));
            createIp = getValueDef(USER_HOST,request, NetUtils.getIp(request));
        }
        if(StringUtils.isBlank(createBy)) {
            createBy = DsMallContext.getClientId();
        }
        Field field = ReflectionUtils.getAccessibleField(entity,CREATE_AT);
        Object[] values;
        if(null != field && field.getType().equals(Date.class)) {
            values = new Object []{createBy,new Date(),createIp};
            setDefaultValues(entity,CREATE_FIELDS,values);
        }
        Field idField = ReflectionUtils.getAccessibleField(entity,ID);
        if(idField != null) {
            Annotation mid = idField.getAnnotation(Mid.class);
            if(mid != null){
                setDefaultValues(entity,new String[]{ID},new Long[]{1L});
            }
        }
    }

    public static <T> void update(T entity) {
        String updateBy = null;
        String updateIp="";
        HttpServletRequest request = getRequest();
        if(null != request) {
            updateIp = getValueDef(USER_HOST,request, NetUtils.getIp(request));
            updateBy = StringUtils.trimToEmpty(request.getHeader(Constants.CLIENT_ID));
        }
        if(StringUtils.isBlank(updateBy)) {
            updateBy = DsMallContext.getClientId();
        }
        Object[] values;
        Field field = ReflectionUtils.getAccessibleField(entity,UPDATE_AT);
        if(null != field && field.getType().equals(Date.class)) {
            values = new Object []{updateBy,new Date(),updateIp};
            setDefaultValues(entity,UPDATE_FIELDS,values);
        }
    }

    private static HttpServletRequest getRequest() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if(attributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes)attributes).getRequest();
        }
        return null;
    }

    private static <T> void setDefaultValues(T entity, String[] fields, Object[] value) {
        for(int i=0;i<fields.length;i++){
            String field = fields[i];
            if(ReflectionUtils.hasField(entity, field)){
                ReflectionUtils.invokeSetter(entity, field, value[i]);
            }
        }
    }

    private static String decode(String value) {
        try {
            return URLDecoder.decode(URLDecoder.decode(value, StandardCharsets.UTF_8.displayName()),StandardCharsets.UTF_8.displayName());
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
