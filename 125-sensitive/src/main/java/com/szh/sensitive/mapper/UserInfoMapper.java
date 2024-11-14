package com.szh.sensitive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.szh.sensitive.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ASUS
 * @description 针对表【user_info】的数据库操作Mapper
 * @createDate 2024-08-12 17:22:49
 * @Entity com/szh/struct.entity.UserInfo
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




