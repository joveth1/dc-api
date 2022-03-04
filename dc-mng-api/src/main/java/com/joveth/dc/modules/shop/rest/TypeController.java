package com.joveth.dc.modules.shop.rest;

import com.joveth.dc.annotation.Log;
import com.joveth.dc.exception.BadRequestException;
import com.joveth.dc.modules.shop.domain.Spec;
import com.joveth.dc.modules.shop.domain.Type;
import com.joveth.dc.modules.shop.query.SpecQueryCriteria;
import com.joveth.dc.modules.shop.query.TypeQueryCriteria;
import com.joveth.dc.modules.shop.service.SpecService;
import com.joveth.dc.modules.shop.service.TypeService;
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
@RequestMapping("/api/type")
public class TypeController {
    private final TypeService service;

    @GetMapping
    @PreAuthorize("@el.check('type:list')")
    public ResponseEntity<Object> query(TypeQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(service.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @RequestMapping("/queryall")
    @PreAuthorize("@el.check('type:list')")
    public ResponseEntity<Object> queryall() {
        return new ResponseEntity<>(service.queryAll(), HttpStatus.OK);
    }

    @Log("新增类别")
    @PostMapping
    @PreAuthorize("@el.check('type:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Type resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("新增错误");
        }
        service.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改类别")
    @PutMapping
    @PreAuthorize("@el.check('type:edit')")
    public ResponseEntity<Object> update(@Validated(Type.Update.class) @RequestBody Type resources) {
        service.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除类别")
    @DeleteMapping
    @PreAuthorize("@el.check('type:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        service.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
