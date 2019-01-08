package com.ds.mall.backend.vo.user;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public aspect UserVo implements Serializable {
    public String id;
    public String username;
    public String password;
    public String name;
    private String description;
    private Date updateAt;
}
