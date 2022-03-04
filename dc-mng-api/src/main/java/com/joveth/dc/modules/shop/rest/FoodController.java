package com.joveth.dc.modules.shop.rest;

import com.joveth.dc.annotation.Log;
import com.joveth.dc.exception.BadRequestException;
import com.joveth.dc.modules.shop.domain.Food;
import com.joveth.dc.modules.shop.dto.ImageDatas;
import com.joveth.dc.modules.shop.query.FoodQueryCriteria;
import com.joveth.dc.modules.shop.service.FoodService;
import com.joveth.dc.modules.shop.service.ImageFileService;
import com.joveth.dc.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author Joveth
 * @date 2022-02-23 16:59
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/food")
public class FoodController {
    private final FoodService foodService;
    private final ImageFileService imageFileService;

    @GetMapping
    @PreAuthorize("@el.check('food:list')")
    public ResponseEntity<Object> query(FoodQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(foodService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增菜谱")
    @PostMapping
    @PreAuthorize("@el.check('food:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Food resources) {
        if (resources.getId() != null) {
            throw new BadRequestException("新增错误");
        }
        foodService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改菜谱")
    @PutMapping
    @PreAuthorize("@el.check('food:edit')")
    public ResponseEntity<Object> update(@Validated(Food.Update.class) @RequestBody Food resources) {
        foodService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除菜谱")
    @DeleteMapping
    @PreAuthorize("@el.check('food:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        foodService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/upload")
    public ResponseEntity<Object> query(MultipartFile file, @RequestParam Long id) {
        Food food = foodService.findById(id);
        if (food.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (file != null) {
            ImageDatas images = imageFileService.uploadImages(file);
            if (images.isSuccess()) {
                food.setPicid(images.getPicid());
                food.setPiclastupdate(DateUtil.getNow());
                foodService.update(food);
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("图片上传识失败:" + images.getRetmsg());
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
