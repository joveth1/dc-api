package com.joveth.dc.domain;

import com.joveth.dc.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Joveth
 * @date 2022-03-24 11:27
 */
@Entity
@Getter
@Setter
@Table(name = "dc_mobile_user", indexes = {@Index(name = "dc_mobile_user_openid_index", columnList = "openid", unique = true)})
public class MobileUser extends BaseEntity implements Serializable {
    @Id
    @Column(name = "user_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String openid;

    private String nickName;

    private Integer gender;

    private String city;

    private String province;

    private String status;

    private String sessionKey;
}
