package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.QaStandardRecordItem;

import java.io.File;
import java.util.List;

public interface ExcelImportService {
    /**
     *
     * @param userId 上传文件用户id
     * @param excelFile 文件
     * @param modelName
     * @return Message
     * @throws Exception 用户通过excel录入信息
     */
     Message importExcel(String userId,File excelFile,String modelName) throws
             Exception;
    /**
     *
     * @param userId 上传文件用户id
     * @param excelFile 文件
     * @param modelName
     * @return Message
     * @throws Exception 用户通过excel录入信息
     */
     Message importExcelNew(String userId,File excelFile,String modelName) throws
             Exception;

    /**
     * 保存用户录入的数据
     * @return
     */
     Message saveOrEditUseData(List<QaStandardRecordItem> useData);
}
