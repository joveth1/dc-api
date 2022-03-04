package com.joveth.dc.modules.shop.domain;

import com.joveth.dc.base.BaseEntity;
import com.joveth.dc.modules.shop.dto.SpecDetailDto;
import com.joveth.dc.modules.shop.dto.SpecSmallDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 规格明细
 *
 * @author Joveth
 * @date 2022-02-22 16:48
 */
@Entity
@Getter
@Setter
@Table(name = "dc_spec_detail")
public class SpecDetail extends BaseEntity implements Serializable {
    @Id
    @Column(name = "spec_detail_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    /**
     * 状态：normal-正常，close-不启用
     */
    private String status;
    /**
     * 在价格基础上添加金额，精度：0.00
     */
    private Double addPrice;

    @JoinColumn(name = "spec_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Spec spec;
    /**
     * 排序
     */
    private Integer sortNum = 1;

    public SpecDetail(Long id) {
        this.id = id;
    }

    public SpecDetail() {
    }

    public SpecDetailDto toDto() {
        SpecDetailDto detailDto = new SpecDetailDto();
        detailDto.setId(this.getId());
        detailDto.setName(this.getName());
        detailDto.setAddPrice(this.getAddPrice());
        detailDto.setSortNum(this.getSortNum());
        detailDto.setSpec(new SpecSmallDto(this.id, this.name));
        return detailDto;
    }
}

