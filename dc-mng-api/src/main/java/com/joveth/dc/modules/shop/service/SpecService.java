package com.joveth.dc.modules.shop.service;

import com.joveth.dc.modules.shop.domain.Spec;
import com.joveth.dc.modules.shop.query.SpecQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface SpecService {

    /**
     * 新增
     *
     * @param resources /
     */
    void create(Spec resources);

    /**
     * 编辑
     *
     * @param resources /
     * @throws Exception /
     */
    void update(Spec resources) ;

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
    Spec findByName(String name);

    Object queryAll(SpecQueryCriteria criteria, Pageable pageable);

    Object queryAll();
}
