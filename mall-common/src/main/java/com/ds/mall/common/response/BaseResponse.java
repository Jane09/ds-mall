package com.ds.mall.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/7 11:48
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseResponse {
    private int status = 200;
    private String message;

    public BaseResponse status(int status){
        this.setStatus(status);
        return this;
    }

    public BaseResponse message(String message) {
        this.setMessage(message);
        return this;
    }
}
