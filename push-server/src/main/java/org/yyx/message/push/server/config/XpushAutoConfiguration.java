package org.yyx.message.push.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 自动注入配置
 *
 * @author 叶云轩 at tdg_yyx@foxmail.com
 * @date 2022/4/13 15:55
 */
@ComponentScans({@ComponentScan(basePackages = "org.yyx.message.push.server", excludeFilters = @ComponentScan.Filter(type =
        FilterType.ASSIGNABLE_TYPE, classes = XpushAutoConfiguration.class)), @ComponentScan(basePackages = {"cn.hutool.extra.spring"})})
@Configuration
public class XpushAutoConfiguration {
}
