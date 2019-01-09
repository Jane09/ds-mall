package com.ds.mall.uid;

import com.ds.mall.uid.config.AutoUidConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tb
 * @date 2019/1/9 17:17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AutoUidConfig.class)
@Documented
@Inherited
public @interface EnableMallUid {

}
