package com.joveth.dc.shop.repository;

import com.joveth.dc.shop.domain.Spec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface SpecRepository extends JpaRepository<Spec, Long>, JpaSpecificationExecutor<Spec> {
    Spec findByName(String name);

    Spec findByNameAndIdNot(String name, Long id);
}
