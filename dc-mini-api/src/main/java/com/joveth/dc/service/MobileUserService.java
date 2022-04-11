package com.joveth.dc.service;

import com.joveth.dc.domain.MobileUser;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-03-24 13:31
 */
public interface MobileUserService {
    MobileUser findByOpenid(String openid);

    void create(MobileUser resources);

    void update(MobileUser resources);

    void delete(Set<Long> ids);
}
