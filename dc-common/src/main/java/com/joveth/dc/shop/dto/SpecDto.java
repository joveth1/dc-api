package com.joveth.dc.shop.dto;

import com.joveth.dc.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joveth
 * @date 2022-02-24 17:25
 */
@Getter
@Setter
public class SpecDto extends BaseDTO implements Serializable {
    private Long id;

    private String name;

    private String status;

    private Integer minSelect;

    private Integer maxSelect;

    private List<SpecDetailDto> specDetails;

    private Integer sortNum;
}
