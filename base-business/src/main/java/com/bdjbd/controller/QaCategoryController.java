/**
 * @filename:QaCategoryController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Filter;
import com.bdjbd.common.util.ClassUtil;
import com.bdjbd.service.sys.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.service.QaCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 *
 * @Description: 评审分类接口层
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Api(value = "评审分类 - Controller", description = "评审分类", tags = {"评审分类"})
@RestController
@RequestMapping("/admin/qaCategory")
public class QaCategoryController {

    @Autowired
    public QaCategoryService qaCategoryService;
    @Autowired
    SysAdminService adminService;

    /**
     * @return Message<QaCategory>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取评审分类信息", notes = "获取评审分类信息[qaCategory]，作者：DBC")
    @ApiImplicitParam(name = "id", value = "评审分类id", paramType = "path", dataType = "String")
    public Message<QaCategory> find(@PathVariable("id") String id) {
        try {
            QaCategory qaCategory = qaCategoryService.find(id);
            if (qaCategory != null) {
                return Message.success(qaCategory);
            }
            else {
                log.error("获取评审分类失败ID：" + id);
            }
        } catch (Exception e) {
            log.error("获取评审分类执行异常：", e);
        }
        return Message.error("信息不存在");
    }

    /**
     * @return Message<QaCategory>
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加评审分类", notes = "添加评审分类[qaCategory], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "职业领域或岗位名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型   0：职业领域   1：岗位类型   2：职称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "icon", value = "图标", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orders", value = "排序", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parent", value = "父级", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "applyNum", value = "岗位职称申请累计人数（有新增申请时变更申请总数）", paramType = "query", dataType = "String"),
    })
    public Message<QaCategory> add(QaCategory qaCategory) {
        try {
            int rg = qaCategoryService.save(qaCategory);
            if (rg > 0) {
                return Message.success(qaCategory);
            }
            else {
                log.error("添加评审分类执行失败：" + qaCategory.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加评审分类执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<Object>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除评审分类", notes = "删除评审分类, 作者：DBC")
    @ApiImplicitParam(name = "id", value = "评审分类id", paramType = "query", required = true, dataType = "String")
    public Message<Object> delete(String id) {
        try {
            int reg = qaCategoryService.delete(id);
            if (reg > 0) {
                return Message.success(id);
            }
            else {
                log.error("删除评审分类失败ID：" + id);
                return Message.error("操作失败，请稍后刷新重试");
            }
        } catch (Exception e) {
            log.error("删除评审分类执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaCategory>
     */
    @ApiOperation(value = "修改评审分类", notes = "修改评审分类[qaCategory], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "职业领域或岗位名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类型   0：职业领域   1：岗位类型   2：职称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "icon", value = "图标", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "orders", value = "排序", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "parent", value = "父级", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "applyNum", value = "岗位职称申请累计人数（有新增申请时变更申请总数）", paramType = "query", dataType = "String"),
    })
    @PostMapping("/update")
    public Message<QaCategory> update(QaCategory qaCategory) {
        try {
            int reg = qaCategoryService.update(qaCategory);
            if (reg > 0) {
                return Message.success(qaCategory);
            }
            else {
                log.error("修改评审分类失败ID：" + qaCategory.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("修改评审分类执行异常：" + e.getMessage());
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<QaCategory>
     */
    @ApiOperation(value = "分页条件查询评审分类", notes = "条件查询[qaCategory], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5")
    })
    @PostMapping("/page")
    public Message<Page<QaCategory>> findPage(Pageable pageable) {
        try {
            Page<QaCategory> page = qaCategoryService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取评审分类执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "五大类人员占比统计", notes = "五大类人员占比统计")
    @PostMapping("/findListCategory")
    public Message<?> findListCategory() {
        try {
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.isNull("parent"));
            filters.add(Filter.eq("type", 0));
            List<QaCategory> list = qaCategoryService.findList(filters, null);
            return Message.success(list);
        } catch (Exception e) {
            log.error("五大类人员占比统计：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "17小类人员占比统计", notes = "17小类人员占比统计")
    @PostMapping("/findListChildCategory")
    public Message<?> findListChildCategory() {
        try {
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.isNotNull("parent"));
            filters.add(Filter.eq("type", 1));
            List<QaCategory> list = qaCategoryService.findList(filters, null);
            return Message.success(list);
        } catch (Exception e) {
            log.error("17小类人员占比统计：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "五大类人员职称占比统计", notes = "五大类人员职称占比统计")
    @PostMapping("/findListJobTitle")
    public Message<?> findListJobTitle() {
        try {
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.eq("type", 2));
            List<QaCategory> list = qaCategoryService.findList(filters, null);
            Map<String, Map<String, Object>> map = new LinkedHashMap<>();
            for (QaCategory qaCategory : list) {
                String name = qaCategory.getAnotherName();
                if (map.get(name) != null) {
                    Map<String, Object> map1 = map.get(name);
                    map1.put("applyNum", (Integer) map1.get("applyNum") + qaCategory.getApplyNum());
                    map1.put("firstApproveNum", (Integer) map1.get("firstApproveNum") + qaCategory.getFirstApproveNum());
                    map1.put("approveNum", (Integer) map1.get("approveNum") + qaCategory.getApproveNum());
                    map.put(name, map1);
                }
                else {
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("name", name);
                    map1.put("applyNum", qaCategory.getApplyNum());
                    map1.put("firstApproveNum", qaCategory.getFirstApproveNum());
                    map1.put("approveNum", qaCategory.getApproveNum());
                    map.put(name, map1);
                }
            }
            Iterator<Map.Entry<String, Map<String, Object>>> iterator = map.entrySet().iterator();
            List<Map<String, Object>> list1 = new ArrayList<>();
            while (iterator.hasNext()) {
                list1.add(iterator.next().getValue());
            }
            return Message.success(list1);
        } catch (Exception e) {
            log.error("五大类人员职称占比统计：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "各类详细信息", notes = "各类详细信息")
    @PostMapping("/findListDetails")
    public Message<?> findListDetails() {
        try {
            List<QaCategory> listDetails = qaCategoryService.findListDetails(false);
            return Message.success(listDetails);
        } catch (Exception e) {
            log.error("各类详细信息：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "各类职称评审业绩条件", notes = "各类职称评审业绩条件")
    @PostMapping("/findCategoryList")
    public Message<?> findCategoryList() {
        try {
            List<Filter> filters = new ArrayList<>();
            filters.add(Filter.eq("type", 0));
            List<QaCategory> list = qaCategoryService.findList(filters, null);
            return Message.success(list);
        } catch (Exception e) {
            log.error("各类职称评审业绩条件：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "职称评审业绩条件详情", notes = "职称评审业绩条件详情")
    @PostMapping("/findById")
    public Message<?> findById(String id) {
        try {
            //            QaCategory detailsById = qaCategoryService.findDetailsById(id);
            List<QaCategory> listDetails = qaCategoryService.findListDetails(true);
            for (QaCategory listDetail : listDetails) {
                if (listDetail.getId().equals(id)) {
                    return Message.success(listDetail);
                }
            }
            return Message.error("未查到");
        } catch (Exception e) {
            log.error("职称评审业绩条件详情：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "获取职称分类菜单", notes = "获取职称分类菜单")
    @PostMapping("/findQaCategories")
    public Message<?> findQaCategories() {
        try {
            List<Map> list = new ArrayList<>();
            List<QaCategory> qaCategories = qaCategoryService.findListDetails(false);
            for (QaCategory qaCategory : qaCategories) {
                Map map = new HashMap();
                map.put("id", qaCategory.getId());
                map.put("name", qaCategory.getName());
                map.put("parent", qaCategory.getParent());
                map.put("anotherName", qaCategory.getAnotherName());
                List<QaCategory> child = qaCategory.getChildList();
                List<Map<?, ?>> list1 = ClassUtil.listBeanToMap(true, child, "id", "name", "parent", "anotherName", "childList");
                for (Map childMap : list1) {
                    List<QaCategory> childQaCategory = (List<QaCategory>) childMap.get("childList");
                    List<Map<?, ?>> cMap = ClassUtil.listBeanToMap(false, childQaCategory, "id", "name", "parent", "anotherName");
                    childMap.put("childList", cMap);
                }
                map.put("childList", list1);
                list.add(map);
            }
            return Message.success(list);
        } catch (Exception e) {
            log.error("职称评审业绩条件详情：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "通过职称获取岗位名称", notes = "通过职称获取岗位名称")
    @PostMapping("/findCategoryType")
    public Message<?> findCategoryType(String categoryTitle) {
        try {

            return qaCategoryService.findCategoryTypeByTitle(categoryTitle);
        } catch (Exception e) {
            log.error("职称评审业绩条件详情：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "职称评审各类人员数量饼图数据", notes = "职称评审各类人员数量饼图数据")
    @PostMapping("/findCategorys")
    public Message<?> findCategorys(/*@RequestHeader("Authorization") String
            token*/) {
        try {
//            String userId = adminService.findUserIdByToken(token);
            return qaCategoryService.findCategorys();
        } catch (Exception e) {
            log.error("职称评审各类人员数量：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }
    @ApiOperation(value = "五大类人员占比统计（新）", notes = "五大类人员占比统计（新）")
    @PostMapping("/findCategorysRate")
    public Message<?> findCategorysRate() {
        try {
            return qaCategoryService.findCategorysRate();
        } catch (Exception e) {
            log.error("五大类人员占比统计：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "五大类人员职称占比统计（新）", notes = "五大类人员职称占比统计（新）")
    @PostMapping("/findCategoryTitlesRate")
    public Message<?> findCategoryTitlesRate() {
        try {
            return qaCategoryService.findCategoryTitlesRate();
        } catch (Exception e) {
            log.error("五大类人员职称占比统计：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "17小类人员占比统计（新）", notes = "17小类人员占比统计（新）")
    @PostMapping("/findCategoryTypesRate")
    public Message<?> findCategoryTypesRate() {
        try {
            return qaCategoryService.findCategoryTypesRate();
        } catch (Exception e) {
            log.error("17小类人员占比统计：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "拟参评人员占比", notes = "拟参评人员占比")
    @PostMapping("/findRecordsRate")
    public Message<?> findRecordsRate() {
        try {
            return qaCategoryService.findRecordsRate();
        } catch (Exception e) {
            log.error("模拟参评人员占比：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "职称名称列表", notes = "职称名称列表")
    @PostMapping("/findTitles")
    public Message findTitles() {
        return qaCategoryService.findTitles();
    }

    @ApiOperation(value = "根据职称名称联动拟参评职称等级", notes = "根据职称名称联动拟参评职称等级")
    @PostMapping("/findAcademicType")
    public Message findAcademicType(String titleName) {
        return qaCategoryService.findAcademicType(titleName);
    }
    @ApiOperation(value = "职称岗位列表", notes = "职称岗位列表")
    @PostMapping("/findCategoryTypes")
    public Message findCategoryTypes() {
        return qaCategoryService.findCategoryTypes();
    }
}