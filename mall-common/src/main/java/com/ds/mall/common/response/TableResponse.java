package com.ds.mall.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/7 11:55
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableResponse<T> extends BaseResponse {
    private long total;
    private List<T> rows;
}
