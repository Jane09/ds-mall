package com.ds.mall.generator.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author tb
 * @date 2019/1/7 14:42
 */
@Getter
@Setter
public class TableEntity {
    //表的名称
    private String tableName;
    //表的备注
    private String comments;
    //表的主键
    private ColumnEntity pk;
    //表的列名(不包含主键)
    private List<ColumnEntity> columns;
    //类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    //属性名称(第一个字母小写)，如：user_name => userName
    private String className2;
}
