package com.joveth.dc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author Joveth
 * @date 2022-03-18 17:39
 */
@Data
@ToString
public class UserInfo {
    private String nickName;
    private Integer gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String code;
}
