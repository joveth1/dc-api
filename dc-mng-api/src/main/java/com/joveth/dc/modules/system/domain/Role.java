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
package com.joveth.dc.modules.system.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joveth.dc.modules.system.service.dto.*;
import lombok.Getter;
import lombok.Setter;
import com.joveth.dc.base.BaseEntity;
import com.joveth.dc.utils.enums.DataScopeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色
 *
 * @author Zheng Jie
 * @date 2018-11-22
 */
@Getter
@Setter
@Entity
@Table(name = "dc_role")
public class Role extends BaseEntity implements Serializable {

    @Id
    @Column(name = "role_id")
    @NotNull(groups = {Update.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dc_roles_menus",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "menu_id")})
    private Set<Menu> menus;

    @ManyToMany
    @JoinTable(name = "dc_roles_depts",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "dept_id", referencedColumnName = "dept_id")})
    private Set<Dept> depts;

    @NotBlank
    private String name;

    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    @Column(name = "level")
    private Integer level = 3;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public RoleDto toDto() {
        RoleDto dto = new RoleDto();
        dto.setDataScope(this.getDataScope());
        dto.setDescription(this.getDescription());
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setLevel(this.getLevel());
        dto.setCreateBy(this.getCreateBy());
        dto.setUpdateBy(this.getUpdateBy());
        dto.setCreateTime(this.getCreateTime());
        dto.setUpdateTime(this.getUpdateTime());
        if (this.menus != null) {
            dto.setMenus(this.menus.stream().map(e->{
                MenuDto menuDto = new MenuDto();
                menuDto.setCache(e.getCache());
                menuDto.setComponent(e.getComponent());
                menuDto.setComponentName(e.getComponentName());
                menuDto.setHidden(e.getHidden());
                menuDto.setIcon(e.getIcon());
                menuDto.setId(e.getId());
                menuDto.setIFrame(e.getIFrame());
                menuDto.setMenuSort(e.getMenuSort());
                menuDto.setPath(e.getPath());
                menuDto.setPermission(e.getPermission());
                menuDto.setPid(e.getPid());
                menuDto.setSubCount(e.getSubCount());
                menuDto.setTitle(e.getTitle());
                menuDto.setType(e.getType());
                return menuDto;
            }).collect(Collectors.toSet()));
        }
        return dto;
    }
}
