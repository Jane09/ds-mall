package com.ds.mall.cache.parser;

import java.lang.reflect.Type;

/**
 * @author tb
 * @date 2019/1/10 16:34
 */
public interface IResultParser {

    Object parse(String value, Type returnType, Class<?>... origins);
}
