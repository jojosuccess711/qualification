package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.service.ExcelImportService;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/excel")
@Api(value = "excel导入 - Controller", description = "excel导入 - Controller", tags = {"excel导入 - Controller"})
public class ExcelController {
    @Autowired
    private ExcelImportService excelImportService;
    @Autowired
    SysAdminService adminService;

    /**
     * 文件信息录入
     */
    @ApiOperation(value = "文件信息录入", notes = "文件信息录入")
    //    @IgnoreUserToken
    //    @IgnoreAuthority
    @PostMapping(value = "/import")
    public Message test(@RequestParam("multipartFile") MultipartFile multipartFile, String modelName,
            @RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            File file = FileUtils.multipartFileToFile(multipartFile);
            return excelImportService.importExcelNew(userId, file, modelName);
        } catch (Exception e) {
            log.error("文件录入异常", e);
            return Message.error("文件录入异常");
        }
    }

    @ApiOperation(value = "文件信息录入", notes = "文件信息录入")
    //    @IgnoreUserToken
    //    @IgnoreAuthority
    @PostMapping(value = "/import2")
    public Message import2(@RequestParam("multipartFile") MultipartFile multipartFile,
            String modelName,
            @RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            File file = FileUtils.multipartFileToFile(multipartFile);
            return excelImportService.importExcel(userId, file, modelName);
        } catch (Exception e) {
            log.error("文件录入异常", e);
            return Message.error("文件录入异常");
        }
    }
}
