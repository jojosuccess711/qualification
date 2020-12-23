/**
 * @filename:QaStandardRecordServiceImpl 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.alibaba.fastjson.JSON;
import com.bdjbd.*;
import com.bdjbd.bo.Category;
import com.bdjbd.bo.QaStandardRecordVO;
import com.bdjbd.bo.ResVO;
import com.bdjbd.common.util.StringUtil;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.*;
import com.bdjbd.dao.mapper.*;
import com.bdjbd.enums.CategoryNameEnum;
import com.bdjbd.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.beans.Transient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 用户标准信息记录表 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 */
@Slf4j
@Service
public class QaStandardRecordServiceImpl extends BaseServiceImpl<QaStandardRecord, String> implements QaStandardRecordService {
    @Autowired
    QaCategoryService qaCategoryService;

    @Autowired
    private BaseRelationParameterDefinitionMapper parameterDefinitionMapper;

    @Autowired
    private QaStandardRecordMapper mapper;

    @Autowired
    private QaAcademicRecordItemMapper recordItemMapper;

    @Autowired
    private BaseParameterMapper baseParameterMapper;

    @Autowired
    private QaStandardRecordItemService itemService;

    @Autowired
    QaAcademicRecordService qaAcademicRecordService;
    @Autowired
    BaseParameterGroupService parameterGroupService;

    @Autowired
    private QaApproverMapper approverMapper;

    @Autowired
    private QaStandardRecordItemMapper qaStandardRecordItemMapper;

    @Autowired
    private BaseSimpleDefinitionMapper simpleDefinitionMapper;

    @Autowired
    public void setBaseDao(QaStandardRecordMapper mapper) {
        super.setBaseDao(mapper);
    }

    /**
     * 通过申请人信息查询已填写的申请信息
     *
     * @return
     */
    @Override
    public List<QaStandardRecord> findQaStandardRecords(String userId) {
        return mapper.findQaStandardRecords(userId);
    }

    /**
     * 判断用户基础信息并保存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message saveOrUpdateStandardRecord(QaStandardRecord qaStandardRecord, String userId) {
        List<QaStandardRecord> records = findList
                (1, Collections.singletonList(Filter.eq("userId", userId)), null);
        if (qaStandardRecord != null) {
            qaStandardRecord.setUserId(userId);
            qaStandardRecord.setModifyDate(new Date());
            if (!StringUtils.isEmpty(qaStandardRecord.getCategoryType())) {
                List<QaCategory> categories = qaCategoryService.findList(
                        Collections.singletonList(Filter.eq("name", qaStandardRecord.getCategoryType())), null);
                if (!ObjectUtils.isEmpty(categories)) {
                    qaStandardRecord.setAttr2(categories.get(0).getId());
                    QaCategory parent = qaCategoryService.find(categories.get(0).getParent());
                    if (parent != null) {
                        qaStandardRecord.setCategory(parent.getName());
                        qaStandardRecord.setAttr1(parent.getId());
                        String parentString = qaStandardRecord.getAttr1() + "," + qaStandardRecord.getAttr2();
                        String technologyCategory = qaStandardRecord.getTechnologyCategory();
                        CategoryNameEnum byValue = CategoryNameEnum.getByValue(technologyCategory);
                        if (byValue != null) {
                            technologyCategory = byValue.getName();
                        }
                        List<QaCategory> qaCategoryList = qaCategoryService.findList(
                                Arrays.asList(Filter.eq("parent", parentString), Filter.eq("anotherName", technologyCategory)), null);
                        qaStandardRecord.setAttr3(qaCategoryList.get(0).getId());
                    }
                }
            }

            if (records.size() > 0) {
                qaStandardRecord.setCommitStatus(records.get(0).getCommitStatus());
                qaStandardRecord.setId(records.get(0).getId());
                qaStandardRecord.setCreateDate(records.get(0).getCreateDate());
                update(qaStandardRecord);
            } else {
                qaStandardRecord.setCommitStatus(false);
                qaStandardRecord.setId(UUIDUtils.generateUuid());
                qaStandardRecord.setCreateDate(new Date());
                save(qaStandardRecord);
            }
        } else {
            log.error("用户未录入基本信息数据");
        }
        return Message.success(qaStandardRecord);
    }

    @Override
    public List<QaStandardRecord> findByUserAndParamGroup(String userId, String groupId) {
        return mapper.findByUserAndParamGroup(userId, groupId);
    }

    @Override
    public Page<QaStandardRecord> findPage(Integer pageNum, Integer pageSize, QaStandardRecord qaStandardRecord) {
        try {
            Pageable pageable = new Pageable(pageNum, pageSize);
            List<Filter> filters = getFilters(qaStandardRecord);
            pageable.setFilterList(filters);
            Page<QaStandardRecord> page = this.findPage(pageable);
            return page;
        } catch (Exception e) {
            log.error("查询申请记录失败，{}", e);
            throw new RuntimeException(e);
        }
    }

    private List<Filter> getFilters(QaStandardRecord qaStandardRecord) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Filter> filters = new ArrayList<>();
        List<String> list = Arrays.asList("attr0", "mobile", "name", "idCard", "personnelCategory", "politicalAffiliation", "lastEducation", "major", "lastSchoolMajor",
                "highestSchoolMajor", "highestDegree", "schoolCategory", "technologyTitle", "technologyLevel", "technologyCategory",
                "academicTitle", "category", "categoryType", "categoryTitle", "militaryRank", "officialRank", "department", "techNum", "commitStatus");
        Class<QaStandardRecord> qaStandardRecordClass = QaStandardRecord.class;
        for (String string : list) {
            String methodName = "get" + string.toUpperCase().substring(0, 1) + string.substring(1);
            Method declaredMethod = qaStandardRecordClass.getDeclaredMethod(methodName);
            Object invoke = declaredMethod.invoke(qaStandardRecord);
            if (!StringUtils.isEmpty(invoke)) {
                filters.add(Filter.eq(string, invoke));
            }
        }
        return filters;
    }

    @Transactional
    @Override
    public int saveUserCategory(QaStandardRecord qaStandardRecord) {
        this.update(qaStandardRecord);
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("userId", qaStandardRecord.getUserId()));
        List<QaAcademicRecord> list = qaAcademicRecordService.findList(filters, null);
        if (list.size() == 0) {
            return 0;
        } else {
            for (QaAcademicRecord qaAcademicRecord : list) {
                qaAcademicRecord.setModifyDate(new Date());
                if (qaAcademicRecord.getHasUserInput() && !qaAcademicRecord.getCategoryAcademicId().equals(qaStandardRecord.getUserWantCategoryTitleId())) {
                    qaAcademicRecord.setHasUserInput(Boolean.FALSE);
                    qaAcademicRecordService.update(qaAcademicRecord);
                }
                if (qaAcademicRecord.getCategoryAcademicId().equals(qaStandardRecord.getUserWantCategoryTitleId())) {
                    qaAcademicRecord.setHasUserInput(Boolean.TRUE);
                    qaAcademicRecordService.update(qaAcademicRecord);
                }
            }
            return 1;
        }
    }

    @Override
    public Message<?> findDetails(String userId) {
        List<BaseParameterGroup> parameterGroups = parameterGroupService
                .findOptions(userId, true);
        for (BaseParameterGroup parameterGroup : parameterGroups) {
            List<BaseParameter> parameters = parameterGroup.getParameters();
            List<QaStandardRecordItem> userSelection = parameters.get(0).getUserSelection();
            if (userSelection == null || userSelection.size() == 0) {
                parameterGroup.setIntact(false);
            } else {
                Set<Integer> set = new HashSet<>();
                for (QaStandardRecordItem qaStandardRecordItem : userSelection) {
                    set.add(qaStandardRecordItem.getOrders());
                }
                List<Integer> ordersList = new ArrayList<>(set);
                Collections.sort(ordersList);
                List<Integer> checkErrorList = new ArrayList<>();

                for (Integer integer : ordersList) {
                    List<QaStandardRecordItem> qaStandardRecordItems = new ArrayList<>();
                    for (BaseParameter parameter : parameters) {
                        // 必填项需要验证
                        if ("1".equals(parameter.getAttr2())) {
                            List<QaStandardRecordItem> selections = parameter.getUserSelection();
                            for (QaStandardRecordItem selection : selections) {
                                if (selection.getOrders() == integer) {
                                    if ("date".equals(parameter.getType()) && parameter.getTypeValidate().contains("|")) {
                                        if ("".equals(selection.getParameterValue().split("-")[0])) {
                                            selection.setParameterValue("");
                                        }
                                    }
                                    qaStandardRecordItems.add(selection);
                                }
                            }
                        }
                    }
                    // 验证字段
                    for (QaStandardRecordItem item : qaStandardRecordItems) {
                        if (StringUtils.isEmpty(item.getParameterValue())) {
                            // 验证不通过
                            checkErrorList.add(integer);
                            break;
                        }
                    }
                }

                for (Integer integer : checkErrorList) {
                    for (BaseParameter parameter : parameters) {
                        List<QaStandardRecordItem> selections = new ArrayList<>();
                        for (QaStandardRecordItem item : parameter.getUserSelection()) {
                            if (item.getOrders() != integer) {
                                selections.add(item);
                            }
                        }
                        parameter.setUserSelection(selections);
                    }
                }
                if (parameters.get(0).getUserSelection().size() == 0) {
                    parameterGroup.setIntact(false);
                }
            }
        }

        return Message.success(parameterGroups);
    }

    @Override
    public Page<QaStandardRecordVO> findApproveList(Integer pageNum, Integer pageSize, QaStandardRecord qaStandardRecord) throws Exception {
        Pageable pageable = new Pageable(pageNum, pageSize);
        List<Filter> filters = getFilters(qaStandardRecord);
        pageable.setFilterList(filters);
        Page<QaStandardRecord> page = this.findPage(pageable);
        List<QaStandardRecordVO> result = new ArrayList<>();
        List<QaStandardRecord> content = page.getContent();
        for (QaStandardRecord standardRecord : content) {
            QaStandardRecordVO vo = new QaStandardRecordVO();
            BeanUtils.copyProperties(standardRecord, vo);
            Example example = new Example(QaApprover.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("adminId", standardRecord.getId());
            List<QaApprover> qaApprovers = approverMapper.selectByExample(example);
            if (qaApprovers.isEmpty()) {
                vo.setApproverType(new ArrayList<>());
            } else {
                List<String> collect = qaApprovers.stream().map(QaApprover::getApproverType).distinct().collect(Collectors.toList());
                vo.setApproverType(collect);
            }
            result.add(vo);
        }
        return new Page<>(result, page.getTotal(), page.getPageable());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResVO findListByGroup(Integer pageNum, Integer pageSize, String groupId, String userId, String type, String status) {

        if (org.apache.commons.lang3.StringUtils.equals("3", status)) {
            QaStandardRecord qaStandardRecord1 = new QaStandardRecord();
            qaStandardRecord1.setUserId(userId);
            QaStandardRecord record1 = mapper.selectOne(qaStandardRecord1);
            if (record1 == null)
                return new ResVO(null, false);
            //0 未提交 1已提交
            if (!record1.getCommitStatus()) {
                return new ResVO(null, false);
            }
        }
        ResVO resVO = new ResVO();

        Map<String, String> map = new HashMap<>();
        List<BaseRelationParameterDefinition> baseRelationParameterDefinitions = parameterDefinitionMapper.selectAll();
        for (BaseRelationParameterDefinition baseRelationParameterDefinition : baseRelationParameterDefinitions) {
            map.put(baseRelationParameterDefinition.getParameterId(), baseRelationParameterDefinition.getParameterGroupId());
        }
        if (org.apache.commons.lang3.StringUtils.equals("2", type))
            groupId = map.get(groupId);

        QaStandardRecord qaStandardRecord = new QaStandardRecord();
        qaStandardRecord.setUserId(userId);
        QaStandardRecord record = mapper.selectOne(qaStandardRecord);

        List<BaseParameter> result = baseParameterMapper.findParametersByGroupId(groupId);

        List<QaStandardRecordItem> content2 = new ArrayList<>();
        Pageable pageable = new Pageable(pageNum, pageSize);
        int total = 0;
        List<Integer> orderLists = new ArrayList<>();
        if (record != null) {

            List<QaStandardRecordItem> attr1List = qaStandardRecordItemMapper.selectItemGroup(record.getId(), groupId, (pageNum - 1) * pageSize,  pageSize);
            List<Integer> collect = attr1List.stream().map(QaStandardRecordItem::getAttr1).collect(Collectors.toList());

            List<QaStandardRecordItem> content1 = qaStandardRecordItemMapper.findPageList(record.getId(), groupId);
            for (QaStandardRecordItem qaStandardRecordItem : content1) {
                if (collect.contains(qaStandardRecordItem.getAttr1()))
                    content2.add(qaStandardRecordItem);
            }

            total = qaStandardRecordItemMapper.selectCountByPage(record.getId(), groupId);
            for (QaStandardRecordItem qaStandardRecordItem : content2) {
                if (!orderLists.contains(qaStandardRecordItem.getOrders()))
                    orderLists.add(qaStandardRecordItem.getOrders());
            }
        }
        Integer maxOrder = null;
        String str;
        if (record != null) {
            maxOrder = findMaxOrder(groupId, record.getId());
            str = maxOrder + "";
        } else
            str = "";
        for (BaseParameter baseParameter : result) {
            if (!org.apache.commons.lang3.StringUtils.equals(groupId, baseParameter.getGroupId()))
                baseParameter.setGroupId(groupId);
            baseParameter.setMaxOrder(str);
            if (map.containsKey(baseParameter.getId()))
                baseParameter.setIsLevel("2");
            else
                baseParameter.setIsLevel("1");
            List<BaseSimpleDefinition> simpleDefinitions = new ArrayList<>();
            List<QaStandardRecordItem> userSelection = new ArrayList<>();
            if (baseParameter.getType().contains("select")) {
                Example example = new Example(BaseSimpleDefinition.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("type", baseParameter.getTypeValidate());
                example.orderBy("orders");
                simpleDefinitions = simpleDefinitionMapper.selectByExample(example);
            }
            if (content2.size() != 0) {
                for (QaStandardRecordItem qaStandardRecordItem : content2) {
                    if (qaStandardRecordItem.getParameterId().equals(baseParameter.getId())) {
                        userSelection.add(qaStandardRecordItem);
                    }
                }
            }
            boolean flag = false;
            for (QaStandardRecordItem qaStandardRecordItem : userSelection) {
                if (qaStandardRecordItem.getAttr1() != null)
                    flag = true;
            }
            if (flag) {
                List<QaStandardRecordItem> list = userSelection.stream().sorted((o1, o2) -> {
                    return Integer.valueOf(o1.getAttr1()).compareTo(Integer.valueOf(o2.getAttr1()));
                }).collect(Collectors.toList());
                baseParameter.setUserSelection(list);
            }else
                baseParameter.setUserSelection(userSelection);
//            baseParameter.setUserSelection(list);
            baseParameter.setOptions(simpleDefinitions);
            baseParameter.setOrderList(orderLists);
        }
        result.stream().sorted(Comparator.comparing(BaseParameter::getOrders)).collect(Collectors.toList());
        for (BaseParameter baseParameter : result) {
            if (CollectionUtils.isEmpty(baseParameter.getUserSelection()) && org.apache.commons.lang3.StringUtils.equals("1", baseParameter.getAttr2())) {
                resVO.setShow(false);
                break;
            }
        }
        Page<BaseParameter> resultPage = new Page<>(result, total, pageable);
        resVO.setResultPage(resultPage);
        return resVO;
    }


    private Integer findMaxOrder(String groupId, String recordId) {
        Integer max = recordItemMapper.findMaxOrder(groupId, recordId);
        return max;
    }

    /**
     * 根据用户id查询用户基本信息
     * changpeng添加
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getBaseUserInfo(String userId) {
        return mapper.getBaseUserInfo(userId);
    }

	@Override
	public QaStandardRecord findByIdCard(String idCard) {
		return mapper.findByIdCard(idCard);
	}
}