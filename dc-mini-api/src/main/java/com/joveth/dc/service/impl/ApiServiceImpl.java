package com.joveth.dc.service.impl;

import com.joveth.dc.domain.dto.DictDetailDto;
import com.joveth.dc.domain.dto.DictSmallDto;
import com.joveth.dc.repository.DictDetailRepository;
import com.joveth.dc.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Joveth
 * @date 2022-03-18 16:22
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
    private final DictDetailRepository dictDetailRepository;

    @Override
    @Cacheable(cacheNames = {"dict"}, key = "'name:' + #p0")
    public List<DictDetailDto> getDictByName(String name) {
        return dictDetailRepository.findByDictName(name).stream().map(e -> new DictDetailDto(
                e.getId(),
                new DictSmallDto(e.getDict().getId()),
                e.getLabel(),
                e.getValue(),
                e.getDictSort()
        )).collect(Collectors.toList());
    }
}
