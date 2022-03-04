package com.joveth.dc.modules.shop.domain;

import com.joveth.dc.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 类别
 * @author Joveth
 * @date 2022-02-22 17:20
 */
@Entity
@Getter
@Setter
@Table(name = "dc_type")
public class Type extends BaseEntity implements Serializable {
    @Id
    @Column(name = "type_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    private String name;
    /**
     * 状态：normal-正常，close-不启用
     */
    private String status;
    /**
     * 排序
     */
    private Integer sortNum = 1;
}
