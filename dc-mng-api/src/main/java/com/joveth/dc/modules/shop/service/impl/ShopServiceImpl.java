package com.joveth.dc.modules.shop.service.impl;

import com.joveth.dc.exception.EntityExistException;
import com.joveth.dc.modules.shop.domain.Shop;
import com.joveth.dc.modules.shop.query.ShopQueryCriteria;
import com.joveth.dc.modules.shop.repository.ShopRepository;
import com.joveth.dc.modules.shop.service.ShopService;
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

/**
 * @author Joveth
 * @date 2022-03-04 10:27
 */
@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopRepository repository;

    @Override
    public void create(Shop resources) {
        if (repository.findByName(resources.getName()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        repository.save(resources);
    }

    @Override
    public void update(Shop resources) {
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
    public Shop findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Object queryAll(ShopQueryCriteria criteria, Pageable pageable) {
        Page<Shop> page = repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page);
    }

    @Override
    public Object queryAll() {
        ShopQueryCriteria criteria = new ShopQueryCriteria();
        criteria.setStatus(WebConstants.NORMAL);
        return repository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), Sort.by("id"));
    }
}
