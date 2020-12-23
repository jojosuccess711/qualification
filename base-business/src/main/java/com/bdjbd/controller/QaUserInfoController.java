/**
 * @filename:QaUserInfoController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaApplyRecord;
import com.bdjbd.dao.entity.QaUserInfo;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.service.QaApplyRecordService;
import com.bdjbd.service.QaUserInfoService;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.sys.SysAuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**   
 * 
 * @Description:  用户信息接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@RestController
@Api(value="用户信息 - Controller", description = "用户信息", tags = {"用户信息"})
@RequestMapping("/admin/qaUserInfo")
public class QaUserInfoController {

	@Autowired
	public QaUserInfoService qaUserInfoService;
	@Autowired
	public QaApplyRecordService qaApplyRecordService;
	@Autowired
	SysAuthorityService authorityService;

	@Autowired
	SysAdminService adminService;

	/**
	 * @explain 信息录入阶段  <swagger GET请求>
	 * @return  Message<QaUserInfo>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/infoPart")
	@ApiOperation(value = "信息录入阶段接口", notes = "录入阶段接口[qaUserInfo]，作者：DBC")
	public Message<Integer> infoPart(@RequestHeader("Authorization") String token){
		try {
			String userId = adminService.findUserIdByToken(token);
			QaUserInfo userInfo = qaUserInfoService.find(userId);
			Integer part;
			if(StringUtils.isAnyBlank(userInfo.getName(), userInfo.getAcademicTitle())){
				part = 0;
			}else{
				QaApplyRecord byUserAndAcademic = qaApplyRecordService.findByUserAndAcademic(userId, null);
				if(byUserAndAcademic == null)
					part = 1;
				else
					part = 2;
			}
			if(part == 2){
				List<SysAuthority> authorities = authorityService.getByAdminId(userId);
				for(Iterator<SysAuthority> iterator = authorities.iterator(); iterator.hasNext();){
					if(iterator.next().getAuthority().equals("ROLE_DATA_CENTER")){
						part = 3;
					}
				}
			}

			return Message.success("", part);
		} catch (Exception e) {
			log.error("获取信息录入阶段异常：", e);
		}
		return Message.error("获取信息录入阶段错误");
	}

	/**
	 * @explain 查询用户信息对象  <swagger GET请求>
	 * @return  Message<QaUserInfo>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/info")
	@ApiOperation(value = "获取用户信息信息", notes = "获取用户信息信息[qaUserInfo]，作者：DBC")
	public Message<QaUserInfo> info(@RequestHeader("Authorization") String token){
		try {
			String userId = adminService.findUserIdByToken(token);
			QaUserInfo qaUserInfo = qaUserInfoService.find(userId);
			if (qaUserInfo != null) {
				return Message.success(qaUserInfo);
			} else {
				log.error("获取用户信息失败ID：");
			}
		} catch (Exception e) {
			log.error("获取用户信息执行异常：", e);
		}
		return Message.error("信息不存在");
	}

	/**
	 * @explain 查询用户信息对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaUserInfo>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取用户信息信息", notes = "获取用户信息信息[qaUserInfo]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "用户信息id", paramType = "path", dataType = "String")
	public Message<QaUserInfo> find(@PathVariable("id") String id){
		try {
			QaUserInfo qaUserInfo = qaUserInfoService.find(id);
			if (qaUserInfo != null) {
				return Message.success(qaUserInfo);
			} else {
				log.error("获取用户信息失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取用户信息执行异常：", e);
		}
    	return Message.error("信息不存在");
	}

	/**
	 * @explain 保存/提交用户信息
	 * @param   qaUserInfo
	 * @return  Message<QaUserInfo>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/saveOrUpdate")
	@ApiOperation(value = "保存(编辑)用户信息", notes = "保存用户信息[qaUserInfo], 作者：DBC")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "姓名", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "sex", value = "性别 0：女  1：男", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "idCard", value = "身份证号码", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "personnelCategory", value = "人员类别 : 军人干部, 文职人员", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "birth", value = "出生日期", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "academicTitle", value = "申请职称 1：教授  2：副教授", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "enlistmentTime", value = "入伍时间（军人干部）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "enterEmploymentTime", value = "参加工作时间（文职人员）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "politicalAffiliation", value = "政治面貌党员", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "caucusTime", value = "党团时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "lastEducation", value = "最高学历：大学硕士研究生博士研究生", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "highestGetTime", value = "最高学历时间/最高学位授予时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "highestDegree", value = "最高学位: 学士硕士博士", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "highestDegreeTime", value = "学位时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "major", value = "专业（最高学历）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "lastSchoolMajor", value = "最后毕业学校", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "graduateTime", value = "毕业时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "schoolCategory", value = "院校类别", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "technologyTitle", value = "现任专业技术职务", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "appointTime", value = "现专业技术职务时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "technologyLevel", value = "现技术等级", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "technologyLevelTime", value = "现技术等级时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "militaryRank", value = "现军衔/级别", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "militaryRankTime", value = "现军衔/级别时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "technologyCategory", value = "现技职类别", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "technologyCategoryTime", value = "现技职类别时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "officialRank", value = "现部级别", paramType="query", dataType = "String"),
	})
	public Message<QaUserInfo> add(@RequestHeader("Authorization") String token,QaUserInfo qaUserInfo){
		try {
			String userId = adminService.findUserIdByToken(token);

			if(StringUtils.isAnyBlank(qaUserInfo.getName())){
				return Message.error("请填写姓名");
			}
			if(StringUtils.isAnyBlank(qaUserInfo.getAcademicTitle())){
				return Message.error("请选择评审职称");
			}
			if(qaUserInfo.getSex() == null){
				return Message.error("请选择性别");
			}
			if (StringUtils.isAnyBlank(qaUserInfo.getIdCard())) {
				return Message.error("请填写身份证号");
			}

			QaUserInfo oldQaUserInfo = qaUserInfoService.find(userId);
			oldQaUserInfo.setModifyDate(new Date());
			oldQaUserInfo.setName(qaUserInfo.getName());
			oldQaUserInfo.setSex(qaUserInfo.getSex());
			oldQaUserInfo.setIdCard(qaUserInfo.getIdCard());
			oldQaUserInfo.setPersonnelCategory(qaUserInfo.getPersonnelCategory());
			oldQaUserInfo.setBirth(qaUserInfo.getBirth());
			oldQaUserInfo.setAcademicTitle(qaUserInfo.getAcademicTitle());
			oldQaUserInfo.setEnlistmentTime(qaUserInfo.getEnlistmentTime());
			oldQaUserInfo.setEnterEmploymentTime(qaUserInfo.getEnterEmploymentTime());
			oldQaUserInfo.setPoliticalAffiliation(qaUserInfo.getPoliticalAffiliation());
			oldQaUserInfo.setCaucusTime(qaUserInfo.getCaucusTime());
			oldQaUserInfo.setLastEducation(qaUserInfo.getLastEducation());
			oldQaUserInfo.setHighestGetTime(qaUserInfo.getHighestGetTime());
			oldQaUserInfo.setHighestDegree(qaUserInfo.getHighestDegree());
			oldQaUserInfo.setHighestDegreeTime(qaUserInfo.getHighestDegreeTime());
			oldQaUserInfo.setMajor(qaUserInfo.getMajor());
			oldQaUserInfo.setLastSchoolMajor(qaUserInfo.getLastSchoolMajor());
			oldQaUserInfo.setGraduateTime(qaUserInfo.getGraduateTime());
			oldQaUserInfo.setSchoolCategory(qaUserInfo.getSchoolCategory());
			oldQaUserInfo.setTechnologyTitle(qaUserInfo.getTechnologyTitle());
			oldQaUserInfo.setAppointTime(qaUserInfo.getAppointTime());
			oldQaUserInfo.setTechnologyLevel(qaUserInfo.getTechnologyLevel());
			oldQaUserInfo.setTechnologyLevelTime(qaUserInfo.getTechnologyLevelTime());
			oldQaUserInfo.setMilitaryRank(qaUserInfo.getMilitaryRank());
			oldQaUserInfo.setMilitaryRankTime(qaUserInfo.getMilitaryRankTime());
			oldQaUserInfo.setTechnologyCategory(qaUserInfo.getTechnologyCategory());
			oldQaUserInfo.setTechnologyCategoryTime(qaUserInfo.getTechnologyCategoryTime());
			oldQaUserInfo.setOfficialRank(qaUserInfo.getOfficialRank());

			int rg = qaUserInfoService.update(oldQaUserInfo);
			if (rg > 0) {
				return Message.success(oldQaUserInfo);
			} else {
				log.error("添加用户信息执行失败：" + qaUserInfo.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加用户信息执行异常：{}", e);
		}
		return Message.error("操作异常，请稍后重试");
	}

	/**
	 * @explain 删除用户信息对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除用户信息", notes = "删除用户信息, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "用户信息id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaUserInfoService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除用户信息失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除用户信息执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改用户信息对象
	 * @param   qaUserInfo
	 * @return  Message<QaUserInfo>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息[qaUserInfo], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "姓名", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "mobile", value = "手机号码", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "sex", value = "性别 0：女  1：男", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "birth", value = "出生日期", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "academicTitle", value = "申请职称 1：教授  2：副教授", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "breakRule", value = "是否破格 0：否  1：是", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "breakRuleAnnex", value = "破格附件地址", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "enterEmploymentTime", value = "参加工作时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "department", value = "工作部门", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "technologyTitle", value = "现任专业技术职务", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "appointTime", value = "聘任时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "appointAnnex", value = "聘任附件地址", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "getQualificationsTime", value = "获得资格时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "getQualificationsAnnex", value = "获得资格附件", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "major", value = "专业", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "lastEducation", value = "最后学历", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "lastSchoolMajor", value = "最后毕业学校、专业", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "graduateTime", value = "毕业时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "highestDegree", value = "最高学位", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "highestSchoolMajor", value = "最高学位授予学校、专业", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "highestGetTime", value = "最高学位授予时间", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaUserInfo> update(@RequestHeader("Authorization") String token, QaUserInfo qaUserInfo){
		try {
			String userId = adminService.findUserIdByToken(token);
			qaUserInfo.setId(userId);
			qaUserInfo.setModifyDate(new Date());
			int reg = qaUserInfoService.update(qaUserInfo);
			if (reg > 0) {
				return Message.success(qaUserInfo);
			} else {
				log.error("修改用户信息失败ID：" + qaUserInfo.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改用户信息执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配用户信息
	 * @return  Page<QaUserInfo>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询用户信息", notes = "条件查询[qaUserInfo], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaUserInfo>> findPage(Pageable pageable){
		try {
    		Page<QaUserInfo> page = qaUserInfoService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取用户信息执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}

}