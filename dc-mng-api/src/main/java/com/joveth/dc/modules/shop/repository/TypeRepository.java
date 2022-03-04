package com.joveth.dc.modules.shop.repository;

import com.joveth.dc.modules.shop.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface TypeRepository extends JpaRepository<Type, Long>, JpaSpecificationExecutor<Type> {
    Type findByName(String name);
    Type findByNameAndIdNot(String name, Long id);
}
