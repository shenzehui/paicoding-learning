package com.szh.binder.pojo;

import lombok.Data;

@Data
public class Mail {

    private String host;

    private String port;

    private String user;

    private String password;

    private String from;
}