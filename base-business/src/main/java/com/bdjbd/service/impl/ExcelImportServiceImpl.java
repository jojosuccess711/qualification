package com.bdjbd.service.impl;

import com.bdjbd.Filter;
import com.bdjbd.Message;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.common.util.StringUtil;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.service.*;
import com.bdjbd.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;

/**
 * 通过excel录入用户信息
 * @author songzekun
 */
@Service
@Slf4j
public class ExcelImportServiceImpl implements ExcelImportService {
    @Autowired
    BaseParameterService baseParameterService;
    @Autowired
    BaseParameterGroupService baseParameterGroupService;
    @Autowired
    QaStandardRecordService qaStandardRecordService;
    @Autowired
    QaStandardRecordItemService qaStandardRecordItemService;

    /**
     * 保存用户录入的数据
     * @return
     */
    @Override
    public Message saveOrEditUseData(List<QaStandardRecordItem> useData) {
        qaStandardRecordItemService.batchInsert(useData);
        return Message.success();
    }

    /**
     * @param userId 上传文件用户id
     * @param excelFile 文件
     * @param modelName 上传文件模块名称
     * @return Message
     * @throws Exception 用户通过excel录入信息
     */
    @Override
    public Message importExcel(String userId, File excelFile, String modelName)
            throws
            Exception {
        //        //        Workbook book = new XSSFWorkbook(new FileInputStream(excelFile));
        //        FileInputStream is = new FileInputStream(excelFile);
        //        if (!excelFile.getName().endsWith(".xls") && !excelFile.getName().endsWith(".xlsx")) {
        //            FileUtils.delteTempFile(excelFile);
        //            return Message.error("导入文件非excel类型文件");
        //        }
        //        Workbook book;
        //        try {
        //            book = new HSSFWorkbook(is);
        //        } catch (Exception ex) {
        //            // 解决read error异常
        //            is = new FileInputStream(excelFile);
        //            book = new XSSFWorkbook(is);
        //        }
        //        int sheetCount = book.getNumberOfSheets();
        //        Sheet sheet;
        //        List<QaStandardRecordItem> list = new ArrayList<>();
        //        QaStandardRecord importUserInfo = null;
        //        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList(1, Collections.singletonList(Filter.eq("userId", userId)), null);
        //        int id = 0;
        //        if (!ObjectUtils.isEmpty(qaStandardRecords)) {
        //            importUserInfo = qaStandardRecords.get(0);
        //            Integer maxId = qaStandardRecordItemService.findMaxId(importUserInfo.getId());
        //            id = maxId == null ? 0 : maxId;
        //        }
        //
        //        for (int i = 0; i < sheetCount; i++) {
        //            sheet = book.getSheetAt(i);
        //            String sheetName = sheet.getSheetName();
        //            if (!"个人基本信息".equals(sheetName) && ObjectUtils.isEmpty(importUserInfo)) {
        //                FileUtils.delteTempFile(excelFile);
        //                return Message.error("您未录入基本信息数据，请先录入！");
        //            }
        //            if (!"all".equals(modelName) && !sheetName.equals(modelName)) {
        //                continue;
        //            }
        //            List<BaseParameterGroup> baseParameterGroups =
        //                    baseParameterGroupService.findList(0, Collections.singletonList(Filter.eq("name", sheetName)), null);
        //            if (ObjectUtils.isEmpty(baseParameterGroups)) {
        //                log.error("名为： " + sheetName + "未找到相关的录入项");
        //                continue;
        //            }
        //            String attr1 = baseParameterGroups.get(0).getAttr1();
        //            //单项数据录入数量
        //            int rowCount = attr1 == null ? 0 : Integer.valueOf(attr1);
        //            //行数
        //            int rowNum = sheet.getLastRowNum();
        //            Row row;
        //            //通过第一列的表头数据获取数据库配置的参数id
        //            row = sheet.getRow(1);
        //            //空白sheet直接跳过
        //            if (row == null) {
        //                continue;
        //            }
        //            int columnNum = row.getPhysicalNumberOfCells();
        //            List<Map<String, Object>> ruleMap = new ArrayList<>();
        //            for (int k = 0; k < columnNum; k++) {
        //                if (isRowEmpty(row)) {
        //                    break;
        //                }
        //                String parameter = row.getCell(k).getStringCellValue();
        //                List<BaseParameter> parameters =
        //                        baseParameterService.findList(1,
        //                                Arrays.asList(Filter.eq("name", parameter),
        //                                        Filter.eq("groupId", baseParameterGroups.get(0).getId())),
        //                                null);
        //                if (!ObjectUtils.isEmpty(parameters)) {
        //                    BaseParameter baseParameter = parameters.get(0);
        //                    Map<String, Object> map = new HashMap<>(3);
        //                    map.put("name", parameter);
        //                    //参数id
        //                    map.put("parameterId", baseParameter.getId());
        //                    map.put("groupId", baseParameter.getGroupId());
        //                    //参数类型
        //                    map.put("type", baseParameter.getType());
        //                    String validate = baseParameter.getTypeValidate();
        //                    if (validate.contains("|")) {
        //                        validate = validate.split("\\|")[0].split("-")[0];
        //                    }
        //                    map.put("validate", validate);
        //                    map.put("validate1", baseParameter.getTypeValidate());
        //                    map.put("attr0", baseParameter.getAttr0());
        //                    ruleMap.add(map);
        //                }
        //            }
        //            if ("个人基本信息".equals(sheetName)) {
        //                Message m = structStandardRecord(sheet, ruleMap, rowNum, userId);
        //                if (!m.isSuccess()) {
        //                    FileUtils.delteTempFile(excelFile);
        //                    return m;
        //                }
        //                importUserInfo = (QaStandardRecord) m.getData();
        //            }
        //            int rowNumber = 0;
        //            rowInfo:
        //            for (int h = 2; h <= rowNum; h++) {
        //                row = sheet.getRow(h);
        //                if (isRowEmpty(row)) {
        //                    if (rowCount != -1 && h - 2 > rowCount) {
        //                        FileUtils.delteTempFile(excelFile);
        //                        return Message.error(sheetName + "只可录入" + rowCount + "条数据");
        //                    }
        //                    break rowInfo;
        //                }
        //                for (int j = 0; j < ruleMap.size(); j++) {
        //                    Map ruleMapItem = ruleMap.get(j);
        //                    QaStandardRecordItem qaStandardRecordItem = new QaStandardRecordItem();
        //                    List<QaStandardRecordItem> list1 = qaStandardRecordItemService.findList(1, Arrays.asList(Filter.eq("recordId", importUserInfo.getId()),
        //                            Filter.eq("parameterId", ruleMapItem.get
        //                                    ("parameterId").toString()),
        //                            Filter.eq("parameterGroupId", ruleMapItem.get("groupId")
        //                                    .toString())), null);
        //                    qaStandardRecordItem.setId(importUserInfo.getId() + "_" + (id + 1 > 9 ? id + 1 : "0" + (id + 1)));
        //                    ++id;
        //                    qaStandardRecordItem.setRecordId(importUserInfo.getId());
        //                    qaStandardRecordItem.setGroupCategory("");
        //                    qaStandardRecordItem.setOrders(h - 2);
        //                    qaStandardRecordItem.setParameterGroupId(baseParameterGroups.get(0).getId());
        //                    qaStandardRecordItem.setParameterId(ruleMapItem.get("parameterId").toString());
        //                    qaStandardRecordItem.setParameterAnnexUrl("");
        //                    qaStandardRecordItem.setGroupId(baseParameterGroups.get(0).getId());
        //                    qaStandardRecordItem.setType(ruleMapItem.get("type").toString());
        //                    qaStandardRecordItem.setTypeValidate(ruleMapItem.get("validate1").toString());
        //                    qaStandardRecordItem.setName(ruleMapItem.get("name").toString());
        //                    if (row.getCell(j) == null) {
        //                        qaStandardRecordItem.setParameterValue("");
        //                    }
        //                    else if ("date".equals(ruleMapItem.get("type").toString())) {
        //                        try {
        //                            Date date = row.getCell(j).getDateCellValue();
        //                            if (date == null) {
        //                                qaStandardRecordItem.setParameterValue("");
        //                            }
        //                            else {
        //                                qaStandardRecordItem.setParameterValue
        //                                        (DateUtil.dateFormat(ruleMapItem.get("validate").toString(), row.getCell(j).getDateCellValue()));
        //                            }
        //                        } catch (Exception e) {
        //                            String rowStr = row.getCell(j).getStringCellValue();
        //                            if (StringUtils.isEmpty(rowStr)) {
        //                                qaStandardRecordItem.setParameterValue("");
        //                            }
        //                            else if (rowStr.contains("-")) {
        //                                String[] dates = rowStr.split("-");
        //                                String startDate = "-".equals(dates[0])
        //                                        || "".equals(dates[0]) ? "" : DateUtil.dateFormat(ruleMapItem.get("validate").toString(),
        //                                        DateUtil.dateParse(ruleMapItem.get
        //                                                        ("validate").toString(),
        //                                                dates[0]));
        //                                String endDate = dates.length == 1 || "至今".equals
        //                                        (dates[1]) ? "至今" : DateUtil.dateFormat(ruleMapItem.get("validate").toString(),
        //                                        DateUtil.dateParse(ruleMapItem.get
        //                                                        ("validate").toString(),
        //                                                dates[1]));
        //                                qaStandardRecordItem.setParameterValue
        //                                        (startDate + "-" + endDate);
        //                            }
        //                            else {
        //                                Date date = DateUtil.dateParse(ruleMapItem.get
        //                                        ("validate").toString(), rowStr);
        //                                if (date == null) {
        //                                    FileUtils.delteTempFile(excelFile);
        //                                    return Message.error
        //                                            (sheetName + " 第" + (h + 1) + "行" + ruleMapItem
        //                                                    .get("name") + ",时间格式有误");
        //                                }
        //                                qaStandardRecordItem.setParameterValue
        //                                        (DateUtil.dateFormat(ruleMapItem.get("validate").toString(),
        //                                                date));
        //                            }
        //                        }
        //                    }
        //                    else {
        //                        try {
        //                            qaStandardRecordItem.setParameterValue(row.getCell(j).getStringCellValue());
        //                        } catch (Exception e) {
        //                            System.out.println(row.getCell(j).getNumericCellValue());
        //                            qaStandardRecordItem.setParameterValue("" + row.getCell(j).getNumericCellValue());
        //                        }
        //                    }
        //                    list.add(qaStandardRecordItem);
        //                }
        //                rowNumber++;
        //            }
        //            if (rowNumber > rowCount) {
        //                FileUtils.delteTempFile(excelFile);
        //                return Message.error(sheetName + "只可录入" + rowCount + "条数据");
        //            }
        //            if(rowNumber!=0) {
        //                qaStandardRecordItemService
        //                        .deleteByRecordIdAndparamId(baseParameterGroups.get
        //                                (0).getId(), null, importUserInfo.getId(),null);
        //            }
        //        }
        //
        //        if (!ObjectUtils.isEmpty(list)) {
        //            saveOrEditUseData(list);
        //        }
        //        else {
        //            FileUtils.delteTempFile(excelFile);
        //            return Message.error("文件录入信息为空或分类不匹配");
        //        }
        //        FileUtils.delteTempFile(excelFile);
        //        return Message.success(list);
        return Message.success();
    }

    @Override
    public Message importExcelNew(String userId, File excelFile, String modelName) throws Exception {
        FileInputStream is = new FileInputStream(excelFile);
        if (!excelFile.getName().endsWith(".xls") && !excelFile.getName().endsWith(".xlsx")) {
            FileUtils.delteTempFile(excelFile);
            return Message.error("导入文件非excel类型文件");
        }
        Workbook book = getWorkbook(excelFile, is);
        int sheetCount = book.getNumberOfSheets();
        Sheet sheet;
        List<List<QaStandardRecordItem>> list = new ArrayList<>();
        //        QaStandardRecord importUserInfo = null;
        //        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList(1, Collections.singletonList(Filter.eq("userId", userId)), null);
        //        int id = 0;
        //        if (!ObjectUtils.isEmpty(qaStandardRecords)) {
        //            importUserInfo = qaStandardRecords.get(0);
        //            Integer maxId = qaStandardRecordItemService.findMaxId(importUserInfo.getId());
        //            id = maxId == null ? 0 : maxId;
        //        }

        for (int i = 0; i < sheetCount; i++) {
            sheet = book.getSheetAt(i);
            String sheetName = sheet.getSheetName();
            //            if (!"个人基本信息".equals(sheetName) && ObjectUtils.isEmpty(importUserInfo)) {
            //                FileUtils.delteTempFile(excelFile);
            //                return Message.error("您未录入基本信息数据，请先录入！");
            //            }
            if (!sheetName.equals(modelName)) {
                continue;
            }
            List<BaseParameterGroup> baseParameterGroups =
                    baseParameterGroupService.findList(Collections.singletonList(Filter.eq("name", sheetName)), null);
            if (ObjectUtils.isEmpty(baseParameterGroups)) {
                log.error("名为： " + sheetName + "未找到相关的录入项");
                continue;
            }
            String attr1 = baseParameterGroups.get(0).getAttr1();
            //单项数据录入数量
            int rowCount = attr1 == null ? 0 : Integer.valueOf(attr1);
            //行数
            int rowNum = sheet.getLastRowNum();
            Row row;
            //通过第一列的表头数据获取数据库配置的参数id
            row = sheet.getRow(1);
            //空白sheet直接跳过
            if (row == null) {
                continue;
            }
            //int columnNum = row.getPhysicalNumberOfCells();
            List<Map<String, Object>> ruleMap = getRuleMaps(baseParameterGroups, row, row.getPhysicalNumberOfCells());

            /*if ("个人基本信息".equals(sheetName)) {
                Message m = structStandardRecordNew(sheet, ruleMap, rowNum, userId);
                if (!m.isSuccess()) {
                    FileUtils.delteTempFile(excelFile);
                    return m;
                }
                importUserInfo = (QaStandardRecord) m.getData();
            }*/
            int rowNumber = 0;
            rowInfo:
            for (int h = 2; h <= rowNum; h++) {
                row = sheet.getRow(h);
                if (isRowEmpty(row)) {
                    continue rowInfo;
                }
                List<QaStandardRecordItem> list1 = new ArrayList<>();
                for (int j = 0; j < ruleMap.size(); j++) {
                    Map ruleMapItem = ruleMap.get(j);

                    //                    List<QaStandardRecordItem> list1 = qaStandardRecordItemService.findList(1, Arrays.asList(Filter.eq("recordId", importUserInfo.getId()),
                    //                            Filter.eq("parameterId", ruleMapItem.get
                    //                                    ("parameterId").toString()),
                    //                            Filter.eq("parameterGroupId", ruleMapItem.get("groupId")
                    //                                    .toString())), null);
                    //                    qaStandardRecordItem.setId(importUserInfo.getId() + "_" + (id + 1 > 9 ? id + 1 : "0" + (id + 1)));
                    //                    ++id;
                    QaStandardRecordItem qaStandardRecordItem = new QaStandardRecordItem();
                    //qaStandardRecordItem.setRecordId(importUserInfo.getId());
                    qaStandardRecordItem.setGroupCategory("");
                    qaStandardRecordItem.setOrders(h - 2);
                    qaStandardRecordItem.setParameterGroupId(baseParameterGroups.get(0).getId());
                    qaStandardRecordItem.setParameterId(ruleMapItem.get("parameterId").toString());
                    qaStandardRecordItem.setParameterAnnexUrl("");
                    qaStandardRecordItem.setGroupId(baseParameterGroups.get(0).getId());
                    qaStandardRecordItem.setType(ruleMapItem.get("type").toString());
                    qaStandardRecordItem.setTypeValidate(ruleMapItem.get("validate1").toString());
                    qaStandardRecordItem.setName(ruleMapItem.get("name").toString());
                    if (row.getCell(j) == null) {
                        qaStandardRecordItem.setParameterValue("");
                    }
                    else if ("date".equals(ruleMapItem.get("type").toString())) {
                        try {
                            Date date = row.getCell(j).getDateCellValue();
                            if (date == null) {
                                qaStandardRecordItem.setParameterValue("");
                            }
                            else {
                                qaStandardRecordItem.setParameterValue
                                        (DateUtil.dateFormat(ruleMapItem.get("validate").toString(), row.getCell(j).getDateCellValue()));
                            }
                        } catch (Exception e) {
                            String rowStr = row.getCell(j).getStringCellValue();
                            if (StringUtils.isEmpty(rowStr)) {
                                qaStandardRecordItem.setParameterValue("");
                            }
                            else if (rowStr.contains("-")) {
                                String[] dates = rowStr.split("-");
                                String startDate = "-".equals(dates[0])
                                        || "".equals(dates[0]) ? "" : DateUtil.dateFormat(ruleMapItem.get("validate").toString(),
                                        DateUtil.dateParse(ruleMapItem.get
                                                        ("validate").toString(),
                                                dates[0]));
                                String endDate = dates.length == 1 || "至今".equals
                                        (dates[1]) ? "至今" : DateUtil.dateFormat(ruleMapItem.get("validate").toString(),
                                        DateUtil.dateParse(ruleMapItem.get
                                                        ("validate").toString(),
                                                dates[1]));
                                qaStandardRecordItem.setParameterValue
                                        (startDate + "-" + endDate);
                            }
                            else {
                                Date date = DateUtil.dateParse(ruleMapItem.get
                                        ("validate").toString(), rowStr);
                                if (date == null) {
                                    FileUtils.delteTempFile(excelFile);
                                    return Message.error
                                            (sheetName + " 第" + (h + 1) + "行" + ruleMapItem
                                                    .get("name") + ",时间格式有误");
                                }
                                qaStandardRecordItem.setParameterValue
                                        (DateUtil.dateFormat(ruleMapItem.get("validate").toString(),
                                                date));
                            }
                        }
                    }
                    else {
                        try {
                            qaStandardRecordItem.setParameterValue(row.getCell(j).getStringCellValue());
                        } catch (Exception e) {
                            System.out.println(row.getCell(j).getNumericCellValue());
                            qaStandardRecordItem.setParameterValue("" + row.getCell(j).getNumericCellValue());
                        }
                    }
                    list1.add(qaStandardRecordItem);
                }
                rowNumber++;
                list.add(list1);
            }
            if (rowNumber > rowCount) {
                FileUtils.delteTempFile(excelFile);
                return Message.error(sheetName + "只可录入" + rowCount + "条数据");
            }
        }

        FileUtils.delteTempFile(excelFile);
        if (ObjectUtils.isEmpty(list) || ObjectUtils.isEmpty(list.get(0))) {
            return Message.error("文件录入信息为空或分类不匹配");
        }
        return Message.success(list);
    }

    private List<Map<String, Object>> getRuleMaps(List<BaseParameterGroup> baseParameterGroups, Row row, int columnNum) {
        List<Map<String, Object>> ruleMap = new ArrayList<>();
        for (int k = 0; k < columnNum; k++) {
            if (isRowEmpty(row)) {
                break;
            }
            String parameter = row.getCell(k).getStringCellValue();
            List<BaseParameter> parameters =
                    baseParameterService.findList( Arrays.asList(Filter.eq("name", parameter),
                            Filter.eq("groupId", baseParameterGroups.get(0).getId())), null);
            if (!ObjectUtils.isEmpty(parameters)) {
                ruleMap.add(getStringObjectMap(parameter, parameters.get(0)));
            }
        }
        List<BaseParameter> all = baseParameterService.findParametersByGroupId("all");
        for (BaseParameter baseParameter : all) {
            ruleMap.add(getStringObjectMap(baseParameter.getName(), baseParameter));
        }
        return ruleMap;
    }

    private Map<String, Object> getStringObjectMap(String parameter, BaseParameter baseParameter) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", parameter);
        //参数id
        map.put("parameterId", baseParameter.getId());
        map.put("groupId", baseParameter.getGroupId());
        //参数类型
        map.put("type", baseParameter.getType());
        String validate = baseParameter.getTypeValidate();
        if (validate.contains("|")) {
            validate = validate.split("\\|")[0].split("-")[0];
        }
        map.put("validate", validate);
        map.put("validate1", baseParameter.getTypeValidate());
        map.put("attr0", baseParameter.getAttr0());
        return map;
    }

    private Workbook getWorkbook(File excelFile, FileInputStream is) throws IOException {
        Workbook book;
        try {
            book = new HSSFWorkbook(is);
        } catch (Exception ex) {
            // 解决read error异常
            is = new FileInputStream(excelFile);
            book = new XSSFWorkbook(is);
        }
        return book;
    }

    private Message structStandardRecordNew(Sheet sheet, List<Map<String, Object>> ruleMap, int rowNum, String userId) {
        QaStandardRecord standardRecord = null;
        Class recordClass = QaStandardRecord.class;
        Object object = null;
        try {
            object = recordClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // 第三行为数据
        Row row = sheet.getRow(2);
        if (isRowEmpty(row)) {
            return Message.error("数据录入格式不正确，数据在第三行");
        }
        for (int j = 0; j < ruleMap.size(); j++) {
            // parameterId -> 10100-031
            if (ruleMap.get(j).get("parameterId").toString().equals("10100-031")) {
                continue;
            }
            Field field;
            try {
                field = recordClass.getDeclaredField(ruleMap.get(j).get("attr0").toString());
                field.setAccessible(true);
                if (row.getCell(j) == null) {
                    return Message.error("基础信息-" + ruleMap.get(j).get("name").toString() + "必须填写");
                }
                else if ("sex".equals(field.getName())) {
                    if ("男".equals(row.getCell(j).getStringCellValue())) {
                        field.set(object, 1L);
                    }
                    else {
                        field.set(object, 0L);
                    }
                }
                else {
                    if (Long.class == field.getType()) {
                        field.set(object, Long.parseLong(row.getCell(j).getStringCellValue()));
                    }
                    else if (Date.class == field.getType()) {
                        try {
                            field.set(object, row.getCell(j).getDateCellValue());
                        } catch (Exception e) {
                            String dateStr = row.getCell(j).getStringCellValue();
                            if (StringUtils.isEmpty(dateStr)) {
                                return Message.error("基础信息-" + ruleMap.get(j).get("name").toString() + "必须填写");
                            }
                            else {
                                Date date = DateUtil.dateParse(ruleMap.get(j).get("validate").toString(), dateStr);
                                if (date == null) {
                                    return Message.error("个人基本信息" + ruleMap.get(j).get("name") + ",时间格式有误");
                                }
                                field.set(object, date);
                            }
                        }
                    }
                    else {
                        try {
                            String value = row.getCell(j).getStringCellValue();
                            if (StringUtils.isEmpty(value)) {
                                return Message.error("基础信息-" + ruleMap.get(j).get("name").toString() + "必须填写");
                            }
                            field.set(object, value);
                        } catch (Exception e) {
                            field.set(object, "" + row.getCell(j).getNumericCellValue());
                        }
                    }
                }
                standardRecord = (QaStandardRecord) object;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (standardRecord == null) {
            return Message.error("数据为空");
        }
        return Message.success(standardRecord);
    }

    //    private Message structStandardRecord
    //            (Sheet sheet, List<Map<String, Object>> ruleMap, int rowNum, String userId) {
    //        QaStandardRecord standardRecord = null;
    //        Class recordClass = QaStandardRecord.class;
    //        Object object = null;
    //        try {
    //            object = recordClass.newInstance();
    //        } catch (InstantiationException | IllegalAccessException e) {
    //            e.printStackTrace();
    //        }
    //        int userInfoCount = 0;
    //        userInfo:
    //        for (int i = 2; i <= rowNum; i++) {
    //            Row row = sheet.getRow(i);
    //            for (int j = 0; j < ruleMap.size(); j++) {
    //                // parameterId -> 10100-031
    //                if (ruleMap.get(j).get("parameterId").toString().equals("10100-031")) {
    //                    continue;
    //                }
    //                if (isRowEmpty(row)) {
    //                    if (i > 3) {
    //                        return Message.error("基本信息只能录入一条数据，请确认文件数据是否正确");
    //                    }
    //                    else if (standardRecord == null) {
    //                        List<QaStandardRecord> records =
    //                                qaStandardRecordService.findList
    //                                        (0, Collections.singletonList(Filter.eq("userId", userId)), null);
    //                        if (ObjectUtils.isEmpty(records)) {
    //                            break userInfo;
    //                        }
    //                        else {
    //                            return Message.success();
    //                        }
    //                    }
    //                    else {
    //                        standardRecord.setUserId(userId);
    //                        standardRecord.setModifyDate(new Date());
    //                        return qaStandardRecordService.saveOrUpdateStandardRecord(standardRecord, userId);
    //                    }
    //                }
    //                Field field;
    //                try {
    //                    field = recordClass.
    //                            getDeclaredField(ruleMap.get(j).get("attr0").toString());
    //                    field.setAccessible(true);
    //                    if (row.getCell(j) == null) {
    //                        return Message.error("基础信息-" + ruleMap.get(j).get
    //                                ("name").toString() + "必须填写");
    //                    }
    //                    else if ("sex".equals(field.getName())) {
    //                        if ("男".equals(row.getCell(j).getStringCellValue())) {
    //                            field.set(object, 1L);
    //                        }
    //                        else {
    //                            field.set(object, 0L);
    //                        }
    //                    }
    //                    else {
    //                        if (Long.class == field.getType()) {
    //                            field.set(object, Long.parseLong(row.getCell(j).getStringCellValue()));
    //                        }
    //                        else if (Date.class == field.getType()) {
    //                            try {
    //                                Date d = row.getCell(j).getDateCellValue();
    //                                field.set(object, row.getCell(j).getDateCellValue());
    //                            } catch (Exception e) {
    //                                String dateStr = row.getCell(j).getStringCellValue();
    //                                if (StringUtils.isEmpty(dateStr)) {
    //                                    return Message.error("基础信息-" + ruleMap.get(j).get
    //                                            ("name").toString() + "必须填写");
    //                                }
    //                                else {
    //                                    Date date = DateUtil.dateParse(ruleMap
    //                                            .get(j).get("validate").toString(), dateStr);
    //                                    if (date == null) {
    //                                        return Message.error
    //                                                ("个人基本信息" + ruleMap
    //                                                        .get(j).get("name") + ",时间格式有误");
    //                                    }
    //                                    field.set(object, date);
    //                                }
    //                            }
    //                        }
    //                        else {
    //
    //                            try {
    //                                String value = row.getCell(j)
    //                                        .getStringCellValue();
    //                                if (StringUtils.isEmpty(value)) {
    //                                    return Message.error("基础信息-" + ruleMap.get(j).get
    //                                            ("name").toString() + "必须填写");
    //                                }
    //                                field.set(object, value);
    //                            } catch (Exception e) {
    //                                field.set(object, "" + row.getCell(j).getNumericCellValue());
    //                            }
    //                        }
    //                    }
    //                    standardRecord = (QaStandardRecord) object;
    //                } catch (NoSuchFieldException | IllegalAccessException e) {
    //                    e.printStackTrace();
    //                }
    //            }
    //            userInfoCount++;
    //        }
    //        if (userInfoCount > 1) {
    //            return Message.error("基本信息只能录入一条数据，请确认文件数据是否正确");
    //        }
    //        if (standardRecord == null) {
    //            return Message.error("基础信息数据为空");
    //        }
    //        standardRecord.setUserId(userId);
    //        standardRecord.setModifyDate(new Date());
    //        return qaStandardRecordService.saveOrUpdateStandardRecord(standardRecord, userId);
    //
    //    }

    private static boolean isRowEmpty(Row row) {
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

}
