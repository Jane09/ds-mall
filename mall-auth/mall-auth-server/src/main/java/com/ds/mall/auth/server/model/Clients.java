package com.ds.mall.auth.server.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/10 14:35
 */
@Getter
@Setter
public class Clients implements Serializable {

    private Integer id;

    private String code;

    private String secret;

    private String name;

    private String locked = "0";

    private String description;
}
