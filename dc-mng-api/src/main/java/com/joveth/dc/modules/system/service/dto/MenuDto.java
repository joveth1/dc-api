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
package com.joveth.dc.modules.system.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.joveth.dc.base.BaseDTO;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Zheng Jie
 * @date 2018-12-17
 */
@Getter
@Setter
@NoArgsConstructor
public class MenuDto extends BaseDTO implements Serializable {

    private Long id;

    private List<MenuDto> children;

    private Integer type;

    private String permission;

    private String title;

    private Integer menuSort;

    private String path;

    private String component;

    private Long pid;

    private Integer subCount;

    private Boolean iFrame;

    private Boolean cache;

    private Boolean hidden;

    private String componentName;

    private String icon;

    public MenuDto(Long id, Integer type, String permission, String title, Integer menuSort, String path, String component, Long pid, Integer subCount, Boolean iFrame, Boolean cache, Boolean hidden, String componentName, String icon) {
        this.id = id;
        this.type = type;
        this.permission = permission;
        this.title = title;
        this.menuSort = menuSort;
        this.path = path;
        this.component = component;
        this.pid = pid;
        this.subCount = subCount;
        this.iFrame = iFrame;
        this.cache = cache;
        this.hidden = hidden;
        this.componentName = componentName;
        this.icon = icon;
    }

    public Boolean getHasChildren() {
        return subCount > 0;
    }
    public Boolean getLeaf() {
        return subCount <= 0;
    }

    public String getLabel() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuDto menuDto = (MenuDto) o;
        return Objects.equals(id, menuDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
