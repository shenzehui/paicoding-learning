package com.szh.autoconf.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "config")
public class UserConfig {
    private String user;

    private String pwd;

    private Integer type;

    private String wechat;
}