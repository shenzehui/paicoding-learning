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
public class UserInfoVO2 implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passWord;
}
