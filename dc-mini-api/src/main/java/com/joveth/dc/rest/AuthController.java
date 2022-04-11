package com.joveth.dc.rest;

import com.google.gson.Gson;
import com.joveth.dc.bean.CodeToSession;
import com.joveth.dc.bean.UserInfo;
import com.joveth.dc.domain.MobileUser;
import com.joveth.dc.service.ApiService;
import com.joveth.dc.service.MobileUserService;
import com.joveth.dc.utils.ApiConstant;
import com.joveth.dc.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author Joveth
 * @date 2022-03-17 16:55
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final ApiService apiService;
    private final RestTemplate restTemplate;
    private static String appid, secret;
    private final MobileUserService mobileUserService;

    @RequestMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody UserInfo userInfo) {
        if (!StringUtils.hasLength(userInfo.getCode())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        apiService.getDictByName(ApiConstant.DICT_WECHAT_MINI).forEach(dict -> {
            if (Objects.equals("appid", dict.getLabel())) {
                appid = dict.getValue();
            }
            if (Objects.equals("secret", dict.getLabel())) {
                secret = dict.getValue();
            }
        });
        if (!StringUtils.hasLength(appid) || !StringUtils.hasLength(secret)) {
            log.error("系统未配置appid={},sercet={}", appid, secret);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ResponseEntity<String> ret = restTemplate.getForEntity(
                String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appid, secret, userInfo.getCode()),
                String.class);
        if (ret.getStatusCode().is2xxSuccessful()) {
            Gson gson = new Gson();
            String body = ret.getBody();
            log.info("小程序认证:{}", body);
            CodeToSession codeToSession = gson.fromJson(body, CodeToSession.class);
            if (!StringUtils.hasLength(codeToSession.getOpenid())) {
                return new ResponseEntity<>(codeToSession.getErrmsg(), HttpStatus.BAD_REQUEST);
            }
            MobileUser mobileUser = mobileUserService.findByOpenid(codeToSession.getOpenid());
            if (mobileUser == null) {
                mobileUser = new MobileUser();
                mobileUser.setStatus("normal");
                mobileUser.setSessionKey(codeToSession.getSession_key());
                mobileUser.setOpenid(codeToSession.getOpenid());
                mobileUser.setNickName(userInfo.getNickName());
                mobileUser.setProvince(userInfo.getProvince());
                mobileUser.setGender(userInfo.getGender());
                mobileUser.setCity(userInfo.getCity());
                mobileUserService.create(mobileUser);
            }
            String enc = "";
            try {
                enc = EncryptUtils.desEncrypt(codeToSession.getOpenid());
            } catch (Exception e) {
            }
            return new ResponseEntity<>(enc, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("请求微信服务失败");
    }
}
