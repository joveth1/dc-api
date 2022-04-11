package com.joveth.dc.shop.dto;

import com.joveth.dc.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
