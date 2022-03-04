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
import com.joveth.dc.modules.system.service.dto.DeptSmallDto;
import com.joveth.dc.modules.system.service.dto.RoleSmallDto;
import com.joveth.dc.modules.system.service.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import com.joveth.dc.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@Entity
@Getter
@Setter
@Table(name = "dc_user")
public class User extends BaseEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    @NotNull(groups = Update.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dc_users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dc_users_jobs",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "job_id", referencedColumnName = "job_id")})
    private Set<Job> jobs;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    private String nickName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    @JsonIgnore
    private String password;

    @NotNull
    private Boolean enabled;

    private Boolean isAdmin = false;

    @Column(name = "pwd_reset_time")
    private Date pwdResetTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    public UserDto toDto() {
        UserDto userDto = new UserDto();
        userDto.setAvatarName(this.getAvatarName());
        userDto.setAvatarPath(this.getAvatarPath());
        userDto.setEmail(this.getEmail());
        userDto.setEnabled(this.getEnabled());
        userDto.setGender(this.getGender());
        userDto.setId(this.getId());
        userDto.setIsAdmin(this.getIsAdmin());
        userDto.setNickName(this.getNickName());
        userDto.setPassword(this.getPassword());
        userDto.setPhone(this.getPhone());
        userDto.setPwdResetTime(this.getPwdResetTime());
        userDto.setUsername(this.getUsername());
        userDto.setCreateBy(this.getCreateBy());
        userDto.setCreateTime(this.getCreateTime());
        userDto.setUpdateBy(this.getUpdateBy());
        userDto.setUpdateTime(this.getUpdateTime());
        if (this.getDept() != null) {
            DeptSmallDto dept = new DeptSmallDto();
            dept.setId(this.getDept().getId());
            dept.setName(this.getDept().getName());
            userDto.setDept(dept);
            userDto.setDeptId(this.getDept().getId());
        }
        if (this.getRoles() != null) {
            userDto.setRoles(this.roles.stream().map(e -> new RoleSmallDto(e.getId(), e.getName(), e.getLevel(), e.getDataScope())).collect(Collectors.toSet()));
        }
        return userDto;
    }
}