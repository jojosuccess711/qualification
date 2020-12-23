/**
 * @filename:BaseParameterGroupController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdjbd.*;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.common.util.StringUtil;
import com.bdjbd.dao.entity.*;
import com.bdjbd.service.BaseSimpleDefinitionService;
import com.bdjbd.service.QaCategoryService;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.util.ExportDealUtil;
import com.bdjbd.util.ExportDealUtilSimple;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.bdjbd.service.BaseParameterGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @Description: 参数组表接口层
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Api(value = "参数组表 - Controller", description = "参数组表", tags = {"参数组表"})
@RestController
@RequestMapping("/admin//baseParameterGroup")
public class BaseParameterGroupController {

    @Autowired
    public BaseParameterGroupService baseParameterGroupService;

    @Autowired
    private BaseSimpleDefinitionService baseSimpleDefinitionService;
    @Autowired
    SysAdminService adminService;

    @Autowired
    public QaStandardRecordService qaStandardRecordService;

    @Autowired
    QaCategoryService qaCategoryService;

    @Autowired
    private ExportDealUtil exportDealUtil;

    @Autowired
    private ExportDealUtilSimple exportDealUtilSimple;

    /**
     * @return Message<BaseParameterGroup>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取参数组表信息", notes = "获取参数组表信息[baseParameterGroup]，作者：DBC")
    @ApiImplicitParam(name = "id", value = "参数组表id", paramType = "path", dataType = "String")
    public Message<BaseParameterGroup> find(@PathVariable("id") String id) {
        try {
            BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(id);
            if (baseParameterGroup != null) {
                return Message.success(baseParameterGroup);
            }
            else {
                log.error("获取参数组表失败ID：" + id);
            }
        } catch (Exception e) {
            log.error("获取参数组表执行异常：", e);
        }
        return Message.error("信息不存在");
    }

    @GetMapping("/findById")
    @ApiOperation(value = "根据id获取参数组表信息", notes = "根据id获取参数组表信息")
    @ApiImplicitParam(name = "id", value = "参数组表id", paramType = "query", dataType = "String")
    public Message<?> findById(String id) {
        try {
            BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(id);
            if (baseParameterGroup == null) {
                return Message.error("信息不存在");
            }
            return Message.success(baseParameterGroup);
        } catch (Exception e) {
            log.error("获取参数组表执行异常：", e);
        }
        return Message.error("信息不存在");
    }


    /**
     * @return Message<BaseParameterGroup>
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加参数组表", notes = "添加参数组表[baseParameterGroup], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "组名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "组类型  0. 默认 1. 基本信息（暂时未启用） 2. 课堂教学信息采集标准 3. 教学奖励信息采集标准 4. 教学评价信息采集标准 5. 教学指导信息采集标准 6. 科研项目信息采集标准 7. 专利信息采集标准 8. 科技奖励信息采集标准 9. 学术论文信息采集标准 10.著作信息采集标准 11.教材信息采集标准 12.军标信息采集标准 13.咨询报告信息采集标准 14.参加学术会议信息采集标准 15.参加学术团体信息采集标准 16.担任收录期刊审稿职务信息采集标准 17.部队任（代）职经历信息采集标准 18.受训经历信息采集标准 19.维和援外任务经历信息采集标准 20.出国留学经历信息采集标准 21.参加重大军事行动/部队活动信息采集标准 22.服务部队信息采集标准 23.获人才工程计划和奖励表彰项目信息采集标准", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "titile", value = "标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "介绍", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orders", value = "排序", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memo", value = "备注", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "链接", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr0", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr1", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr2", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr3", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr4", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "grade", value = "层级", paramType = "query", dataType = "String"),
    })
    public Message<BaseParameterGroup> add(BaseParameterGroup baseParameterGroup) {
        try {
            BaseParameterGroup res = baseParameterGroupService.find(baseParameterGroup.getId());
            if (res != null) {
                return Message.error("id已存在");
            }
            baseParameterGroup.setCreateDate(new Date());
            baseParameterGroup.setModifyDate(new Date());
            int rg = baseParameterGroupService.save(baseParameterGroup);
            if (rg > 0) {
                return Message.success(baseParameterGroup);
            }
            else {
                log.error("添加参数组表执行失败：" + baseParameterGroup.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加参数组表执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<Object>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除参数组表", notes = "删除参数组表, 作者：DBC")
    @ApiImplicitParam(name = "id", value = "参数组表id", paramType = "query", required = true, dataType = "String")
    public Message<Object> delete(String id) {
        try {
            int reg = baseParameterGroupService.delete(id);
            if (reg > 0) {
                return Message.success(id);
            }
            else {
                log.error("删除参数组表失败ID：" + id);
                return Message.error("操作失败，请稍后刷新重试");
            }
        } catch (Exception e) {
            log.error("删除参数组表执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<BaseParameterGroup>
     */
    @ApiOperation(value = "修改参数组表", notes = "修改参数组表[baseParameterGroup], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "组名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "组类型  0. 默认 1. 基本信息（暂时未启用） 2. 课堂教学信息采集标准 3. 教学奖励信息采集标准 4. 教学评价信息采集标准 5. 教学指导信息采集标准 6. 科研项目信息采集标准 7. 专利信息采集标准 8. 科技奖励信息采集标准 9. 学术论文信息采集标准 10.著作信息采集标准 11.教材信息采集标准 12.军标信息采集标准 13.咨询报告信息采集标准 14.参加学术会议信息采集标准 15.参加学术团体信息采集标准 16.担任收录期刊审稿职务信息采集标准 17.部队任（代）职经历信息采集标准 18.受训经历信息采集标准 19.维和援外任务经历信息采集标准 20.出国留学经历信息采集标准 21.参加重大军事行动/部队活动信息采集标准 22.服务部队信息采集标准 23.获人才工程计划和奖励表彰项目信息采集标准", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "titile", value = "标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "introduction", value = "介绍", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orders", value = "排序", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memo", value = "备注", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "链接", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr0", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr1", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr2", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr3", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr4", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "grade", value = "层级", paramType = "query", dataType = "String"),
    })
    @PostMapping("/update")
    public Message<BaseParameterGroup> update(BaseParameterGroup baseParameterGroup) {
        try {
            baseParameterGroup.setModifyDate(new Date());
            int reg = baseParameterGroupService.update(baseParameterGroup);
            if (reg > 0) {
                return Message.success(baseParameterGroup);
            }
            else {
                log.error("修改参数组表失败ID：" + baseParameterGroup.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("修改参数组表执行异常：" + e.getMessage());
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<BaseParameterGroup>
     */
    @ApiOperation(value = "分页条件查询参数组表", notes = "条件查询[baseParameterGroup], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5")
    })
    @PostMapping("/page")
    public Message<Page<BaseParameterGroup>> findPage(Pageable pageable) {
        try {
            Page<BaseParameterGroup> page = baseParameterGroupService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取参数组表执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "查询条件组", notes = "查询条件组")
    @GetMapping("/findList")
    public Message<?> findList() {
        try {
            return baseParameterGroupService.findGroupList();
        } catch (Exception e) {
            log.error("查询条件组异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @PostMapping("/addParameters")
    @ApiOperation(value = "批量添加参数", notes = "批量添加参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "参数组id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "参数名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型select 下拉框text 文本框select&text 下拉框可输入date 日期", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "typeValidate", value = "类型校验", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orders", value = "排序", paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "param", value = "参数组，json数据格式字符串：[{\"id\":\"1001\",\"name\":\"aaa\"},\n" +
                    "{\"id\":\"1002\",\"name\":\"bbb\"}\n" +
                    "]", paramType = "query", dataType = "String"),
    })
    public Message<?> addParameters(String param) {
        try {
            List<BaseParameter> list = new ArrayList<>();
            JSONArray array = JSONObject.parseArray(param);
            for (Object o : array) {
                BaseParameter baseParameter = new BaseParameter();
                JSONObject jsonObject = (JSONObject) o;
                baseParameter.setId(jsonObject.getString("id"));
                baseParameter.setGroupId(jsonObject.getString("groupId"));
                baseParameter.setName(jsonObject.getString("name"));
                baseParameter.setTitle(jsonObject.getString("title"));
                baseParameter.setType(jsonObject.getString("type"));
                baseParameter.setTypeValidate(jsonObject.getString("typeValidate"));
                baseParameter.setOrders(jsonObject.getInteger("orders"));
                list.add(baseParameter);
            }
            baseParameterGroupService.insertParam(list);
            return Message.success();
        } catch (Exception e) {
            log.error("批量添加参数异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @PostMapping("/addParameter")
    @ApiOperation(value = "单个添加参数", notes = "单个添加参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "参数组id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "参数名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型select 下拉框text 文本框select&text 下拉框可输入date 日期", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "typeValidate", value = "类型校验", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orders", value = "排序", paramType = "query", dataType = "Int"),
    })
    public Message<?> addParameter(BaseParameter baseParameter) {
        try {
            List<BaseParameter> list = new ArrayList<>();
            list.add(baseParameter);
            baseParameterGroupService.insertParam(list);
            return Message.success();
        } catch (Exception e) {
            log.error("添加参数异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @PostMapping("/findParamByGroupId")
    @ApiOperation(value = "通过分组id查询", notes = "通过分组id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "参数组id", paramType = "query", dataType = "String"),
    })
    public Message<?> findParamByGroupId(String groupId) {
        try {
            //BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
            List<BaseParameter> list = baseParameterGroupService.findParamByGroupId(groupId, true);
            return Message.success(list);
        } catch (Exception e) {
            log.error("通过分组id查询异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @PostMapping("/relationSave")
    @ApiOperation(value = "参数组与(定义/参数)关系添加", notes = "参数组与(定义/参数)关系添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parameterId", value = "参数表id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "relationId", value = "relationId", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parameterGroupId", value = "参数组id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "required", value = "是否必填", paramType = "query", dataType = "Boolean"),
            @ApiImplicitParam(name = "type", value = "类型", paramType = "query", dataType = "Int"),
    })
    public Message<?> relationSave(BaseRelationParameterDefinition relationParameterDefinition) {
        try {
            baseParameterGroupService.relationSave(relationParameterDefinition);
            return Message.success();
        } catch (Exception e) {
            log.error("参数组与(定义/参数)关系添加失败：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @IgnoreAuthority
    @IgnoreUserToken
    @GetMapping("/exportModel")
    @ApiOperation(value = "导出参数模板", notes = "导出参数模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "分类id:  个人基本信息;  教务工作业绩;  科研工作业绩; ...", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "参数组id", paramType = "query", dataType = "String"),
    })
    public void exportModelSimple(String groupId, String typeId, HttpServletResponse response, HttpServletRequest request,
                                  @RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            List<BaseParameterGroup> baseParameterGroupList = null;
            if (!StringUtils.isEmpty(typeId)) {
                baseParameterGroupList = baseParameterGroupService.findGroupListByCategory(typeId);
            } else if (!StringUtils.isEmpty(groupId)) {
                // 查询用户是否有数据
                if (exportDealUtilSimple.getLists(groupId, userId).size() == 0) {
                    BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
                    exportDealUtil.fileExport(baseParameterGroup, response, null);
                    return;
                }
                BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
                baseParameterGroupList = Arrays.asList(baseParameterGroup);
            } else {
                baseParameterGroupList = baseParameterGroupService.findAllGroupList();
            }
            if (baseParameterGroupList != null && baseParameterGroupList.size() > 0) {
                exportDealUtilSimple.fileExport(baseParameterGroupList, response, userId);
            } else {
                exportDealUtilSimple.errorFileExport(response);
            }
        } catch (Exception e) {
            log.error("导出参数模板失败：{}", e);
        }
    }

    @IgnoreAuthority
    @IgnoreUserToken
    @GetMapping("/exportModel1")
    @ApiOperation(value = "导出参数模板", notes = "只导出参数模板，不包含用户数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "参数组id", paramType = "query", dataType = "String"),
    })
    public void exportModel1(String groupId, HttpServletResponse response) {
        // http://localhost:8091/admin/baseParameterGroup/exportModel1?groupId=10100
        try {
            BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
            exportDealUtil.fileExport(baseParameterGroup, response, null);
        } catch (Exception e) {
            log.error("导出参数模板失败：{}", e);
        }
    }

    @IgnoreAuthority
    @IgnoreUserToken
    @GetMapping("/exportModel2")
    @ApiOperation(value = "导出参数模板", notes = "导出参数模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "分类id:  个人基本信息;  教务工作业绩;  科研工作业绩; ...", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "groupId", value = "参数组id", paramType = "query", dataType = "String"),
    })
    public void exportModel(String groupId, String typeId, HttpServletResponse response, HttpServletRequest request,
            @RequestHeader("Authorization") String authorization) {
        try {
            log.info("导出参数模板,typeId={},groupId={}", typeId, groupId);
            // http://localhost:8057/admin/baseParameterGroup/exportModel?groupId=10600
            String userId = adminService.findUserIdByToken(authorization);

            if (!StringUtils.isEmpty(typeId)) {
                List<BaseParameterGroup> groupListByCategory = baseParameterGroupService.findGroupListByCategory(typeId);
                if (groupListByCategory.size() > 0) {

                    String path = "data/" + userId;
                    File file = new File(path);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdirs();
                    }
                    String filePath = "data/" + userId + "/";

                    File zip = new File(filePath + "data.zip");// 压缩文件
                    List<String> fileNames = new ArrayList();// 用于存放生成的文件名称s
                    exportDealUtil.setResponseHeader(response, "data");
                    OutputStream out = response.getOutputStream();
                    for (BaseParameterGroup baseParameterGroup : groupListByCategory) {
                        exportDealUtil.writeWorkbook(userId, fileNames, baseParameterGroup, filePath);
                        List<BaseParameter> parameters = baseParameterGroup.getParameters();
                        if (parameters != null) {
                            for (BaseParameter parameter : parameters) {
                                List<BaseParameter> childParameters = parameter.getChildParameters();
                                if (childParameters != null && childParameters.size() > 0) {
                                    BaseParameterGroup baseParameterGroup1 = baseParameterGroupService.find(childParameters.get(0).getGroupId());
                                    exportDealUtil.writeWorkbook(userId, fileNames, baseParameterGroup1, filePath);
                                }
                            }
                        }
                    }
                    File srcfile[] = new File[fileNames.size()];
                    for (int i = 0, n1 = fileNames.size(); i < n1; i++) {
                        srcfile[i] = new File(fileNames.get(i));
                    }
                    exportDealUtil.ZipFiles(srcfile, zip);
                    FileInputStream inStream = new FileInputStream(zip);
                    byte[] buf = new byte[4096];
                    int readLength;
                    while (((readLength = inStream.read(buf)) != -1)) {
                        out.write(buf, 0, readLength);
                    }
                    inStream.close();
                    exportDealUtil.delFolder(path);
                }
            }
            else if (!StringUtils.isEmpty(groupId)) {
                BaseParameterGroup baseParameterGroup = baseParameterGroupService.find(groupId);
                exportDealUtil.fileExport(baseParameterGroup, response, userId);
            }
        } catch (Exception e) {
            log.error("导出参数模板失败：{}", e);
        }
    }

    /**
     * @return
     */
    @ApiOperation(value = "获取录入信息下拉选项及当前用户选项", notes = "获取录入信息下拉选项及当前用户选项")
    @PostMapping("/options")
    public Message findOptions(@RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            return Message.success(baseParameterGroupService.findOptions
                    (userId,false));
        } catch (Exception e) {
            log.error("获取录入信息下拉选项异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return
     */
    @ApiOperation(value = "通过分组id获取录入信息下拉选项及当前用户选项", notes =
            "通过分组id获取录入信息下拉选项及当前用户选项")
    @PostMapping("/findGroup")
    public Message findGroup(@RequestHeader("Authorization") String
            authorization, String groupId) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            List<String> groupIds = JSON.parseArray(groupId, String.class);
            return Message.success(baseParameterGroupService.findGroup(userId, groupIds));
        } catch (Exception e) {
            log.error("获取录入信息下拉选项异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }
}