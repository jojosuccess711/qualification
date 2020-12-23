/**
 * @filename:QaStandardRecordController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.controller;

import com.alibaba.fastjson.JSON;
import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.bo.QaStandardRecordVO;
import com.bdjbd.bo.ResVO;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.service.AssessorsService;
import com.bdjbd.service.BaseParameterGroupService;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.service.QaStandardRecordItemService;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.common.ExcelService;
import com.bdjbd.service.common.UploadService;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.sys.SysConfigService;
import com.bdjbd.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @Description: 用户标准信息记录表接口层
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 */
@Slf4j
@Api(value = "用户标准信息记录表 - Controller", description = "用户标准信息记录表", tags = {"用户标准信息记录表"})
@RestController
@RequestMapping("/admin//qaStandardRecord")
public class QaStandardRecordController {

    @Autowired
    public QaStandardRecordService qaStandardRecordService;
    @Autowired
    QaStandardRecordItemService qaStandardRecordItemService;
    @Autowired
    SysAdminService adminService;
    @Autowired
    BaseParameterGroupService parameterGroupService;
    @Autowired
    private AssessorsService assessorsService;
    @Autowired
    private  UploadService uploadService;
    @Autowired
    private ExcelService excelService;

    @Autowired
    SysConfigService sysConfigService;

    /**
     * @return Message<QaStandardRecord>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取用户标准信息记录表信息", notes = "获取用户标准信息记录表信息[qaStandardRecord]，作者：DBC")
    @ApiImplicitParam(name = "id", value = "用户标准信息记录表id", paramType = "path", dataType = "String")
    public Message<QaStandardRecord> find(@PathVariable("id") String id) {
        try {
            QaStandardRecord qaStandardRecord = qaStandardRecordService.find(id);
            if (qaStandardRecord != null) {
                return Message.success(qaStandardRecord);
            } else {
                log.error("获取用户标准信息记录表失败ID：" + id);
            }
        } catch (Exception e) {
            log.error("获取用户标准信息记录表执行异常：", e);
        }
        return Message.error("信息不存在");
    }

    /**
     * @return Message<QaStandardRecord>
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加用户标准信息记录表", notes = "添加用户标准信息记录表[qaStandardRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别 0：女  1：男", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "personnelCategory", value = "人员类别  军人干部 文职人员", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enlistmentTime", value = "入伍时间（军人干部）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enterEmploymentTime", value = "参加工作时间（文职人员）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "politicalAffiliation", value = "政治面貌  党员", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "caucusTime", value = "党团时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lastEducation", value = "最高学历  大学 硕士研究生 博士研究生 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "major", value = "专业（最高学历）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestDegree", value = "最高学位  学士 硕士 博士 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestDegreeTime", value = "学位时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lastSchoolMajor", value = "最后毕业学校", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "graduateTime", value = "毕业时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestSchoolMajor", value = "最高学位授予学校、专业", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestGetTime", value = "最高学位授予时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "schoolCategory", value = "院校类别   211 985 普通全日制 军内院校 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyTitle", value = "现任专业技术职务  助教 讲师 副教授 教授 研究实习员 助理研究员 副研究员 研究员 实验员 助理实验师 实验师 高级实验师 正高级实验师 助理工程师 技术员 工程师 高级工程师 正高级工程师 会计员 助理会计师 会计师 高级会计师 正高级会计师 助理编辑 编辑 副编审 编审 管理员 助理馆员 馆员 副研究馆员 研究馆员 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appointTime", value = "聘任时间（现专业技术职务时间）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appointAnnex", value = "聘任相关附件地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyLevel", value = "现技术等级  专业技术一级 专业技术二级 专业技术三级 专业技术四级 专业技术五级 专业技术六级 专业技术七级 专业技术八级 专业技术九级 专业技术十级 专业技术十一级 专业技术十二级 专业技术十三级 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyLevelTime", value = "现技术等级时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "militaryRank", value = "现军衔/级别  大校 上将 中将 少将 上校 中校 少校 上尉 中尉 少尉 转改特级 转改1级 转改2级 转改3级 转改4级 转改5级 转改6级 转改7级 转改8级 特级 1级 2级 3级 4级 …… 25级 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "militaryRankTime", value = "现军衔/级别时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyCategory", value = "现技职类别  初职 中职 副高职 正高职 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyCategoryTime", value = "现技职类别时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "officialRank", value = "现部级别  训练管理系助教", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "academicTitle", value = "申请职称，存储下拉框的选择值", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "breakRule", value = "是否破格 0：否  1：是", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "breakRuleAnnex", value = "破格相关附件地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "department", value = "工作部门", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "getQualificationsTime", value = "获得资格时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "getQualificationsAnnex", value = "获得资格相关附件地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "techNum", value = "编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr0", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr1", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr2", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr3", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr4", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr5", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr6", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr7", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr8", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr9", value = "", paramType = "query", dataType = "String"),
    })
    public Message<QaStandardRecord> add(QaStandardRecord qaStandardRecord) {
        try {
            int rg = qaStandardRecordService.save(qaStandardRecord);
            if (rg > 0) {
                return Message.success(qaStandardRecord);
            } else {
                log.error("添加用户标准信息记录表执行失败：" + qaStandardRecord.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加用户标准信息记录表执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<Object>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除用户标准信息记录表", notes = "删除用户标准信息记录表, 作者：DBC")
    @ApiImplicitParam(name = "id", value = "用户标准信息记录表id", paramType = "query", required = true, dataType = "String")
    public Message<Object> delete(String id) {
        try {
            int reg = qaStandardRecordService.delete(id);
            if (reg > 0) {
                return Message.success(id);
            } else {
                log.error("删除用户标准信息记录表失败ID：" + id);
                return Message.error("操作失败，请稍后刷新重试");
            }
        } catch (Exception e) {
            log.error("删除用户标准信息记录表执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaStandardRecord>
     */
    @ApiOperation(value = "修改用户标准信息记录表", notes = "修改用户标准信息记录表[qaStandardRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "sex", value = "性别 0：女  1：男", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "personnelCategory", value = "人员类别  军人干部 文职人员", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "birth", value = "出生日期", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enlistmentTime", value = "入伍时间（军人干部）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "enterEmploymentTime", value = "参加工作时间（文职人员）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "politicalAffiliation", value = "政治面貌  党员", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "caucusTime", value = "党团时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lastEducation", value = "最高学历  大学 硕士研究生 博士研究生 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "major", value = "专业（最高学历）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestDegree", value = "最高学位  学士 硕士 博士 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestDegreeTime", value = "学位时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "lastSchoolMajor", value = "最后毕业学校", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "graduateTime", value = "毕业时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestSchoolMajor", value = "最高学位授予学校、专业", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "highestGetTime", value = "最高学位授予时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "schoolCategory", value = "院校类别   211 985 普通全日制 军内院校 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyTitle", value = "现任专业技术职务  助教 讲师 副教授 教授 研究实习员 助理研究员 副研究员 研究员 实验员 助理实验师 实验师 高级实验师 正高级实验师 助理工程师 技术员 工程师 高级工程师 正高级工程师 会计员 助理会计师 会计师 高级会计师 正高级会计师 助理编辑 编辑 副编审 编审 管理员 助理馆员 馆员 副研究馆员 研究馆员 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appointTime", value = "聘任时间（现专业技术职务时间）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "appointAnnex", value = "聘任相关附件地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyLevel", value = "现技术等级  专业技术一级 专业技术二级 专业技术三级 专业技术四级 专业技术五级 专业技术六级 专业技术七级 专业技术八级 专业技术九级 专业技术十级 专业技术十一级 专业技术十二级 专业技术十三级 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyLevelTime", value = "现技术等级时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "militaryRank", value = "现军衔/级别  大校 上将 中将 少将 上校 中校 少校 上尉 中尉 少尉 转改特级 转改1级 转改2级 转改3级 转改4级 转改5级 转改6级 转改7级 转改8级 特级 1级 2级 3级 4级 …… 25级 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "militaryRankTime", value = "现军衔/级别时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyCategory", value = "现技职类别  初职 中职 副高职 正高职 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "technologyCategoryTime", value = "现技职类别时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "officialRank", value = "现部级别  训练管理系助教", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "academicTitle", value = "申请职称，存储下拉框的选择值", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "breakRule", value = "是否破格 0：否  1：是", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "breakRuleAnnex", value = "破格相关附件地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "department", value = "工作部门", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "getQualificationsTime", value = "获得资格时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "getQualificationsAnnex", value = "获得资格相关附件地址", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "techNum", value = "编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr0", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr1", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr2", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr3", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr4", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr5", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr6", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr7", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr8", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr9", value = "", paramType = "query", dataType = "String"),
    })
    @PostMapping("/update")
    public Message<QaStandardRecord> update(QaStandardRecord qaStandardRecord) {
        try {
            int reg = qaStandardRecordService.update(qaStandardRecord);
            if (reg > 0) {
                return Message.success(qaStandardRecord);
            } else {
                log.error("修改用户标准信息记录表失败ID：" + qaStandardRecord.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("修改用户标准信息记录表执行异常：" + e.getMessage());
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<QaStandardRecord>
     */
    @ApiOperation(value = "分页条件查询用户标准信息记录表", notes = "条件查询[qaStandardRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5")
    })
    @PostMapping("/page")
    public Message<Page<QaStandardRecord>> findPage(Pageable pageable) {
        try {
            Page<QaStandardRecord> page = qaStandardRecordService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取用户标准信息记录表执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return
     */
    @ApiOperation(value = "获取用户已录入信息", notes = "获取用户已录入信息")
    @PostMapping("/userSelections")
    public Message<Page<QaStandardRecord>> findStandardRecords(@RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findQaStandardRecords(userId);
            return Message.success(qaStandardRecords);
        } catch (Exception e) {
            log.error("获取用户已录入信息异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return
     */

    @ApiOperation(value = "用户录入数据保存", notes = "用户录入数据保存")
    @PostMapping("/saveStandardRecordItems")
    public Message saveStandardRecordItems(@RequestHeader("Authorization") String authorization,
                                           String list, String groupId) {
        try {
            // 检验该功能是否可使用
            Message<?> message = sysConfigService.checkStartDateEndDate("INPUT_OPEN");
            if (message != null) return message;
            List<QaStandardRecordItem> qaStandardRecordItems = JSON.parseArray(list, QaStandardRecordItem.class);
            String userId = adminService.findUserIdByToken(authorization);
            return qaStandardRecordItemService.saveStandardRecordItems2
                    (qaStandardRecordItems, userId, groupId);
        } catch (Exception e) {
            log.error("用户录入数据保存异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "用户录入数据删除", notes = "用户录入数据删除")
    @PostMapping("/deleteRecordAndItem")
    public Message deleteRecordAndItem(@RequestHeader("Authorization") String authorization, String list) {
        try {
            // 检验该功能是否可使用
            Message<?> message = sysConfigService.checkStartDateEndDate("INPUT_OPEN");
            if (message != null) return message;
            List<QaStandardRecordItem> qaStandardRecordItems = JSON.parseArray(list, QaStandardRecordItem.class);
            String userId = adminService.findUserIdByToken(authorization);
            return qaStandardRecordItemService.deleteRecordAndItem(qaStandardRecordItems, userId);
        } catch (Exception e) {
            log.error("用户录入数据删除异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "组id", paramType =
                    "query", required = true, dataType = "String", example = "1"),
    })
    @ApiOperation(value = "用户录入文件上传", notes = "用户录入文件上传")
    @PostMapping("/uploadStandardRecordFile")
    public Message uploadStandardRecordFile(@RequestHeader("Authorization")
                                                    String authorization,
                                            @RequestParam("multipartFiles") MultipartFile[] multipartFiles,
                                            String groupId, String parameterId, String orders) {
        try {
            // 检验该功能是否可使用
            Message<?> message = sysConfigService.checkStartDateEndDate("INPUT_OPEN");
            if (message != null) return message;
            String userId = adminService.findUserIdByToken(authorization);
            return qaStandardRecordItemService.uploadStandardRecordFile
                    (multipartFiles, userId, groupId, parameterId, orders);
        } catch (Exception e) {
            log.error("用户录入数据保存异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "用户基本信息图片上传", notes = "用户基本信息图片上传")
    @PostMapping("/uploadUserImg")
    public Message uploadUserImg(@RequestHeader("Authorization")
                                         String authorization,
                                 @RequestParam("multipartFiles") MultipartFile multipartFile) {
        try {
            // 检验该功能是否可使用
            Message<?> message = sysConfigService.checkStartDateEndDate("INPUT_OPEN");
            if (message != null) return message;
            String userId = adminService.findUserIdByToken(authorization);
            return qaStandardRecordItemService.uploadUserImg
                    (multipartFile, userId);
        } catch (Exception e) {
            log.error("用户录入数据保存异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * 下载文件
     *
     * @return
     */
    @IgnoreUserToken
    @IgnoreAuthority
    @ApiOperation(value = "用户下载文件", notes = "用户下载文件")
    @GetMapping("/download")
    public ResponseEntity downloadFile(@RequestParam("targetUrl") String targetUrl) {
        HttpStatus status = HttpStatus.CREATED;
        String fileName = "";
        try {
            fileName = new String((targetUrl.substring(targetUrl
                    .lastIndexOf(File.separator) + 1)).getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error("文件名读取失败");
        }
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_OCTET_STREAM, Charset.forName("utf-8"));
        headers.setContentType(mediaType);
        headers.setContentDispositionFormData("attachment", fileName);
        InputStream inStream;
        try {
            File file = new File(targetUrl);
            inStream = new FileInputStream(file);
            byte[] bytes = FileUtils.readInputStream(inStream);
            inStream.close();
            return new ResponseEntity<>(bytes, headers, status);
        } catch (Exception e) {
            log.error("文件地址无效");
            return new ResponseEntity<>(Message.error("文件地址无效"), HttpStatus.OK);
        }
    }

    /**
     * 下载文件
     *
     * @return
     */
    @ApiOperation(value = "用户点击提交按钮，进行数据验证", notes = "用户点击提交按钮，进行数据验证")
    @PostMapping("/commit")
    public Message commit(@RequestHeader("Authorization") String authorization) {
        // 检验该功能是否可使用
        Message<?> msg = sysConfigService.checkStartDateEndDate("INPUT_OPEN");
        if (msg != null) return msg;
        String userId = adminService.findUserIdByToken(authorization);
        Message message = qaStandardRecordItemService.commitRecord(userId);
        if (message.isSuccess()) {
            // 提交成功后对用户进行电脑评审
            try {
                log.info("提交成功后对用户进行电脑评审");
                assessorsService.saveOrUpdateAcademicRecord(userId);
                System.out.println("电脑评审结束");
            } catch (Exception e) {
                System.out.println("电脑评审异常错误");
                log.error("电脑评审异常，{}", e);
            }
        }
        return message;
    }

    @ApiOperation(value = "条件查询用户标准信息记录列表", notes = "条件查询用户标准信息记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attr0", value = "实际工作单位", paramType = "query", example = ""),

            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5"),
            @ApiImplicitParam(name = "mobile", value = "mobile", paramType = "query", example = ""),
            @ApiImplicitParam(name = "name", value = "name", paramType = "query", example = ""),
            @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", example = ""),
            @ApiImplicitParam(name = "personnelCategory", value = "人员类别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "politicalAffiliation", value = "政治面貌", paramType = "query", example = ""),
            @ApiImplicitParam(name = "lastEducation", value = "最高学历", paramType = "query", example = ""),
            @ApiImplicitParam(name = "major", value = "专业（最高学历）", paramType = "query", example = ""),
            @ApiImplicitParam(name = "highestDegree", value = "最高学位", paramType = "query", example = ""),
            @ApiImplicitParam(name = "lastSchoolMajor", value = "最后毕业学校", paramType = "query", example = ""),
            @ApiImplicitParam(name = "highestSchoolMajor", value = "最高学位授予学校、专业", paramType = "query", example = ""),
            @ApiImplicitParam(name = "schoolCategory", value = "院校类别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "technologyTitle", value = "现任专业技术职务", paramType = "query", example = ""),
            @ApiImplicitParam(name = "technologyLevel", value = "现技术等级", paramType = "query", example = ""),
            @ApiImplicitParam(name = "technologyCategory", value = "现技职类别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "academicTitle", value = "申请职称", paramType = "query", example = ""),
            @ApiImplicitParam(name = "category", value = "现所属技职领域", paramType = "query", example = ""),
            @ApiImplicitParam(name = "categoryType", value = "现所属技职领域类型", paramType = "query", example = ""),
            @ApiImplicitParam(name = "categoryTitle", value = "现职称", paramType = "query", example = ""),
            @ApiImplicitParam(name = "militaryRank", value = "现军衔/级别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "officialRank", value = "现部级别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "department", value = "工作部门", paramType = "query", example = ""),
            @ApiImplicitParam(name = "techNum", value = "编号", paramType = "query", example = ""),
            @ApiImplicitParam(name = "commitStatus", value = "提交状态: true  false", paramType = "query", example = ""),
    })
    @PostMapping("/findList")
    public Message<?> findList(Integer pageNum, Integer pageSize, QaStandardRecord qaStandardRecord) {
        try {
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 20;
            }
            Page<QaStandardRecord> page = qaStandardRecordService.findPage(pageNum, pageSize, qaStandardRecord);
            return Message.success(page);
        } catch (Exception e) {
            log.error("条件查询用户标准信息记录列表异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "查询记录详情", notes = "查询记录详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id"),
    })
    @GetMapping("/findDetails")
    public Message<?> findDetails(String userId) {
        try {
            return qaStandardRecordService.findDetails(userId);
        } catch (Exception e) {
            log.error("查询记录详情失败：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }


    @ApiOperation(value = "查询审批记录详情", notes = "查询审批记录详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attr0", value = "实际工作单位", paramType = "query", example = ""),
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5"),
            @ApiImplicitParam(name = "mobile", value = "mobile", paramType = "query", example = ""),
            @ApiImplicitParam(name = "name", value = "name", paramType = "query", example = ""),
            @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", example = ""),
            @ApiImplicitParam(name = "personnelCategory", value = "人员类别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "politicalAffiliation", value = "政治面貌", paramType = "query", example = ""),
            @ApiImplicitParam(name = "lastEducation", value = "最高学历", paramType = "query", example = ""),
            @ApiImplicitParam(name = "major", value = "专业（最高学历）", paramType = "query", example = ""),
            @ApiImplicitParam(name = "highestDegree", value = "最高学位", paramType = "query", example = ""),
            @ApiImplicitParam(name = "lastSchoolMajor", value = "最后毕业学校", paramType = "query", example = ""),
            @ApiImplicitParam(name = "highestSchoolMajor", value = "最高学位授予学校、专业", paramType = "query", example = ""),
            @ApiImplicitParam(name = "schoolCategory", value = "院校类别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "technologyTitle", value = "现任专业技术职务", paramType = "query", example = ""),
            @ApiImplicitParam(name = "technologyLevel", value = "现技术等级", paramType = "query", example = ""),
            @ApiImplicitParam(name = "technologyCategory", value = "现技职类别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "academicTitle", value = "申请职称", paramType = "query", example = ""),
            @ApiImplicitParam(name = "category", value = "现所属技职领域", paramType = "query", example = ""),
            @ApiImplicitParam(name = "categoryType", value = "现所属技职领域类型", paramType = "query", example = ""),
            @ApiImplicitParam(name = "categoryTitle", value = "现职称", paramType = "query", example = ""),
            @ApiImplicitParam(name = "militaryRank", value = "现军衔/级别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "officialRank", value = "现部级别", paramType = "query", example = ""),
            @ApiImplicitParam(name = "department", value = "工作部门", paramType = "query", example = ""),
            @ApiImplicitParam(name = "techNum", value = "编号", paramType = "query", example = ""),
            @ApiImplicitParam(name = "commitStatus", value = "提交状态: true  false", paramType = "query", example = ""),
    })
    @PostMapping("/findApproveList")
    public Message<?> findApproveList(Integer pageNum, Integer pageSize, QaStandardRecord qaStandardRecord) {
        try {
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 20;
            }
            Page<QaStandardRecordVO> page = qaStandardRecordService.findApproveList(pageNum, pageSize, qaStandardRecord);

            return Message.success(page);
        } catch (Exception e) {
            log.error("查询审批记录详情异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "分页条件查询用户标准信息记录表", notes = "条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5"),
            @ApiImplicitParam(name = "groupId", value = "组名称", paramType = "query", required = true, dataType = "String", example = "10100"),
            @ApiImplicitParam(name = "type", value = "状态", paramType = "query", required = true, dataType = "String", example = "1")
    })
    @IgnoreAuthority
    @IgnoreUserToken
    @PostMapping("/findListByGroup")
    public Message findListByGroup(@RequestHeader("Authorization") String authorization, Integer pageNum, Integer pageSize, String groupId, String type, String userId, String status) {
        try {
            if (pageNum == null)
                pageNum = 1;
            if (pageSize == null)
                pageSize = 5;
            if (StringUtils.isBlank(groupId) && StringUtils.isBlank(type))
                return Message.error("参数不能为空");
            if (StringUtils.isBlank(userId)) {
                userId = adminService.findUserIdByToken(authorization);
                if (userId == null)
                    return Message.error("操作异常，请稍后重试");
            }
            ResVO resVO = qaStandardRecordService.findListByGroup(pageNum, pageSize, groupId, userId, type, status);

            return Message.success(resVO);
        } catch (Exception e) {
            log.error("查询记录详情异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }
    
	@ApiOperation(value = "批量文件上传", notes = "批量文件上传")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file", value = "文件base64编码", paramType="query", required = true, dataType = "String")
	})
	@PostMapping("/upload")
	public Message upload(@RequestHeader("Authorization") String authorization, @RequestParam("multipartFiles") MultipartFile multipartFile){
		try {			
			System.out.println(multipartFile.getOriginalFilename());
			excelService.deal(multipartFile.getBytes());
		} catch (Exception e) {
			log.error("获取批量执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}

}