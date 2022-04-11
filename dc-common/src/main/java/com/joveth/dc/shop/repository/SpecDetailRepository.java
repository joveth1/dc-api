package com.joveth.dc.shop.repository;

import com.joveth.dc.shop.domain.SpecDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface SpecDetailRepository extends JpaRepository<SpecDetail, Long>, JpaSpecificationExecutor<SpecDetail> {
    List<SpecDetail> findBySpecName(String name);

    SpecDetail findByName(String name);

    SpecDetail findByNameAndIdNot(String name, Long id);
}
