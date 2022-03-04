package com.joveth.dc.modules.shop.service.impl;

import com.joveth.dc.exception.EntityExistException;
import com.joveth.dc.modules.shop.domain.Food;
import com.joveth.dc.modules.shop.domain.Spec;
import com.joveth.dc.modules.shop.query.FoodQueryCriteria;
import com.joveth.dc.modules.shop.query.SpecQueryCriteria;
import com.joveth.dc.modules.shop.repository.FoodRepository;
import com.joveth.dc.modules.shop.repository.SpecRepository;
import com.joveth.dc.modules.shop.service.FoodService;
import com.joveth.dc.modules.shop.service.SpecService;
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
public class SpecServiceImpl implements SpecService {
    private final SpecRepository specRepository;

    @Override
    public void create(Spec resources) {
        if (specRepository.findByName(resources.getName()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        specRepository.save(resources);
    }

    @Override
    public void update(Spec resources) {
        if (specRepository.findByNameAndIdNot(resources.getName(), resources.getId()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        specRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        specRepository.deleteAllById(ids);
    }

    @Override
    public Spec findByName(String name) {
        return specRepository.findByName(name);
    }

    @Override
    public Object queryAll(SpecQueryCriteria criteria, Pageable pageable) {
        Page<Spec> page = specRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(e -> e.toDto()));
    }

    @Override
    public Object queryAll() {
        SpecQueryCriteria criteria = new SpecQueryCriteria();
        criteria.setStatus(WebConstants.NORMAL);
        return specRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), Sort.by("sortNum"))
                .stream().map(e->e.toDto()).collect(Collectors.toList());
    }
}
