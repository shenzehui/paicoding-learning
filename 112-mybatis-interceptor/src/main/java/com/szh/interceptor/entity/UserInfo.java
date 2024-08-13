package com.szh.interceptor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "userid")
    private String userid;

    /**
     * 用户昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "mobilephone")
    private String mobilephone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户头像
     */
    @TableField(value = "uimage")
    private String uimage;

    /**
     * 用户性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 学校
     */
    @TableField(value = "school")
    private String school;

    /**
     * 院系
     */
    @TableField(value = "faculty")
    private String faculty;

    /**
     * 入学时间
     */
    @TableField(value = "startime")
    private String startime;

    /**
     * 1正常 0封号
     */
    @TableField(value = "userstatus")
    private Integer userstatus;

    /**
     * 注册时间
     */
    @TableField(value = "createtime")
    private Date createtime;

    /**
     * 用户状态
     */
    @TableField(value = "status")
    private String status;

    /**
     * 
     */
    @TableField(value = "sign")
    private String sign;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}