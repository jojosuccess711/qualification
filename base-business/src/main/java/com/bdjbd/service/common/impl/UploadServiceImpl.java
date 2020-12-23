package com.bdjbd.service.common.impl;

import com.bdjbd.Message;
import com.bdjbd.common.util.base64.Base64Util;
import com.bdjbd.dao.entity.SysConfig;
import com.bdjbd.service.sys.SysConfigService;
import com.bdjbd.service.common.UploadService;
import com.obs.services.ObsClient;
import com.obs.services.model.ListObjectsRequest;
import com.obs.services.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
@Slf4j
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    SysConfigService sysConfigService;
    /** 华为obs */
    String HUA_WEI_OBS = "HUA_WEI_OBS";
    /** 访问key */
    String ACCESS_KEY = "accessKey";
    /** 安全key */
    String SECURITY_KEY = "securityKey";
    /** SDK访问端地址 */
    String END_POINT = "endPoint";
    /** obs-2224 */
    String OBS_2224 = "obs-2224";
    @Override
    public Message<String> uploadToObs(String bucketName, String objectKey, String file) {
        sysConfigService.clearCache();
        SysConfig sysConfig = sysConfigService.findHasAttributes(HUA_WEI_OBS);
        if(sysConfig == null || !sysConfig.getStatus()){
            return Message.error("未配置上传参数，请联系开发人员配置！");
        }
        String accessKey = sysConfig.getAttributeValue(ACCESS_KEY);
        String securityKey = sysConfig.getAttributeValue(SECURITY_KEY);
        String endPoint = sysConfig.getAttributeValue(END_POINT);
        // 创建ObsClient实例
        ObsClient obsClient = new ObsClient(accessKey, securityKey, endPoint);
        ListObjectsRequest request = new ListObjectsRequest(bucketName);
        request.setMaxKeys(10);

        try {
//            MultipartFile multipartFile = Base64Util.base64ToMultipart(file);
//            InputStream inputStream = multipartFile.getInputStream();
            InputStream inputStream =   Base64Util.base64ToInputStream(file);
            // 创建ObsClient实例
            PutObjectResult putObjectResult = obsClient.putObject(bucketName, objectKey, inputStream);
            int statusCode = putObjectResult.getStatusCode();
            try {
                obsClient.close();
            }catch (Exception e){
            }
            if (statusCode != 200) {
                log.error("上传失败");
                return Message.error("上传失败, 请重试！");
            }
            return Message.success("上传成功", putObjectResult.getObjectUrl());
        }catch (Exception e){
            log.error("上传异常", e);
        }
        return Message.error("上传异常, 请重试！");
    }
}
