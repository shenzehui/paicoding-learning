package com.szh.struct.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Getter
@Setter
public class UserInfoVO implements Serializable {

    /**
     * 用户id
     */
    private String userid;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

}
