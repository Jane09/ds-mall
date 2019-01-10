package com.ds.mall.uid.zookeeper;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/10 10:58
 */
@Getter
@Setter
public class AuthInfo implements Serializable {
    public String scheme;
    public byte[] payload;
}
