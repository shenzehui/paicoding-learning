package com.szh.rabbitmq.auto.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Client {
    private String id = UUID.randomUUID().toString();
    private String name = "szh";
    private Integer age = 22;
    private LocalDateTime crateTime = LocalDateTime.now();
}