package com.joveth.dc.modules.shop.dto;

import com.joveth.dc.base.BaseDTO;
import com.joveth.dc.modules.shop.domain.SpecDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Joveth
 * @date 2022-02-24 17:25
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecSmallDto extends BaseDTO implements Serializable {
    private Long id;

    private String name;
}
