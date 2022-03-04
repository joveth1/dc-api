package com.joveth.dc.modules.shop.service.impl;

import com.joveth.dc.exception.EntityExistException;
import com.joveth.dc.modules.shop.domain.Food;
import com.joveth.dc.modules.shop.query.FoodQueryCriteria;
import com.joveth.dc.modules.shop.repository.FoodRepository;
import com.joveth.dc.modules.shop.repository.TypeRepository;
import com.joveth.dc.modules.shop.service.FoodService;
import com.joveth.dc.modules.system.domain.User;
import com.joveth.dc.utils.PageUtil;
import com.joveth.dc.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-02-23 16:46
 */
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final TypeRepository typeRepository;

    @Override
    public void create(Food resources) {
        if (foodRepository.findByName(resources.getName()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        if (resources.getTypeId() != null) {
            typeRepository.findById(resources.getTypeId()).ifPresent(it -> {
                resources.setTypeName(it.getName());
            });
        }
        foodRepository.save(resources);
    }

    @Override
    public void update(Food resources) {
        if (foodRepository.findByNameAndIdNot(resources.getName(), resources.getId()) != null) {
            throw new EntityExistException(User.class, "name", resources.getName());
        }
        if (resources.getTypeId() != null) {
            typeRepository.findById(resources.getTypeId()).ifPresent(it -> {
                resources.setTypeName(it.getName());
            });
        }
        foodRepository.save(resources);
    }

    @Override
    public void delete(Set<Long> ids) {
        foodRepository.deleteAllById(ids);
    }

    @Override
    public Food findByName(String name) {
        return foodRepository.findByName(name);
    }

    @Override
    public Object queryAll(FoodQueryCriteria criteria, Pageable pageable) {
        Page<Food> page = foodRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(e -> e.toDto()));
    }

    @Override
    public Food findById(Long id) {
        return foodRepository.findById(id).orElseGet(Food::new);
    }
}
