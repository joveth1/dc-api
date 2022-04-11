package com.joveth.dc.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author Joveth
 * @date 2022-03-18 17:39
 */
@Data
@ToString
public class CodeToSession {
    private String openid;
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String errmsg;
}
