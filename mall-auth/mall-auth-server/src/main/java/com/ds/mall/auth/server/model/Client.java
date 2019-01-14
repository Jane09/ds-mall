package com.ds.mall.auth.server.model;

import com.ds.mall.backend.BaseCreateAndUpdate;
import com.ds.mall.common.service.Mid;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/10 14:35
 */
@Getter
@Setter
@Table(name = "auth_clients")
public class Client extends BaseCreateAndUpdate implements Serializable {
    //随机ID
    @Mid
    private Long id;
    //客户端编码
    private String code;
    //密码
    private String secret;
    //名称
    private String name;
    //是否锁定
    private String locked = "0";
    //秒杀
    private String description;
}
