package com.szh.struct.controller;

import com.szh.struct.converter.UserInfoStructMapper;
import com.szh.struct.entity.UserInfo;
import com.szh.struct.mapper.UserInfoMapper;
import com.szh.struct.vo.UserInfoVO;
import com.szh.struct.vo.UserInfoVO2;
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
    public UserInfoVO2 getUserInfoById(Long id) {
        try {
            UserInfo userInfo = userInfoMapper.selectById(id);
            return UserInfoStructMapper.INSTANCE.toUserInfoVO2(userInfo);
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败\r\n", e);
        }
    }
}
