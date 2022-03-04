package com.joveth.dc.modules.shop.service.impl;

import com.joveth.dc.exception.EntityExistException;
import com.joveth.dc.modules.shop.domain.Spec;
import com.joveth.dc.modules.shop.domain.Type;
import com.joveth.dc.modules.shop.query.SpecQueryCriteria;
import com.joveth.dc.modules.shop.query.TypeQueryCriteria;
import com.joveth.dc.modules.shop.repository.SpecRepository;
import com.joveth.dc.modules.shop.repository.TypeRepository;
import com.joveth.dc.modules.shop.service.SpecService;
import com.joveth.dc.modules.shop.service.TypeService;
import com.joveth.dc.modules.system.domain.User;
import com.joveth.dc.modules.utils.WebConstants;
import com.joveth.dc.utils.PageUtil;
import com.joveth.dc.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Joveth
 * @date 2022-02-23 16:46
 */
@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository repository;

    @Override
    public void create(Type resources) {
        if (repository.findByName(resources.getName()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        repository.save(resources);
    }

    @Override
    public void update(Type resources) {
        if (repository.findByNameAndIdNot(resources.getName(), resources.getId()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        repository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public Type findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Object queryAll(TypeQueryCriteria criteria, Pageable pageable) {
        Page<Type> page = repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page);
    }

    @Override
    public Object queryAll() {
        SpecQueryCriteria criteria = new SpecQueryCriteria();
        criteria.setStatus(WebConstants.NORMAL);
        return repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), Sort.by("sortNum"));
    }
}
