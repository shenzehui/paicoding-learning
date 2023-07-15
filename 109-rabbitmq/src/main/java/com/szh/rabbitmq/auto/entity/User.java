package com.szh.rabbitmq.auto.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by szh on 2023-07-06
 *
 * @author szh
 */
@Data
public class User implements Serializable {
    private String id = UUID.randomUUID().toString();
    private String name = "szh";
    private Integer age = 22;
    private LocalDateTime crateTime = LocalDateTime.now();
}

