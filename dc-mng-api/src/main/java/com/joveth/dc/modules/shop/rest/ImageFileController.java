package com.joveth.dc.modules.shop.rest;

import com.joveth.dc.annotation.AnonymousAccess;
import com.joveth.dc.annotation.rest.AnonymousGetMapping;
import com.joveth.dc.service.ImageFileService;
import com.joveth.dc.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

/**
 * 图片服务
 *
 * @author Jov
 * @date 2021-06-07 14:23
 */
@RestController
@RequestMapping("/file")
@Slf4j
@RequiredArgsConstructor
public class ImageFileController {
    private final ImageFileService imageFileService;

    @AnonymousAccess
    @AnonymousGetMapping(value = "/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<Object> query(@PathVariable String filename) {
        if (filename == null || !FileUtil.isSafeFile(filename)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        File file = imageFileService.getImageFile(filename);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            inputStream.close();
            return new ResponseEntity<>(bytes, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error:{}", e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
