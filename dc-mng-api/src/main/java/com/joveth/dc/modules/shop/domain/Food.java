package com.joveth.dc.modules.shop.domain;

import com.joveth.dc.base.BaseEntity;
import com.joveth.dc.modules.shop.dto.FoodDto;
import com.joveth.dc.modules.shop.dto.SpecDetailDto;
import com.joveth.dc.modules.shop.dto.SpecDto;
import com.joveth.dc.modules.shop.dto.SpecSmallDto;
import com.joveth.dc.modules.system.domain.Dept;
import com.joveth.dc.modules.system.domain.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 商品
 *
 * @author Joveth
 * @date 2022-02-22 15:43
 */
@Entity
@Getter
@Setter
@Table(name = "dc_food")
public class Food extends BaseEntity implements Serializable {
    @Id
    @Column(name = "food_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    /**
     * 可选规格
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dc_food_specs",
            joinColumns = {@JoinColumn(name = "food_id", referencedColumnName = "food_id")},
            inverseJoinColumns = {@JoinColumn(name = "spec_detail_id", referencedColumnName = "spec_detail_id")})
    private List<SpecDetail> specDetails;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 以下两个参数为库存做准备
     */
    private Integer totalNum ;

    private Integer leftNum = 1;

    public FoodDto toDto(){
        FoodDto dto = new FoodDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setPrice(this.price);
        dto.setDescription(this.description);
        dto.setPicid(this.picid);
        dto.setPiclastupdate(this.piclastupdate);
        dto.setTypeId(this.typeId);
        dto.setTypeName(this.typeName);
        dto.setSortNum(this.sortNum);
        dto.setStatus(this.status);
        dto.setId(this.id);
        dto.setCreateTime(this.getCreateTime());
        dto.setUpdateTime(this.getUpdateTime());
        dto.setCreateBy(this.getCreateBy());
        dto.setUpdateBy(this.getUpdateBy());
        if (this.specDetails != null) {
            dto.setSpecDetails(this.specDetails.stream().map(e->{
                SpecDetailDto detailDto = new SpecDetailDto();
                detailDto.setId(e.getId());
                detailDto.setName(e.getName());
                detailDto.setAddPrice(e.getAddPrice());
                detailDto.setSortNum(e.getSortNum());
                detailDto.setSpec(new SpecSmallDto(e.getSpec().getId(),e.getSpec().getName()));
                return detailDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
