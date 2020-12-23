package com.bdjbd.service.common.impl;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.common.ExcelService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shujukuss
 * @version 1.0
 * @className ExcelServiceImpl
 * @description
 * @date 2020/12/22
 **/
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {

	@Autowired
	public QaStandardRecordService qaStandardRecordService;

	@Override
	public List<QaStandardRecord> deal(byte[] bytes) throws IOException {
		List<QaStandardRecord> result = new ArrayList<QaStandardRecord>();
		XSSFWorkbook wb = new XSSFWorkbook(new ByteArrayInputStream(bytes));
		// 第一面是0
		XSSFSheet sheet = wb.getSheetAt(0);
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			XSSFRow xssfRow = sheet.getRow(rowNum);
			QaStandardRecord record = new QaStandardRecord();

			// 身份证号（干部登记号）
			record.setIdCard(xssfRow.getCell(0).toString());
			// 人员类别
			record.setPersonnelCategory(xssfRow.getCell(1).toString());
			// 姓名
			record.setName(xssfRow.getCell(2).toString());
			// 现部职别（现部别）
			record.setTechnologyCategory(xssfRow.getCell(3).toString());
			// 现专业技术职务（技术职务）
			record.setTechnologyTitle(xssfRow.getCell(4).toString());
			// 现专业技术职务时间（技职时间）
			record.setAppointTime(dateParseYMD(xssfRow.getCell(5).toString()));
			// 性别
			// record.setSex(Integer.valueOf(xssfRow.getCell(6).toString()));
			// 民族
			// 籍贯
			// 出生年月（出生时间）
			record.setBirth(dateParseYMD(xssfRow.getCell(9).toString()));
			// 入伍/工作时间（入伍时间）
			record.setEnlistmentTime(dateParseYMD(xssfRow.getCell(10).toString()));
			// 政治面貌
			record.setPoliticalAffiliation(xssfRow.getCell(11).toString());
			result.add(record);
		}
		wb.close();
		return result;
	}

	public void update(List<QaStandardRecord> list) {
		List<String> emList = new ArrayList<>();
		for (QaStandardRecord record : list) {
			String idCard = record.getIdCard();
			QaStandardRecord db = qaStandardRecordService.findByIdCard(idCard);
			if (db == null) {
				emList.add(record.getName());
			}
			// 身份证号（干部登记号）
			// 人员类别
			if (compare(db.getPersonnelCategory(), record.getPersonnelCategory())) {
				db.setPersonnelCategory(record.getPersonnelCategory());
			}
			// 姓名
			if (compare(db.getName(), record.getName())) {
				db.setName(record.getName());
			}
			// 现部职别（现部别）
			if (compare(db.getTechnologyCategory(), record.getTechnologyCategory())) {
				db.setTechnologyCategory(record.getTechnologyCategory());
				// 现专业技术职务（技术职务）
				if (compare(db.getTechnologyTitle(), record.getTechnologyTitle())) {
					db.setTechnologyTitle(record.getTechnologyTitle());
					// 现专业技术职务时间（技职时间）
					if (compare(db.getAppointTime().toString(), record.getAppointTime().toString())) {
						db.setAppointTime(record.getAppointTime());
					}
				}
				// 性别
				// record.setSex(Integer.valueOf(xssfRow.getCell(6).toString()));
				// 民族
				// 籍贯
				// 出生年月（出生时间）
				if (compare(db.getBirth().toString(), record.getBirth().toString())) {
					db.setBirth(record.getBirth());
				}
				// 入伍/工作时间（入伍时间）
				if (compare(db.getEnlistmentTime().toString(), record.getEnlistmentTime().toString())) {
					db.setEnlistmentTime(record.getEnlistmentTime());
				}
				// 政治面貌
				if (compare(db.getPoliticalAffiliation(), record.getPoliticalAffiliation())) {
					db.setPoliticalAffiliation(record.getPoliticalAffiliation());
				}
				qaStandardRecordService.update(db);
			}
		}

	}

	boolean compare(String one, String two) {
		if (one == null && two == null) {
			// 相同
			return false;
		}
		if (one == null && two != null || one != null && two == null) {
			// 相同
			return false;
		}
		if (one.equals(two)) {
			return false;
		}
		return true;
	}

	private Date dateParseYMD(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

}
