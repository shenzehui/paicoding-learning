package com.szh.struct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szh.struct.entity.UserInfo;
import com.szh.struct.mapper.UserInfoMapper;
import com.szh.struct.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author shenzehui
 * @description 针对表【user_info】的数据库操作Service实现
 * @createDate 2024-08-12 17:22:49
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}




