package com.bdjbd.util;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.Order;
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
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.model.CommentsTable;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author zhuzhe
 * @date 2020/3/12 18:36
 * @email zhuzhe_mail@163.com
 */
@Slf4j
@Component
public class ExportDealUtil {

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

    // 设置并引用其他Sheet作为绑定下拉列表数据
    public DataValidation setDataValidation(Workbook wb, String strFormula, int firstRow, int firstCol, int endRow, int endCol,
                                            String hiddenSheetName) {
        // 原顺序为 起始行 起始列 终止行 终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) wb.getSheet(hiddenSheetName));
        DataValidationConstraint formulaListConstraint = dvHelper.createFormulaListConstraint(strFormula);
        DataValidation dataValidation = dvHelper.createValidation(formulaListConstraint, regions);
        dataValidation.setShowErrorBox(true);
        return dataValidation;
    }

    // 创建下拉列表值存储工作表并设置值
    public void createDropDownSheet(Workbook wb, String[] typeArrays, String sheetName) {
        // 创建下拉列表值存储工作表
        Sheet sheet = wb.createSheet(sheetName);
        // 循环往该sheet中设置添加下拉列表的值
        for (int i = 0; i < typeArrays.length; i++) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell((int) 0);
            cell.setCellValue(typeArrays[i]);
        }
        wb.setSheetHidden(wb.getSheetIndex(sheetName), true);
    }

    public List<BaseSimpleDefinition> getBaseSimpleDefinitions(BaseParameter baseParameter) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("type", baseParameter.getTypeValidate()));
        List<Order> orders = new ArrayList<>();
        orders.add(Order.asc("orders"));
        return baseSimpleDefinitionService.findList( filters, orders);
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

    /**
     * 处理二级下拉框
     *
     * @param wb
     * @param list
     * @param sheet
     * @param row
     */
    public void dealDropDownList(Workbook wb, List<BaseParameter> list, Sheet sheet, Row row) {
        // 设置 现专业技术职务 -> 现职称所属岗位 二级联动
        // 现专业技术职务(technologyTitle)
        // 现职称所属岗位(categoryType)
        int childCellAddress = 0;
        for (int i = 0; i < list.size(); i++) {
            BaseParameter baseParameter = list.get(i);
            String attr0 = baseParameter.getAttr0();
            if (attr0 != null && attr0.equals("categoryType")) {
                childCellAddress = i + 1;
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            BaseParameter baseParameter = list.get(i);
            String attr0 = baseParameter.getAttr0();
            if (attr0 != null && attr0.equals("technologyTitle")) {
                List<BaseSimpleDefinition> definitions = getBaseSimpleDefinitions(baseParameter);
                Map<String, Object> map = getStringObjectMap(definitions);
                Cell parentCell = row.getCell(i);
                Sheet hidden_sheet = wb.createSheet("hidden_sheet_technologyTitle");
                wb.setSheetHidden(wb.getSheetIndex("hidden_sheet_technologyTitle"), true);
                int rowId = 0;
                //// 设置第一行，存一级信息
                Row row1_ = hidden_sheet.createRow(rowId++);
                row1_.createCell(0).setCellValue("name");
                for (int k = 0; k < definitions.size(); k++) {
                    row1_.createCell(k + 1).setCellValue(definitions.get(k).getName());
                }
                // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
                for (int k = 0; k < definitions.size(); k++) {
                    Row row2_ = hidden_sheet.createRow(rowId++);
                    String s = definitions.get(k).getName();
                    List<Map<?, ?>> mapList = (List<Map<?, ?>>) map.get(s);
                    row2_.createCell(0).setCellValue(s);
                    for (int i11 = 0; i11 < mapList.size(); i11++) {
                        Map<?, ?> map1 = mapList.get(i11);
                        row2_.createCell(i11 + 1).setCellValue(String.valueOf(map1.get("name")));
                    }
                    // 添加名称管理器
                    String range = getRange(1, rowId, mapList.size());
                    Name name = wb.createName();
                    //key不可重复,将父区域名作为key
                    name.setNameName(s);
                    String formula = "hidden_sheet_technologyTitle!" + range;
                    name.setRefersToFormula(formula);
                }

                XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
                // 父规则
                String[] parentAttr = new String[definitions.size()];
                for (int z = 0; z < definitions.size(); z++) {
                    parentAttr[z] = definitions.get(z).getName();
                }
                DataValidationConstraint provConstraint = dvHelper.createExplicitListConstraint(parentAttr);
                // 四个参数分别是：起始行、终止行、起始列、终止列
                CellRangeAddressList provRangeAddressList = new CellRangeAddressList(2, 100, i, i);
                DataValidation provinceDataValidation = dvHelper.createValidation(provConstraint, provRangeAddressList);
//验证
                provinceDataValidation.createErrorBox("error", "请正确选择");
                provinceDataValidation.setShowErrorBox(true);
                provinceDataValidation.setSuppressDropDownArrow(true);
                sheet.addValidationData(provinceDataValidation);
                String cellAddress = parentCell.getAddress().formatAsString().replaceAll("2", "");
//对前100行设置有效性
                boolean showErrorBox = true;
                if (baseParameter.getType().equals("select&text")) {
                    showErrorBox = false;
                }
                for (int d = 2; d < 100; d++) {
                    setDataValidation(cellAddress, (XSSFSheet) sheet, d, childCellAddress, showErrorBox);
                }
                break;
            }
        }
    }

    /**
     * 计算formula
     *
     * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId    第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     */
    public String getRange(int offset, int rowId, int colCount) {
        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char) ('A' + 25);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }

    /**
     * 设置有效性
     *
     * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
     * @param sheet
     * @param rowNum 行数
     * @param colNum 列数
     */
    public void setDataValidation(String offset, XSSFSheet sheet, int rowNum, int colNum, boolean showErrorBox) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        DataValidation data_validation_list;
        data_validation_list = getDataValidationByFormula(
                "INDIRECT($" + offset + (rowNum) + ")", rowNum, colNum, dvHelper);
        data_validation_list.setShowErrorBox(showErrorBox);
        sheet.addValidationData(data_validation_list);
    }

    /**
     * 加载下拉列表内容
     *
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @param dvHelper
     * @return
     */
    private DataValidation getDataValidationByFormula(
            String formulaString, int naturalRowIndex, int naturalColumnIndex, XSSFDataValidationHelper dvHelper) {
        // 加载下拉列表内容
        // 举例：若formulaString = "INDIRECT($A$2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，
        //如果A2是江苏省，那么此处就是江苏省下的市信息。
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formulaString);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        int firstRow = naturalRowIndex - 1;
        int lastRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        // 数据有效性对象
        // 绑定
        XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        data_validation_list.setEmptyCellAllowed(false);
        if (data_validation_list instanceof XSSFDataValidation) {
            data_validation_list.setSuppressDropDownArrow(true);
            data_validation_list.setShowErrorBox(true);
        } else {
            data_validation_list.setSuppressDropDownArrow(false);
        }
        // 设置输入信息提示信息
        data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
        // 设置输入错误提示信息
        //data_validation_list.createErrorBox("选择错误提示", "你输入的值未在备选列表中，请下拉选择合适的值！");
        return data_validation_list;
    }

    //压缩文件
    public void ZipFiles(java.io.File[] srcfile, java.io.File zipfile) {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void insertSheet(BaseParameterGroup baseParameterGroup, String userId, Workbook wb) throws Exception {
        List<BaseParameter> list = baseParameterGroupService.findParamByGroupId(baseParameterGroup.getId(), false);
        if (list.size() == 0) {
            return;
        }

        Sheet sheet = wb.createSheet(baseParameterGroup.getName());

        CellStyle textStyle = wb.createCellStyle();
        DataFormat  format = wb.createDataFormat();
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
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            BaseParameter baseParameter = list.get(i);
            if (baseParameter.getType() != null && baseParameter.getType().contains("select")) {
                List<BaseSimpleDefinition> definitions = getBaseSimpleDefinitions(baseParameter);
                if (definitions != null && definitions.size() > 0) {
                    String[] strings = new String[definitions.size()];
                    for (int i1 = 0; i1 < definitions.size(); i1++) {
                        strings[i1] = definitions.get(i1).getName();
                    }
                    j++;
                    String hiddenSheetName = "hidden_" + baseParameter.getId().replaceAll("-", "");
                    //wb = dropDownList(wb, sheet, strings, 2, 1000, i, i, hiddenSheetName, j);
                    createDropDownSheet(wb, strings, hiddenSheetName);
                    DataValidation dataValidation = setDataValidation(wb, hiddenSheetName + "!$A$1:$A" + strings.length,
                            2, i, 1000, i, hiddenSheetName);
                    if (baseParameter.getType().equals("select&text")) {
                        //dataValidation.setShowErrorBox(false);
                    }
                    sheet.addValidationData(dataValidation);
                }
            }
            if (baseParameter.getType() != null && baseParameter.getType().equals("date")) {
                //设置注释体
                XSSFSheet sheet1 = (XSSFSheet) sheet;
                XSSFDrawing p = sheet1.createDrawingPatriarch();
                for (int k = 2; k < 10; k++) {
                    XSSFCell cell = sheet1.createRow(k).createCell(i);
                    try {
                        if (k == 3 && i == 3) {
                            continue;
                        }
                        if (cell.getCellComment() == null) {
                            XSSFClientAnchor xssfClientAnchor = new XSSFClientAnchor(0, 0, 0, 0, 3, 3, 5, 6);
                            XSSFComment comment = p.createCellComment(xssfClientAnchor);
                            comment.setString(new XSSFRichTextString("日期格式：" + baseParameter.getTypeValidate()));
                            cell.setCellComment(comment);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    cell.setCellStyle(sheet.getColumnStyle(0));
                }
            }
        }
        dealDropDownList(wb, list, sheet, row);

        // 插入用户已经申请数据
        insertExcelData(baseParameterGroup.getId(), userId, list, sheet);
    }

    public void writeWorkbook(String userId, List<String> fileNames, BaseParameterGroup baseParameterGroup, String filePath) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        insertSheet(baseParameterGroup, userId, workbook);
        fileNames.add(filePath + baseParameterGroup.getName() + ".xlsx");
        FileOutputStream o = new FileOutputStream(filePath + baseParameterGroup.getName() + ".xlsx");
        workbook.write(o);
    }

    public void fileExport(BaseParameterGroup baseParameterGroup, HttpServletResponse response, String userId) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        insertSheet(baseParameterGroup, userId, wb);
        response.setHeader("Content-disposition", "attachment; filename=" + baseParameterGroup.getId() + ".xlsx");
        response.setContentType("application/msexcel");
        OutputStream fileOut = response.getOutputStream();
//        FileOutputStream fileOut = new FileOutputStream("D:\\360安全浏览器下载\\" + baseParameterGroup.getId() + ".xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    /**
     * 设置响应头
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            response.reset();// 清空输出流
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes("GB2312"), "8859_1")
                    + ".zip");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            log.error("设置响应头失败，{}", ex);
        }
    }

    public void insertExcelData(String groupId, String userId, List<BaseParameter> list, Sheet sheet) {
        if (userId == null) {
            return;
        }
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
                        if (parameter.getOrders() != null) {
                            set.add(parameter.getOrders());
                        }
                    }
                    List<Integer> ordersList = new ArrayList<>(set);
                    Collections.sort(ordersList);
                    //Map<Integer, List<BaseParameter>> map = new LinkedHashMap<>();
                    for (Integer order : ordersList) {
                        List<BaseParameter> parameterList = new ArrayList<>();
                        for (BaseParameter parameter : parameters) {
                            if (order == parameter.getOrders()) {
                                parameterList.add(parameter);
                            }
                        }
                        //map.put(order, parameterList);
                        recordList.add(parameterList);
                    }
                }
            }
        }

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

    /**
     * @param wb               HSSFWorkbook对象
     * @param realSheet        需要操作的sheet对象
     * @param datas            下拉的列表数据
     * @param startRow         开始行
     * @param endRow           结束行
     * @param startCol         开始列
     * @param endCol           结束列
     * @param hiddenSheetName  隐藏的sheet名
     * @param hiddenSheetIndex 隐藏的sheet索引
     * @return
     * @throws Exception
     */
    private static XSSFWorkbook dropDownList(Workbook wb, Sheet realSheet, String[] datas, int startRow, int endRow,
                                             int startCol, int endCol, String hiddenSheetName, int hiddenSheetIndex)
            throws Exception {

        XSSFWorkbook workbook = (XSSFWorkbook) wb;
        // 创建一个数据源sheet
        XSSFSheet hidden = workbook.createSheet(hiddenSheetName);
        // 数据源sheet页不显示
        workbook.setSheetHidden(hiddenSheetIndex, true);
        // 将下拉列表的数据放在数据源sheet上
        XSSFRow row = null;
        XSSFCell cell = null;
        for (int i = 0, length = datas.length; i < length; i++) {
            row = hidden.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(datas[i]);
        }
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(hiddenSheetName + "!$A$1:$A" + datas.length);
        org.apache.poi.ss.util.CellRangeAddressList addressList = null;
        HSSFDataValidation validation = null;
        row = null;
        cell = null;
        // 单元格样式
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
//		style.setAlignment(CellStyle.ALIGN_CENTER);
//		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        // 循环指定单元格下拉数据
        for (int i = startRow; i <= endRow; i++) {
            row = (XSSFRow) realSheet.createRow(i);
            cell = row.createCell(startCol);
            cell.setCellStyle(style);
            addressList = new org.apache.poi.ss.util.CellRangeAddressList(i, i, startCol, endCol);
            validation = new HSSFDataValidation(addressList, constraint);
            realSheet.addValidationData(validation);
        }
        return workbook;
    }

    //删除文件夹
    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            log.error("删除文件夹失败，{}", e);
        }
    }

    public boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

}
