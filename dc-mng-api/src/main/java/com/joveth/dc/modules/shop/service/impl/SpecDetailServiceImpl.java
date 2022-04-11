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
package com.joveth.dc.modules.shop.service.impl;

import com.joveth.dc.exception.EntityExistException;
import com.joveth.dc.shop.domain.SpecDetail;
import com.joveth.dc.shop.query.SpecDetailQueryCriteria;
import com.joveth.dc.shop.repository.SpecDetailRepository;
import com.joveth.dc.modules.shop.service.SpecDetailService;
import com.joveth.dc.modules.system.domain.User;
import com.joveth.dc.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Zheng Jie
 * @date 2019-04-10
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "dict")
public class SpecDetailServiceImpl implements SpecDetailService {

    private final SpecDetailRepository specDetailRepository;

    @Override
    public Map<String, Object> queryAll(SpecDetailQueryCriteria criteria, Pageable pageable) {
        Page<SpecDetail> page = specDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(e -> e.toDto()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SpecDetail resources) {
        if (specDetailRepository.findByName(resources.getName()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        specDetailRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SpecDetail resources) {
        if (specDetailRepository.findByNameAndIdNot(resources.getName(), resources.getId()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        SpecDetail detail = specDetailRepository.findById(resources.getId()).orElseGet(SpecDetail::new);
        ValidationUtil.isNull(detail.getId(), "SpecDetail", "id", resources.getId());
        detail.setAddPrice(resources.getAddPrice());
        detail.setName(resources.getName());
        detail.setSortNum(resources.getSortNum());
        detail.setStatus(resources.getStatus());
        specDetailRepository.save(detail);
    }

    @Override
    public List<SpecDetail> getByName(String name) {
        return specDetailRepository.findBySpecName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        specDetailRepository.deleteById(id);
    }
}