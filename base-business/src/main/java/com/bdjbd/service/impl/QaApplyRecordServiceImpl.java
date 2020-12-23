/**
 * @filename:QaApplyRecordServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.service.impl;

import com.bdjbd.Message;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.*;
import com.bdjbd.enums.ApplyInfoStatusEnum;
import com.bdjbd.enums.ApproveStatusEnum;
import com.bdjbd.enums.CategoryTypeEnum;
import com.bdjbd.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.bdjbd.dao.mapper.QaApplyRecordMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import com.bdjbd.Filter;
import com.bdjbd.Order;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  
 * @Description:  申请记录 —— SERVICEIMPL
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Service
public class QaApplyRecordServiceImpl extends BaseServiceImpl<QaApplyRecord, String> implements QaApplyRecordService {
    @Autowired
    QaUserInfoService qaUserInfoService;
    @Autowired
    QaApplyRecordAttributeService qaApplyRecordAttributeService;
    @Autowired
    QaCategoryService qaCategoryService;
	@Autowired
    QaApplyRecordMapper mapper;

    @Autowired
    QaApplyLogService qaApplyLogService;

    @Autowired
    QaClauseService qaClauseService;

    @Autowired
    public void setBaseDao(QaApplyRecordMapper mapper){
        super.setBaseDao(mapper);
    }

    @Override
    public List<QaApplyRecord> findList(Integer pageNum, Integer pageSize, String category, String subCategory, String userName, String jobTitle, String approveTime, Integer approveResult, List<Integer> approveResultList) {

        return mapper.findList((pageNum - 1) * pageSize, pageSize, category, subCategory, userName, jobTitle, approveTime, approveResult, approveResultList);
    }

    @Override
    public Integer countList(String category, String subCategory, String userName, String jobTitle, String approveTime, Integer approveResult, List<Integer> approveResultList) {
        return mapper.countList(category, subCategory, userName, jobTitle, approveTime, approveResult, approveResultList);
    }

    /**
     * 新增申请记录
     * @param userId  申请用户ID
     * @param userName 用户姓名
     * @param categoryId  领域id
     * @param categoryChildId 岗位id
     * @param categoryAcademicId 申请职称
     * @param list 条件列表
     * @param operateType
     * @return
     */
    @Override
    @Transactional
    public QaApplyRecord create(String userId, String userName, String categoryId, String categoryChildId, String categoryAcademicId, Map<String, String> list, Integer operateType) {
        QaApplyRecord qaApplyRecord = new QaApplyRecord();
        qaApplyRecord = new QaApplyRecord();
        qaApplyRecord.setId(UUIDUtils.generateUuid());
        qaApplyRecord.setCreateDate(new Date());
        qaApplyRecord.setModifyDate(new Date());
        qaApplyRecord.setUserId(userId);
        qaApplyRecord.setUserName(userName);
        qaApplyRecord.setStatus(operateType);
        qaApplyRecord.setCategoryId(categoryId);
        qaApplyRecord.setCategoryChildId(categoryChildId);
        qaApplyRecord.setCategoryAcademicId(categoryAcademicId);
        qaApplyRecord.setFirstApproveStatus(ApproveStatusEnum.UNDERREVIEW.getKey());
        qaApplyRecord.setSecondApproveStatus(ApproveStatusEnum.UNDERREVIEW.getKey());
        this.save(qaApplyRecord);
        //将选择条件存储至QaApplyRecordAttribute表
        boolean isUpdateApplyNum = qaApplyRecordAttributeService.create(list,qaApplyRecord,operateType);
        /*所有内容均符合职位申请规则时，且operateType 为1：提交审核申请时， QaApplyRecord 申请记录中初审自动通过，更新初审状态和初审时间*/
        if(operateType ==1) {
            //需变更category表中的申请总数
            qaCategoryService.updateApplyNum(qaApplyRecord.getCategoryId(), qaApplyRecord.getCategoryChildId(),qaApplyRecord.getCategoryAcademicId());
            //如初审通过，变更初审时间、初审状态，变更category表中的初审通过人数
            if (isUpdateApplyNum) {
                updateFirstApprove(qaApplyRecord,ApproveStatusEnum.PAST.getKey());
            }else{
                updateFirstApprove(qaApplyRecord,ApproveStatusEnum.NOPASS.getKey());
            }
        }
        return qaApplyRecord;
    }

    /**
     * 修改申请记录
     *
     * @param id
     * @param list 条件列表
     * @param operateType
     * @return
     */
    @Override
    @Transactional
    public Message<QaApplyRecord> edit(String id, Map<String, String> list, Integer operateType) {
        QaApplyRecord qaApplyRecord = this.find(id);
        if(qaApplyRecord!= null){
            if(qaApplyRecord.getSecondApproveStatus() == ApproveStatusEnum.PAST.getKey()){
                return Message.error("该申请已评审通过，不能修改");
            }else{
                //删除原来的选择条件记录
                qaApplyRecordAttributeService.deleteByApplyId(id);
                //新增选择条件记录
                boolean isUpdateApplyNum =  qaApplyRecordAttributeService.create(list,qaApplyRecord, 0);
                qaApplyRecord.setModifyDate(new Date());
                /*如原状态为保存(operateType:0)，现操作变仍为保存(operateType:0),qaApplyRecord表不做变更*/
                /*如原状态为保存(operateType:0)，现操作变更为提交(operateType:1)，需变更category表中的申请人数*/
                if(qaApplyRecord.getStatus()== ApplyInfoStatusEnum.SAVE.getKey() && operateType ==ApplyInfoStatusEnum.SUMMIT.getKey()){
                    qaApplyRecord.setStatus(operateType);
                    //需变更category表中的申请总数
                    qaCategoryService.updateApplyNum(qaApplyRecord.getCategoryId(), qaApplyRecord.getCategoryChildId(),qaApplyRecord.getCategoryAcademicId());
                    //如初审通过，变更初审时间、初审状态，变更category表中的初审通过人数
                    if(isUpdateApplyNum){
                        updateFirstApprove(qaApplyRecord,ApproveStatusEnum.PAST.getKey());
                    }else{
                        updateFirstApprove(qaApplyRecord,ApproveStatusEnum.NOPASS.getKey());
                    }
                }
                /*原状态为提交(operateType:1)未通过初审，现操作变仍为提交(operateType:1),不变更申请人数，如此次初审通过，需变更初审人数*/
                else if(qaApplyRecord.getStatus()==ApplyInfoStatusEnum.SUMMIT.getKey() && operateType == ApplyInfoStatusEnum.SUMMIT.getKey() && qaApplyRecord.getFirstApproveTime() ==null ){
                    //如初审通过，变更初审时间、初审状态，变更category表中的初审通过人数
                    if(isUpdateApplyNum){
                        updateFirstApprove(qaApplyRecord,ApproveStatusEnum.PAST.getKey());
                    }else{
                        updateFirstApprove(qaApplyRecord,ApproveStatusEnum.NOPASS.getKey());
                    }
                }
                    return Message.success(qaApplyRecord);
            }
        }
        return Message.error("修改申请数据失败");
    }

    /**
     * 初审通过后，变更初审时间、状态、初审通过的总人数
     * @param qaApplyRecord
     */
    private void updateFirstApprove(QaApplyRecord qaApplyRecord,Integer status) {
        //变更申请记录中的初审时间和状态
        qaApplyRecord.setFirstApproveStatus(status);
        qaApplyRecord.setFirstApproveTime(new Date());
        this.update(qaApplyRecord);
        if(status == ApproveStatusEnum.PAST.getKey()) {
            //需变更category表中的初审通过总数
            qaCategoryService.updateFirstApproveNum(qaApplyRecord.getCategoryId(), qaApplyRecord.getCategoryChildId(), qaApplyRecord.getCategoryAcademicId());
        }
    }

    /**
     * 人工审核
     * @param applyId
     * @param opinion
     * @param type
     * @return
     */
    @Override
    public Message<QaApplyRecord> approve(String applyId, String opinion, Integer type) {
        QaApplyRecord qaApplyRecord = this.find(applyId);
        if(applyId != null){
            qaApplyRecord.setSecondApproveStatus(type);
            qaApplyRecord.setSecondApproveTime(new Date());
            qaApplyRecord.setApproveOpinion(opinion);
            this.update(qaApplyRecord);
            //记录审核日志
            qaApplyLogService.create(qaApplyRecord.getId(),qaApplyRecord.getUserId(),qaApplyRecord.getUserName(),qaApplyRecord.getCategoryId(),qaApplyRecord.getCategoryChildId(),qaApplyRecord.getCategoryAcademicId(),type);
            if(type == ApproveStatusEnum.PAST.getKey()){
                //变更category表中的审核通过人数
                qaCategoryService.updateApplyNum(qaApplyRecord.getCategoryId(),qaApplyRecord.getCategoryChildId(),qaApplyRecord.getCategoryAcademicId());
            }
            return Message.success(qaApplyRecord);
        }
        return Message.error("审核操作执行失败");
    }

    /**
     * 审核详情查看
     * @param id
     * @return
     */
    @Override
    public Message<QaApplyRecord> approveDetails(String id) {
        QaApplyRecord qaApplyRecord = this.find(id);
        List<Map<String,Object>> listMap = new ArrayList<>();
        if(qaApplyRecord.getSecondApproveTime()!=null) {
            //人工审核详情
            Map<String, Object> secondApproveResult = new HashMap<>();
            secondApproveResult.put("isPass", qaApplyRecord.getSecondApproveStatus());
            secondApproveResult.put("opinion", qaApplyRecord.getApproveOpinion());
            listMap.add(secondApproveResult);
        }
        //初审详情
        Map<String,Object> firstApproveResult  = new HashMap<>();
        List<QaApplyRecordAttribute> attributes = qaApplyRecordAttributeService.findPassInfoByApplyId(id,0);
        firstApproveResult.put("isPass",qaApplyRecord.getFirstApproveStatus());
        Map<String,Object> firstValue  = new HashMap<>();
        for (QaApplyRecordAttribute attribute:attributes){
            firstValue.put("content",qaClauseService.find(attribute.getCaluseId()).getContent());
            firstValue.put("choice",attribute.getUserChoice());
            firstApproveResult.put(attribute.getCaluseId(),firstValue);
        }
        listMap.add(firstApproveResult);
        return Message.success(listMap);
    }

    /**
     * 插入某个领域下的多条申请记录
     * @param userId
     * @param userName
     * @param categoryId
     * @param list
     * @param operateType
     * @return
     */
    @Override
    public int createList(String userId, String userName, String categoryId, Map<String, String> list, int operateType) {
        //补全因去重导致的提交结果集合不全
        list = this.completionClauseResult(list);
        //查询指定领域下的所有职称
        List<QaCategory> academicCategoryList = qaCategoryService.findListByCategory(categoryId, CategoryTypeEnum.ACADEMIC.getKey());
        if(academicCategoryList != null && !academicCategoryList.isEmpty()) {
            //循环职称列表，为所有职称添加申请记录
            for (QaCategory category : academicCategoryList) {
                Map<String, String> academicMap = new HashMap<>();
                /*从提交的结果结合中将每个职称的选择结果筛选出来*/
                //查询数据库中该职称的条件集合
                List<QaClause> qaClauseList = qaClauseService.findListByAcademic(category.getId());
                //循环遍历条件列表，为每个条件赋值
                for(QaClause qaClause:qaClauseList) {
                    Iterator<String> iter = list.keySet().iterator();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        String value = list.get(key);
                        if(key.equals(qaClause.getId())){
                            academicMap.put(key,value);
                            break;
                        }
                    }
                }
                //新增资料
                if(operateType ==1) {
                    this.create(userId, userName, categoryId, category.getParent().split(",")[1], category.getId(), academicMap, 1);
                }
                //修改资料
                else if(operateType ==2){
                    QaApplyRecord qaApplyRecord = this.findByUserAndAcademic(userId,category.getId());
                    this.edit(qaApplyRecord.getId(),academicMap,1);
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * 根据用户ID和申请职称获取申请记录
     * @param userId
     * @param categoryAcademicId
     * @return
     */
    @Override
    public QaApplyRecord findByUserAndAcademic(String userId, String categoryAcademicId) {
        List<Filter> filters = ListUtil.addToList(Filter.eq("userId", userId));
        if(categoryAcademicId != null){
            filters.add(Filter.eq("categoryAcademicId",categoryAcademicId));
        }
        List<QaApplyRecord> qaClauseList = this.findList(1, filters, null);
        if(qaClauseList != null && ! qaClauseList.isEmpty()) {
            return qaClauseList.get(0);
        }
        return null;
    }

    /**
     * 根据用户ID和申请领域获取申请记录
     * @param userId
     * @param categoryId
     * @return
     */
    @Override
    public List<QaApplyRecord> findByUserAndCategory(String userId, String categoryId) {
        List<QaApplyRecord> qaClauseList = this.findList(1, ListUtil.addToList(Filter.eq("userId", userId),Filter.eq("categoryId",categoryId)), null);
        return  qaClauseList;
    }

    /**
     * 补全因去重导致的提交结果集合不全
     * @param list
     */
    private Map<String, String>  completionClauseResult(Map<String, String> list) {
        Map<String, String> resultList = new HashMap<>();
        Iterator<String> iter = list.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            String value = list.get(key);
            resultList.put(key,value);
            //查询与本条重复的其他条件
            List<QaClause> clauseList = qaClauseService.findRepeatClauseListById(key);
            if(clauseList != null && !clauseList.isEmpty()){
                //循环遍历条件列表
                for(QaClause qaClause:clauseList){
                    //为其他条件赋值，并将其他条件也加入到集合中
                    if(!qaClause.getId().equals(key)){
                        resultList.put(qaClause.getId(),value);
                    }
                }
            }
        }
        return  resultList;
    }

    @Override
    public Message findPersonalPage(String userId,String userName,String  phoneNum) {
        Map resultMap   =   new HashMap();
        List filters   = new ArrayList();
        QaUserInfo qaUserInfo  =    null;

        if(!StringUtils.isEmpty(userId))
        {
            qaUserInfo= qaUserInfoService.find(userId);
            filters.add(Filter.eq("userId",userId));
        }else  if(!StringUtils.isEmpty(userName)) {
            List<QaUserInfo>qs= qaUserInfoService.findList(0,Arrays.asList(Filter.eq("name",userName)),null);
            if(!ObjectUtils.isEmpty(qs)){
                qaUserInfo  =   qs.get(0);
                filters.add(Filter.eq("userId",qaUserInfo.getId()));
            }
        }else if(!StringUtils.isEmpty(phoneNum)){
            List<QaUserInfo>qs= qaUserInfoService.findList(0,Arrays.asList(Filter.eq("mobile",phoneNum)),null);
            if(!ObjectUtils.isEmpty(qs)){
                qaUserInfo  =   qs.get(0);
                filters.add(Filter.eq("userId",qaUserInfo.getId()));
            }
        }
        List<Order> orders  =  Arrays.asList(Order.desc("createDate"));
        List<QaApplyRecord>   records = findList(100, filters,orders);
        resultMap.put("qaUserInfo",qaUserInfo);
        List applyList  =   new ArrayList();
        if(ObjectUtils.isEmpty(records)){
            resultMap.put("recoreds",new ArrayList<>());
        }else {
            for (QaApplyRecord  record:records){
                Map map =  new HashMap();
                map.put("id",record.getId());
                QaCategory qaCategory =qaCategoryService.find(record.getCategoryId());
                QaCategory qaCategoryAcademic= qaCategoryService.find(record.getCategoryAcademicId());
                QaCategory qaChildCategory= qaCategoryService.find(record.getCategoryChildId());
                map.put("academicTitle", qaCategoryAcademic != null ? qaCategoryAcademic.getAnotherName()+"("+qaCategory.getName()+ "--" + qaChildCategory.getName() +")" : "");
                map.put("submitDate",record.getModifyDate());
                List<QaApplyRecordAttribute> qaApplyRecordAttribute   =  qaApplyRecordAttributeService.findAttributeByApplyId(record.getId());
                if(ObjectUtils.isEmpty(qaApplyRecordAttribute)){
                    map.put("submitResult","success");
                }else{
                    map.put("submitResult","fail");
                    map.put("qaApplyRecordAttribute",qaApplyRecordAttribute);
                }
                map.put("aiAuditDate",record.getModifyDate());
                map.put("firstApproveStatus",record.getFirstApproveStatus());
                map.put("firstApproveDate",record.getFirstApproveTime());
                map.put("secondApproveDate",record.getSecondApproveTime());
                if(record.getSecondApproveStatus()==0){
                    map.put("secondApproveStatus","notPass");
                }else if(record.getSecondApproveStatus()==1){
                    map.put("secondApproveStatus","pass");
                }else{
                    map.put("secondApproveStatus","checking");
                }
                applyList.add(map);
            }
            resultMap.put("applyList",applyList);
        }
        return Message.success(resultMap);
    }
    /**
     * 通过登录用户信息获取已申请未申请列表
     * @param userId
     * @return
     */
    @Override
    public Message findPersonalApplies(String userId) {
        //获取个人申请数据
        List filters   = Arrays.asList(Filter.eq("userId",userId));
        List<Order> orders  =  Arrays.asList(Order.desc("createDate"));
        List<QaApplyRecord>   records = findList(100, filters,orders);
        List<String>  applyParentCategories   =  records.parallelStream().map(QaApplyRecord::getCategoryId).collect(Collectors.toList());
        List<String>  applyChildCategories   =  records.parallelStream().map(QaApplyRecord::getCategoryChildId).collect(Collectors.toList());

        List<QaCategory>  qaCategories   =   qaCategoryService.findAll();
        //所有职业领域
        List<QaCategory>  parentQaCategories= qaCategories.parallelStream().filter(item->item.getParent()==null)
                .collect(Collectors.toList());
        List    result  =   new ArrayList(parentQaCategories.size());
        for(QaCategory  qaCategory  : parentQaCategories)
        {
            Map map =   new HashMap();
            map.put("id",qaCategory.getId());
            map.put("name",qaCategory.getName());
            map.put("anotherName",qaCategory.getAnotherName());
            List<QaCategory>    childQaCategories   =   qaCategories.parallelStream()
                    .filter(item->qaCategory.getId().equals(item.getParent())).collect(Collectors.toList());
            if(applyParentCategories.contains(qaCategory.getId())){
                map.put("applied",true);
            }else {
                map.put("applied",false);
            }
            List<Map> list= new ArrayList<>();
            for (QaCategory  childQaCategory:childQaCategories){
                Map  childMap    =   new HashMap();
                childMap.put("id",childQaCategory.getId());
                childMap.put("name",childQaCategory.getName());
                childMap.put("anotherName",childQaCategory.getAnotherName());
                if(applyChildCategories.contains(childQaCategory.getId())){
                    childMap.put("applied",true);
                }else {
                    childMap.put("applied",false);
                }
                list.add(childMap);
            }
            map.put("child",list);
            result.add(map);
        }
        return Message.success(result);
    }

    @Override
    public QaApplyRecord findRecordWithAttribute(Filter... filters) {
        return mapper.findRecordWithAttribute(filters);
    }

    @Override
    public List<QaApplyRecord> findApplyAndAttrCount(Integer approveStatus, Integer past, String categoryAcademicId) {
        return mapper.findApplyAndAttrCount(approveStatus, past, categoryAcademicId);
    }
}