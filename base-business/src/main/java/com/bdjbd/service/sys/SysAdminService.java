/**
 * @filename:SysAdminService 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd.
 * All right reserved.
 */
package com.bdjbd.service.sys;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.common.vo.LoginUserInfo;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.web.service.BaseService;
import com.github.pagehelper.PageInfo;

/**
 *
 * @Description: 管理员 —— SERVICE
 * @Author: DBC
 * @CreateDate: 2019/12/03
 * @Version: V1.0
 *
 */
public interface SysAdminService extends BaseService<SysAdmin, String> {

    /**
     * 通过用户名查找管理员信息
     * @param username 用户名
     * @return 管理员信息
     */
    SysAdmin findByUsername(String username);

    /**
     * 保存用户验证码，和randomStr绑定
     * @param randomStr 客户端生成随机码
     * @param imageCode 图片验证码信息
     */
    void saveImageCode(String randomStr, String imageCode);

    /**
     * 获取验证码
     * @param randomStr
     * @return
     */
    String getImageCode(String randomStr);

    /**
     * 创建管理员
     * @param admin
     * @return
     */
    Message<?> createHandler(SysAdmin admin, String... role);

    /**
     * 编辑管理员
     * @param admin
     * @return
     */
    Message<?> updateHandler(SysAdmin admin, String... role);

    /**
     * 删除管理员
     * @param id
     * @return
     */
    Message<?> deleteHandler(String id);

    /**
     * 角色管理
     * @param id
     * @return
     */
    Message<?> role(String id);

    /**
     * 角色保存
     * @param role
     * @return
     */
    Message<?> roleSave(String id, String... role);

    /**
     * 重置角色
     * @param id
     * @return
     */
    Message<?> resetRole(String id);

    /**
     * 信息
     * @param id
     * @return
     */
    Message<?> info(String id);

    /**
     * 登录
     * @param username
     * @param password
     * @param code
     * @return
     */
    Message<LoginUserInfo> login(String username, String password, String code, String randomStr);

    /**
     * 默认创建
     * @param mobile
     * @param password
     * @return
     */
    Message<SysAdmin> defaultCreate(String mobile, String name, String password);


    Message<SysAdmin> defaultCreate1(String mobile, String name, String department);

    /**
     * 获取用户名
     * @param token
     * @return
     */
    String findUsernameByToken(String token);

    /**
     * 获取用户id
     * @param token
     * @return
     */
    String findUserIdByToken(String token);

    /**
     * 获取用户
     * @param token
     * @return
     */
    IJWTInfo findByToken(String token);

    /**
     * 重置密码
     * @param admin
     * @param password
     */
    Message<?> resetPassword(String userId);

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Message<?> updatePassword(String id, String oldPassword, String newPassword);

    Page<SysAdmin> findPageList(Pageable pageable);

    PageInfo<SysAdmin> findExpert(String name, Integer pageNum, Integer pageSize);

    Integer delExpert(String id);

    Integer updateExpert(SysAdmin admin);
}