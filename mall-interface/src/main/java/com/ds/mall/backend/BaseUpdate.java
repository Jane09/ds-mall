package com.ds.mall.backend;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tb
 * @date 2019/1/8 11:09
 */
@Getter
@Setter
public class BaseUpdate implements Serializable {

    private String updateBy;
    private Date updateAt;
    private String updateIp;
}
