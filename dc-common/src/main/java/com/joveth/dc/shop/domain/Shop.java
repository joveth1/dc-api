package com.joveth.dc.shop.domain;

import com.joveth.dc.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 门店信息
 * @author Joveth
 * @date 2022-02-22 15:43
 */
@Entity
@Getter
@Setter
@Table(name = "dc_shop")
public class Shop extends BaseEntity implements Serializable {
    @Id
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    private String name;

    /**
     * 门店状态：normal-正常，close-关闭
     */
    private String status;

    /**
     * 门店负责人
     */
    private String leader;
    /**
     * 门店联系电话
     */
    private String mobile;

    /**
     * 门店详细地址，考虑加入经纬度，为以后扩展定位使用
     * */
    private String address;

    /**
     * 门店营业时间
     */
    private String openTime;

    private String closeTime;

    /**
     * 门店图片ID
     */
    private String picid;

    /**
     * 门店公告说明等
     */
    private String description;
}
