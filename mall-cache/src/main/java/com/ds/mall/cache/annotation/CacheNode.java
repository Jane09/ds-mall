package com.ds.mall.cache.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author tb
 * @date 2019/1/10 16:59
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheNode {
    private String key = "";
    private String desc = "";
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    public CacheNode(String key, Date expireTime) {
        this.key = key;
        this.expireTime = expireTime;
    }
}
