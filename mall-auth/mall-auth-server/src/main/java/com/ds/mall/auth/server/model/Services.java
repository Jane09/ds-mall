package com.ds.mall.auth.server.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tb
 * @date 2019/1/10 15:26
 */
@Getter
@Setter
public class Services {

    private Long id;
    private String serviceId;
    private String clientId;
    private String description;
}