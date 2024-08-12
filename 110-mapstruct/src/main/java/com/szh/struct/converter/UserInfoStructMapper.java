package com.szh.struct.converter;

import com.szh.struct.entity.UserInfo;
import com.szh.struct.vo.UserInfoVO;
import com.szh.struct.vo.UserInfoVO2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Mapper
public interface UserInfoStructMapper {

    UserInfoStructMapper INSTANCE = Mappers.getMapper(UserInfoStructMapper.class);

    UserInfoVO toUserInfoVO(UserInfo info);

    @Mappings({
            @Mapping(source = "userid", target = "userId", defaultValue = "0"),
            @Mapping(source = "username", target = "userName"),
            @Mapping(source = "password", target = "passWord")
    })
    UserInfoVO2 toUserInfoVO2(UserInfo info);

}
