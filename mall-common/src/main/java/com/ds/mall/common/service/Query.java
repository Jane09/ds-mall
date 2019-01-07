package com.ds.mall.common.service;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tb
 * @date 2019/1/7 11:59
 */
@Getter
@Setter
public class Query extends LinkedHashMap<String,Object> {
    private static final String PAGE = "page";
    private static final String LIMIT = "limit";
    //当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);
        //分页参数
        if(params.containsKey(PAGE)) {
            this.page = Integer.parseInt(params.get(PAGE).toString());
        }
        if(params.containsKey(LIMIT)) {
            this.limit = Integer.parseInt(params.get(LIMIT).toString());
        }
        this.remove(PAGE);
        this.remove(LIMIT);
    }
}
