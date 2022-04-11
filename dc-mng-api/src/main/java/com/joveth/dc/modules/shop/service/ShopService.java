package com.joveth.dc.modules.shop.service;

import com.joveth.dc.shop.domain.Shop;
import com.joveth.dc.shop.query.ShopQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface ShopService {
    /**
     * 新增
     *
     * @param resources /
     */
    void create(Shop resources);

    /**
     * 编辑
     *
     * @param resources /
     */
    void update(Shop resources);

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
    Shop findByName(String name);

    Object queryAll(ShopQueryCriteria criteria, Pageable pageable);

    Object queryAll();
}
