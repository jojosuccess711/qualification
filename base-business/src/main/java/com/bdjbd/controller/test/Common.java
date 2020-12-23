package com.bdjbd.controller.test;

import com.bdjbd.service.*;
import com.bdjbd.service.auth.AuthService;
import com.bdjbd.service.common.CacheService;
import com.bdjbd.service.common.KaptchaService;
import com.bdjbd.service.common.RedisService;
import com.bdjbd.service.common.UploadService;
import com.bdjbd.service.matches.*;
import com.bdjbd.service.sms.SmsServerService;
import com.bdjbd.service.sms.SmsService;
import com.bdjbd.service.sys.*;
import com.bdjbd.util.ExportDealUtil;
import com.bdjbd.util.ExportDealUtilSimple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zhuzhe
 * @date 2020/4/9 13:25
 * @email zhuzhe_mail@163.com
 */
@AllArgsConstructor
@Component("testCommonController")
public class Common {

    AuthService authService;
    CacheService cacheService;
    KaptchaService kaptchaService;
    RedisService redisService;
    UploadService uploadService;
    DataCategoryHandlerService dataCategoryHandlerService;
    DataRecordHandlerService dataRecordHandlerService;
    MatchClauseCategoryService matchClauseCategoryService;
    MatchClauseItemService matchClauseItemService;
    MatchRuleDateService matchRuleDateService;
    MatchRuleRadioService matchRuleRadioService;
    MatchRuleSelectAndTextService matchRuleSelectAndTextService;
    MatchRuleSelectService matchRuleSelectService;
    MatchRuleService matchRuleService;
    MatchRuleTextareaService matchRuleTextareaService;
    MatchRuleTextService matchRuleTextService;
    SmsServerService smsServerService;
    SmsService smsService;

    // sys
    SysAdminRoleService sysAdminRoleService;
    SysAdminService sysAdminService;
    SysAuthorityService sysAuthorityService;
    SysConfigService sysConfigService;
    SysConfigAttributesService sysConfigAttributesService;
    SysLogService sysLogService;
    SysRoleAuthorityService sysRoleAuthorityService;
    SysRoleService sysRoleService;

    //
    AssessorsService assessorsService;
    BaseParameterGroupService baseParameterGroupService;
    BaseParameterService baseParameterService;
    BaseRelationParameterDefinitionService baseRelationParameterDefinitionService;
    BaseSimpleDefinitionService baseSimpleDefinitionService;
    QaAcademicRecordService qaAcademicRecordService;
    QaAcademicRecordItemService qaAcademicRecordItemService;
    QaAcademicRecordLogService qaAcademicRecordLogService;
    QaBaseClauseService qaBaseClauseService;
    QaBaseClauseGroupService qaBaseClauseGroupService;
    QaBaseClauseItemService qaBaseClauseItemService;
    QaBaseProjectService qaBaseProjectService;
    QaCategoryService qaCategoryService;
    QaClauseService qaClauseService;
    QaNoticeService qaNoticeService;
    QaRelationClauseCategoryService qaRelationClauseCategoryService;
    QaRelationClauseGroupCategoryService qaRelationClauseGroupCategoryService;
    QaRelationClauseGroupProjectService qaRelationClauseGroupProjectService;
    QaRelationClauseGroupService qaRelationClauseGroupService;
    QaRelationClauseItemCategoryService qaRelationClauseItemCategoryService;
    QaStandardRecordService qaStandardRecordService;
    QaStandardRecordItemService qaStandardRecordItemService;
    QaUserInfoService qaUserInfoService;

    // 导出excel
    ExportDealUtil exportDealUtil;
    ExportDealUtilSimple exportDealUtilSimple;

}
