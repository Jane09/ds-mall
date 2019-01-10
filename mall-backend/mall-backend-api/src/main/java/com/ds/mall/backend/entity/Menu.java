package com.ds.mall.backend.entity;

import com.ds.mall.common.service.Mid;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/10 15:57
 */
@Getter
@Setter
public class Menu implements Serializable {

    @Mid
    private Integer id;
    private String code;
    private String title;
    private Integer parentId;
    private String href;
    private String icon;
    private String type;
    private String description;
}
