/**
 * @filename:QaStandardRecordItemService 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service;

import com.bdjbd.Message;
import com.bdjbd.web.service.BaseService;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  
 * @Description:  用户标准信息记录项 —— SERVICE
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
public interface QaStandardRecordItemService extends BaseService<QaStandardRecordItem, String> {

    void replaceData(List<QaStandardRecordItem> useData);

    /**
     * 通过recordId获取填写项中的最大id
     * @param recordId
     * @return
     */
    Integer findMaxId(String recordId);

    Message saveStandardRecordItems(List<QaStandardRecordItem>
            qaStandardRecordItems,String userId,String groupId);

    /**
     * 保存记录
     *
     * @param qaStandardRecordItems
     * @param userId
     * @param groupId
     * @return
     */
    Message saveStandardRecordItems2(List<QaStandardRecordItem> qaStandardRecordItems, String userId, String groupId) throws IllegalAccessException, InstantiationException;

    /**
     * 删除记录
     *
     * @return
     */
    Message deleteRecordAndItem(List<QaStandardRecordItem> qaStandardRecordItems, String userId);

    /**
     * 数据录入删除录入项数据
     */
    void deleteByRecordIdAndparamId(String groupId,String parameterId,String
            recordId,Integer order);
     int batchInsert(List<QaStandardRecordItem> items);

    Message uploadStandardRecordFile(MultipartFile[] multipartFiles,String
            userId,String groupId,String parameterId,String orders);

    /**
     * 用户基本信息图片上传
     * @param multipartFile
     * @param userId
     * @return
     */
    Message uploadUserImg(MultipartFile multipartFile, String userId);
    /**
     * 用户提交数据
     * @param userId 用户id
     * @return
     */
    Message commitRecord(String userId);

    void batchDelete(List<QaStandardRecordItem> items);


    List<QaStandardRecordItem> findItemsByRecordId(String recordId);
}