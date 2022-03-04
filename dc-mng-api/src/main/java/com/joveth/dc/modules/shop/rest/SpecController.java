package com.joveth.dc.modules.shop.rest;

import com.joveth.dc.annotation.Log;
import com.joveth.dc.exception.BadRequestException;
import com.joveth.dc.modules.shop.domain.Food;
import com.joveth.dc.modules.shop.domain.Spec;
import com.joveth.dc.modules.shop.query.FoodQueryCriteria;
import com.joveth.dc.modules.shop.query.SpecQueryCriteria;
import com.joveth.dc.modules.shop.service.FoodService;
import com.joveth.dc.modules.shop.service.SpecService;
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
@RequestMapping("/api/spec")
public class SpecController {
    private final SpecService service;

    @GetMapping
    @PreAuthorize("@el.check('spec:list')")
    public ResponseEntity<Object> query(SpecQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(service.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @RequestMapping("/queryall")
    @PreAuthorize("@el.check('spec:list')")
    public ResponseEntity<Object> queryall() {
        return new ResponseEntity<>(service.queryAll(), HttpStatus.OK);
    }

    @Log("新增规格")
    @PostMapping
    @PreAuthorize("@el.check('spec:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Spec resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("新增错误");
        }
        service.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改规格")
    @PutMapping
    @PreAuthorize("@el.check('spec:edit')")
    public ResponseEntity<Object> update(@Validated(Spec.Update.class) @RequestBody Spec resources) {
        service.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除规格")
    @DeleteMapping
    @PreAuthorize("@el.check('spec:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
