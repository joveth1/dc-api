package com.joveth.dc.modules.shop.dto;

import com.joveth.dc.base.BaseDTO;
import com.joveth.dc.modules.shop.domain.Spec;
import com.joveth.dc.modules.shop.domain.SpecDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

/**
 * @author Joveth
 * @date 2022-02-24 17:25
 */
@Getter
@Setter
public class SpecDetailDto extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private Double addPrice;

    private SpecSmallDto spec;

    private Integer sortNum;
}
