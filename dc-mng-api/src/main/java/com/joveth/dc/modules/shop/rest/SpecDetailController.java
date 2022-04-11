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
package com.joveth.dc.modules.shop.rest;

import com.joveth.dc.annotation.Log;
import com.joveth.dc.exception.BadRequestException;
import com.joveth.dc.shop.domain.SpecDetail;
import com.joveth.dc.shop.dto.SpecDetailDto;
import com.joveth.dc.shop.query.SpecDetailQueryCriteria;
import com.joveth.dc.modules.shop.service.SpecDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Joveth
 * @date 2022-02-23 16:59
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/specDetail")
public class SpecDetailController {

    private final SpecDetailService specDetailService;
    private static final String ENTITY_NAME = "specDetail";

    @GetMapping
    public ResponseEntity<Object> queryDetail(SpecDetailQueryCriteria criteria,
                                                  @PageableDefault(sort = {"sortNum"}, direction = Sort.Direction.ASC) Pageable pageable){
        return new ResponseEntity<>(specDetailService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping(value = "/map")
    public ResponseEntity<Object> getDetailMaps(@RequestParam String dictName){
        String[] names = dictName.split("[,，]");
        Map<String, List<SpecDetailDto>> dictMap = new HashMap<>(16);
        for (String name : names) {
            dictMap.put(name, specDetailService.getByName(name).stream().map(e->e.toDto()).collect(Collectors.toList()));
        }
        return new ResponseEntity<>(dictMap, HttpStatus.OK);
    }

    @Log("新增详情")
    @PostMapping
    @PreAuthorize("@el.check('spec:add')")
    public ResponseEntity<Object> createDetail(@Validated @RequestBody SpecDetail resources){
        if (resources.getId() != null) {
            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
        }
        specDetailService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改详情")
    @PutMapping
    @PreAuthorize("@el.check('spec:edit')")
    public ResponseEntity<Object> updateDetail(@Validated(SpecDetail.Update.class) @RequestBody SpecDetail resources){
        specDetailService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除详情")
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("@el.check('spec:del')")
    public ResponseEntity<Object> deleteDetail(@PathVariable Long id){
        specDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}