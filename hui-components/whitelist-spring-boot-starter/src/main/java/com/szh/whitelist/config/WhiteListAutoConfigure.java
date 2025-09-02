package com.szh.whitelist.config;

import com.szh.DoJoinPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Configuration
// 当 WhiteListProperties 位于当前类路径上，才会实例化一个类。
@ConditionalOnClass(WhiteListProperties.class)
// properties 配置会被注入进来，当然你也可以选择使用 @Autowired 的方式配置注入在使用属性。
@EnableConfigurationProperties(WhiteListProperties.class)
public class WhiteListAutoConfigure {

    @Bean("whiteListConfig")
    // 代表只会实例化一个 Bean 对象。
    @ConditionalOnMissingBean
    public String whiteListConfig(WhiteListProperties properties) {
        return properties.getUsers();
    }

    @Bean
    public DoJoinPoint traceAspect() {
        return new DoJoinPoint();
    }

}
