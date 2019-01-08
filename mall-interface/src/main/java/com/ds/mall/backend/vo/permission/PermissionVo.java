package com.ds.mall.backend.vo.permission;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/8 11:11
 */
@Getter
@Setter
public class PermissionVo implements Serializable {
    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;
}
