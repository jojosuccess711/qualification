/**
 * @filename:SysAdminServiceImpl 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.service.sys.impl;

import com.alibaba.fastjson.JSON;
import com.bdjbd.*;
import com.bdjbd.common.definition.SecurityConstants;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.common.util.MD5Util;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.common.vo.LoginUserInfo;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.dao.entity.SysRole;
import com.bdjbd.dao.mapper.SysAdminMapper;
import com.bdjbd.jwt.UserAuthUtil;
import com.bdjbd.service.common.RedisService;
import com.bdjbd.service.sys.*;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bdjbd.enums.LogEnum.*;

/**
 * @Description: 管理员 —— SERVICEIMPL
 * @Author: DBC
 * @CreateDate: 2019/12/03
 * @Version: V1.0
 */
@Slf4j
@Service
public class SysAdminServiceImpl extends BaseServiceImpl<SysAdmin, String> implements SysAdminService {

    @Autowired
    private SysAdminMapper mapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysAdminRoleService adminRoleService;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysAuthorityService authorityService;

    @Autowired
    private UserAuthUtil userAuthUtil;

    @Autowired
    public void setBaseDao(SysAdminMapper mapper) {
        super.setBaseDao(mapper);
    }

    /**
     * 通过用户名查找管理员信息
     *
     * @param username 用户名
     * @return 管理员信息
     */
    @Override
    public SysAdmin findByUsername(String username) {
        List<SysAdmin> list = super.findList(ListUtil.addToList(Filter.eq("username", username)), null);
        if (list != null && !list.isEmpty())
            return list.get(0);
        return null;
    }

    /**
     * 保存用户验证码，和randomStr绑定
     *
     * @param randomStr 客户端生成随机码
     * @param imageCode 图片验证码信息
     */
    @Override
    public void saveImageCode(String randomStr, String imageCode) {
        redisService.setForValue(SecurityConstants.DEFAULT_CODE_KEY + randomStr, imageCode, SecurityConstants.DEFAULT_IMAGE_EXPIRE);
    }

    /**
     * 获取验证码
     *
     * @param randomStr
     * @return
     */
    @Override
    public String getImageCode(String randomStr) {
        Object forValue = redisService.getForValue(SecurityConstants.DEFAULT_CODE_KEY + randomStr);
        if (forValue != null)
            return forValue.toString();
        return null;
    }

    /**
     * 创建管理员
     *
     * @param admin
     * @return
     */
    @Override
    public Message<?> createHandler(SysAdmin admin, String... role) {
        if (findByUsername(admin.getUsername()) == null) {
            admin.setId(UUIDUtils.generateShortUuid());
            admin.setCreateDate(new Date());
            admin.setModifyDate(admin.getCreateDate());
            admin.setIsLocked(false);
            admin.setPassword(MD5Util.encode(admin.getPassword()));
            Integer save = save(admin);

            if (role != null && role.length > 0)
                roleSave(admin.getId(), role);

            sysLogService.createLog(ADMIN_CREATE.ordinal(), admin.getUsername(), null, JSON.toJSONString(admin), ADMIN_CREATE.getKey(), null, true);
            if (save == 1)
                return Message.success("保存成功");
            return Message.error("已经保存！");
        }
        return Message.error("用户名已存在！");
    }

    /**
     * 编辑管理员
     *
     * @param admin
     * @return
     */
    @Override
    public Message<?> updateHandler(SysAdmin admin, String... role) {
        SysAdmin oldAdmin = find(admin.getId());
        if (oldAdmin == null) {
            return Message.error("用户不存在");
        }
        String before = JSON.toJSONString(oldAdmin);
        if (!StringUtils.isBlank(admin.getPassword())) {
            oldAdmin.setPassword(MD5Util.encode(admin.getPassword()));
        }
        if (admin.getHasEnabled() != null)
            oldAdmin.setHasEnabled(admin.getHasEnabled());
        else
            oldAdmin.setHasEnabled(false);
        if (!admin.getUsername().equals(oldAdmin.getUsername())) {
            SysAdmin oldUsernameAdmin = findByUsername(admin.getUsername());
            if (oldUsernameAdmin != null) {
                return Message.error("此用户名已存在，请更换");
            }
            oldAdmin.setUsername(admin.getUsername());
        }
        oldAdmin.setDepartment(admin.getDepartment());
        oldAdmin.setEmail(admin.getEmail());
        oldAdmin.setName(admin.getName());
        oldAdmin.setModifyDate(new Date());
        oldAdmin.setMobile(admin.getMobile());
        Integer update = update(oldAdmin);
        if (update == 1) {
            sysLogService.createLog(ADMIN_UPDATE.ordinal(), admin.getUsername(), before, JSON.toJSONString(oldAdmin), ADMIN_UPDATE.getKey(), null, true);
            return Message.success("更新成功");
        }
        roleSave(oldAdmin.getId(), role);
        return Message.error("已经更新！");
    }

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @Override
    public Message<?> deleteHandler(String id) {
        SysAdmin admin = find(id);
        if (admin == null) {
            return Message.error("账号已删除！");
        }
        if (admin.getUsername().equals("admin")) {
            return Message.error("内置账号，不允许删除！");
        }
        //验证使用(已使用不可删除)

        Integer delete = delete(id);
        if (delete == 1) {
            sysLogService.createLog(ADMIN_DELETE.ordinal(), admin.getUsername(), JSON.toJSONString(admin), null, ADMIN_DELETE.getKey(), null, true);

            //关联角色删除
            adminRoleService.deleteByAdminId(id);
            return Message.success("删除成功");
        }
        return Message.error("账号已删除！");
    }

    /**
     * 角色管理
     *
     * @param id
     * @return
     */
    @Override
    public Message<?> role(String id) {
        List<SysRole> hasRoles = roleService.findHasRole(id);
        List<SysRole> notHasRoles = roleService.findNotHasRole(id);
        Map<String, Object> result = new HashMap<>();
        result.put("hasRoles", hasRoles);
        result.put("notHasRoles", notHasRoles);
        return Message.success(result);
    }

    /**
     * 角色保存
     *
     * @param role
     * @return
     */
    @Override
    @Transactional
    public Message<?> roleSave(String id, String... role) {
        SysAdmin admin = find(id);
        if (admin == null)
            return Message.error("用户不存在");
        if (admin.getUsername().equals("admin"))
            return Message.error("顶级管理不可修改");
        if (role == null || role.length < 1)
            return Message.error("角色不可为空");

        admin.setRoles(ArrayUtils.toString(role).replace("{", ",").replace("}", ","));
        update(admin);

        adminRoleService.deleteByAdminId(id);
        List<SysRole> rolList = roleService.findList(role);
        adminRoleService.batchInsert(id, rolList);

        sysLogService.createLog(ADMIN_ROLE.ordinal(), admin.getUsername(), null, JSON.toJSONString(rolList), ADMIN_ROLE.getKey(), null, true);

        authorityService.clearCache();
        return Message.success("保存成功");
    }

    @Override
    public Message<?> resetRole(String id) {
        return roleSave(id, "0");
    }

    @Override
    public Message<?> info(String id) {
        SysAdmin admin = find(id);
        if (admin == null)
            return Message.error("用户不存在");
        List<SysRole> hasRoles = roleService.findHasRole(id);
        admin.setRoleLists(hasRoles);
        return Message.success(admin);
    }

    @Override
    public Message<LoginUserInfo> login(String username, String password, String code, String randomStr) {
        /*if(StringUtils.isBlank(code))
            return Message.error("验证码不存在");
        String imageCode = getImageCode(randomStr);
        if(!code.equalsIgnoreCase(imageCode))
            return Message.error("验证码错误");*/

        SysAdmin admin = findByUsername(username);
        if (admin == null)
            return Message.error("用户不存在");
        if (!admin.getPassword().equals(MD5Util.encode(password)))
            return Message.error("密码错误");

        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setId(admin.getId());
        userInfo.setUsername(admin.getUsername());
        userInfo.setName(admin.getName());
        return Message.success(userInfo);
    }

    @Override
    public Message<SysAdmin> defaultCreate(String mobile, String name, String password) {
        SysAdmin admin = new SysAdmin();
        admin.setId(UUIDUtils.generateShortUuid());
        admin.setCreateDate(new Date());
        admin.setModifyDate(admin.getCreateDate());
        admin.setHasEnabled(true);
        admin.setMobile(mobile);
        admin.setName(name);
        admin.setUsername(mobile);
        admin.setPassword(MD5Util.encode(password));
        admin.setIsLocked(false);
        admin.setRoles(",0,");
        save(admin);

        //默认角色
        List<SysRole> rolList = roleService.findList("0");
        adminRoleService.batchInsert(admin.getId(), rolList);
        return Message.success(admin);
    }

    @Override
    public Message<SysAdmin> defaultCreate1(String mobile, String name, String department) {
        SysAdmin admin = new SysAdmin();
        admin.setId(UUIDUtils.generateShortUuid());
        admin.setCreateDate(new Date());
        admin.setModifyDate(new Date());
        admin.setDepartment(department);
        admin.setHasEnabled(true);
        admin.setMobile(mobile);
        admin.setName(name);
        admin.setUsername(mobile);
        admin.setPassword(MD5Util.encode("000000"));
        admin.setIsLocked(false);
        admin.setRoles(",1000,");
        save(admin);

        //默认角色
        List<SysRole> rolList = roleService.findList("1000");
        adminRoleService.batchInsert(admin.getId(), rolList);
        return Message.success(admin);
    }

    @Override
    public String findUsernameByToken(String token) {
        try {
            return userAuthUtil.getInfoFromToken(token).getUniqueName();
        } catch (Exception e) {
            log.error("从token中获取用户信息异常", e);
        }
        return null;
    }

    @Override
    public String findUserIdByToken(String token) {
        try {
            return userAuthUtil.getInfoFromToken(token).getId();
        } catch (Exception e) {
            log.error("从token中获取用户信息异常", e);
        }
        return null;
    }

    @Override
    public IJWTInfo findByToken(String token) {
        try {
            return userAuthUtil.getInfoFromToken(token);
        } catch (Exception e) {
            log.error("从token中获取用户信息异常", e);
        }
        return null;
    }

    @Override
    public Message<?> resetPassword(String userId) {
        SysAdmin admin = find(userId);
        if (admin == null)
            return Message.error("账号不存在");
        String before = JSON.toJSONString(admin);
        admin.setPassword(MD5Util.encode("000000"));
        update(admin);

        sysLogService.createLog(ADMIN_UPDATE.ordinal(), admin.getUsername(), before, JSON.toJSONString(admin), "重置密码", null, true);
        return Message.success("密码重置成功");
    }

    @Override
    public Message<?> updatePassword(String id, String oldPassword, String newPassword) {
        SysAdmin admin = find(id);
        if (admin == null)
            return Message.error("账号不存在");

//        if (oldPassword.equals(newPassword))
//            return Message.success("密码修改成功");

        if (MD5Util.encode(oldPassword).equals(admin.getPassword())) {
            String before = JSON.toJSONString(admin);

            admin.setPassword(MD5Util.encode(newPassword));
            update(admin);

            sysLogService.createLog(ADMIN_UPDATE.ordinal(), admin.getUsername(), before, JSON.toJSONString(admin), "修改密码", null, true);
            return Message.success("密码修改成功");
        } else {
            return Message.error("原密码错误");
        }
    }

    @Override
    public Page<SysAdmin> findPageList(Pageable pageable) {
        Integer offset = pageable.getPageNum() * pageable.getPageSize() - pageable.getPageSize();
        Integer limit = pageable.getPageSize();
        String attr0 = null;
        String mobile = null;
        String name = null;
        String roles = null;
        List<Filter> filterList = pageable.getFilterList();
        if (!ObjectUtils.isEmpty(filterList)) {
            for (Filter filter : filterList) {
                if (org.springframework.util.StringUtils.isEmpty(filter.getValue())) {
                    continue;
                }
                switch (filter.getProperty()) {
                    case "roles":
                        roles = (String) filter.getValue();
                        break;
                    case "mobile":
                        mobile = (String) filter.getValue();
                        break;
                    case "name":
                        name = (String) filter.getValue();
                        break;
                    case "attr":
                        attr0 = (String) filter.getValue();
                        break;
                }
            }
        }

        List<SysAdmin> list = mapper.selectPage(offset, limit, attr0, mobile, name, roles);

        Page<SysAdmin> page = new Page<>(list, mapper.countPage(attr0, mobile, name, roles), pageable);

        return page;
    }

    @Override
    public PageInfo<SysAdmin> findExpert(String name, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(name))
            System.out.println(name);
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(SysAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isBlank(name))
            criteria.andEqualTo("roles", ",1000,");
        else
            criteria.andEqualTo("roles", ",1000,").andLike("name","%" + name + "%");

        List<SysAdmin> sysAdmins = mapper.selectByExample(example);
        PageInfo<SysAdmin> pageInfo = new PageInfo<>(sysAdmins);
        return pageInfo;
    }

    @Override
    public Integer delExpert(String id) {
        int i = mapper.deleteByPrimaryKey(id);
        return i;
    }

    @Override
    public Integer updateExpert(SysAdmin admin) {
        if (StringUtils.isBlank(admin.getId()))
            return 0;

        return mapper.updateByPrimaryKeySelective(admin);
    }
}