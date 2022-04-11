package com.joveth.dc.service.impl;

import com.joveth.dc.shop.dto.ImageDatas;
import com.joveth.dc.service.ImageFileService;
import com.joveth.dc.utils.ApiConstant;
import com.joveth.dc.utils.DateUtil;
import com.joveth.dc.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 图片文件处理服务，精简版使用
 *
 * @author Jov
 * @date 2021-06-07 9:36
 */
@Service
@Slf4j
public class ImageFileServiceImpl implements ImageFileService {
    @Override
    public ImageDatas uploadImages(MultipartFile multipartFile) {
        ImageDatas imageDatas = new ImageDatas();
        if (multipartFile == null || !FileUtil.isSafeFile(multipartFile)) {
            imageDatas.setSuccess(false);
            imageDatas.setRetmsg("无文件或文件格式不正确");
            return imageDatas;
        }
        String name = multipartFile.getOriginalFilename();
        String fileName = DateUtil.getNow().concat("_").concat(name);
        String path = FileUtil.getPath(ApiConstant.IMAGE_DIR);
        File file = new File(path, fileName);
        try {
            multipartFile.transferTo(file);
            imageDatas.setPicid(fileName);
            imageDatas.setPicName(fileName);
            imageDatas.setSuccess(true);
        } catch (Exception e) {
            log.error("图片文件转换失败:{}-{}", name, e.getMessage());
            imageDatas.setSuccess(false);
            imageDatas.setRetmsg("图片文件转换失败");
            return imageDatas;
        }
        return imageDatas;
    }

    @Override
    public File getImageFile(String filename) {
        String path = FileUtil.getPath(ApiConstant.IMAGE_DIR);
        File file = new File(path, filename);
        if (file.exists()) {
            return file;
        }
        return null;
    }
}
