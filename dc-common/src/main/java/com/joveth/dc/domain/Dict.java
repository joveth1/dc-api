/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.joveth.dc.domain;

import com.joveth.dc.domain.dto.DictDetailDto;
import com.joveth.dc.domain.dto.DictDto;
import com.joveth.dc.domain.dto.DictSmallDto;
import lombok.Getter;
import lombok.Setter;
import com.joveth.dc.base.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Entity
@Getter
@Setter
@Table(name="dc_dict")
public class Dict extends BaseEntity implements Serializable {

    @Id
    @Column(name = "dict_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DictDetail> dictDetails;

    @NotBlank
    private String name;

    private String description;

    public DictDto toDto(){
        DictDto dto = new DictDto();
        dto.setDescription(this.getDescription());
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setCreateTime(this.getCreateTime());
        dto.setUpdateTime(this.getUpdateTime());
        dto.setCreateBy(this.getCreateBy());
        dto.setUpdateBy(this.getUpdateBy());
        if(this.dictDetails!=null){
            dto.setDictDetails(this.dictDetails.stream().map(e->{
                DictDetailDto dictDetailDto = new DictDetailDto();
                dictDetailDto.setId(e.getId());
                dictDetailDto.setLabel(e.getLabel());
                dictDetailDto.setValue(e.getValue());
                dictDetailDto.setCreateTime(e.getCreateTime());
                dictDetailDto.setUpdateTime(e.getUpdateTime());
                dictDetailDto.setCreateBy(e.getCreateBy());
                dictDetailDto.setUpdateBy(e.getUpdateBy());
                dictDetailDto.setDictSort(e.getDictSort());
                dictDetailDto.setDict(new DictSmallDto(e.getDict().getId()));
                return dictDetailDto;
            }).collect(Collectors.toList()));
        }
        return dto;
    }
}