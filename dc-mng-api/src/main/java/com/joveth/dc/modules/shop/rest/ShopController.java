package com.joveth.dc.modules.shop.rest;

import com.joveth.dc.annotation.Log;
import com.joveth.dc.exception.BadRequestException;
import com.joveth.dc.modules.shop.domain.Shop;
import com.joveth.dc.modules.shop.query.ShopQueryCriteria;
import com.joveth.dc.modules.shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Joveth
 * @date 2022-02-23 16:59
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {
    private final ShopService service;

    @GetMapping
    @PreAuthorize("@el.check('shop:list')")
    public ResponseEntity<Object> query(ShopQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(service.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @RequestMapping("/queryall")
    @PreAuthorize("@el.check('shop:list')")
    public ResponseEntity<Object> queryall() {
        return new ResponseEntity<>(service.queryAll(), HttpStatus.OK);
    }

    @Log("新增规格")
    @PostMapping
    @PreAuthorize("@el.check('shop:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Shop resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("新增错误");
        }
        service.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改规格")
    @PutMapping
    @PreAuthorize("@el.check('shop:edit')")
    public ResponseEntity<Object> update(@Validated(Shop.Update.class) @RequestBody Shop resources) {
        service.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除规格")
    @DeleteMapping
    @PreAuthorize("@el.check('shop:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
