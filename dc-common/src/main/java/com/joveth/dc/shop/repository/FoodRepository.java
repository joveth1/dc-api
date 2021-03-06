package com.joveth.dc.shop.repository;

import com.joveth.dc.shop.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Joveth
 * @date 2022-02-23 16:26
 */
public interface FoodRepository extends JpaRepository<Food, Long>, JpaSpecificationExecutor<Food> {
    Food findByName(String name);

    Food findByNameAndIdNot(String name, Long id);
}
