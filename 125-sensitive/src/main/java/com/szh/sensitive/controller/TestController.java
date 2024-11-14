package com.szh.sensitive.controller;

import com.szh.sensitive.entity.UserInfo;
import com.szh.sensitive.mapper.UserInfoMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@RestController
@RequestMapping("/test")
public class TestController {

    private final UserInfoMapper userInfoMapper;

    public TestController(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @RequestMapping("/getUserInfoById")
    public UserInfo getUserInfoById(Long id) {
        try {
            return userInfoMapper.selectById(id);
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败\r\n", e);
        }
    }
}
