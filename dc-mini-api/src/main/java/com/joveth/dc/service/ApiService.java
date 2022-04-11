package com.joveth.dc.service;

import com.joveth.dc.domain.dto.DictDetailDto;

import java.util.List;

/**
 * @author Joveth
 * @date 2022-03-18 16:20
 */
public interface ApiService {
    /**
     * 根据字典名称获取字典详情
     * @param name 字典名称
     * @return /
     */
    List<DictDetailDto> getDictByName(String name);
}
