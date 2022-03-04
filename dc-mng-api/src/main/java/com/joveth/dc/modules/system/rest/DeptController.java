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
package com.joveth.dc.modules.system.rest;

import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import com.joveth.dc.annotation.Log;
import com.joveth.dc.exception.BadRequestException;
import com.joveth.dc.modules.system.domain.Dept;
import com.joveth.dc.modules.system.service.DeptService;
import com.joveth.dc.modules.system.service.dto.DeptDto;
import com.joveth.dc.modules.system.service.dto.DeptQueryCriteria;
import com.joveth.dc.utils.PageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
* @author Zheng Jie
* @date 2019-03-25
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dept")
public class DeptController {

    private final DeptService deptService;
    private static final String ENTITY_NAME = "dept";

    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('dept:list')")
    public void exportDept(HttpServletResponse response, DeptQueryCriteria criteria) throws Exception {
        deptService.download(deptService.queryAll(criteria, false), response);
    }

    @GetMapping
    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> queryDept(DeptQueryCriteria criteria) throws Exception {
        List<DeptDto> deptDtos = deptService.queryAll(criteria, true);
        return new ResponseEntity<>(PageUtil.toPage(deptDtos, deptDtos.size()),HttpStatus.OK);
    }

    @PostMapping("/superior")
    @PreAuthorize("@el.check('user:list','dept:list')")
    public ResponseEntity<Object> getDeptSuperior(@RequestBody List<Long> ids) {
        Set<DeptDto> deptDtos  = new LinkedHashSet<>();
        for (Long id : ids) {
            DeptDto deptDto = deptService.findById(id);
            List<DeptDto> depts = deptService.getSuperior(deptDto, new ArrayList<>());
            deptDtos.addAll(depts);
        }
        return new ResponseEntity<>(deptService.buildTree(new ArrayList<>(deptDtos)),HttpStatus.OK);
    }

    @Log("新增部门")
    @PostMapping
    @PreAuthorize("@el.check('dept:add')")
    public ResponseEntity<Object> createDept(@Validated @RequestBody Dept resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        deptService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改部门")
    @PutMapping
    @PreAuthorize("@el.check('dept:edit')")
    public ResponseEntity<Object> updateDept(@Validated(Dept.Update.class) @RequestBody Dept resources){
        deptService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除部门")
    @DeleteMapping
    @PreAuthorize("@el.check('dept:del')")
    public ResponseEntity<Object> deleteDept(@RequestBody Set<Long> ids){
        Set<DeptDto> deptDtos = new HashSet<>();
        for (Long id : ids) {
            List<Dept> deptList = deptService.findByPid(id);
            deptDtos.add(deptService.findById(id));
            if(CollectionUtil.isNotEmpty(deptList)){
                deptDtos = deptService.getDeleteDepts(deptList, deptDtos);
            }
        }
        // 验证是否被角色或用户关联
        deptService.verification(deptDtos);
        deptService.delete(deptDtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}