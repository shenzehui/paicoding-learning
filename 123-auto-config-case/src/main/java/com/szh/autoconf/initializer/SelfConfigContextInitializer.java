package com.szh.autoconf.initializer;

import com.szh.autoconf.context.SelfConfigContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

/**
 * 容器刷新前回调 ApplicationContextInitializer
 */
public class SelfConfigContextInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("postProcessEnvironment#initialize");
        ConfigurableEnvironment env = applicationContext.getEnvironment();
        initialize(env);
    }

    protected void initialize(ConfigurableEnvironment environment) {
        if (environment.getPropertySources().contains("selfSource")) {
            // 已经初始化过了，忽略
            return;
        }

        MapPropertySource propertySource = new MapPropertySource("selfSource", SelfConfigContext.getInstance().getCache());
        environment.getPropertySources().addFirst(propertySource);
    }
}
