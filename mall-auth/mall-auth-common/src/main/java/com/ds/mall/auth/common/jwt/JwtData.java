package com.ds.mall.auth.common.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author tb
 * @date 2019/1/7 18:13
 */
@Getter
@AllArgsConstructor
public class JwtData implements IJwtData, Serializable {
    //服务编码，如mall-gate
    private String clientId;
    //服务名称，如 访问网关
    private String clientName;
    //唯一性表示，如 ID
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JwtData jwtInfo = (JwtData) o;
        if (!Objects.equals(clientName, jwtInfo.clientName)) {
            return false;
        }
        return Objects.equals(clientId, jwtInfo.clientId);
    }

    @Override
    public int hashCode() {
        int result = clientName != null ? clientName.hashCode() : 0;
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }
}
