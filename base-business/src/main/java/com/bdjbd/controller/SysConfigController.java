
package com.bdjbd.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.SysConfig;
import com.bdjbd.dao.entity.SysConfigAttributes;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.sys.SysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/admin/sysConfig")
@Api(value = "系统配置接口 - Controller", tags = {"系统配置接口"})
public class SysConfigController {

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    SysAdminService adminService;

    @ApiOperation(value = "获取配置列表", notes = "获取配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "pageNum", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", paramType = "query", dataType = "String"),
    })
    @GetMapping("/list")
    public Message<?> list(@RequestHeader("Authorization") String token, Integer pageNum, Integer pageSize) {
        try {
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 20;
            }
            Pageable pageable = new Pageable(pageNum, pageSize);
            Page<SysConfig> page = sysConfigService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取配置列表失败，{}", e);
        }
        return Message.error("");
    }

    @ApiOperation(value = "获取配置", notes = "获取配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query", dataType = "String"),
    })
    @GetMapping("/findById")
    public Message<?> findById(String id) {
        try {
            SysConfig sysConfig = sysConfigService.findAllHasAttributes(id);
            return Message.success(sysConfig);
        } catch (Exception e) {
            log.error("获取配置失败，{}", e);
        }
        return Message.error("");
    }

    @ApiOperation(value = "添加config", notes = "添加config")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "params", value = "参数，json字符串", paramType = "query", dataType = "String",
                    example = "{\n" +
                            "  \"id\": \"BB\",\n" +
                            "  \"name\": \"bbb\",\n" +
                            "  \"status\": \"false\",\n" +
                            "  \"value\": \"123\",\n" +
                            "  \"des\": \"\",\n" +
                            "  \"attrs\": [\n" +
                            "    {\n" +
                            "      \"name\": \"haha\",\n" +
                            "      \"value\": \"12333\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"name\": \"hehe\",\n" +
                            "      \"value\": \"000\"\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}"),
    })
    @PostMapping("/add")
    public Message<?> add(String params) {
        try {
            SysConfig sysConfig = getSysConfig(params);
            // save
            sysConfigService.saveConfig(sysConfig);
            return Message.success();
        } catch (Exception e) {
            log.error("添加config 失败，{}", e);
        }
        return Message.error("");
    }

    @ApiOperation(value = "修改config", notes = "修改config")
    @PostMapping("/update")
    public Message<?> update(String params) {
        try {
            SysConfig sysConfig = getSysConfig(params);
            // update
            sysConfigService.updateConfig(sysConfig);
            return Message.success();
        } catch (Exception e) {
            log.error("修改config 失败，{}", e);
        }
        return Message.error("");
    }

    private SysConfig getSysConfig(String params) {
        JSONObject jsonObject = JSONObject.parseObject(params);
        SysConfig sysConfig = new SysConfig();
        sysConfig.setId(jsonObject.getString("id"));
        sysConfig.setName(jsonObject.getString("name"));
        sysConfig.setValue(jsonObject.getString("value"));
        sysConfig.setStatus(jsonObject.getBoolean("status"));
        sysConfig.setDes(jsonObject.getString("des"));

        JSONArray attrs = jsonObject.getJSONArray("attrs");
        if (attrs != null && attrs.size() > 0) {
            List<SysConfigAttributes> attributes = new ArrayList<>();
            for (Object attr : attrs) {
                JSONObject object = (JSONObject) attr;
                SysConfigAttributes attribute = new SysConfigAttributes();
                attribute.setConfigId(sysConfig.getId());
                attribute.setName(object.getString("name"));
                attribute.setValue(object.getString("value"));
                attributes.add(attribute);
            }
            sysConfig.setAttributes(attributes);
        }
        return sysConfig;
    }

    /**
     * 信息录入时间范围  INPUT_OPEN
     * 申请参评时间范围  COMMIT_ACADEMIC_CATEGORY
     * 专家评审时间范围  ASSESSORS_OPEN
     * @param
     * @return
     */
    @ApiOperation(value = "信息录入,申请参评,专家评审时间范围查询", notes = "信息录入,申请参评,专家评审时间范围")
    @GetMapping("/findOperatorTime")
    public Message<?> findOperatorTime() {
        try {
            List<Map<String,Object>> resList = new ArrayList<>();
            List<String> list = Arrays.asList("INPUT_OPEN", "COMMIT_ACADEMIC_CATEGORY", "ASSESSORS_OPEN");
            for (String string : list) {
                SysConfig sysConfig = sysConfigService.findAllHasAttributes(string);
                boolean b = sysConfigService.checkStartDateEndDate(sysConfig);
                Map<String, Object> map = new HashMap<>();
                map.put("key", string);
                map.put("value", sysConfig);
                map.put("status", b);
                resList.add(map);
            }
            return Message.success(resList);
        } catch (Exception e) {
            log.error("信息录入,申请参评,专家评审时间范围查询 失败，{}", e);
        }
        return Message.error("");
    }
}