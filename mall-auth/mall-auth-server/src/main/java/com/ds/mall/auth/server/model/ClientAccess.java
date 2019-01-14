package com.ds.mall.auth.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * 内部服务相互访问的控制
 * @author tb
 * @date 2019/1/10 15:26
 */
@Getter
@Setter
@Table(name = "auth_client")
public class ClientAccess {
    //访问ID
    private String clientFid;
    //访问能访问的ID
    private String clientTid;
}
