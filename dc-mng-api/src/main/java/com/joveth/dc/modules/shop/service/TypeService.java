package com.joveth.dc.modules.shop.service;

import com.joveth.dc.modules.shop.domain.Type;
import com.joveth.dc.modules.shop.query.SpecQueryCriteria;
import com.joveth.dc.modules.shop.query.TypeQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-03-03 16:21
 */
public interface TypeService {
    /**
     * 新增
     *
     * @param resources /
     */
    void create(Type resources);

    /**
     * 编辑
     *
     * @param resources /
     * @throws Exception /
     */
    void update(Type resources) ;

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
    Type findByName(String name);

    Object queryAll(TypeQueryCriteria criteria, Pageable pageable);

    Object queryAll();
}
