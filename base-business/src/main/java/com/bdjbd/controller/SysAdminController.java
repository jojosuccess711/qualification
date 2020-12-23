/**
 * @filename:SysAdminController 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.*;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.common.util.FieldValidateUtil;
import com.bdjbd.common.util.MapUtil;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.dao.entity.SysAuthority;
import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.sys.SysAuthorityService;
import com.bdjbd.service.sys.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**   
 * 
 * @Description:  管理员接口层
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@RestController
@RequestMapping("/admin/account")
@Api(value = "管理员接口 - Controller", description = "管理员接口", tags = {"管理员接口"})
public class SysAdminController {

	@Autowired
	SysAdminService adminService;
	@Autowired
    SysRoleService roleService;
	@Autowired
	SysAuthorityService authorityService;

	@Autowired
	QaStandardRecordService qaStandardRecordService;

	@ApiOperation(value = "拥有权限列表接口", notes = "拥有权限列表接口")
	@GetMapping("/hasAuthorities")
	@IgnoreAuthority
	public Message<SysRole> hasAuthorities(@RequestHeader("Authorization") String token){
		IJWTInfo info = adminService.findByToken(token);
		List<SysAuthority> authorities = authorityService.getByAdminId(info.getId());
		Map<String, Object> result = new HashMap<>();
		List<QaStandardRecord> qaStandardRecordList = qaStandardRecordService.findList( Arrays.asList(Filter.eq("userId", info.getId())), null);
		if (qaStandardRecordList != null && qaStandardRecordList.size() > 0) {
			result.put("userHeader", qaStandardRecordList.get(0).getAttr5());
		}
		result.put("authorities", authorities);
		result.put("username", info.getName());
		result.put("date", DateUtil.formatYMD(null));

		return Message.success(result);
	}

	@ApiOperation(value = "角色列表接口", notes = "角色列表接口")
	@GetMapping("/roles")
	public Message<SysRole> roles(){
		return Message.success(roleService.findList(SysAuthority.Type.ADMIN));
	}

    @ApiOperation(value = "管理员信息接口", notes = "管理员信息接口")
    @GetMapping("/info")
    public Message<Map<String, Object>> info(String id){
		Map<String, Object> result = MapUtil.put("data", adminService.info(id).getData());
		result.put("roles", roleService.findList(SysAuthority.Type.ADMIN));
        return Message.success(result);
    }

    @ApiOperation(value = "管理员列表接口", notes = "管理员列表接口")
	@GetMapping("/list")
	@ResponseBody
	public Message<Page<SysAdmin>> list(Pageable pageable){
		List<Filter> filters = pageable.getFilterList();
		for(Iterator<Filter> iterator = filters.iterator(); iterator.hasNext();){
			Filter next = iterator.next();
			if("roles".equals(next.getProperty())){
				next.setValue("," + next.getValue() + ",");
				next.setOperator(Filter.Operator.like);
			}
		}
		pageable.getOrders().add(Order.desc("roles"));
		//Page<SysAdmin> page = adminService.findPage(pageable);
		Page<SysAdmin> page = adminService.findPageList(pageable);

		List<SysRole> roles = roleService.findList(SysAuthority.Type.ADMIN);

		for(Iterator<SysAdmin> iterator = page.getContent().iterator(); iterator.hasNext();){
			SysAdmin next = iterator.next();
			next.setRoleLists(new ArrayList<>());
			String[] split = next.getRoles().split(",");
			for(Iterator<SysRole> it = roles.iterator(); it.hasNext();){
				SysRole item = it.next();
				if(ArrayUtils.contains(split, item.getId())){
					next.getRoleLists().add(item);
				}
			}
		}
		return Message.success(page);
	}

    @ApiOperation(value = "角色重置接口", notes = "角色重置接口")
	@GetMapping("/resetRole")
	@ResponseBody
	public Message<?> resetRole(String id){
		return adminService.resetRole(id);
	}

    @ApiOperation(value = "管理员角色保存接口", notes = "管理员角色保存接口")
	@PostMapping("/role")
	@ResponseBody
	public Message<?> roleSave(String id, String... role){
		return adminService.roleSave(id, role);
	}


	@ApiOperation(value = "重置密码接口", notes = "重置密码接口")
	@GetMapping("/resetPassword")
	public Message<?> resetPassword(@RequestHeader("Authorization") String token,String userId){
		return adminService.resetPassword(userId);
	}

}