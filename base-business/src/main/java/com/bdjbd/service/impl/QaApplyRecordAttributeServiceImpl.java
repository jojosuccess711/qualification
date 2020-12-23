/**
 * @filename:QaApplyRecordAttributeServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Order;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.QaApplyRecord;
import com.bdjbd.dao.entity.QaClause;
import com.bdjbd.enums.ClauseTypeEnum;
import com.bdjbd.service.QaApplyRecordService;
import com.bdjbd.service.QaCategoryService;
import com.bdjbd.service.QaClauseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.entity.QaApplyRecordAttribute;
import com.bdjbd.dao.mapper.QaApplyRecordAttributeMapper;
import com.bdjbd.service.QaApplyRecordAttributeService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * @Description: 申请记录属性 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Service
public class QaApplyRecordAttributeServiceImpl extends BaseServiceImpl<QaApplyRecordAttribute, String> implements QaApplyRecordAttributeService {

    @Autowired
    private QaApplyRecordAttributeMapper mapper;

    @Autowired
    private QaClauseService qaClauseService;

    @Autowired
    QaCategoryService qaCategoryService;

    @Autowired
    private QaApplyRecordService qaApplyRecordService;

    @Autowired
    public void setBaseDao(QaApplyRecordAttributeMapper mapper) {
        super.setBaseDao(mapper);
    }

    /**
     * 新增选择结果
     * @return
     */
    @Override
    @Transactional
    public boolean create(Map<String, String> list, QaApplyRecord qaApplyRecord, Integer operateType) {
        if (list.size() < 1) {
            return true;  //如没有任何条件时（如中职），默认初审通过
        }
        List<QaApplyRecordAttribute> qaApplyRecordAttributeList = new ArrayList<>();
        Iterator<String> iter = list.keySet().iterator();
        boolean isUpdateApplyNum = true;
        while (iter.hasNext()) {
            String key = iter.next();
            String checkeResult = "", annexUrlResult = null;
            if (list.get(key).contains(",")) {
                String[] value = list.get(key).split(",");
                checkeResult = value[0];
                annexUrlResult = value[1];
            }
            else {
                checkeResult = list.get(key);
            }
            QaApplyRecordAttribute qaApplyRecordAttribute = new QaApplyRecordAttribute();
            qaApplyRecordAttribute.setId(UUIDUtils.generateUuid());
            qaApplyRecordAttribute.setApplyId(qaApplyRecord.getId());
            qaApplyRecordAttribute.setCaluseId(key);
            qaApplyRecordAttribute.setUserChoice(Integer.parseInt(checkeResult));
            qaApplyRecordAttribute.setAnnexUrl(annexUrlResult);
            /*判断用户选择结果是否符合职位申请规则*/
            //第一步：查询规则
            List<QaClause> qaClauseList = qaClauseService.findClauseList(key);
            //第二步：判断是否匹配规则
            int result = this.isMatching(checkeResult, annexUrlResult, qaClauseList, list);
            if (result == 0) {
                isUpdateApplyNum = false;
            }
            qaApplyRecordAttribute.setPast(new Long(result));
            qaApplyRecordAttributeList.add(qaApplyRecordAttribute);
        }
        /*批量插入ApplyRecordAttribute*/
        this.batchInsert(qaApplyRecordAttributeList);
        return isUpdateApplyNum;
    }

    /**
     * 批量新增选择结果
     * @return
     */
    @Override
    public int batchInsert(List<QaApplyRecordAttribute> qaApplyRecordAttributeList) {
        return mapper.batchInsert(qaApplyRecordAttributeList);
    }

    /**
     * 判断用户选择结果是否符合规则
     * @return
     */
    @Override
    public int isMatching(String checkeResult, String annexUrlResult, List<QaClause> qaClauseList, Map<String, String> list) {
        //单个条件要求选择 是 或 否 时 ，与规则相同返回true ，不同返回false
        if (qaClauseList.size() == 1) {
            if (qaClauseList.get(0).getAnnex() == 1) {
                if (StringUtils.isNotEmpty(annexUrlResult) && checkeResult.equals(qaClauseList.get(0).getType())) {
                    return 1;
                }
            }
            else {
                if (checkeResult.equals(qaClauseList.get(0).getType().toString())) {
                    return 1;
                }
            }
        }
        //多个条件选择其一时，其中一个为 是 返回 true  否则返回 false
        else if (qaClauseList.size() > 1) {
            for (QaClause clause : qaClauseList) {
                Iterator<String> iter = list.keySet().iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    if (clause.getId().equals(key)) {
                        String value = "", annexUrl = null;
                        if (list.get(key).contains(",")) {
                            String[] content = list.get(key).split(",");
                            value = content[0];
                            annexUrl = content[1];
                        }
                        else {
                            value = list.get(key);
                        }
                        if (clause.getType().equals(ClauseTypeEnum.ONE.getKey())) {
                            if (value.equals(ClauseTypeEnum.YES.getKey().toString())) {
                                return 1;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    /**
     * 删除某条申请记录的全部条件
     */
    @Override
    public void deleteByApplyId(String id) {
        mapper.deleteByApplyId(id);
    }

    @Override
    public List<QaApplyRecordAttribute> findAttributeByApplyId(String applyId) {
        return mapper.findAttributeByApplyId(applyId);
    }

    /**
     * 根据某条申请记录中所有通过项或所有未通过项
     * @return
     */
    @Override
    public List<QaApplyRecordAttribute> findPassInfoByApplyId(String id, int pastType) {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("caluseId"));
        List<QaApplyRecordAttribute> list = this.findList(ListUtil.addToList(Filter.eq("applyId", id), Filter.eq("past", pastType)), orders);
        return list;
    }

    @Override
    public List<QaApplyRecordAttribute> findByUser(String userId) {
        return mapper.findByUser(userId);
    }
}