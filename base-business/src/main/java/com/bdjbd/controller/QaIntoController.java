package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.bo.ScheduleVO;
import com.bdjbd.bo.TimeResVO;
import com.bdjbd.bo.TimeVO;
import com.bdjbd.dao.entity.QaExpertCommit;
import com.bdjbd.dao.entity.QaIntoParticipants;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.dao.entity.SysConfigAttributes;
import com.bdjbd.service.QaIntoService;
import com.bdjbd.service.QaReviewProfessionalEvaluationService;
import com.bdjbd.service.ReviewApplyParticipateService;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 9:38 AM 2020/9/8
 */
@RestController
@RequestMapping("/admin/into")
@Slf4j
@Api(value = "专家评审模块 - Controller", description = "专家评审模块", tags = {"专家评审模块"})
public class QaIntoController {

    @Autowired
    private QaReviewProfessionalEvaluationService qaReviewProfessionalEvaluationService;

    @Autowired
    private ReviewApplyParticipateService reviewApplyParticipateService;

    @Autowired
    private QaIntoService qaIntoService;

    @Autowired
    private SysAdminService adminService;

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    @ApiOperation(value = "进入评审人员列表")
    public Message findList(@RequestHeader("Authorization") String authorization, String rounds, String typeStatus, String lastCategory, String type) {
        String userId = adminService.findUserIdByToken(authorization);
        Map<String, Object> map = qaIntoService.findList(userId, rounds, typeStatus, lastCategory,type);
        return Message.success(map);
    }


    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    @ApiOperation(value = "投票")
    public Message vote(@RequestHeader("Authorization") String authorization, String id) {
        String userId = adminService.findUserIdByToken(authorization);

        int result = qaIntoService.vote(id, userId);
        if (result > 0)
            return Message.success("投票成功~~");
        else
            return Message.success("投票失败请重试~~");
    }


    @RequestMapping(value = "/unVote", method = RequestMethod.POST)
    @ApiOperation(value = "取消投票")
    public Message unVote(@RequestHeader("Authorization") String authorization, String id) {
        String userId = adminService.findUserIdByToken(authorization);

        int result = qaIntoService.unVote(id, userId);
        if (result > 0)
            return Message.success("取消投票成功~~");
        else
            return Message.success("取消投票失败请重试~~");
    }

    @RequestMapping(value = "/commitVote", method = RequestMethod.POST)
    @ApiOperation(value = "提交投票结果")
    public Message commitVote(@RequestHeader("Authorization") String authorization, String rounds, String typeStatus, String lastCategory, String pic,String type) {
        String userId = adminService.findUserIdByToken(authorization);

        int result = qaIntoService.commitVote(userId, rounds, typeStatus, lastCategory, pic,type);
        if (result > 0)
            return Message.success("提交成功~~");
        else
            return Message.success("提交失败请重试~~");
    }

    @RequestMapping(value = "/findResult", method = RequestMethod.POST)
    @ApiOperation(value = "进入评审人员投票列表列表")
    public Message findResult(@RequestHeader("Authorization") String authorization, String rounds, String typeStatus, String lastCategory, String type) {
        String userId = adminService.findUserIdByToken(authorization);
        List<QaIntoParticipants> result = qaIntoService.findResult(userId, rounds, typeStatus, lastCategory, type);
        return Message.success(result);
    }

    @RequestMapping(value = "/findSchedule", method = RequestMethod.POST)
    @ApiOperation(value = "获取投票进度")
    public Message findSchedule(@RequestHeader("Authorization") String authorization, String rounds, String typeStatus) {
        String userId = adminService.findUserIdByToken(authorization);
        List<ScheduleVO> schedule = qaIntoService.findSchedule(userId, rounds, typeStatus);
        return Message.success(schedule);
    }


    @RequestMapping(value = "/intoAgain", method = RequestMethod.POST)
    @ApiOperation(value = "再次进入评审")
    public Message intoAgain(@RequestHeader("Authorization") String authorization, String userIds, String rounds, String typeStatus) {
        String userId = adminService.findUserIdByToken(authorization);
        qaIntoService.intoAgain(userId, rounds, userIds, typeStatus);
        return Message.success();
    }

    @IgnoreAuthority
    @IgnoreUserToken
    @RequestMapping(value = "/exportInfo", method = RequestMethod.GET)
    @ApiOperation(value = "导出选中人员")
    public void exportInfo(String userIds, HttpServletResponse response) {
//        String userId = adminService.findUserIdByToken(authorization);
        String userId = "";
        List<QaIntoParticipants> list = qaIntoService.exportInfo(userId, userIds);
        String[] tableHeader = {"姓名", "现职称", "参评岗位", "得票"};
        short cellNumber = (short) tableHeader.length;//表的列数
        HSSFWorkbook workbook = new HSSFWorkbook(); //创建一个Excel
        HSSFCellStyle style = workbook.createCellStyle();//设置表头的类型
        style.setAlignment(HorizontalAlignment.CENTER);
        style = workbook.createCellStyle(); //设置数据类型
        style.setAlignment(HorizontalAlignment.CENTER);
        HSSFFont font = workbook.createFont(); //设置字体
        HSSFSheet sheet = workbook.createSheet("sheet1"); //创建一个sheet
        HSSFHeader header = sheet.getHeader();//设置sheet的头
        HSSFCell cell = null;
        try {
            //根据是否取出数据，设置header信息
            if (list.size() < 1) {
                header.setCenter("查无资料");
            } else {
                header.setCenter("评审人员表");
                HSSFRow row = sheet.createRow(0);
                row.setHeight((short) 400);
                //表头
                for (int k = 0; k < cellNumber; k++) {
                    cell = row.createCell((short) k);//创建第0行第k列
                    cell.setCellValue(tableHeader[k]);//设置第0行第k列的值
                    sheet.setColumnWidth((short) k, (short) 8000);//设置列的宽度
                    font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
                    font.setFontHeight((short) 350); //设置单元字体高度
                    style.setFont(font);//设置字体风格
                    cell.setCellStyle(style);
                }
                // 给Excel填充数据
                for (int i = 0; i < list.size(); i++) {
                    //获取InternationalStudent对象
                    QaIntoParticipants participants = list.get(i);
                    row = sheet.createRow((short) (i + 1));//创建第i+1行
                    row.setHeight((short) 400);//设置行高

                    if (participants.getName() != null) {
                        cell = row.createCell((short) 0);//创建第i+1行第0列
                        cell.setCellValue(participants.getName());//设置第i+1行第0列的值
                        cell.setCellStyle(style);//设置风格
                    }
                    if (participants.getCurrentTitle() != null) {
                        cell = row.createCell((short) 1); //创建第i+1行第1列
                        cell.setCellValue(participants.getCurrentTitle());//设置第i+1行第1列的值
                        cell.setCellStyle(style); //设置风格
                    }
                    if (participants.getParticipatingTitle() != null) {
                        cell = row.createCell((short) 2);
                        cell.setCellValue(participants.getParticipatingTitle());
                        cell.setCellStyle(style);
                    }
                    if (participants.getPostType() != null) {
                        cell = row.createCell((short) 3);
                        cell.setCellValue(participants.getPostType());
                        cell.setCellStyle(style);
                    }
                    if (participants.getTicketIds() != null) {
                        cell = row.createCell((short) 4);
                        cell.setCellValue(JsonUtils.jsonToList(participants.getTicketIds(), String.class).size());
                        cell.setCellStyle(style);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        outputSetting("参评人员得票结果.xls", response, workbook);
    }

    public void outputSetting(String fileName, HttpServletResponse response, HSSFWorkbook workbook) {

        OutputStream out = null;//创建一个输出流对象
        try {
            out = response.getOutputStream();// 得到输出流
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO-8859-1"));//filename是下载的xls的名
            response.setContentType("application/msexcel;charset=UTF-8");//设置类型
            response.setHeader("Pragma", "No-cache");//设置头
            response.setHeader("Cache-Control", "no-cache");//设置头
            response.setDateHeader("Expires", 0);//设置日期头
            workbook.write(out);
            out.flush();
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/repairData", method = RequestMethod.POST)
    @ApiOperation(value = "数据修复")
    public Message repairData(@RequestHeader("Authorization") String authorization) {
        String userId = adminService.findUserIdByToken(authorization);

        qaIntoService.repairData(userId);

        return Message.success();
    }


    @RequestMapping(value = "/timeData", method = RequestMethod.POST)
    @ApiOperation(value = "评审时间")
    public Message timeData(@RequestHeader("Authorization") String authorization) {
        Map<String, TimeVO> result = qaIntoService.timeData();
        return Message.success(result);
    }


    @RequestMapping(value = "/findDataByUserId", method = RequestMethod.POST)
    @ApiOperation(value = "获取人员预览信息")
    public Message findDataByUserId(@RequestHeader("Authorization") String authorization, String userId) {
        return Message.success(reviewApplyParticipateService.preview(userId, "", "a"));
    }


    @RequestMapping(value = "/updateDataByUserId", method = RequestMethod.POST)
    @ApiOperation(value = "修改预览")
    public Message updateDataByUserId(@RequestHeader("Authorization") String authorization, String userId, QaReviewProfessionalEvaluationEntity entity) {
        qaReviewProfessionalEvaluationService.saveProfessionalEvaluation(userId, entity);
        return Message.success();
    }


    @RequestMapping(value = "/unSubmit", method = RequestMethod.POST)
    @ApiOperation(value = "撤销提交")
    public Message unSubmit(@RequestHeader("Authorization") String authorization) {

        String userId = adminService.findUserIdByToken(authorization);

        int result = qaReviewProfessionalEvaluationService.unSubmit(userId);
        if (result == 0)
            return Message.error("当前人员 已经进入参评 无法修订");
        return Message.success("修订成功");
    }

    @RequestMapping(value = "/adminSubmit", method = RequestMethod.POST)
    @ApiOperation(value = "审核管理员提交")
    public Message adminSubmit(@RequestHeader("Authorization") String authorization, Integer id) {

        String userId = adminService.findUserIdByToken(authorization);

        int result = qaReviewProfessionalEvaluationService.adminSubmit(userId, id);

        return Message.success("提交成功");
    }

    @RequestMapping(value = "/adminFind", method = RequestMethod.POST)
    @ApiOperation(value = "审核管理员提交")
    public Message adminFind(@RequestHeader("Authorization") String authorization, Integer id) {

        String userId = adminService.findUserIdByToken(authorization);

        QaReviewProfessionalEvaluationEntity entity = qaReviewProfessionalEvaluationService.adminFind(userId, id);

        return Message.success(entity);
    }

    @RequestMapping(value = "/findImg", method = RequestMethod.POST)
    @ApiOperation(value = "专家签字")
    public Message findImg(@RequestHeader("Authorization") String authorization,String rounds,String type, String typeStatus) {

        String userId = adminService.findUserIdByToken(authorization);

        QaExpertCommit commit = qaIntoService.findImg(userId,rounds,type,typeStatus);

        return Message.success(commit);
    }


}
