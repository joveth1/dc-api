package com.joveth.dc.shop.repository;

import com.joveth.dc.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface ShopRepository extends JpaRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {
    Shop findByName(String name);

    Shop findByNameAndIdNot(String name, Long id);
}
