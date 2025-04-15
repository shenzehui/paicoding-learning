package com.szh.breaker.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * 断路器开关
 *
 * @author shenzehui
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = BreakerProperties.PREFIX)
@Component
public class BreakerProperties {

    public static final String PREFIX = "local.breaker.setting";

    private boolean keyConvert = true;

    /** 断路器 */
    private Breaker breaker = new Breaker();


    /** 断路器配置，注意这是一种相对粗略的保护规则 */
    @Data
    public static class Breaker {

        /** 断路器 开关 */
        private boolean enabled = true;

        /** 窗口次数，这里不用时间 */
        private Integer windowInTimes = 10;

        /** 错误率 */
        private Integer breakerErrorPercentage = 60;

        /** 断路睡眠60秒 */
        private Duration breakerSleep = Duration.parse("PT60S");

        /** 最大响应时间，0代表不限制响应时间 */
        private Duration resCostMax = Duration.parse("PT1S");
    }

}