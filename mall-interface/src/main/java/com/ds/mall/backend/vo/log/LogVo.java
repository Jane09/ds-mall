package com.ds.mall.backend.vo.log;

import com.ds.mall.backend.BaseCreate;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/8 11:11
 */
@Getter
@Setter
public class LogVo extends BaseCreate implements Serializable {
    private String menu;
    private String opt;
    private String uri;
}
