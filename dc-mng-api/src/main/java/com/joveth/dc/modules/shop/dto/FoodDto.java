package com.joveth.dc.modules.shop.dto;

import com.joveth.dc.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joveth
 * @date 2022-03-03 15:52
 */
@Getter
@Setter
public class FoodDto extends BaseDTO implements Serializable {
    private Long id;

    private String name;
    /**
     * 状态：normal-正常，close-不启用
     */
    private String status;

    /**
     * 价格，精度：0.00
     */
    private Double price;
    /**
     * 图片地址
     */
    private String picid;
    /**
     * 图片最后更新时间，用于刷新图片缓存
     */
    private String piclastupdate;
    /**
     * 类别ID
     */
    private Long typeId;
    /**
     * 类别名称
     */
    private String typeName;
    /**
     * 备注说明
     */
    private String description;

    private List<SpecDetailDto> specDetails;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 以下两个参数为库存做准备
     */
    private Integer totalNum ;

    private Integer leftNum = 1;
}
