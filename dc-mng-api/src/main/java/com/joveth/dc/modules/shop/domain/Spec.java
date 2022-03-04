package com.joveth.dc.modules.shop.domain;

import com.joveth.dc.base.BaseEntity;
import com.joveth.dc.modules.shop.dto.SpecDetailDto;
import com.joveth.dc.modules.shop.dto.SpecDto;
import com.joveth.dc.modules.shop.dto.SpecSmallDto;
import com.joveth.dc.modules.system.service.dto.DeptSmallDto;
import com.joveth.dc.modules.system.service.dto.RoleSmallDto;
import com.joveth.dc.modules.system.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品规格
 *
 * @author Joveth
 * @date 2022-02-22 16:42
 */
@Entity
@Getter
@Setter
@Table(name = "dc_spec")
public class Spec extends BaseEntity implements Serializable {
    @Id
    @Column(name = "spec_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 规格名称
     */
    private String name;
    /**
     * 状态：normal-正常，close-不启用
     */
    private String status;
    /**
     * 最少可选多少个
     */
    private Integer minSelect;
    /**
     * 最多可选多少个
     */
    private Integer maxSelect;
    /**
     * 规格明细
     */
    @OneToMany(mappedBy = "spec", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<SpecDetail> specDetails;
    /**
     * 排序
     */
    private Integer sortNum = 1;

    public SpecDto toDto() {
        SpecDto dto = new SpecDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setMaxSelect(this.maxSelect);
        dto.setMinSelect(this.minSelect);
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
                detailDto.setSpec(new SpecSmallDto(this.id,this.name));
                return detailDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}
