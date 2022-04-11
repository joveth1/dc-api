package com.joveth.dc.service.impl;

import com.joveth.dc.domain.MobileUser;
import com.joveth.dc.repository.MobileUserRepository;
import com.joveth.dc.service.MobileUserService;
import com.joveth.dc.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-03-24 13:32
 */
@Service
@RequiredArgsConstructor
public class MobileUserServiceImpl implements MobileUserService {
    private final MobileUserRepository mobileUserRepository;

    @Override
    public MobileUser findByOpenid(String openid) {
        return mobileUserRepository.findByOpenid(openid);
    }

    @Override
    public void create(MobileUser resources) {
        mobileUserRepository.save(resources);
    }

    @Override
    public void update(MobileUser resources) {
        MobileUser bean = mobileUserRepository.findById(resources.getId()).orElseGet(MobileUser::new);
        ValidationUtil.isNull(bean.getId(), "MobileUser", "id", resources.getId());
        bean.setOpenid(resources.getOpenid());
        bean.setCity(resources.getCity());
        bean.setGender(resources.getGender());
        bean.setNickName(resources.getNickName());
        bean.setProvince(resources.getProvince());
        bean.setSessionKey(resources.getSessionKey());
        bean.setStatus(resources.getStatus());
        mobileUserRepository.save(bean);
    }

    @Override
    public void delete(Set<Long> ids) {
        mobileUserRepository.deleteAllById(ids);
    }
}
