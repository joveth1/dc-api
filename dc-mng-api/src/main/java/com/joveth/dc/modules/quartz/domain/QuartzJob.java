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
package com.joveth.dc.modules.quartz.domain;

import lombok.Getter;
import lombok.Setter;
import com.joveth.dc.base.BaseEntity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Getter
@Setter
@Entity
@Table(name = "dc_quartz_job")
public class QuartzJob extends BaseEntity implements Serializable {

    public static final String JOB_KEY = "JOB_KEY";

    @Id
    @Column(name = "job_id")
    @NotNull(groups = {Update.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private String uuid;

    private String jobName;

    @NotBlank
    private String beanName;

    @NotBlank
    private String methodName;

    private String params;

    @NotBlank
    private String cronExpression;

    private Boolean isPause = false;

    private String personInCharge;

    private String email;

    private String subTask;

    private Boolean pauseAfterFailure;

    @NotBlank
    private String description;
}