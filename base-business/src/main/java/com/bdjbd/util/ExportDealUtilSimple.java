package com.bdjbd.util;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Order;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.service.BaseParameterGroupService;
import com.bdjbd.service.BaseSimpleDefinitionService;
import com.bdjbd.service.QaCategoryService;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.sys.SysAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 导出处理，多个sheet
 *
 * @author zhuzhe
 * @date 2020/3/12 18:36
 * @email zhuzhe_mail@163.com
 */
@Slf4j
@Component
public class ExportDealUtilSimple {

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


    public List<BaseSimpleDefinition> getBaseSimpleDefinitions(BaseParameter baseParameter) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("type", baseParameter.getTypeValidate()));
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("orders"));
        return baseSimpleDefinitionService.findList(filters, orders);
    }

    /**
     * 获取二级联动map
     *
     * @param definitions
     * @return
     */
    private Map<String, Object> getStringObjectMap(List<BaseSimpleDefinition> definitions) {
        Map<String, Object> map = new HashMap<>();
        for (BaseSimpleDefinition definition : definitions) {
            Message message = qaCategoryService.findCategoryTypeByTitle(definition.getName());
            if (message.isSuccess()) {
                List<Map<?, ?>> data = (List<Map<?, ?>>) message.getData();
                if (data != null) {
                    map.put(definition.getName(), data);
                } else {
                    map.put(definition.getName(), new ArrayList<>());
                }
            } else {
                map.put(definition.getName(), new ArrayList<>());
            }
        }
        return map;
    }

    private void insertSheet(BaseParameterGroup baseParameterGroup, String userId, Workbook wb) throws Exception {
        List<BaseParameter> list = baseParameterGroup.getParameters();
        if (list == null || list.size() == 0) {
            list = baseParameterGroupService.findParamByGroupId(baseParameterGroup.getId(), false);
        }
        if (list.size() == 0) {
            return;
        }

        Sheet sheet = wb.createSheet(baseParameterGroup.getName());

        CellStyle textStyle = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        textStyle.setDataFormat(format.getFormat("@"));

        sheet.setDefaultRowHeightInPoints(18);
        sheet.setDefaultColumnWidth(18);
        // 第一行标题
        createTitle(baseParameterGroup, list, wb, sheet);

        Row row = sheet.createRow(1);
        for (int i = 0; i < list.size(); i++) {
            row.createCell(i).setCellValue(list.get(i).getName());
            sheet.setDefaultColumnStyle(i, textStyle);
        }

        // 插入用户已经申请数据
        insertExcelData(baseParameterGroup.getId(), userId, list, sheet);
    }

    /**
     * 导出文件
     *
     * @param baseParameterGroups
     * @param response
     * @param userId
     * @throws Exception
     */
    public void fileExport(List<BaseParameterGroup> baseParameterGroups, HttpServletResponse response, String userId) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        for (BaseParameterGroup baseParameterGroup : baseParameterGroups) {
            insertSheet(baseParameterGroup, userId, wb);
        }
        response.setHeader("Content-disposition", "attachment; filename=" + System.currentTimeMillis() + ".xlsx");
        response.setContentType("application/msexcel");
        OutputStream fileOut = response.getOutputStream();
//        FileOutputStream fileOut = new FileOutputStream("D:\\360安全浏览器下载\\" + System.currentTimeMillis() + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    public void errorFileExport(HttpServletResponse response) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Sheet1");
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("数据不存在，导出错误！");
        response.setHeader("Content-disposition", "attachment; filename=" + DateUtil.formatYMDNoUnderline(new Date()) + ".xlsx");
        response.setContentType("application/msexcel");
        OutputStream fileOut = response.getOutputStream();
        wb.write(fileOut);
        fileOut.close();
    }


    public void insertExcelData(String groupId, String userId, List<BaseParameter> list, Sheet sheet) {
        if (userId == null) {
            return;
        }
        List<List<BaseParameter>> recordList = getLists(groupId, userId);

        for (int i = 0; i < recordList.size(); i++) {
            List<BaseParameter> parameterList = recordList.get(i);
            Row sheetRow = sheet.createRow(i + 2);
            for (int k = 0; k < list.size(); k++) {
                String name = list.get(k).getName();
                for (BaseParameter baseParameter : parameterList) {
                    if (name.equalsIgnoreCase(baseParameter.getName())) {
                        String userParameterValue = baseParameter.getUserParameterValue();
                        Cell cell = sheetRow.createCell(k);
                        cell.setCellStyle(sheet.getColumnStyle(0));
                        if (userParameterValue == null) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue(userParameterValue);
                        }
                        break;
                    }
                }
            }
        }
    }

    public List<List<BaseParameter>> getLists(String groupId, String userId) {
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findByUserAndParamGroup(userId, groupId);
        List<List<BaseParameter>> recordList = new ArrayList<>();
        for (int i = 0; i < qaStandardRecords.size(); i++) {
            QaStandardRecord qaStandardRecord = qaStandardRecords.get(i);
            List<BaseParameterGroup> baseParameterGroups = qaStandardRecord.getBaseParameterGroups();
            if (baseParameterGroups != null && baseParameterGroups.size() > 0) {
                List<BaseParameter> parameters = baseParameterGroups.get(0).getParameters();
                if (parameters != null && parameters.size() > 0) {
                    Set<Integer> set = new HashSet<>();
                    for (BaseParameter parameter : parameters) {
                        if (parameter.getAttr1() != null) {
                            set.add(Integer.valueOf(parameter.getAttr1()));
                        }
                    }
                    List<Integer> ordersList = new ArrayList<>(set);
                    Collections.sort(ordersList);
                    //Map<Integer, List<BaseParameter>> map = new LinkedHashMap<>();
                    for (int order : ordersList) {
                        List<BaseParameter> parameterList = new ArrayList<>();
                        for (BaseParameter parameter : parameters) {
                            if (order == Integer.valueOf(parameter.getAttr1())) {
                                parameterList.add(parameter);
                            }
                        }
                        //map.put(order, parameterList);
                        recordList.add(parameterList);
                    }
                }
            }
        }
        return recordList;
    }

    public void createTitle(BaseParameterGroup baseParameterGroup, List<BaseParameter> list, Workbook wb, Sheet sheet) {
        Row row_0 = sheet.createRow(0);
        row_0.setHeightInPoints(28);
        Cell titleCell = row_0.createCell(0);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = wb.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        cellStyle.setFont(font);
        titleCell.setCellStyle(cellStyle);
        titleCell.setCellValue(baseParameterGroup.getName());
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, list.size() - 1));
    }
}
