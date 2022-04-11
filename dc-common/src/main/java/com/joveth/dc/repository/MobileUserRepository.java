package com.joveth.dc.repository;

import com.joveth.dc.domain.MobileUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Joveth
 * @date 2022-03-24 13:30
 */
public interface MobileUserRepository extends JpaRepository<MobileUser, Long>, JpaSpecificationExecutor<MobileUser> {
    MobileUser findByOpenid(String openid);
}
