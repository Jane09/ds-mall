package com.ds.mall.common.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/7 14:20
 */
@Getter
@Setter
public class ObjectResponse<T> extends BaseResponse {

    T data;
    boolean rel;

    public ObjectResponse rel(boolean rel) {
        this.setRel(rel);
        return this;
    }


    public ObjectResponse data(T data) {
        this.setData(data);
        return this;
    }
}
