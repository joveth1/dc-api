package com.joveth.dc.modules.shop.service;

import com.joveth.dc.modules.shop.dto.ImageDatas;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author QiaoWei
 * @date 2021-06-07 9:35
 */
public interface ImageFileService {
    ImageDatas uploadImages(MultipartFile file);

    File getImageFile(String filename);
}
