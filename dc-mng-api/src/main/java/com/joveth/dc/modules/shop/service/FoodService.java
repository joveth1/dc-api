package com.joveth.dc.modules.shop.service;

import com.joveth.dc.modules.shop.domain.Food;
import com.joveth.dc.modules.shop.query.FoodQueryCriteria;
import com.joveth.dc.modules.system.service.dto.UserQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface FoodService {

    /**
     * 新增
     *
     * @param resources /
     */
    void create(Food resources);

    /**
     * 编辑
     *
     * @param resources /
     * @throws Exception /
     */
    void update(Food resources) ;

    /**
     * 删除
     *
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据名称查询
     *
     * @param name /
     * @return /
     */
    Food findByName(String name);

    Object queryAll(FoodQueryCriteria criteria, Pageable pageable);

    Food findById(Long id);
}
