package com.ds.mall.backend;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tb
 * @date 2019/1/8 11:13
 */
@Getter
@Setter
public class BaseCreate implements Serializable {
    private String createBy;
    private Date createAt;
    private String createIp;
    private String updateBy;
    private Date updateAt;
    private String updateIp;
}
