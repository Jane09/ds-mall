package com.ds.mall.auth.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/10 14:33
 */
@Getter
@Setter
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String username;
    private String password;
}
