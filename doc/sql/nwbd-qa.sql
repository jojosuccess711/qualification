/*
 Navicat Premium Data Transfer

 Source Server         : localhost57
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : nwbd-qa

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 23/12/2020 01:05:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_parameter
-- ----------------------------
DROP TABLE IF EXISTS `base_parameter`;
CREATE TABLE `base_parameter`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数组id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型\r\n\r\nselect 下拉框\r\nradio 单选\r\ntext 文本框\r\nselect&text 下拉框可输入\r\ndate 日期\r\n',
  `type_validate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型校验\r\n\r\ntype      ---  value\r\nselect    关联definition表type值\r\nradio    关联definition表type值\r\ntext       integer 正整数\r\n            name 姓名\r\n            default 默认（小于255）\r\n            number 小数\r\ndate     yyyy.MM.dd\r\n            yyyy.MM\r\n            yyyy.MM-yyyy.MM|至今\r\n            yyyy',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '示例',
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填字段\r\n1：必填\r\n0：非必填',
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态\r\n0 禁用\r\n1 启用',
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `groupIdIndex`(`group_id`) USING BTREE,
  INDEX `statusIndex`(`status`) USING BTREE,
  INDEX `name_index`(`name`) USING BTREE,
  INDEX `type_validate_index`(`type_validate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_parameter
-- ----------------------------
INSERT INTO `base_parameter` VALUES ('10000', 'all', '附件', NULL, 'file', '2010000000', 100, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-001', '10100', '姓名', NULL, 'text', 'name', 1, 'name', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-002', '10100', '性别', NULL, 'select', '2010007001', 2, 'sex', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-003', '10100', '身份证号', NULL, 'text', 'default', 3, 'idCard', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-004', '10100', '人员类别', NULL, 'select', '2010007002', 5, 'personnelCategory', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-005', '10100', '出生年月', NULL, 'date', 'yyyy.MM', 4, 'birth', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-006', '10100', '入伍/工作时间', NULL, 'date', 'yyyy.MM', 6, 'enlistmentTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-008', '10100', '政治面貌', NULL, 'select', '2010007003', 8, 'politicalAffiliation', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-009', '10100', '党团时间', NULL, 'date', 'yyyy.MM', 9, 'caucusTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-010', '10100', '最高学历', NULL, 'select', '2010002005', 10, 'lastEducation', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-011', '10100', '最高学历时间', NULL, 'date', 'yyyy.MM', 11, 'highestGetTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-012', '10100', '学位', NULL, 'select', '2010007005', 12, 'highestDegree', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-013', '10100', '学位时间', NULL, 'date', 'yyyy.MM', 13, 'highestDegreeTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-014', '10100', '最高学位专业', NULL, 'text', 'default', 14, 'highestSchoolMajor', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-015', '10100', '最后毕业院校', NULL, 'text', 'default', 15, 'lastSchoolMajor', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-016', '10100', '毕业时间', NULL, 'date', 'yyyy.MM', 16, 'graduateTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-017', '10100', '院校类别', NULL, 'select', '2010007006', 17, 'schoolCategory', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-018', '10100', '现专业技术职务', NULL, 'select', '2010007007', 27, 'technologyTitle', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-019', '10100', '现专业技术职务时间', NULL, 'date', 'yyyy.MM', 28, 'appointTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-020', '10100', '现技术等级', NULL, 'select', '2010007008', 20, 'technologyLevel', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-021', '10100', '现技术等级时间', NULL, 'date', 'yyyy.MM', 21, 'technologyLevelTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-022', '10100', '现军衔/级别', NULL, 'select', '2010007009', 22, 'militaryRank', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-023', '10100', '现军衔/级别时间', NULL, 'date', 'yyyy.MM', 23, 'militaryRankTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-024', '10100', '现技职类别', NULL, 'select', '2010007010', 24, 'technologyCategory', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-025', '10100', '现技职类别时间', NULL, 'date', 'yyyy.MM', 25, 'technologyCategoryTime', 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-026', '10100', '现部职别', NULL, 'text', 'default', 26, 'officialRank', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-028', '10100', '现职称所属岗位', NULL, 'select', 'default', 29, 'categoryType', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10100-029', '10100', '实际工作单位', NULL, 'select', '1010002901', 30, 'attr0', NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-002', '10200', '年度', NULL, 'date', 'yyyy', 2, NULL, 'yyyy年', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-003', '10200', '学期', NULL, 'select', '2010005012', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-004', '10200', '授课 课程名称', NULL, 'text', 'default', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-005', '10200', '课表课时', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-006', '10200', '理论课时', NULL, 'text', 'integer', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-007', '10200', '实践课时', NULL, 'text', 'integer', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-008', '10200', '承担任务', NULL, 'select', '2010005001', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-009', '10200', '个人主讲课时总数', NULL, 'text', 'integer', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-010', '10200', '学员层次', NULL, 'select', '2010005002', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-011', '10200', '授课教学班', NULL, 'text', 'default', 11, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-012', '10200', '是否优质课', NULL, 'select', '1020001201', 12, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-013', '10200', '是否军队级以上金课', NULL, 'select', '1020001301', 13, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-014', '10200', '净课时量（学时）（不含折算课时，不乘系数）', NULL, 'text', 'integer', 14, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10200-015', '10200', '军队级金课排名', NULL, 'text', 'integer', 15, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-001', '10300', '时间', NULL, 'date', 'yyyy.MM', 1, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-002', '10300', '奖励类别', NULL, 'select&text', '2010005007', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-003', '10300', '奖励等级', NULL, 'select&text', '2010005008', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-004', '10300', '项目名称', NULL, 'text', 'default', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-005', '10300', '排序', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-006', '10300', '证书编号（文件号）', NULL, 'text', 'default', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-007', '10300', '组织单位', NULL, 'text', 'default', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10300-008', '10300', '奖励级别', NULL, 'select', '1030000801', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10400-001', '10400', '教员姓名', NULL, 'text', 'default', 1, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10400-002', '10400', '课程名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10400-003', '10400', '学员层次', NULL, 'select', '2010005002', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10400-004', '10400', '评价方式', NULL, 'select', '2010005003', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10400-005', '10400', '评价结果', NULL, 'select', '2010005005', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10400-006', '10400', '教学事故', NULL, 'select', '2010005006', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-001', '10500', '指导的青年教员获得校级以上教学比赛奖励', NULL, 'text', 'default', 1, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-002', '10500', '指导的青年教员获得校级以上教学比赛奖励（等级）', NULL, 'select', '1050000201', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-003', '10500', '担任本科全程导师或指导本科优异生或本科学员毕业设计（人次）', NULL, 'text', 'integer', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-004', '10500', '指导学员获得校级优秀毕业设计（人次）', NULL, 'text', 'integer', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-005', '10500', '指导硕士生或博士生数量（人）', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-006', '10500', '有无学生论文被评为全军优博优硕', NULL, 'select', '2010005009', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10500-007', '10500', '指导的研究生学位论文在各级抽查中有无不合格', NULL, 'select', '2010005009', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-001', '10600', '项目编号', NULL, 'text', 'default', 1, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-002', '10600', '项目名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-003', '10600', '项目负责人', NULL, 'text', 'default', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-004', '10600', '承担单位', NULL, 'text', 'default', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-005', '10600', '项目总经费', NULL, 'text', 'default', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-006', '10600', '项目起止时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 6, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-007', '10600', '项目密级', NULL, 'select', '2010006001', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-008', '10600', '项目来源', NULL, 'select', '2010006002', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-009', '10600', '项目类别', NULL, 'select', '2010006003', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-010', '10600', '项目级别', NULL, 'select', '2010006004', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-011', '10600', '本人排序', NULL, 'text', 'integer', 12, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10600-012', '10600', '项目类型', NULL, 'select', '1060001201', 11, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-002', '10700', '知识产权名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-003', '10700', '类别', NULL, 'select', '2010006005', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-004', '10700', '发明人/完成人', NULL, 'text', 'default', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-005', '10700', '代理机构', NULL, 'text', 'default', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-006', '10700', '密级', NULL, 'select', '2010006001', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-007', '10700', '证书编号', NULL, 'text', 'default', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-008', '10700', '获证时间', NULL, 'date', 'yyyy.MM.dd', 8, NULL, 'yyyy年MM月dd日', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10700-009', '10700', '发明人排序', NULL, 'text', 'integer', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-002', '10800', '获奖项目名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-003', '10800', '奖励级别', NULL, 'select', '2010006006', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-004', '10800', '奖励类型', NULL, 'select&text', '2010006007', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-005', '10800', '等级', NULL, 'select', '2010006008', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-006', '10800', '时间', NULL, 'date', 'yyyy', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-007', '10800', '个人排序', NULL, 'text', 'integer', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-008', '10800', '奖励证书编号', NULL, 'text', 'default', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10800-009', '10800', '完成单位', NULL, 'text', 'default', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-002', '10900', '论文题目', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-003', '10900', '本人排序', NULL, 'text', 'integer', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-004', '10900', '录用时间', NULL, 'date', 'yyyy.MM', 4, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-005', '10900', '录用期刊', NULL, 'text', 'default', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-006', '10900', '期刊刊号', NULL, 'text', 'default', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-007', '10900', '检索类型', NULL, 'select', '2010006009', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('10900-008', '10900', '影响因子', NULL, 'text', 'default', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-002', '11000', '著作名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-003', '11000', '参与类别', NULL, 'select', '2010006010', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-004', '11000', '参与者工作量（字数/万字）', NULL, 'text', 'integer', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-005', '11000', '著作类型', NULL, 'select', '2010006011', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-006', '11000', '著作字数（万字）', NULL, 'text', 'integer', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-007', '11000', '出版时间', NULL, 'date', 'yyyy.MM', 7, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-008', '11000', '出版单位', NULL, 'text', 'default', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11000-009', '11000', '出版书号', NULL, 'text', 'default', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-002', '11100', '教材名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-003', '11100', '参与类别', NULL, 'select', '2010006010', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-004', '11100', '参与者工作量（字数/万字）', NULL, 'text', 'integer', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-005', '11100', '教材类型', NULL, 'select', '2010006012', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-006', '11100', '教材字数（万字）', NULL, 'text', 'integer', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-007', '11100', '出版时间', NULL, 'date', 'yyyy.MM', 7, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-008', '11100', '出版单位', NULL, 'text', 'default', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11100-009', '11100', '出版书号', NULL, 'text', 'default', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-002', '11200', '标准名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-003', '11200', '主管单位', NULL, 'text', 'default', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-004', '11200', '参与人员', NULL, 'text', 'default', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-005', '11200', '本人排序', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-006', '11200', '标准类型', NULL, 'select&text', '2010006013', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-007', '11200', '项目类别', NULL, 'select', '2010006014', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-008', '11200', '报批时间', NULL, 'date', 'yyyy.MM', 8, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-009', '11200', '是否颁布', NULL, 'select', '2010006015', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11200-010', '11200', '标准编号', NULL, 'text', 'default', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-002', '11300', '报告名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-003', '11300', '本人排序', NULL, 'text', 'integer', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-004', '11300', '报告类别', NULL, 'select', '2010006016', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-005', '11300', '报告字数（万字）', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-006', '11300', '报送单位（军以上）', NULL, 'text', 'default', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-007', '11300', '报送时间', NULL, 'date', 'yyyy.MM', 7, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-008', '11300', '肯定性批示内容', NULL, 'text', 'default', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-009', '11300', '批示领导', NULL, 'text', 'default', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11300-010', '11300', '批示领导级别', NULL, 'select', '2010006017', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11400-002', '11400', '会议时间', NULL, 'date', 'yyyy.MM', 2, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11400-003', '11400', '会议地点', NULL, 'text', 'default', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11400-004', '11400', '会议名称', NULL, 'text', 'default', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11400-005', '11400', '会议类别', NULL, 'select&text', '2010006018', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11400-006', '11400', '邀请报告', NULL, 'select', '2010006019', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11400-007', '11400', '报告题目', NULL, 'text', 'default', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11500-002', '11500', '团体名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11500-003', '11500', '审批时间', NULL, 'date', 'yyyy.MM', 3, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11500-004', '11500', '担任职务', NULL, 'select', '2010006020', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11600-002', '11600', '期刊名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11600-003', '11600', '收录类型', NULL, 'select', '2010006021', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11600-004', '11600', '时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 4, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11600-005', '11600', '担任职务', NULL, 'select&text', '2010006022', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11700-001', '11700', '起止时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 10, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11700-002', '11700', '大单位', NULL, 'select', '2010003001', 20, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11700-003', '11700', '单位', NULL, 'text', 'default', 30, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11700-004', '11700', '职务', NULL, 'text', 'default', 40, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11700-005', '11700', '类别', NULL, 'select', '2010003004', 50, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11700-006', '11700', '表彰奖励情况', NULL, 'select', '1170000601', 60, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11800-001', '11800', '起止时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 10, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11800-002', '11800', '大单位', NULL, 'select', '2010004002', 20, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11800-003', '11800', '培训院校', NULL, 'text', 'default', 30, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11800-004', '11800', '培训专业', NULL, 'text', 'default', 40, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11800-005', '11800', '培训层次', NULL, 'select&text', '2010004014', 50, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11800-006', '11800', '培训形式', NULL, 'select', '2010004003', 60, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11900-001', '11900', '起止时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 10, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11900-002', '11900', '派遣单位', NULL, 'text', 'default', 20, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11900-003', '11900', '执行任务国家或院区', NULL, 'text', 'default', 30, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('11900-004', '11900', '任务类别', NULL, 'select&text', '2010004004', 40, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12000-001', '12000', '起止时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 10, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12000-002', '12000', '留学途径', NULL, 'select&text', '2010004005', 20, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12000-003', '12000', '国家', NULL, 'text', 'default', 30, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12000-004', '12000', '留学院校或单位', NULL, 'text', 'default', 40, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12000-005', '12000', '专业方向', NULL, 'text', 'default', 50, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-001', '12100', '起止时间', NULL, 'date', 'yyyy.MM-yyyy.MM|至今', 10, NULL, 'yyyy年MM月-yyyy年MM月|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-002', '12100', '任务名称', NULL, 'text', 'default', 20, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-003', '12100', '担任职务', NULL, 'text', 'default', 30, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-004', '12100', '任务来源', NULL, 'select', '2010004006', 40, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-005', '12100', '任务类别', NULL, 'select', '2010004007', 50, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-006', '12100', '表彰奖励情况', NULL, 'select', '2010004008', 60, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12100-007', '12100', '施奖单位', NULL, 'text', 'default', 70, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-001', '12200', '部队单位', NULL, 'text', 'default', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-002', '12200', '任务内容（代号）', NULL, 'select&text', '2010004010', 20, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-003', '12200', '工作时间', NULL, 'date', 'yyyy.MM.dd-yyyy.MM.dd|至今', 30, NULL, 'yyyy年MM月dd日-yyyy年MM月dd日|至今', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-004', '12200', '派遣单位', NULL, 'text', 'default', 40, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-005', '12200', '表彰奖励情况', NULL, 'select', '2010004011', 50, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-006', '12200', '工作内容', NULL, 'text', 'default', 60, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12200-007', '12200', '部队级别', '', 'select', '2010004009', 15, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12300-001', '12300', '各级人才工程计划', NULL, 'select&text', '2010004012', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12300-002', '12300', '入选时间', NULL, 'date', 'yyyy.MM', 20, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12300-003', '12300', '各级人才表彰项目', NULL, 'select&text', '2010004013', 30, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12300-004', '12300', '表彰时间', NULL, 'date', 'yyyy.MM', 40, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-002', '12400', '工作名称', NULL, 'text', 'default', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-003', '12400', '本人排序', NULL, 'text', 'integer', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-004', '12400', '工作属性', NULL, 'select', '1240000401', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-005', '12400', '工作内容', NULL, 'select', '1240000501', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-006', '12400', '工作级别', NULL, 'select', '1240000601', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-007', '12400', '是否颁布实施', NULL, 'select', '1240000701', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12400-008', '12400', '备注', NULL, 'text', 'default', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-002', '12500', '积极落实军委机关相关政策及任务（项）', NULL, 'text', 'integer', 1, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-003', '12500', '推动校内外重要战略合作（项）', NULL, 'text', 'integer', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-004', '12500', '技术研发成果是否在部队列装使用', NULL, 'select', '1250000401', 14, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-005', '12500', '科技成果转换收益（万元）', NULL, 'text', 'integer', 51, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-006', '12500', '促进学校科研成果转化（项）', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-007', '12500', '促进科研管理信息化建设（项）', NULL, 'text', 'integer', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-008', '12500', '在学校以上范围内组织培训或技术交流会（项）', NULL, 'text', 'integer', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-009', '12500', '组织学校质量管理体系审核（项）', NULL, 'text', 'integer', 11, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-010', '12500', '是否取得标志性成果', NULL, 'select', '1250001001', 13, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-011', '12500', '积极落实军委机关相关政策及任务情况', NULL, 'textarea', 'textarea', 2, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-012', '12500', '推动校内外重要战略合作情况', NULL, 'textarea', 'textarea', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-013', '12500', '促进学校科研成果转化情况', NULL, 'textarea', 'textarea', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-014', '12500', '促进科研管理信息化建设情况', NULL, 'textarea', 'textarea', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-015', '12500', '在学校以上范围内组织培训或技术交流会情况', NULL, 'textarea', 'textarea', 10, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12500-016', '12500', '组织学校质量管理体系审核情况', NULL, 'textarea', 'textarea', 12, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-010', '12600', '任务类型', NULL, 'select', '2010026001', 1, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-020', '12600', '任务时间', NULL, 'date', 'yyyy.MM', 2, NULL, 'yyyy年MM月', '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-030', '12600', '工作内容', NULL, 'select', '2010026002', 3, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-040', '12600', '下达机关', NULL, 'select', '2010026003', 4, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-050', '12600', '排名', NULL, 'text', 'integer', 5, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-060', '12600', '提出关键决策建议、推动创新成果转换，对提升战斗力发挥重要作用情况', NULL, 'text', 'default', 6, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-070', '12600', '解决重大技术难题、提出关键决策建议和作出重大贡献，收到军级以上单位表彰情况', NULL, 'text', 'default', 7, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-080', '12600', '采纳应用单位', NULL, 'select', '2010026001', 8, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('12600-090', '12600', '单位级别', NULL, 'select', '2010026004', 9, NULL, NULL, '1', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-001-001', '5-10500-001', '研究生姓名', NULL, 'text', 'name', 10, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-001-002', '5-10500-001', '论文题目', NULL, 'text', 'default', 20, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-001-003', '5-10500-001', '抽查年度', NULL, 'date', 'yyyy', 30, NULL, 'yyyy年', '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-001-004', '5-10500-001', '抽查单位', NULL, 'select', '2010005010', 40, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-001-005', '5-10500-001', '评选结果', NULL, 'select', '2010005013', 50, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-002-001', '5-10500-002', '研究生姓名', NULL, 'text', 'name', 10, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-002-002', '5-10500-002', '论文题目', NULL, 'text', 'default', 20, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-002-003', '5-10500-002', '评优年度', NULL, 'date', 'yyyy', 30, NULL, 'yyyy年', '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-002-004', '5-10500-002', '评优单位', NULL, 'select', '2010005010', 40, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-002-005', '5-10500-002', '抽查结果', NULL, 'select', '2010005011', 50, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-003-001', '5-10500-003', '博士后姓名', NULL, 'text', 'default', 1, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-003-002', '5-10500-003', '进站时间', NULL, 'date', 'yyyy.MM', 2, NULL, 'yyyy年MM月', '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-003-003', '5-10500-003', '指导类型', NULL, 'select', '2010008001', 3, NULL, NULL, '0', NULL, NULL, 1, NULL);
INSERT INTO `base_parameter` VALUES ('5-10500-003-004', '5-10500-003', '进站获资助情况', NULL, 'select', '2010008002', 4, NULL, NULL, '0', NULL, NULL, 1, NULL);

-- ----------------------------
-- Table structure for base_parameter_group
-- ----------------------------
DROP TABLE IF EXISTS `base_parameter_group`;
CREATE TABLE `base_parameter_group`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '组类型\r\n\r\n0. 默认\r\n1. 基本信息（暂时未启用）\r\n2. 课堂教学信息采集标准\r\n3. 教学奖励信息采集标准\r\n4. 教学评价信息采集标准\r\n5. 教学指导信息采集标准\r\n6. 科研项目信息采集标准\r\n7. 专利信息采集标准\r\n8. 科技奖励信息采集标准\r\n9. 学术论文信息采集标准\r\n10.著作信息采集标准\r\n11.教材信息采集标准\r\n12.军标信息采集标准\r\n13.咨询报告信息采集标准\r\n14.参加学术会议信息采集标准\r\n15.参加学术团体信息采集标准\r\n16.担任收录期刊审稿职务信息采集标准\r\n17.部队任（代）职经历信息采集标准\r\n18.受训经历信息采集标准\r\n19.维和援外任务经历信息采集标准\r\n20.出国留学经历信息采集标准\r\n21.参加重大军事行动/部队活动信息采集标准\r\n22.服务部队信息采集标准\r\n23.获人才工程计划和奖励表彰项目信息采集标准',
  `titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1 个人基本信息; 2 教务工作业绩; 3 科研工作业绩;\r\n4 工程工作业绩；',
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用于限制当前标准可以录入的数量\r\n0：不可录入\r\n1：录入一组 （2、3、4类推）\r\n-1：不限制\r\n',
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(11) NULL DEFAULT NULL COMMENT '层级',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态\r\n0 禁用\r\n1 启用',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `status_index`(`status`) USING BTREE,
  INDEX `name_index`(`name`) USING BTREE,
  INDEX `grade_index`(`grade`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_parameter_group
-- ----------------------------
INSERT INTO `base_parameter_group` VALUES ('10100', '2020-03-06 14:16:45', '2020-03-06 14:16:45', '个人基本信息', 0, '个人基本信息采集标准', NULL, 0, NULL, NULL, '个人基本信息', '1', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10200', '2020-03-11 20:04:58', '2020-03-11 20:04:58', '课堂教学信息', 2, '课堂教学信息采集标准', NULL, 0, NULL, NULL, '教务工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10300', '2020-03-11 20:04:59', '2020-03-11 20:04:59', '教学奖励信息', 3, '教学奖励信息采集标准', NULL, 30, NULL, NULL, '教务工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10400', '2020-03-11 20:05:01', '2020-03-11 20:05:01', '教学评价信息', 4, '教学评价信息采集标准', NULL, 40, NULL, NULL, '教务工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10500', '2020-03-11 17:34:15', '2020-03-11 17:34:15', '教学指导信息', 5, '教学指导信息采集标准', NULL, 50, NULL, NULL, '教务工作业绩', '1', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10600', '2020-03-11 20:05:02', '2020-03-11 20:05:02', '科研项目信息', 6, '科研项目信息采集标准', NULL, 60, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10700', '2020-03-11 20:05:04', '2020-03-11 20:05:04', '专利信息', 7, '专利信息采集标准', NULL, 70, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10800', '2020-03-11 20:05:05', '2020-03-11 20:05:05', '科技奖励信息', 8, '科技奖励信息采集标准', NULL, 80, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('10900', '2020-03-11 20:05:07', '2020-03-11 20:05:07', '学术论文信息', 9, '学术论文信息采集标准', NULL, 90, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11000', '2020-03-11 20:05:09', '2020-03-11 20:05:09', '著作信息', 10, '著作信息采集标准', NULL, 100, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11100', '2020-03-11 20:05:10', '2020-03-11 20:05:10', '教材信息', 11, '教材信息采集标准', NULL, 110, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11200', '2020-03-11 20:05:12', '2020-03-11 20:05:12', '军标信息', 12, '军标信息采集标准', NULL, 120, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11300', '2020-03-11 20:05:13', '2020-03-11 20:05:13', '咨询报告信息', 13, '咨询报告信息采集标准', NULL, 130, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11400', '2020-03-11 20:05:15', '2020-03-11 20:05:15', '参加学术会议信息', 14, '参加学术会议信息采集标准', NULL, 140, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11500', '2020-03-11 20:05:17', '2020-03-11 20:05:17', '参加学术团体信息', 15, '参加学术团体信息采集标准', NULL, 150, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11600', '2020-03-11 20:05:18', '2020-03-11 20:05:18', '担任收录期刊审稿职务信息', 16, '担任收录期刊审稿职务信息采集标准', NULL, 160, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11700', '2020-03-11 20:05:20', '2020-03-11 20:05:20', '部队任（代）职经历信息', 17, '部队任（代）职经历信息采集标准', NULL, 170, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11800', '2020-03-11 20:05:22', '2020-03-11 20:05:22', '受训经历信息', 18, '受训经历信息采集标准', NULL, 180, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('11900', '2020-03-11 20:05:23', '2020-03-11 20:05:23', '维和援外任务经历信息', 19, '维和援外任务经历信息采集标准', NULL, 190, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12000', '2020-03-11 20:05:25', '2020-03-11 20:05:25', '出国留学经历信息', 20, '出国留学经历信息采集标准', NULL, 200, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12100', '2020-03-12 19:37:11', '2020-03-12 19:37:11', '参加军以上重大军事行动或部队活动信息', 21, '参加军以上重大军事行动/部队活动信息采集标准', NULL, 210, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12200', '2020-03-11 20:05:30', '2020-03-11 20:05:30', '日常服务部队信息', 22, '日常服务部队信息采集标准', NULL, 220, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12300', '2020-03-11 20:05:34', '2020-03-11 20:05:34', '获人才工程计划和人才奖励表彰项目信息', 23, '获人才工程计划和人才奖励表彰项目信息采集标准', NULL, 230, NULL, NULL, '个人基本信息', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12400', '2020-03-11 20:05:36', '2020-03-11 20:05:36', '完成政策法规、制度办法、重大技术等工作信息', 24, '完成政策法规、制度办法、重大技术等工作信息采集标准', NULL, 240, NULL, NULL, '科研工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12500', '2020-03-11 20:06:14', '2020-03-11 20:06:14', '其他工作信息', 25, '其他工作信息采集标准', NULL, 250, NULL, NULL, '科研工作业绩', '1', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('12600', '2020-03-11 20:35:04', '2020-03-11 20:35:04', '工程任务保障', 25, '工程任务保障情况采集标准', NULL, 260, NULL, NULL, '工程工作业绩', '10', NULL, NULL, NULL, 0, 1);
INSERT INTO `base_parameter_group` VALUES ('5-10500-001', '2020-03-06 14:35:11', '2020-03-06 14:35:11', '有无学生论文被评为全军优博优硕', 0, '有无学生论文被评为全军优博优硕', NULL, 10, NULL, NULL, NULL, '20', NULL, NULL, NULL, 1, 1);
INSERT INTO `base_parameter_group` VALUES ('5-10500-002', '2020-03-06 14:35:13', '2020-03-06 14:35:13', '指导的研究生学位论文在各级抽查中有无不合格', 0, '指导的研究生学位论文在各级抽查中有无不合格', NULL, 20, NULL, NULL, NULL, '20', NULL, NULL, NULL, 1, 1);
INSERT INTO `base_parameter_group` VALUES ('5-10500-003', '2020-03-19 18:29:40', '2020-03-19 18:29:40', '指导硕士生或博士生数量（人）', 0, '指导硕士生或博士生数量（人）', NULL, 30, NULL, NULL, NULL, '20', NULL, NULL, NULL, 1, 1);

-- ----------------------------
-- Table structure for base_relation_parameter_definition
-- ----------------------------
DROP TABLE IF EXISTS `base_relation_parameter_definition`;
CREATE TABLE `base_relation_parameter_definition`  (
  `parameter_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数表id (base_parameter)',
  `relation_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简单数据定义 关联id\r\n\r\ntype=0： base_simple_definition表type\r\ntype=1:   base_simple_definition表id',
  `parameter_group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数组id (base_parameter_group)',
  `required` bit(1) NULL DEFAULT b'0' COMMENT '是否必填',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型\r\n\r\n0：base_simple_definition表type\r\n1：base_simple_definition表id\r\n2：文本框',
  UNIQUE INDEX `un`(`parameter_id`, `parameter_group_id`) USING BTREE,
  INDEX `relationIdIndex`(`relation_id`) USING BTREE,
  INDEX `paramIdIndex`(`parameter_id`) USING BTREE,
  INDEX `parameter_group_id_index`(`parameter_group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数组与(定义/参数)关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_relation_parameter_definition
-- ----------------------------
INSERT INTO `base_relation_parameter_definition` VALUES ('10500-006', '2010005009', '5-10500-001', b'0', 0);
INSERT INTO `base_relation_parameter_definition` VALUES ('10500-007', '2010005009', '5-10500-002', b'0', 0);
INSERT INTO `base_relation_parameter_definition` VALUES ('10500-005', NULL, '5-10500-003', b'0', 2);

-- ----------------------------
-- Table structure for base_simple_definition
-- ----------------------------
DROP TABLE IF EXISTS `base_simple_definition`;
CREATE TABLE `base_simple_definition`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `orders` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  `status` bit(1) NULL DEFAULT b'1' COMMENT '状态 0禁用 1启用',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '领域ID qa_category表主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '名称',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '类型（根据实际场景定义）',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_index`(`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简单数据定义' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_simple_definition
-- ----------------------------
INSERT INTO `base_simple_definition` VALUES ('2010-07', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '男', '性别', '2010002001', 'gender', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-08', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '女', '性别', '2010002001', 'gender', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-09', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '军人干部', '人员类别', '2010002002', 'personalType', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-10', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '文职人员', '人员类别', '2010002002', 'personalType', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-100', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '正高', '评审职称', '2010002003', 'accreditationTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-101', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '副高', '评审职称', '2010002003', 'accreditationTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-102', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '中职', '评审职称', '2010002003', 'accreditationTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-103', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '群众', '政治面貌', '2010002004', 'politicalAffiliation', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-104', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '团员', '政治面貌', '2010002004', 'politicalAffiliation', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-105', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '党员', '政治面貌', '2010002004', 'politicalAffiliation', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-106', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '大学', '最高学历', '2010002005', 'highestDegree', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-107', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '硕士研究生', '最高学历', '2010002005', 'highestDegree', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-108', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '博士研究生', '最高学历', '2010002005', 'highestDegree', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-109', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '学士', '学位', '2010002006', 'academicDegree', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-11', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '硕士', '学位', '2010002006', 'academicDegree', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-110', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '博士', '学位', '2010002006', 'academicDegree', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-111', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '211', '院校类别', '2010002007', 'collegeCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-112', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '985', '院校类别', '2010002007', 'collegeCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-113', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '普通全日制', '院校类别', '2010002007', 'collegeCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-114', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '军内院校', '院校类别', '2010002007', 'collegeCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-144', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术一级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-145', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术二级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-146', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术三级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-147', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术四级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-148', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术五级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-149', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术六级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-15', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术七级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-150', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术八级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-151', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术九级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-152', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术十级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-153', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术十一级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-154', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术十二级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-155', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '专业技术十三级', '现技术等级', '2010002009', 'technicalLevel', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-156', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '大校', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-157', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '上将', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-158', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '中将', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-159', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '少将', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-16', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '上校', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-160', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '中校', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-161', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '少校', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-162', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '上尉', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-163', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '中尉', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-164', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '少尉', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-165', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改特级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-166', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改1级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-167', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改2级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-168', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改3级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-169', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改4级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-17', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改5级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-170', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改6级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-171', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改7级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-172', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '转改8级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-173', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '特级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-174', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '1级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-175', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '2级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-176', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '3级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-177', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '4级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-178', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '5级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-179', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '6级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-18', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '7级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-180', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '8级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-181', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '9级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-182', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '10级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-183', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '11级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-184', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '12级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-185', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '13级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-186', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '14级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-187', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '15级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-188', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '16级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-189', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '17级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-19', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '18级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-190', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '19级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-191', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '20级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-192', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '21级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-193', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '22级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-194', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '23级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-195', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '24级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-196', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '25级', '现军衔/级别', '2010002010', 'militaryRank', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-197', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '初职', '现技职类别', '2010002011', 'technicalTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-198', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '中职', '现技职类别', '2010002011', 'technicalTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-199', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '副高职', '现技职类别', '2010002011', 'technicalTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-20', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '正高职', '现技职类别', '2010002011', 'technicalTitle', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-200', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '训练管理系助教', '现部职别', '2010002012', 'TileType', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-201', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '陆军', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-202', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '海军', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-203', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '空军', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-204', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '火箭军', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-205', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '战略支援部队', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-206', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '东部战区', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-207', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '南部战区', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-208', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '西部战区', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-209', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '北部战区', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-21', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '中部战区', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-210', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '院内', '大单位', '2010003001', 'armyCategory', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-211', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', 'XX旅', '单位', '2010003002', 'armyUnit', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-212', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '一大队', '单位', '2010003002', 'armyUnit', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-213', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '二大队', '单位', '2010003002', 'armyUnit', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-216', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '任职', '类别', '2010003004', 'resignType', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-217', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '代职', '类别', '2010003004', 'resignType', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-218', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '院内代职', '类别', '2010003004', 'resignType', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-219', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '陆军', '军兵培训经历', '2010003005', 'trainingExperience', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-22', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '海军', '军兵培训经历', '2010003005', 'trainingExperience', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-220', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '空军', '军兵培训经历', '2010003005', 'trainingExperience', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-221', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '火箭军', '军兵培训经历', '2010003005', 'trainingExperience', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-222', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '战略支援部队', '军兵培训经历', '2010003005', 'trainingExperience', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-223', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', 'XX旅', '培训院校', '2010003006', 'trainingCollege', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-224', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '副旅长', '培训专业', '2010004001', 'trainingMajor', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-225', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '本科', '培训层次', '2010004001', 'arrangement', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-226', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '硕士研究生', '培训层次', '2010004001', 'arrangement', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-227', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '博士研究生', '培训层次', '2010004001', 'arrangement', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-228', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '大专', '培训层次', '2010004001', 'arrangement', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-229', '2020-02-26 15:22:35', '2020-02-26 15:22:35', 0, b'1', '', '任职', '培训层次', '2010004001', 'arrangement', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-230', '2020-03-02 15:11:09', '2020-03-02 15:11:11', 0, b'1', '', '陆军', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-231', '2020-03-02 15:13:11', '2020-03-02 15:13:14', 0, b'1', '', '海军', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-232', '2020-03-02 15:13:57', '2020-03-02 15:13:59', 0, b'1', '', '空军', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-233', '2020-03-02 15:14:04', '2020-03-02 15:14:01', 0, b'1', '', '火箭军', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-234', '2020-03-02 15:15:14', '2020-03-02 15:15:16', 0, b'1', '', '战略支援部队', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-235', '2020-03-02 15:18:35', '2020-03-02 15:18:37', 0, b'1', '', '学历教育', '培训形式', '2010004003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-236', '2020-03-02 15:18:39', '2020-03-02 15:18:41', 0, b'1', '', '任职培训', '培训形式', '2010004003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-237', '2020-03-02 15:21:38', '2020-03-02 15:21:40', 0, b'1', '', '军事观察员', '任务类别', '2010004004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-238', '2020-03-02 15:22:10', '2020-03-03 10:02:15', 0, b'1', '', '驻外武官', '任务类别', '2010004004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-239', '2020-03-03 10:00:16', '2020-03-03 10:00:19', 0, b'1', '', '参谋军官', '任务类别', '2010004004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-240', '2020-03-03 10:00:26', '2020-03-03 10:00:23', 0, b'1', '', '研究学者', '留学途径', '2010004005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-241', '2020-03-03 10:00:29', '2020-03-03 10:01:08', 0, b'1', '', '访问学者', '留学途径', '2010004005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-242', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '博士后项目', '留学途径', '2010004005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-243', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '青年骨干教师出国研修项目', '留学途径', '2010004005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-244', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '出国攻读博士研究生', '留学途径', '2010004005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-245', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '军委', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-246', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '东部战区', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-247', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '南部战区', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-248', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '西部战区', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-249', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '北部战区', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-250', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '中部战区', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-251', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '陆军', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-252', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '海军', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-253', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '空军', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-254', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '火箭军', '任务来源', '2010004006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-255', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '作战任务', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-256', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '军事演习', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-257', '2020-03-03 10:02:00', '2020-03-03 10:02:15', 0, b'1', '', '科研试验', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-258', '2020-03-02 15:34:49', '2020-03-02 15:34:49', 0, b'1', NULL, '抢险救灾', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-259', '2020-03-02 15:34:50', '2020-03-02 15:34:50', 0, b'1', NULL, '反恐维稳', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-260', '2020-03-02 15:34:50', '2020-03-02 15:34:50', 0, b'1', NULL, '阅兵', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-261', '2020-03-02 15:34:50', '2020-03-02 15:34:50', 3, b'1', NULL, '嘉奖', '表彰奖励情况', '2010004008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-262', '2020-03-02 15:34:51', '2020-03-02 15:34:51', 2, b'1', NULL, '三等功', '表彰奖励情况', '2010004008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-263', '2020-03-02 15:34:51', '2020-03-02 15:34:51', 1, b'1', NULL, '二等功', '表彰奖励情况', '2010004008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-264', '2020-03-02 15:34:51', '2020-03-02 15:34:51', 0, b'1', NULL, '一等功', '表彰奖励情况', '2010004008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-265', '2020-03-02 15:34:52', '2020-03-02 15:34:52', 3, b'1', NULL, '团', '部队级别', '2010004009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-266', '2020-03-02 15:34:52', '2020-03-02 15:34:52', 2, b'1', NULL, '旅', '部队级别', '2010004009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-267', '2020-03-02 15:34:53', '2020-03-02 15:34:53', 0, b'1', NULL, '军', '部队级别', '2010004009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-268', '2020-03-02 15:34:53', '2020-03-02 15:34:53', 1, b'1', NULL, '师', '部队级别', '2010004009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-269', '2020-03-02 15:34:53', '2020-03-02 15:34:53', 0, b'1', NULL, '综合演练', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-270', '2020-03-02 15:34:54', '2020-03-02 15:34:54', 0, b'1', NULL, '军事比武', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-271', '2020-03-02 15:34:54', '2020-03-02 15:34:54', 0, b'1', NULL, '情报侦察', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-272', '2020-03-02 15:34:54', '2020-03-02 15:34:54', 0, b'1', NULL, '装备维修', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-273', '2020-03-02 15:34:55', '2020-03-02 15:34:55', 0, b'1', NULL, '辅导讲学', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-274', '2020-03-02 15:34:55', '2020-03-02 15:34:55', 0, b'1', NULL, '指导训练', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-275', '2020-03-02 15:34:55', '2020-03-02 15:34:55', 0, b'1', NULL, '专项任务', '任务内容（代号）', '2010004010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-276', '2020-03-02 15:34:56', '2020-03-02 15:34:56', 0, b'1', NULL, '先进个人', '表彰奖励情况', '2010004011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-277', '2020-03-02 15:34:56', '2020-03-02 15:34:56', 0, b'1', NULL, '嘉奖', '表彰奖励情况', '2010004011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-278', '2020-03-02 15:34:56', '2020-03-02 15:34:56', 0, b'1', NULL, '通报表扬', '表彰奖励情况', '2010004011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-279', '2020-03-02 15:34:57', '2020-03-02 15:34:57', 0, b'1', NULL, '无', '表彰奖励情况', '2010004011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-280', '2020-03-02 15:34:57', '2020-03-02 15:34:57', 0, b'1', NULL, '中国科学院院士', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-281', '2020-03-02 15:34:57', '2020-03-02 15:34:57', 0, b'1', NULL, '中国工程院院士', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-282', '2020-03-02 15:34:58', '2020-03-02 15:34:58', 0, b'1', NULL, '国家“千人计划”', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-283', '2020-03-02 15:34:58', '2020-03-02 15:34:58', 0, b'1', NULL, '国家“万人计划”', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-284', '2020-03-02 15:34:58', '2020-03-02 15:34:58', 0, b'1', NULL, '国家百千万人才工程', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-285', '2020-03-02 15:34:59', '2020-03-02 15:34:59', 0, b'1', NULL, '中央直接掌握联系的高级专家', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-286', '2020-03-02 15:34:59', '2020-03-02 15:34:59', 0, b'1', NULL, '长江学者奖励计划', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-287', '2020-03-02 15:35:04', '2020-03-02 15:35:04', 0, b'1', NULL, '创新人才推进计划', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-288', '2020-03-02 15:35:05', '2020-03-02 15:35:05', 0, b'1', NULL, '中国科协青年人才托举工程', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-289', '2020-03-02 15:35:05', '2020-03-02 15:35:05', 0, b'1', NULL, '国家杰出青年科学基金', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-290', '2020-03-02 15:35:05', '2020-03-02 15:35:05', 0, b'1', NULL, '优秀青年科学基金', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-291', '2020-03-02 15:35:06', '2020-03-02 15:35:06', 0, b'1', NULL, '国防科技卓越青年人才基金资助', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-292', '2020-03-02 15:35:06', '2020-03-02 15:35:06', 0, b'1', NULL, '军队科技领军人才', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-293', '2020-03-02 15:35:07', '2020-03-02 15:35:07', 0, b'1', NULL, '军队科技领军人才培养对象', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-294', '2020-03-02 15:35:07', '2020-03-02 15:35:07', 0, b'1', NULL, '学科拔尖人才', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-295', '2020-03-02 15:35:07', '2020-03-02 15:35:07', 0, b'1', NULL, '学科拔尖人才培养对象', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-296', '2020-03-02 15:35:08', '2020-03-02 15:35:08', 0, b'1', NULL, '军队青年科技英才支持计划', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-297', '2020-03-02 15:35:08', '2020-03-02 15:35:08', 0, b'1', NULL, '省（部）级人才工程计划', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-298', '2020-03-02 15:35:09', '2020-03-02 15:35:09', 0, b'1', NULL, '学校高层次创新人才培养计划', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-299', '2020-03-02 15:35:09', '2020-03-02 15:35:09', 0, b'1', NULL, '学院“博穹·英才”', '各级人才工程计划', '2010004012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-300', '2020-03-02 15:58:16', '2020-03-02 15:58:16', 0, b'1', NULL, '全国创新争先奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-301', '2020-03-02 15:58:16', '2020-03-02 15:58:16', 0, b'1', NULL, '全国优秀科技工作者', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-302', '2020-03-02 15:58:17', '2020-03-02 15:58:17', 0, b'1', NULL, '中国青年科技奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-303', '2020-03-02 15:58:17', '2020-03-02 15:58:17', 0, b'1', NULL, '中国青年科学家奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-304', '2020-03-02 15:58:18', '2020-03-02 15:58:18', 0, b'1', NULL, '中国青年女科学家奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-305', '2020-03-02 15:58:18', '2020-03-02 15:58:18', 0, b'1', NULL, '中国科协“求是”杰出青年实用工程奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-306', '2020-03-02 15:58:19', '2020-03-02 15:58:19', 0, b'1', NULL, '军队科技创新群体奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-307', '2020-03-02 15:58:19', '2020-03-02 15:58:19', 0, b'1', NULL, '军队杰出专业技术人才奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-308', '2020-03-02 15:58:19', '2020-03-02 15:58:19', 0, b'1', NULL, '军队院校育才奖金奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-309', '2020-03-02 15:58:20', '2020-03-02 15:58:20', 0, b'1', NULL, '高等学校教学名师奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-310', '2020-03-02 15:58:20', '2020-03-02 15:58:20', 0, b'1', NULL, '高校青年教师奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-311', '2020-03-02 15:58:21', '2020-03-02 15:58:21', 0, b'1', NULL, '全军优秀教师奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-312', '2020-03-02 15:58:21', '2020-03-02 15:58:21', 0, b'1', NULL, '军队（省）级教学名师奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-313', '2020-03-02 15:58:21', '2020-03-02 15:58:21', 0, b'1', NULL, '学校优秀教师奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-314', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '学校优秀导师奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-315', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '学校青年创新奖', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-316', '2020-03-02 16:23:11', '2020-03-02 16:23:11', 0, b'1', NULL, '实践辅导', '承担任务', '2010005001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-317', '2020-03-02 16:23:12', '2020-03-02 16:23:12', 0, b'1', NULL, '实践主讲', '承担任务', '2010005001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-318', '2020-03-02 16:23:12', '2020-03-02 16:23:12', 0, b'1', NULL, '课程辅导', '承担任务', '2010005001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-319', '2020-03-02 16:23:13', '2020-03-02 16:23:13', 0, b'1', NULL, '课程主讲', '承担任务', '2010005001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-320', '2020-03-02 16:23:13', '2020-03-02 16:23:13', 0, b'1', NULL, '本科', '学员层次', '2010005002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-321', '2020-03-02 16:23:13', '2020-03-02 16:23:13', 0, b'1', NULL, '研究生', '学员层次', '2010005002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-322', '2020-03-02 16:23:14', '2020-03-02 16:23:14', 0, b'1', NULL, '大专', '学员层次', '2010005002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-323', '2020-03-02 16:23:14', '2020-03-02 16:23:14', 0, b'1', NULL, '外训', '学员层次', '2010005002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-324', '2020-03-02 16:23:15', '2020-03-02 16:23:15', 0, b'1', NULL, '任职', '学员层次', '2010005002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-325', '2020-03-02 16:23:15', '2020-03-02 16:23:15', 0, b'1', NULL, '学校课堂教学质量考核评价', '评价方式', '2010005003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-326', '2020-03-02 16:23:15', '2020-03-02 16:23:15', 0, b'1', NULL, '学院课堂教学质量考核评价', '评价方式', '2010005003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-327', '2020-03-02 16:23:16', '2020-03-02 16:23:16', 0, b'1', NULL, '领导听查课', '评价方式', '2010005003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-328', '2020-03-02 16:23:16', '2020-03-02 16:23:16', 0, b'1', NULL, '专家督导', '评价方式', '2010005003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-329', '2020-03-02 16:23:17', '2020-03-02 16:23:17', 0, b'1', NULL, '优秀', '评价结果', '2010005005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-330', '2020-03-02 16:23:17', '2020-03-02 16:23:17', 1, b'1', NULL, '良好', '评价结果', '2010005005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-331', '2020-03-02 16:23:17', '2020-03-02 16:23:17', 0, b'1', NULL, '有', '教学事故', '2010005006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-332', '2020-03-02 16:23:18', '2020-03-02 16:23:18', 0, b'1', NULL, '无', '教学事故', '2010005006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-333', '2020-03-02 16:23:18', '2020-03-02 16:23:18', 0, b'1', NULL, '教学成果奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-334', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '教学比赛奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-335', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '优秀教学奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-336', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '优秀学位论文导师奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-337', '2020-03-02 16:23:20', '2020-03-02 16:23:20', 0, b'1', NULL, '教学竞赛类', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-338', '2020-03-02 16:23:20', '2020-03-02 16:23:20', 0, b'1', NULL, '指导学生学科竞赛奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-339', '2020-03-02 16:23:21', '2020-03-02 16:23:21', 0, b'1', NULL, '网络安全优秀教师奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-340', '2020-03-02 16:23:21', '2020-03-02 16:23:21', 0, b'1', NULL, '精品课程', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-341', '2020-03-02 16:23:21', '2020-03-02 16:23:21', 0, b'1', NULL, '一流本科专业', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-342', '2020-03-02 16:23:22', '2020-03-02 16:23:22', 0, b'1', NULL, '学员A类竞赛优秀指导教员', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-343', '2020-03-02 16:23:22', '2020-03-02 16:23:22', 0, b'1', NULL, '教员A类教学比武竞赛先进个人', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-344', '2020-03-02 16:23:22', '2020-03-02 16:23:22', 0, b'1', NULL, '其他优秀教学奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-345', '2020-03-02 16:23:23', '2020-03-02 16:23:23', 0, b'1', NULL, '特等奖', '奖励等级', '2010005008', '0', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-346', '2020-03-02 16:23:23', '2020-03-02 16:23:23', 1, b'1', NULL, '一等奖', '奖励等级', '2010005008', '1', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-347', '2020-03-02 16:23:24', '2020-03-02 16:23:24', 2, b'1', NULL, '二等奖', '奖励等级', '2010005008', '2', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-348', '2020-03-02 16:23:24', '2020-03-02 16:23:24', 3, b'1', NULL, '三等奖', '奖励等级', '2010005008', '3', NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-363', '2020-03-02 16:49:35', '2020-03-02 16:49:35', 4, b'1', NULL, '其他', '奖励等级', '2010005008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-364', '2020-03-02 16:49:35', '2020-03-02 16:49:35', 0, b'1', NULL, '有', '有无选择', '2010005009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-365', '2020-03-02 16:49:35', '2020-03-02 16:49:35', 0, b'1', NULL, '无', '有无选择', '2010005009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-366', '2020-03-02 16:49:36', '2020-03-02 16:49:36', 0, b'1', NULL, '湖南省', '抽查/评优单位', '2010005010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-367', '2020-03-02 16:49:36', '2020-03-02 16:49:36', 0, b'1', NULL, '安徽省', '抽查/评优单位', '2010005010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-368', '2020-03-02 16:49:37', '2020-03-02 16:49:37', 0, b'1', NULL, '全军', '抽查/评优单位', '2010005010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-369', '2020-03-02 16:49:37', '2020-03-02 16:49:37', 0, b'1', NULL, '合格', '抽查/评优结果', '2010005011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-370', '2020-03-02 17:03:08', '2020-03-02 17:03:08', 0, b'1', NULL, '不合格', '抽查/评优结果', '2010005011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-371', '2020-03-02 17:03:09', '2020-03-02 17:03:09', 0, b'1', NULL, '优秀博士论文', '抽查/评优结果', '2010005013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-372', '2020-03-02 17:03:09', '2020-03-02 17:03:09', 0, b'1', NULL, '优秀硕士论文', '抽查/评优结果', '2010005013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-373', '2020-03-02 17:03:10', '2020-03-02 17:03:10', 0, b'1', NULL, '绝密', '项目密级', '2010006001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-374', '2020-03-02 17:03:10', '2020-03-02 17:03:10', 0, b'1', NULL, '机密', '项目密级', '2010006001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-375', '2020-03-02 17:03:10', '2020-03-02 17:03:10', 0, b'1', NULL, '秘密', '项目密级', '2010006001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-376', '2020-03-02 17:03:11', '2020-03-02 17:03:11', 0, b'1', NULL, '公开', '项目密级', '2010006001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-377', '2020-03-02 17:03:11', '2020-03-02 17:03:11', 0, b'1', NULL, '国防科技创新研究课题（战略先导、基础加强）', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-378', '2020-03-02 17:03:12', '2020-03-02 17:03:12', 0, b'1', NULL, '装备军内科研课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-379', '2020-03-02 17:03:12', '2020-03-02 17:03:12', 0, b'1', NULL, '装备预先研究课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-380', '2020-03-02 17:03:12', '2020-03-02 17:03:12', 0, b'1', NULL, '装备研制课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-381', '2020-03-02 17:03:13', '2020-03-02 17:03:13', 0, b'1', NULL, '装备技术基础计划课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-382', '2020-03-02 17:03:13', '2020-03-02 17:03:13', 0, b'1', NULL, '试验技术研究课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-383', '2020-03-02 17:03:13', '2020-03-02 17:03:13', 0, b'1', NULL, '后勤科研计划课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-384', '2020-03-02 17:03:14', '2020-03-02 17:03:14', 0, b'1', NULL, '后勤扩试计划课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-385', '2020-03-02 17:03:14', '2020-03-02 17:03:14', 0, b'1', NULL, '军事理论研究课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-386', '2020-03-02 17:03:15', '2020-03-02 17:03:15', 0, b'1', NULL, '除上述军队计划外由军队有关部门下达的课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-387', '2020-03-02 17:03:15', '2020-03-02 17:03:15', 0, b'1', NULL, '国家科技重大专项（含国防领域）', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-388', '2020-03-02 17:03:15', '2020-03-02 17:03:15', 0, b'1', NULL, '国家自然科学基金课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-389', '2020-03-02 17:03:16', '2020-03-02 17:03:16', 0, b'1', NULL, '科技创新2030重大专项课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-390', '2020-03-02 17:21:02', '2020-03-02 17:21:02', 0, b'1', NULL, '重点研发计划国家级课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-391', '2020-03-02 17:21:03', '2020-03-02 17:21:03', 0, b'1', NULL, '星火计划国家级课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-392', '2020-03-02 17:21:03', '2020-03-02 17:21:03', 0, b'1', NULL, '国防科工局计划', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-393', '2020-03-02 17:21:04', '2020-03-02 17:21:04', 0, b'1', NULL, '工业和信息化部计划', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-394', '2020-03-02 17:21:04', '2020-03-02 17:21:04', 0, b'1', NULL, '国家社会科学基金课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-395', '2020-03-02 17:21:04', '2020-03-02 17:21:04', 0, b'1', NULL, '除上述国家计划外由中央政府部门下达的课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-396', '2020-03-02 17:21:05', '2020-03-02 17:21:05', 0, b'1', NULL, '地方自然科学基金课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-397', '2020-03-02 17:21:05', '2020-03-02 17:21:05', 0, b'1', NULL, '地方科技攻关计划课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-398', '2020-03-02 17:21:05', '2020-03-02 17:21:05', 0, b'1', NULL, '火炬计划地方级课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-399', '2020-03-02 17:21:06', '2020-03-02 17:21:06', 0, b'1', NULL, '星火计划地方级课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-400', '2020-03-02 17:21:06', '2020-03-02 17:21:06', 0, b'1', NULL, '地方社会科学基金课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-401', '2020-03-02 17:21:07', '2020-03-02 17:21:07', 0, b'1', NULL, '由地方政府部门下达的其他课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-402', '2020-03-02 17:21:07', '2020-03-02 17:21:07', 0, b'1', NULL, '后勤条件建设计划课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-403', '2020-03-02 17:21:08', '2020-03-02 17:21:08', 0, b'1', NULL, '企业委托：各类生产企业委托课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-404', '2020-03-02 17:21:08', '2020-03-02 17:21:08', 0, b'1', NULL, '自选：本单位选定并支付费用的课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-405', '2020-03-02 17:21:08', '2020-03-02 17:21:08', 0, b'1', NULL, '国际合作课题', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-406', '2020-03-02 17:21:09', '2020-03-02 17:21:09', 0, b'1', NULL, '其它（可输入）', '项目来源', '2010006002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-407', '2020-03-02 17:22:56', '2020-03-02 17:22:56', 0, b'1', NULL, '国家级', '项目类别', '2010006003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-408', '2020-03-02 17:22:56', '2020-03-02 17:22:56', 1, b'1', NULL, '军队级', '项目类别', '2010006003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-409', '2020-03-02 17:22:57', '2020-03-02 17:22:57', 2, b'1', NULL, '省部级', '项目类别', '2010006003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-410', '2020-03-02 17:22:57', '2020-03-02 17:22:57', 5, b'1', NULL, '其他', '项目类别', '2010006003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-411', '2020-03-02 17:24:27', '2020-03-02 17:24:27', 0, b'1', NULL, '重大', '项目级别', '2010006004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-412', '2020-03-02 17:24:28', '2020-03-02 17:24:28', 0, b'1', NULL, '重点', '项目级别', '2010006004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-413', '2020-03-02 17:24:28', '2020-03-02 17:24:28', 0, b'1', NULL, '主要', '项目级别', '2010006004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-414', '2020-03-02 17:24:29', '2020-03-02 17:24:29', 0, b'1', NULL, '一般', '项目级别', '2010006004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-415', '2020-03-02 17:24:29', '2020-03-02 17:24:29', 0, b'1', NULL, '面上', '项目级别', '2010006004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-416', '2020-03-02 17:24:29', '2020-03-02 17:24:29', 0, b'1', NULL, '其他', '项目级别', '2010006004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-417', '2020-03-02 23:27:06', '2020-03-02 23:27:06', 0, b'1', NULL, '国家发明专利', '类别', '2010006005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-418', '2020-03-02 23:27:06', '2020-03-02 23:27:06', 0, b'1', NULL, '国防专利', '类别', '2010006005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-419', '2020-03-02 23:27:07', '2020-03-02 23:27:07', 0, b'1', NULL, '实用新型专利', '类别', '2010006005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-420', '2020-03-02 23:27:07', '2020-03-02 23:27:07', 0, b'1', NULL, '外观设计专利', '类别', '2010006005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-421', '2020-03-02 23:27:08', '2020-03-02 23:27:08', 0, b'1', NULL, '其他', '类别', '2010006005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-422', '2020-03-02 23:33:24', '2020-03-02 23:33:24', 0, b'1', NULL, '国家级', '奖励级别', '2010006006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-423', '2020-03-02 23:33:25', '2020-03-02 23:33:25', 0, b'1', NULL, '省部级', '奖励级别', '2010006006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-424', '2020-03-02 23:33:25', '2020-03-02 23:33:25', 0, b'1', NULL, '军队级', '奖励级别', '2010006006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-425', '2020-03-02 23:34:47', '2020-03-02 23:34:47', 0, b'1', NULL, '国家发明奖', '奖励类型', '2010006007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-426', '2020-03-02 23:34:47', '2020-03-02 23:34:47', 0, b'1', NULL, '国家科技进步奖', '奖励类型', '2010006007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-427', '2020-03-02 23:34:47', '2020-03-02 23:34:47', 0, b'1', NULL, '军队科技进步奖', '奖励类型', '2010006007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-428', '2020-03-02 23:34:48', '2020-03-02 23:34:48', 0, b'1', NULL, '军事理论成果奖', '奖励类型', '2010006007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-429', '2020-03-02 23:34:48', '2020-03-02 23:34:48', 0, b'1', NULL, '安徽省科技进步奖', '奖励类型', '2010006007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-430', '2020-03-02 23:34:49', '2020-03-02 23:34:49', 0, b'1', NULL, '其他', '奖励类型', '2010006007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-431', '2020-03-02 23:36:05', '2020-03-02 23:36:05', 0, b'1', NULL, '特等奖', '等级', '2010006008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-432', '2020-03-02 23:36:06', '2020-03-02 23:36:06', 1, b'1', NULL, '一等奖', '等级', '2010006008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-433', '2020-03-02 23:36:06', '2020-03-02 23:36:06', 2, b'1', NULL, '二等奖', '等级', '2010006008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-434', '2020-03-02 23:36:07', '2020-03-02 23:36:07', 3, b'1', NULL, '三等奖', '等级', '2010006008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-435', '2020-03-02 23:41:33', '2020-03-02 23:41:33', 0, b'1', NULL, 'SCI', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-436', '2020-03-02 23:41:34', '2020-03-02 23:41:34', 0, b'1', NULL, 'EI', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-437', '2020-03-02 23:41:34', '2020-03-02 23:41:34', 0, b'1', NULL, 'SCIE', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-438', '2020-03-02 23:41:34', '2020-03-02 23:41:34', 0, b'1', NULL, 'SSCI', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-439', '2020-03-02 23:41:35', '2020-03-02 23:41:35', 0, b'1', NULL, '中文核心期刊', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-440', '2020-03-02 23:41:35', '2020-03-02 23:41:35', 0, b'1', NULL, '军事学核心期刊', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-441', '2020-03-02 23:41:35', '2020-03-02 23:41:35', 0, b'1', NULL, '其他', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-442', '2020-03-02 23:48:28', '2020-03-02 23:48:28', 0, b'1', NULL, '主编著', '参与类别', '2010006010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-443', '2020-03-02 23:48:29', '2020-03-02 23:48:29', 0, b'1', NULL, '副编著', '参与类别', '2010006010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-444', '2020-03-02 23:48:29', '2020-03-02 23:48:29', 0, b'1', NULL, '编著人员', '参与类别', '2010006010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-445', '2020-03-02 23:48:29', '2020-03-02 23:48:29', 0, b'1', NULL, '其他', '参与类别', '2010006010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-446', '2020-03-02 23:49:34', '2020-03-02 23:49:34', 0, b'1', NULL, '专著', '著作类型', '2010006011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-447', '2020-03-02 23:49:35', '2020-03-02 23:49:35', 0, b'1', NULL, '译著', '著作类型', '2010006011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-448', '2020-03-02 23:49:35', '2020-03-02 23:49:35', 0, b'1', NULL, '其他', '著作类型', '2010006011', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-449', '2020-03-02 23:54:55', '2020-03-02 23:54:55', 0, b'1', NULL, '全国统编', '教材类型', '2010006012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-450', '2020-03-02 23:54:56', '2020-03-02 23:54:56', 0, b'1', NULL, '军队级', '教材类型', '2010006012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-451', '2020-03-03 00:02:57', '2020-03-03 00:02:57', 0, b'1', NULL, '国家标准', '标准类型', '2010006013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-452', '2020-03-03 00:02:58', '2020-03-03 00:02:58', 0, b'1', NULL, '国家军用标准', '标准类型', '2010006013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-453', '2020-03-03 00:02:58', '2020-03-03 00:02:58', 0, b'1', NULL, '行业标准', '标准类型', '2010006013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-454', '2020-03-03 00:02:58', '2020-03-03 00:02:58', 0, b'1', NULL, '其他', '标准类型', '2010006013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-455', '2020-03-03 00:02:59', '2020-03-03 00:02:59', 0, b'1', NULL, '修订', '项目类别', '2010006014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-456', '2020-03-03 00:02:59', '2020-03-03 00:02:59', 0, b'1', NULL, '制定', '项目类别', '2010006014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-457', '2020-03-03 00:02:59', '2020-03-03 00:02:59', 0, b'1', NULL, '是', '是否颁布', '2010006015', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-458', '2020-03-03 00:03:00', '2020-03-03 00:03:00', 0, b'1', NULL, '否', '是否颁布', '2010006015', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-459', '2020-03-03 09:23:01', '2020-03-03 09:23:01', 0, b'1', NULL, '咨询报告（意见）', '报告类别', '2010006016', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-460', '2020-03-03 09:23:02', '2020-03-03 09:23:02', 0, b'1', NULL, '研究论证报告', '报告类别', '2010006016', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-461', '2020-03-03 09:23:02', '2020-03-03 09:23:02', 0, b'1', NULL, '新华社内参', '报告类别', '2010006016', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-462', '2020-03-03 09:23:03', '2020-03-03 09:23:03', 0, b'1', NULL, '解放军报', '报告类别', '2010006016', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-463', '2020-03-03 09:23:03', '2020-03-03 09:23:03', 0, b'1', NULL, '军队情况摘报', '报告类别', '2010006016', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-464', '2020-03-03 09:23:03', '2020-03-03 09:23:03', 0, b'1', NULL, '其他', '报告类别', '2010006016', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-465', '2020-03-03 09:24:43', '2020-03-03 09:24:43', 0, b'1', NULL, '大区正', '批示领导级别', '2010006017', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-466', '2020-03-03 09:24:44', '2020-03-03 09:24:44', 1, b'1', NULL, '大区副', '批示领导级别', '2010006017', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-467', '2020-03-03 09:24:44', '2020-03-03 09:24:44', 2, b'1', NULL, '正军', '批示领导级别', '2010006017', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-468', '2020-03-03 09:24:45', '2020-03-03 09:24:45', 3, b'1', NULL, '副军', '批示领导级别', '2010006017', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-469', '2020-03-03 09:29:52', '2020-03-03 09:29:52', 0, b'1', NULL, '国际', '会议类别', '2010006018', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-470', '2020-03-03 09:29:52', '2020-03-03 09:29:52', 0, b'1', NULL, '全国', '会议类别', '2010006018', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-471', '2020-03-03 09:29:53', '2020-03-03 09:29:53', 0, b'1', NULL, '全军', '会议类别', '2010006018', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-472', '2020-03-03 09:29:53', '2020-03-03 09:29:53', 0, b'1', NULL, '其他', '会议类别', '2010006018', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-473', '2020-03-03 09:31:09', '2020-03-03 09:31:09', 0, b'1', NULL, '大会主报告', '邀请报告', '2010006019', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-474', '2020-03-03 09:31:10', '2020-03-03 09:31:10', 0, b'1', NULL, '分会场报告', '邀请报告', '2010006019', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-475', '2020-03-03 09:38:34', '2020-03-03 09:38:34', 0, b'1', NULL, '副主任委员', '担任职务', '2010006020', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-476', '2020-03-03 09:38:34', '2020-03-03 09:38:34', 0, b'1', NULL, '主任委员', '担任职务', '2010006020', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-477', '2020-03-03 09:46:18', '2020-03-03 09:46:18', 0, b'1', NULL, 'CSSCI', '收录类型', '2010006021', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-478', '2020-03-03 09:46:19', '2020-03-03 09:46:19', 0, b'1', NULL, 'CSCD', '收录类型', '2010006021', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-479', '2020-03-03 09:46:19', '2020-03-03 09:46:19', 0, b'1', NULL, 'SCI', '收录类型', '2010006021', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-480', '2020-03-03 09:46:20', '2020-03-03 09:46:20', 0, b'1', NULL, 'SSCI', '收录类型', '2010006021', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-481', '2020-03-03 09:46:20', '2020-03-03 09:46:20', 0, b'1', NULL, 'EI', '收录类型', '2010006021', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-482', '2020-03-03 09:46:20', '2020-03-03 09:46:20', 0, b'1', NULL, 'A&HCI', '收录类型', '2010006021', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-483', '2020-03-03 09:48:46', '2020-03-03 09:48:46', 0, b'1', NULL, '主编', '担任职务', '2010006022', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-484', '2020-03-03 09:48:47', '2020-03-03 09:48:47', 0, b'1', NULL, '副主编', '担任职务', '2010006022', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-485', '2020-03-03 09:48:47', '2020-03-03 09:48:47', 0, b'1', NULL, '编委', '担任职务', '2010006022', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-486', '2020-03-03 09:48:48', '2020-03-03 09:48:48', 0, b'1', NULL, '编辑', '担任职务', '2010006022', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-487', '2020-03-03 09:48:48', '2020-03-03 09:48:48', 0, b'1', NULL, '审稿人', '担任职务', '2010006022', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-488', '2020-03-03 17:43:49', '2020-03-03 17:43:49', 0, b'1', NULL, '男', '性别', '2010007001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-489', '2020-03-03 17:43:50', '2020-03-03 17:43:50', 0, b'1', NULL, '女', '性别', '2010007001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-490', '2020-03-03 17:45:24', '2020-03-03 17:45:24', 0, b'1', NULL, '军人干部', '人员类别', '2010007002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-491', '2020-03-03 17:45:25', '2020-03-03 17:45:25', 0, b'1', NULL, '文职人员', '人员类别', '2010007002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-492', '2020-03-03 17:46:29', '2020-03-03 17:46:29', 0, b'1', NULL, '党员', '政治面貌', '2010007003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-493', '2020-03-03 17:46:30', '2020-03-03 17:46:30', 0, b'1', NULL, '群众', '政治面貌', '2010007003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-494', '2020-03-03 17:48:22', '2020-03-03 17:48:22', 0, b'1', NULL, '大学', '最高学历', '2010007004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-495', '2020-03-03 17:48:23', '2020-03-03 17:48:23', 0, b'1', NULL, '硕士', '最高学历', '2010007004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-496', '2020-03-03 17:48:23', '2020-03-03 17:48:23', 0, b'1', NULL, '研究生', '最高学历', '2010007004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-497', '2020-03-03 17:48:23', '2020-03-03 17:48:23', 0, b'1', NULL, '博士', '最高学历', '2010007004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-498', '2020-03-03 17:49:35', '2020-03-03 17:49:35', 0, b'1', NULL, '学士', '学位', '2010007005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-499', '2020-03-03 17:49:36', '2020-03-03 17:49:36', 0, b'1', NULL, '硕士', '学位', '2010007005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-500', '2020-03-03 17:49:36', '2020-03-03 17:49:36', 0, b'1', NULL, '博士', '学位', '2010007005', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-501', '2020-03-03 17:52:00', '2020-03-03 17:52:00', 0, b'1', NULL, '211', '院校类别', '2010007006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-502', '2020-03-03 17:52:00', '2020-03-03 17:52:00', 0, b'1', NULL, '985', '院校类别', '2010007006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-503', '2020-03-03 17:52:01', '2020-03-03 17:52:01', 0, b'1', NULL, '普通全日制', '院校类别', '2010007006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-504', '2020-03-03 17:52:02', '2020-03-03 17:52:02', 0, b'1', NULL, '军内院校', '院校类别', '2010007006', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-505', '2020-03-03 17:55:31', '2020-03-03 17:55:31', 0, b'1', NULL, '助教', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-506', '2020-03-03 17:55:31', '2020-03-03 17:55:31', 0, b'1', NULL, '讲师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-507', '2020-03-03 17:55:32', '2020-03-03 17:55:32', 0, b'1', NULL, '副教授', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-508', '2020-03-03 17:55:32', '2020-03-03 17:55:32', 0, b'1', NULL, '教授', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-509', '2020-03-03 17:55:33', '2020-03-03 17:55:33', 0, b'1', NULL, '研究实习员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-510', '2020-03-03 17:55:33', '2020-03-03 17:55:33', 0, b'1', NULL, '助理研究员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-511', '2020-03-03 17:55:34', '2020-03-03 17:55:34', 0, b'1', NULL, '副研究员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-512', '2020-03-03 17:55:34', '2020-03-03 17:55:34', 0, b'1', NULL, '研究员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-513', '2020-03-03 17:55:34', '2020-03-03 17:55:34', 0, b'1', NULL, '实验员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-514', '2020-03-03 17:55:35', '2020-03-03 17:55:35', 0, b'1', NULL, '助理实验师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-515', '2020-03-03 17:55:35', '2020-03-03 17:55:35', 0, b'1', NULL, '实验师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-516', '2020-03-03 17:55:36', '2020-03-03 17:55:36', 0, b'1', NULL, '高级实验师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-517', '2020-03-03 17:55:36', '2020-03-03 17:55:36', 0, b'1', NULL, '正高级实验师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-518', '2020-03-03 17:55:36', '2020-03-03 17:55:36', 0, b'1', NULL, '助理工程师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-520', '2020-03-03 17:55:37', '2020-03-03 17:55:37', 0, b'1', NULL, '工程师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-521', '2020-03-03 17:55:37', '2020-03-03 17:55:37', 0, b'1', NULL, '高级工程师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-522', '2020-03-03 17:55:38', '2020-03-03 17:55:38', 0, b'1', NULL, '正高级工程师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-524', '2020-03-03 17:55:39', '2020-03-03 17:55:39', 0, b'1', NULL, '助理会计师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-525', '2020-03-03 17:55:39', '2020-03-03 17:55:39', 0, b'1', NULL, '会计师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-526', '2020-03-03 17:55:39', '2020-03-03 17:55:39', 0, b'1', NULL, '高级会计师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-527', '2020-03-03 17:55:40', '2020-03-03 17:55:40', 0, b'1', NULL, '正高级会计师', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-528', '2020-03-03 17:55:40', '2020-03-03 17:55:40', 0, b'1', NULL, '助理编辑', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-529', '2020-03-03 17:55:41', '2020-03-03 17:55:41', 0, b'1', NULL, '编辑', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-530', '2020-03-03 17:55:41', '2020-03-03 17:55:41', 0, b'1', NULL, '副编审', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-531', '2020-03-03 17:55:41', '2020-03-03 17:55:41', 0, b'1', NULL, '编审', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-532', '2020-03-03 17:55:42', '2020-03-03 17:55:42', 0, b'1', NULL, '管理员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-533', '2020-03-03 17:55:42', '2020-03-03 17:55:42', 0, b'1', NULL, '助理馆员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-534', '2020-03-03 17:55:43', '2020-03-03 17:55:43', 0, b'1', NULL, '馆员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-535', '2020-03-03 17:55:43', '2020-03-03 17:55:43', 0, b'1', NULL, '副研究馆员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-536', '2020-03-03 17:55:43', '2020-03-03 17:55:43', 0, b'1', NULL, '研究馆员', '现专业技术职务', '2010007007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-537', '2020-03-03 17:57:22', '2020-03-03 17:57:22', 0, b'1', NULL, '专业技术一级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-538', '2020-03-03 17:57:22', '2020-03-03 17:57:22', 0, b'1', NULL, '专业技术二级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-539', '2020-03-03 17:57:23', '2020-03-03 17:57:23', 0, b'1', NULL, '专业技术三级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-540', '2020-03-03 17:57:23', '2020-03-03 17:57:23', 0, b'1', NULL, '专业技术四级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-541', '2020-03-03 17:57:23', '2020-03-03 17:57:23', 0, b'1', NULL, '专业技术五级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-542', '2020-03-03 17:57:24', '2020-03-03 17:57:24', 0, b'1', NULL, '专业技术六级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-543', '2020-03-03 17:57:24', '2020-03-03 17:57:24', 0, b'1', NULL, '专业技术七级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-544', '2020-03-03 17:57:25', '2020-03-03 17:57:25', 0, b'1', NULL, '专业技术八级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-545', '2020-03-03 17:57:25', '2020-03-03 17:57:25', 0, b'1', NULL, '专业技术九级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-546', '2020-03-03 17:57:26', '2020-03-03 17:57:26', 0, b'1', NULL, '专业技术十级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-547', '2020-03-03 17:57:26', '2020-03-03 17:57:26', 0, b'1', NULL, '专业技术十一级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-548', '2020-03-03 17:57:26', '2020-03-03 17:57:26', 0, b'1', NULL, '专业技术十二级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-549', '2020-03-03 17:57:27', '2020-03-03 17:57:27', 0, b'1', NULL, '专业技术十三级', '现技术等级', '2010007008', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-550', '2020-03-03 18:00:31', '2020-03-03 18:00:31', 0, b'1', NULL, '上将', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-551', '2020-03-03 18:00:31', '2020-03-03 18:00:31', 0, b'1', NULL, '中将', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-552', '2020-03-03 18:00:32', '2020-03-03 18:00:32', 0, b'1', NULL, '少将', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-553', '2020-03-03 18:00:32', '2020-03-03 18:00:32', 0, b'1', NULL, '大校', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-554', '2020-03-03 18:00:32', '2020-03-03 18:00:32', 0, b'1', NULL, '上校', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-555', '2020-03-03 18:00:33', '2020-03-03 18:00:33', 0, b'1', NULL, '中校', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-556', '2020-03-03 18:00:33', '2020-03-03 18:00:33', 0, b'1', NULL, '少校', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-557', '2020-03-03 18:00:34', '2020-03-03 18:00:34', 0, b'1', NULL, '上尉', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-558', '2020-03-03 18:00:34', '2020-03-03 18:00:34', 0, b'1', NULL, '中尉', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-559', '2020-03-03 18:00:35', '2020-03-03 18:00:35', 0, b'1', NULL, '少尉', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-560', '2020-03-03 18:00:35', '2020-03-03 18:00:35', 0, b'1', NULL, '转改特级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-561', '2020-03-03 18:00:35', '2020-03-03 18:00:35', 0, b'1', NULL, '转改1级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-562', '2020-03-03 18:00:36', '2020-03-03 18:00:36', 0, b'1', NULL, '转改2级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-563', '2020-03-03 18:00:36', '2020-03-03 18:00:36', 0, b'1', NULL, '转改3级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-564', '2020-03-03 18:00:36', '2020-03-03 18:00:36', 0, b'1', NULL, '转改4级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-565', '2020-03-03 18:00:37', '2020-03-03 18:00:37', 0, b'1', NULL, '转改5级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-566', '2020-03-03 18:00:37', '2020-03-03 18:00:37', 0, b'1', NULL, '转改6级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-567', '2020-03-03 18:00:38', '2020-03-03 18:00:38', 0, b'1', NULL, '转改7级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-568', '2020-03-03 18:00:38', '2020-03-03 18:00:38', 0, b'1', NULL, '转改8级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-569', '2020-03-03 18:00:39', '2020-03-03 18:00:39', 0, b'1', NULL, '特级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-570', '2020-03-03 18:00:39', '2020-03-03 18:00:39', 0, b'1', NULL, '1级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-571', '2020-03-03 18:00:39', '2020-03-03 18:00:39', 0, b'1', NULL, '2级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-572', '2020-03-03 18:00:40', '2020-03-03 18:00:40', 0, b'1', NULL, '3级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-573', '2020-03-03 18:00:40', '2020-03-03 18:00:40', 0, b'1', NULL, '4级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-574', '2020-03-03 18:00:41', '2020-03-03 18:00:41', 0, b'1', NULL, '5级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-575', '2020-03-03 18:00:41', '2020-03-03 18:00:41', 0, b'1', NULL, '6级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-576', '2020-03-03 18:00:41', '2020-03-03 18:00:41', 0, b'1', NULL, '7级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-577', '2020-03-03 18:00:42', '2020-03-03 18:00:42', 0, b'1', NULL, '8级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-578', '2020-03-03 18:00:42', '2020-03-03 18:00:42', 0, b'1', NULL, '9级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-579', '2020-03-03 18:00:42', '2020-03-03 18:00:42', 0, b'1', NULL, '10级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-580', '2020-03-03 18:00:43', '2020-03-03 18:00:43', 0, b'1', NULL, '11级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-581', '2020-03-03 18:00:43', '2020-03-03 18:00:43', 0, b'1', NULL, '12级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-582', '2020-03-03 18:00:44', '2020-03-03 18:00:44', 0, b'1', NULL, '13级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-583', '2020-03-03 18:00:44', '2020-03-03 18:00:44', 0, b'1', NULL, '14级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-584', '2020-03-03 18:00:45', '2020-03-03 18:00:45', 0, b'1', NULL, '15级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-585', '2020-03-03 18:00:45', '2020-03-03 18:00:45', 0, b'1', NULL, '16级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-586', '2020-03-03 18:00:45', '2020-03-03 18:00:45', 0, b'1', NULL, '17级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-587', '2020-03-03 18:00:46', '2020-03-03 18:00:46', 0, b'1', NULL, '18级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-588', '2020-03-03 18:00:46', '2020-03-03 18:00:46', 0, b'1', NULL, '19级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-589', '2020-03-03 18:00:47', '2020-03-03 18:00:47', 0, b'1', NULL, '20级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-590', '2020-03-03 18:00:47', '2020-03-03 18:00:47', 0, b'1', NULL, '21级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-591', '2020-03-03 18:00:47', '2020-03-03 18:00:47', 0, b'1', NULL, '22级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-592', '2020-03-03 18:00:48', '2020-03-03 18:00:48', 0, b'1', NULL, '23级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-593', '2020-03-03 18:00:48', '2020-03-03 18:00:48', 0, b'1', NULL, '24级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-594', '2020-03-03 18:00:49', '2020-03-03 18:00:49', 0, b'1', NULL, '25级', '现军衔/级别', '2010007009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-595', '2020-03-03 18:03:11', '2020-03-03 18:03:11', 0, b'1', NULL, '初职', '现技职类别', '2010007010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-596', '2020-03-03 18:03:12', '2020-03-03 18:03:12', 0, b'1', NULL, '中职', '现技职类别', '2010007010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-597', '2020-03-03 18:03:12', '2020-03-03 18:03:12', 0, b'1', NULL, '副高职', '现技职类别', '2010007010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-598', '2020-03-03 18:03:13', '2020-03-03 18:03:13', 0, b'1', NULL, '正高职', '现技职类别', '2010007010', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-599', '2020-03-04 10:18:42', '2020-03-04 10:18:42', 0, b'1', NULL, '武器装备实验', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-600', '2020-03-04 10:18:42', '2020-03-04 10:18:42', 0, b'1', NULL, '武器装备发展规划', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-601', '2020-03-04 10:18:42', '2020-03-04 10:18:42', 0, b'1', NULL, '质量管理', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-602', '2020-03-04 10:18:42', '2020-03-04 10:18:42', 0, b'1', NULL, '勤务保障', '任务类别', '2010004007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-603', '2020-03-04 10:29:39', '2020-03-04 10:29:39', 3, b'1', NULL, '校级', '项目类别', '2010006003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-604', '2020-03-04 10:29:39', '2020-03-04 10:29:39', 4, b'1', NULL, '院级', '项目类别', '2010006003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-605', '2020-03-04 11:03:45', '2020-03-04 11:03:45', 0, b'1', NULL, '独立指导', '指导类型', '2010008001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-606', '2020-03-04 11:03:45', '2020-03-04 11:03:45', 0, b'1', NULL, '协助指导', '指导类型', '2010008001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-607', '2020-03-04 11:04:58', '2020-03-04 11:04:58', 0, b'1', NULL, '中国博士后科学基金', '进站获资助情况', '2010008002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-608', '2020-03-04 11:04:58', '2020-03-04 11:04:58', 0, b'1', NULL, '入选博士后国（境）外交流项目', '进站获资助情况', '2010008002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-609', '2020-03-04 11:04:58', '2020-03-04 11:04:58', 0, b'1', NULL, '博士后创新人才支持计划', '进站获资助情况', '2010008002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-610', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '基础研究类', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-611', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '教育教学类', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-612', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '科学研究类', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-613', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '重大工程类', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-614', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '武器装维护类', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-615', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '装备实验楼', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-616', '2020-03-04 11:13:59', '2020-03-04 11:13:59', 0, b'1', NULL, '勤务保障类', '项目类型', '1060001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-617', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, '中国科技论文核心期刊', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-618', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, 'CSSCI来源期刊', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-619', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, '中国科学引文数据库论文', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-620', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, 'A&HCI', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-621', '2020-03-04 11:23:01', '2020-03-04 11:23:01', 0, b'1', NULL, '理事', '担任职务', '2010006020', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-622', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '政策法规', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-623', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '制度办法', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-624', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重大技术保障规范', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-625', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重大技术保障标准', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-626', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重大技术保障软件', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-627', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重大学科建设', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-628', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '教学改革', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-629', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研发展', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-630', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '人才队伍建设', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-631', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '规划计划', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-632', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重要规章制度', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-633', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重要报告', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-634', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重要总结', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-635', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '重要讲话', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-636', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研质量管理体系', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-637', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '工程型号研制', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-638', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研项目管理', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-639', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研项目计量', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-640', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研项目质量', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-641', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研项目科技信息', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-642', '2020-03-04 11:32:58', '2020-03-04 11:32:58', 0, b'1', NULL, '科研项目知识产权', '工作属性', '1240000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-643', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '制定', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-644', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '论证', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-645', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '调研', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-646', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '组织实施', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-647', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '文件编写', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-648', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '文件修订', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-649', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '编写管理文件', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-650', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '编写技术文件', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-651', '2020-03-04 11:34:21', '2020-03-04 11:34:21', 0, b'1', NULL, '编写工艺文件', '工作内容', '1240000501', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-652', '2020-03-04 11:35:07', '2020-03-04 11:35:07', 0, b'1', NULL, '军队级以上', '工作级别', '1240000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-653', '2020-03-04 11:35:07', '2020-03-04 11:35:07', 1, b'1', NULL, '校级以上', '工作级别', '1240000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-654', '2020-03-04 11:35:07', '2020-03-04 11:35:07', 2, b'1', NULL, '院级以上', '工作级别', '1240000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-655', '2020-03-04 11:35:52', '2020-03-04 11:35:52', 0, b'1', NULL, '是', '是否颁布实施', '1240000701', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-656', '2020-03-04 11:35:52', '2020-03-04 11:35:52', 0, b'1', NULL, '否', '是否颁布实施', '1240000701', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-657', '2020-03-04 11:40:41', '2020-03-04 11:40:41', 0, b'1', NULL, '是', '技术研发成果是否在部队列装使用', '1250000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-658', '2020-03-04 11:40:41', '2020-03-04 11:40:41', 0, b'1', NULL, '否', '技术研发成果是否在部队列装使用', '1250000401', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-659', '2020-03-04 14:14:37', '2020-03-04 14:14:35', 0, b'1', '', '是', '是否优质课', '1020001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-660', '2020-03-04 14:14:55', '2020-03-04 14:14:58', 0, b'1', '', '否', '是否优质课', '1020001201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-661', '2020-03-04 14:15:54', '2020-03-04 14:15:58', 0, b'1', '', '是', '是否军队级以上金课', '1020001301', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-662', '2020-03-04 14:15:56', '2020-03-04 14:15:59', 0, b'1', '', '否', '是否军队级以上金课', '1020001301', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-663', '2020-03-04 15:57:51', '2020-03-04 15:57:51', 0, b'1', NULL, '国家级', '院校等级', '1030000801', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-664', '2020-03-04 15:57:51', '2020-03-04 15:57:51', 1, b'1', NULL, '军队级', '院校等级', '1030000801', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-665', '2020-03-04 15:57:51', '2020-03-04 15:57:51', 2, b'1', NULL, '省级', '院校等级', '1030000801', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-666', '2020-03-04 15:57:51', '2020-03-04 15:57:51', 3, b'1', NULL, '校级', '院校等级', '1030000801', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-667', '2020-03-04 15:57:51', '2020-03-04 15:57:51', 4, b'1', NULL, '院级', '院校等级', '1030000801', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-668', '2020-03-04 18:29:09', '2020-03-04 18:29:09', 0, b'1', NULL, '特等奖', '指导的青年教员获得校级以上教学比赛奖励（等级）', '1050000201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-669', '2020-03-04 18:29:09', '2020-03-04 18:29:09', 0, b'1', NULL, '一等奖', '指导的青年教员获得校级以上教学比赛奖励（等级）', '1050000201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-670', '2020-03-04 18:29:09', '2020-03-04 18:29:09', 0, b'1', NULL, '二等奖', '指导的青年教员获得校级以上教学比赛奖励（等级）', '1050000201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-671', '2020-03-04 18:29:09', '2020-03-04 18:29:09', 0, b'1', NULL, '三等奖', '指导的青年教员获得校级以上教学比赛奖励（等级）', '1050000201', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-672', '2020-03-05 09:56:02', '2020-03-05 09:56:02', 0, b'1', NULL, '先进个人', '表彰奖励情况', '1170000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-673', '2020-03-05 09:56:02', '2020-03-05 09:56:02', 0, b'1', NULL, '嘉奖', '表彰奖励情况', '1170000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-674', '2020-03-05 09:56:02', '2020-03-05 09:56:02', 0, b'1', NULL, '通报表扬', '表彰奖励情况', '1170000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-675', '2020-03-05 09:56:02', '2020-03-05 09:56:02', 0, b'1', NULL, '感谢信', '表彰奖励情况', '1170000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-676', '2020-03-05 09:56:02', '2020-03-05 09:56:02', 0, b'1', NULL, '表扬信', '表彰奖励情况', '1170000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-677', '2020-03-05 09:56:02', '2020-03-05 09:56:02', 0, b'1', NULL, '无', '表彰奖励情况', '1170000601', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-678', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, '教育类核心期刊', '检索类型', '2010006009', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-679', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, '是', '是否取得标志性成果', '1250001001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-680', '2020-03-04 11:18:59', '2020-03-04 11:18:59', 0, b'1', NULL, '否', '是否取得标志性成果', '1250001001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-681', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '政府特殊津贴', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-682', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '军队专业技术人才一类岗位津贴', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-683', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '军队专业技术人才二类岗位津贴', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-684', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '军队专业技术人才三类岗位津贴', '各级人才表彰项目', '2010004013', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-685', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '作战任务', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-686', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '军事演习', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-687', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '科研实验', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-688', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '重大装备实验', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-689', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '抢险救灾', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-690', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '反恐维稳', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-691', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '其他', '任务类型', '2010026001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-692', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '工程设计方案', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-693', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '技术标准规范', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-694', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '军事需求研究', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-695', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '决策咨询建议', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-696', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '技术标准规范', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-697', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '技术保障决策咨询建议', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-698', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '试验方案', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-699', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '测试大纲', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-700', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '测试报告', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-701', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '武器装备维护', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-702', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '勤务保障', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-703', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '技术报告（GF报告）', '工作内容', '2010026002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-704', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '教务处', '下达机关', '2010026003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-705', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '科研处', '下达机关', '2010026003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-706', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '教保处', '下达机关', '2010026003', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-707', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 1, b'1', NULL, '师以上', '下达机关', '2010026004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-708', '2020-03-02 15:58:22', '2020-03-02 15:58:22', 0, b'1', NULL, '军以上', '下达机关', '2010026004', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-709', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '其他优秀导师奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-710', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '其他优秀教师奖', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-711', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '其他', '奖励类别', '2010005007', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-712', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '春季学期', '学期', '2010005012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-713', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '秋（夏）季学期', '学期', '2010005012', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-714', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '全日制本科', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-715', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '全日制硕士研究生', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-716', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '全日制博士研究生', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-717', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '大专', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-718', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '在职硕士研究生', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-719', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '在职博士研究生', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-720', '2020-03-02 16:23:19', '2020-03-02 16:23:19', 0, b'1', NULL, '其他', '培训层次', '2010004014', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-721', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '指挥控制对抗系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-722', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '预警探测对抗系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-723', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '导航和制导对抗系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-724', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '战场网络对抗系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-725', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '网电作战目标侦查系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-726', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '兵种战术系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-727', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '作战指挥系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-728', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '训练管理系', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-729', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '国家重点实验室', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-730', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '教研保障中心', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-731', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '服务保障中心', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-732', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '办公室', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-733', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '教务处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-734', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '教保处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-735', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '科研学术处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-736', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '政治工作处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-737', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '纪检监察处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-738', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '安全管理处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-739', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '供应保障处', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-740', '2020-03-16 10:55:48', '2020-03-16 10:56:09', 0, b'1', '', '教练勤务营', '实际工作单位', '1010002901', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-741', '2020-03-16 11:01:11', '2020-03-16 11:01:14', 0, b'1', '', '军委直属院校', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `base_simple_definition` VALUES ('2010-742', '2020-03-16 11:01:13', '2020-03-16 11:01:17', 0, b'1', '', '原总部机关直属院校', '大单位', '2010004002', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_academic_record
-- ----------------------------
DROP TABLE IF EXISTS `qa_academic_record`;
CREATE TABLE `qa_academic_record`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '五大类id',
  `category_child_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域id',
  `category_academic_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称id',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态\r\n1：审核通过\r\n10：待提交审核（默认）\r\n20：待电脑审核\r\n21：电脑审核未通过\r\n30：待人工审核（电脑审核通过）\r\n31：人工审核未通过',
  `first_check_status` int(11) NULL DEFAULT NULL COMMENT '\r\n电脑审核状态\r\n0：初始化状态（默认）\r\n1：审核通过\r\n2:  待审核\r\n3：电脑审核未通过',
  `first_check_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '初审时间',
  `second_check_status` int(11) NULL DEFAULT NULL COMMENT '人工审核状态\r\n\r\n0：初始状态\r\n1：审核通过\r\n2：待人工审核\r\n3：人工审核未通过',
  `second_check_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '人工审核时间',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职称评审记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa_academic_record_log
-- ----------------------------
DROP TABLE IF EXISTS `qa_academic_record_log`;
CREATE TABLE `qa_academic_record_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `academic_record_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称记录id',
  `operator_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批内容',
  `before_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '之前信息',
  `after_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '之后信息',
  `type` int(11) NULL DEFAULT NULL COMMENT '操作类型\r\n1. 电脑审批\r\n2. 人工审批',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职称评审记录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa_apply_log
-- ----------------------------
DROP TABLE IF EXISTS `qa_apply_log`;
CREATE TABLE `qa_apply_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请记录id（qa_apply_record表主键）',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用户姓名  冗余字段',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id  冗余字段',
  `category_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请领域（与qa_apply_record中该字段相同）冗余字段',
  `type` int(11) NULL DEFAULT NULL COMMENT '操作类型：1：评审通过    2：评审未通过',
  `category_child_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请岗位（与qa_apply_record中该字段相同）冗余字段',
  `category_academic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请职称（与qa_apply_record中该字段相同）冗余字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_apply_log
-- ----------------------------
INSERT INTO `qa_apply_log` VALUES ('1', '11', '2020-02-20 19:17:44', '2020-02-20 21:17:46', '11', '11', '1000', 1, '1', '1');
INSERT INTO `qa_apply_log` VALUES ('122fe71d-a55e-45a7-a1a2-91d90ef12e33', '98dd14b4-bf15-4119-ab37-b472cf87dc56', '2020-02-21 22:02:03', '2020-02-21 22:02:03', '盼盼', '1', '1000', 0, '1010', '101001');
INSERT INTO `qa_apply_log` VALUES ('2', '22', '2020-02-20 18:19:41', '2020-02-20 18:19:43', '11', '11', '2000', 1, '1', '1');

-- ----------------------------
-- Table structure for qa_apply_record
-- ----------------------------
DROP TABLE IF EXISTS `qa_apply_record`;
CREATE TABLE `qa_apply_record`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用户（qa_user_info表主键）',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请用户姓名',
  `category_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请领域（qa_category表主键）',
  `category_child_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请岗位（qa_category表主键）',
  `category_academic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请职称（qa_category表主键）',
  `first_approve_status` int(11) NULL DEFAULT NULL COMMENT '初审状态  0：未通过 1：通过 ',
  `first_approve_time` datetime(0) NULL DEFAULT NULL COMMENT '初审时间',
  `second_approve_status` int(11) NULL DEFAULT NULL COMMENT '复审状态  0：未通过 1：通过   2：审批中 ',
  `second_approve_time` datetime(0) NULL DEFAULT NULL COMMENT '复审时间',
  `approve_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复审意见',
  `approve_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人id',
  `approve_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批人姓名',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0：仅存储   1：进入申请',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '录入信息记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa_apply_record_attribute
-- ----------------------------
DROP TABLE IF EXISTS `qa_apply_record_attribute`;
CREATE TABLE `qa_apply_record_attribute`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `apply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请id（qa_apply_record表主键）',
  `caluse_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件id（qa_caluse表主键）',
  `user_choice` int(1) NULL DEFAULT NULL COMMENT '用户选择结果  1：是 0：否',
  `annex_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `past` bigint(1) NULL DEFAULT NULL COMMENT '是否满足业绩条件要求  1：是 0：否  （一个条件下有多个条件要求具备其一时：若至少有一个为是，则所有是否满足业绩条件要求均为是；若一个都为选中，则所有是否满足业绩条件要求均为否）',
  `fail_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '未通过原因（未选中、无附件等）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职称申请记录扩展表（用于存储是否满足业绩条件，与职称申请记录为多对一的关系）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_apply_record_attribute
-- ----------------------------
INSERT INTO `qa_apply_record_attribute` VALUES ('02040bfe-a0f9-43ef-aac6-26969291b922', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100154', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('06f1641d-8ddc-4f75-b118-a4879293542f', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300131', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('0944136b-5ed4-41fd-9a7c-8cd8e9030f3a', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300141', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('0bfd1fe7-25dd-48de-963f-c409cd1576e9', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200141', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('0fba51bb-0aac-437a-bfd0-8798bc01ded1', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100141', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('15210b32-4936-4b74-baff-09dbc4b93733', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300221', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('1afb36ed-2fe8-45ca-bf59-55d71221b108', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300161', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('1c074002-54ce-43d7-8b41-1d85cb08bea5', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300241', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('1f85c8d0-6e20-44bf-a07c-d0ae800afa5a', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100152', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('20cb79c5-4e1c-4130-9495-e11824dea423', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100153', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('29062549-fb7d-40a0-8ab2-151d683bea6d', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300261', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('2c20b8cc-7b8d-4956-95c9-9a9865359f35', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200211', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('32fab233-8fd7-420c-b064-b3fd840acb42', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200178', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('3499425e-6146-4f36-aba6-72a2f34156f9', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100257', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('401097d2-e70b-41e8-8984-018dbb52fd19', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100161', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('40c0f679-1405-481d-aada-2888bc78b883', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200261', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('42067093-6675-47ae-9317-3404aa587369', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300251', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('430e7669-446e-487d-b8f8-71d0c8bcdbce', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300231', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('43308fc4-e316-4acc-90af-4dd8c3472290', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100253', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('44357ba2-4490-40de-a30c-8bc6710089e2', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200174', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('453ab8c0-7f0c-488b-91ad-65158ddb7682', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200221', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('48aa3594-c2ab-42dd-bed9-1cc698fca9ff', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200231', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('58bbb98d-f4b0-402b-945b-0d07f899444c', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300111', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('5e07931f-13c4-4db5-996a-7958c23e3da9', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200161', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('6220dd80-ea4c-49cb-ae7e-b7cbb16be946', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100131', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('634431e5-15bd-4b95-8fcd-a205b1a250f8', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200251', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('640a11c4-1706-455f-b49d-510811d3df27', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300121', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('7053ca37-ebe3-4a10-a577-7b6803581ab4', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300281', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('775c68e4-7bc2-4bf7-b7ec-409e99e8efb8', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100251', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('84760de4-e575-461f-b0dc-24dbcc1b89e5', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100261', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('8621f4ce-1fbf-48a8-9c5b-39116d76b985', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200171', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('8b51509e-ba9e-4239-9347-7837edeec2e1', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200272', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('8c57747d-4745-4dd5-af1c-4cbcc984e700', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200175', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('8c57f74b-5fde-4c53-ae5b-3bb9ff009289', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200176', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('8d5db0ed-ee39-4c13-9608-a5704b2c4837', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100256', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('90313c7e-338d-4bc2-96af-1696be629c9e', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200276', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('9354a8f0-f8fa-4e4f-b679-86c7dae535b9', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300171', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('9769b653-0c68-4f67-a0e4-e61277032637', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200277', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('998ad035-d76a-49a9-a688-7f2f2eca0c61', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100231', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('a3e2b215-80fd-4d3a-bace-9a9d350ae293', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200172', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('ab43aadc-2684-4440-b62e-832d714db50e', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100211', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('abde387a-ef35-496e-962e-332ec417c7af', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200131', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('ad085240-36bd-4467-9b7b-c0a392911e18', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200181', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('af6765d5-6505-4c11-a4bf-1a85595b416f', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200271', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('b002415b-8ddb-43ed-93a3-df07c887bd48', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100221', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('b0de6c58-93d3-4af8-b0de-5015493fcb6e', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100255', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('b5d6b030-271e-43fb-a68d-b60799485614', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200111', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('b69d16e3-7ba9-430a-92e5-bde750e4f57f', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300211', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('b85b9132-bfc4-47f6-9ff5-3dfcf076d952', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100157', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('b9f7cb47-c6cc-4100-ab1b-0a72eabc0fef', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300151', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('bb07c513-f920-45f4-94d9-ca1e7d3bd7a5', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100156', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('bc1f1301-e8ff-4851-8f76-4f5207a05ee9', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100241', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('bcee04c6-e56c-4a30-904b-dce559b10831', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100271', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('bdadeb66-da18-4bc7-a288-22e121cf5673', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200173', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('ccae267d-197a-4105-9341-46993aeb75d8', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200121', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('d15d3080-73c9-4f19-a8e3-f0be9ce8b573', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100151', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('d260a215-bcc1-4ec8-b15d-6c2b44f4cd8d', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100121', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('d3162fb5-04d0-4715-be82-54fbe2b6f07b', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100155', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('d3cf5cfd-c7cf-4a2a-ac34-067b581cb98e', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200177', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('d59a3507-0d6b-4aa9-8320-2f7270ef57e7', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100111', 1, NULL, 1, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('dce6a8e9-e839-4f23-904d-a086381534b2', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100252', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('dcf3900a-284c-43d6-9ccf-f090f2a1fab5', '721df71b-7eb0-4e12-a1ea-df20d39b3ba0', '10100171', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('deedeaa2-b905-44a8-afbb-0a3a2116001a', '37286a77-fa83-4c96-b3f9-db0ca798e8eb', '10300271', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('df4661aa-193c-46cd-ae46-502e3a8fe8dc', 'f24e17dc-0d06-4b92-89ad-db0cafa9e936', '10100254', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('e0f21879-ff24-4dc8-9420-2f43a13531e3', 'e86d6e30-791d-4c0f-96cf-bee96b23b1c9', '10200151', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('e2d9c3ae-984e-4ff7-9ff1-56fd5f529eb0', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200241', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('e75b37b6-e25d-448c-8866-94cc611aaa33', 'dc777222-d59c-4458-a1f0-7b180ae6fe32', '10300181', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('eeabee87-0c5d-4397-8b32-197b18320ad7', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200275', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('f0681362-01a5-4413-962a-288fb463c749', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200273', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('f5529042-0761-46f3-874c-c54511d282fc', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200281', 0, NULL, 0, NULL);
INSERT INTO `qa_apply_record_attribute` VALUES ('ff8dd291-a691-48db-a04a-42c436e81b68', 'c6bb7576-e3da-4246-9a2d-473293b66e77', '10200274', 0, NULL, 0, NULL);

-- ----------------------------
-- Table structure for qa_base_clause
-- ----------------------------
DROP TABLE IF EXISTS `qa_base_clause`;
CREATE TABLE `qa_base_clause`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码code',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标头',
  `content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件表达式',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基本条件项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_base_clause
-- ----------------------------
INSERT INTO `qa_base_clause` VALUES ('1000', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1000', '系统讲授课程数量（门）', NULL, NULL, NULL, 1000, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1001', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1001', '是否为本科学员讲授1门以上课程', NULL, NULL, NULL, 1001, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1002', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1002', '协助军队级以上金课主讲教师系统辅导1门本科课程', NULL, NULL, NULL, 1002, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1003', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1003', '年均净课时量', NULL, NULL, NULL, 1003, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1010', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1010', '参加课堂教学质量校级评价并获优秀次数（次）', NULL, NULL, NULL, 1010, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1011', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1011', '课堂教学质量学校随机抽查结果', NULL, NULL, NULL, 1011, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1020', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1020', '被评为优秀教师奖、优秀导师奖、优秀教学奖情况 ', NULL, NULL, NULL, 1020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1021', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1020', '参加校级以上各类教学比赛中获得奖励', NULL, NULL, NULL, 1020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1022', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1022', '获得教学成果奖', NULL, NULL, NULL, 1022, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1030', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1030', '担任军队级以上金课主讲教师及排名', NULL, NULL, NULL, 1030, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1040', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1040', '作为主编、副主编出版全国统编教材或军队级教材', NULL, NULL, NULL, 1040, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1041', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1041', '出版本专业领域专著（编著、译著）', NULL, NULL, NULL, 1041, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1050', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1050', '指导的青年教员获得校级以上教学比赛奖励', NULL, NULL, NULL, 1050, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1051', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1051', '指导学员参加创新实践竞赛，被评为学员A类竞赛优秀指导教员', NULL, NULL, NULL, 1051, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1052', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1052', '担任本科全程导师或指导本科优异生或本科学员毕业设计（人）', NULL, NULL, NULL, 1052, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1053', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1053', '指导学员获得校级优秀毕业设计（人）', NULL, NULL, NULL, 1053, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1060', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1060', '指导硕士生或博士生数量', NULL, NULL, NULL, 1060, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1061', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1061', '指导的研究生学位论文在各级抽查中没有不合格情况', NULL, NULL, NULL, 1061, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1070', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1070', '协助指导的博士后，进站获资助情况', NULL, NULL, NULL, 1070, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1080', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1080', '参加军委、战区或军兵种组织的作战任务、军事研究等重大军事行动或全军组织的代职支援工作中奖励 ', NULL, NULL, NULL, 1080, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1081', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1081', '参加师级以上单位组织的综合演练、军事比武等活动，被表彰为先进个人', NULL, NULL, NULL, 1081, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1090', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1090', '主持或参与国家、军队级重大项目、课题任务（项）', NULL, NULL, NULL, 1090, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1091', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1091', '作为核心骨干参与国家、军队级重大项目、课题任务（项）', NULL, NULL, NULL, 1091, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1100', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1100', '参与国家、军队级重大项目 1 项以上（排名前 7），获得国家发明专利或国防专利 1 项以上，或者实用新型专利或外观设计专利 1 项以上', NULL, NULL, NULL, 1100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1110', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1110', '获得科技奖励', NULL, NULL, NULL, 1110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1120', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1120', '发表进入SCIE、EI Compendex、SSCI、A&HCI检索论文（篇）', NULL, NULL, NULL, 1120, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1121', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1121', '在学科重要刊物上论文发表（篇）', NULL, NULL, NULL, 1121, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1122', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1122', '发表进入中国科技论文核心期刊、CSSCI来源期刊、全国中文核心期刊、军事学核心期刊和中国科学引文数据库论文（篇，可含重大项目的技术报告 1 篇）', NULL, NULL, NULL, 1122, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1123', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1123', '在有CN刊号的期刊或出版物发表与本职工作紧密相关的论文或报告（篇）', NULL, NULL, NULL, 1123, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1124', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1124', '教育类核心期刊或军事学核心期刊发表论文或报告（篇）', NULL, NULL, NULL, 1124, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1130', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1130', '撰写决策咨询意见、研究论证报告，及军级以上单位领导的肯定性批示（篇）', NULL, NULL, NULL, 1130, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1140', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1140', '参与完成军队级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作 ', NULL, NULL, NULL, 1140, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1141', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1141', '提出的业务建议、保障方案、咨询报告、工作经验等被军委机关以上领导采纳、获得批示批转 ', NULL, NULL, NULL, 1141, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1142', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1142', '作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证及组织实施工作 ', NULL, NULL, NULL, 1142, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('1143', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '1143', '主笔起草重要规章制度、报告、总结、讲话等材料 （篇）', NULL, NULL, NULL, 1143, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2000', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2000', '主持国家、军队级项目（项）', NULL, NULL, NULL, 2000, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2001', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2001', '作为核心骨干，参与国家、军队级项目（项）', NULL, NULL, NULL, 2001, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2010', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2010', '发表进入SCIE、EI Compendex、SSCI、A&HCI检索论文（篇）', NULL, NULL, NULL, 2010, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2011', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2011', '在学科重要刊物上论文发表（篇）', NULL, NULL, NULL, 2011, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2012', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2012', '发表进入SCIE、EI Compendex、SSCI、A&HCI检索论文或在学科重要刊物上论文发表（篇）', NULL, NULL, NULL, 2012, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2013', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2013', '发表进入中国科技论文核心期刊、CSSCI来源期刊、全国中文核心期刊、军事学核心期刊和中国科学引文数据库论文（篇）', NULL, NULL, NULL, 2013, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2014', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2014', '在有CN刊号的期刊或出版物发表与本职工作紧密相关的论文或报告（篇）', NULL, NULL, NULL, 2014, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2020', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2020', '出版本专业领域专著（编著、译著）', NULL, NULL, NULL, 2020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2030', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2030', '主笔起草国家标准、军用标准、行业标准等正式颁布的标准或规范（项）', NULL, NULL, NULL, 2030, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2040', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2040', '第一作者，撰写决策咨询意见、研究论证报告，及军级以上单位领导的肯定性批示（篇）', NULL, NULL, NULL, 2040, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2041', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2041', '作为第一作者的咨询报告被新华社内参、解放军报内参、军队情况摘报等刊用数量（篇）', NULL, NULL, NULL, 2041, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2050', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2050', '参加国际会议，作大会邀请报告（次）', NULL, NULL, NULL, 2050, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2051', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2051', '参加国际会议，作大会分会场邀请报告（次）', NULL, NULL, NULL, 2051, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2052', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2052', '参加全国性学会举办的学术会议，作大会邀请报告（次）', NULL, NULL, NULL, 2052, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2053', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2053', '参加全国性学会举办的学术会议，作大会分会场邀请报告（次）', NULL, NULL, NULL, 2053, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2060', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2060', '担任CSSIC\\CSCD\\SCI\\SSCI\\EI\\A&HCI收录期刊', NULL, NULL, NULL, 2060, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2070', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2070', '在本专业领域知名学会、协会等军内外专业学术团体中担任过理事以上职务1届以上', NULL, NULL, NULL, 2070, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2080', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2080', '指导硕士生或博士生数量', NULL, NULL, NULL, 2080, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2081', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2081', '指导的研究生学位论文在各级抽查中没有不合格情况', NULL, NULL, NULL, 2081, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2082', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2082', '协助指导的博士后，进站或中国博士后科学基金资助', NULL, NULL, NULL, 2082, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2083', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2083', '被评为学员A类竞赛优秀指导教员', NULL, NULL, NULL, 2083, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2084', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2084', '担任本科全程导师或指导本科优异生或本科学员毕业设计（人）', NULL, NULL, NULL, 2084, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2085', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2085', '指导学员获得校级优秀毕业设计（人）', NULL, NULL, NULL, 2085, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2090', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2090', '获得教学科技奖励', NULL, NULL, NULL, 2090, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2091', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2091', '质量奖励表彰', NULL, NULL, NULL, 2091, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2100', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2100', '获得国家发明专利或国防专利 ，或者实用新型专利或外观设计专利（项）', NULL, NULL, NULL, 2100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2101', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2101', '作为团队核心骨干，技术研发成果在部队列装使用，或科技成果转化收益', NULL, NULL, NULL, 2101, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2110', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2110', '参加军委、战区或军兵种组织的作战任务、军事研究等重大军事行动或或全军组织的代职支援工作中奖励 ', NULL, NULL, NULL, 2110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2111', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2111', '参加师级以上单位组织的综合演练、军事比武等活动，被表彰为先进个人', NULL, NULL, NULL, 2111, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2120', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2120', '参与完成军队级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作 ', NULL, NULL, NULL, 2120, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2121', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2121', '作为主要起草人编写或修订科研质量管理体系文件，并颁布实施', NULL, NULL, NULL, 2121, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2122', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2122', '提出的业务建议、保障方案、咨询报告、工作经验等被军委机关以上领导采纳、获得批示批转 ', NULL, NULL, NULL, 2122, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2123', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2123', '编写工程型号项目研制过程所需的管理、技术、工艺等文件（份）', NULL, NULL, NULL, 2123, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2124', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2124', '作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证及组织实施工作 ', NULL, NULL, NULL, 2124, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2125', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2125', '主笔起草重要规章制度、报告、总结、讲话等材料 （篇）', NULL, NULL, NULL, 2125, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2126', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2126', '作为主要参加者，完成校级以上科研项目管理或标准、计量、质量、科技信息、知识产权等方面的调研、论证及组织实施工作，主笔起草相关的规章制度、报告、总结、讲话、方案、规划等材料', NULL, NULL, NULL, 2126, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2130', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '2130', '积极落实军委机关相关政策及任务、推动校内外重要战略合作、促进学校科研成果转化、促进科研管理信息化建设、在学校以上范围内组织培训或技术交流会、组织学校质量管理体系审核（项）', NULL, NULL, NULL, 2130, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('2140', '2020-03-04 14:47:34', '2020-03-04 14:47:34', '2140', '', NULL, NULL, NULL, 2140, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3000', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3000', '主持国家、军队级项目（项）', NULL, NULL, NULL, 3000, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3010', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3010', '校级以上教育教学改革研究课题', NULL, NULL, NULL, 3010, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3020', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3020', '作为核心骨干，参与国家、军队级项目（项）', NULL, NULL, NULL, 3020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3030', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3030', '在参加重大军事行动任务中解决重大技术难题、提出关键决策建议、推动创新成果转化运用，对提升战斗力发挥重要作用', NULL, NULL, NULL, 3030, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3040', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3040', '参加作战任务、军事演习、科研试验、重大装备试验、抢险救灾、反恐维稳等重大军事行动中，作为主要负责人在武器装备维护、勤务保障工作中，解决重大技术难题、提出关键决策建议和作出重大贡献者，受到军级以上单位表彰', NULL, NULL, NULL, 3040, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3050', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3050', '在参加重大装备试验任务中解决重大技术难题、提出关键决策建议、推动创新成果转化运用，对提升战斗力发挥重要作用', NULL, NULL, NULL, 3050, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3060', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3060', '撰写技术报告（GF报告）', NULL, NULL, NULL, 3060, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3070', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3070', '获得教学科技奖励', NULL, NULL, NULL, 3070, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3080', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3080', '获得国家发明专利或国防专利 ，或者实用新型专利或外观设计专利（项）', NULL, NULL, NULL, 3080, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3090', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3090', '作为团队核心骨干，技术研发成果在部队列装使用，或科技成果转化收益', NULL, NULL, NULL, 3090, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3100', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3100', '以第一作者撰写工程设计方案、技术标准规范（篇）', NULL, NULL, NULL, 3100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3110', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3110', '以第一作者撰写军事需求研究、决策咨询建议、技术标准规范、技术保障决策咨询建议、试验方案、测试大纲、测试报告（篇）', NULL, NULL, NULL, 3110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3120', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3120', '被师级以上单位采纳应用', NULL, NULL, NULL, 3120, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3130', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3130', '承担武器装备试验任务或师级以上单位武器装备发展规划、质量管理、勤务保障等任务（项）', NULL, NULL, NULL, 3130, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3140', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3140', '承担武器装备试验任务（项）', NULL, NULL, NULL, 3140, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3150', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3150', '师级以上单位武器装备发展规划、质量管理、勤务保障等任务（项）', NULL, NULL, NULL, 3150, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3160', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3160', '在有CN刊号的期刊或出版物发表与本职工作紧密相关的论文或报告（篇', NULL, NULL, NULL, 3160, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3170', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3170', '出版本专业领域专著（编著、译著）', NULL, NULL, NULL, 3170, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3180', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3180', '参与完成军队级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作', NULL, NULL, NULL, 3180, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3190', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3190', '提出的业务建议、保障方案、咨询报告、工作经验等被军委机关以上领导采纳、获得批示批转 ', NULL, NULL, NULL, 3190, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3200', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3200', '作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证级组织实施工作', NULL, NULL, NULL, 3200, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3210', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3210', '主笔起草重要规章制度、报告、总结、讲话等材料 （篇）', NULL, NULL, NULL, 3210, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause` VALUES ('3220', '2020-03-04 14:47:11', '2020-03-04 14:47:11', '3220', '作为主要成员撰写具有较高学术水平和技术价值的技术报告、论证报告（篇）', NULL, NULL, NULL, 3220, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_base_clause_group
-- ----------------------------
DROP TABLE IF EXISTS `qa_base_clause_group`;
CREATE TABLE `qa_base_clause_group`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标头',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件表达式',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '基本条件项组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_base_clause_group
-- ----------------------------
INSERT INTO `qa_base_clause_group` VALUES ('1000', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1000', '系统讲授课程数量（门）', NULL, NULL, NULL, 1000, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1001', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1001', '是否为本科学员讲授1门以上课程', NULL, NULL, NULL, 1001, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1002', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1002', '协助军队级以上金课主讲教师系统辅导1门本科课程', NULL, NULL, NULL, 1002, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1003', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1003', '年均净课时量', NULL, NULL, NULL, 1003, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1010', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1010', '参加课堂教学质量校级评价并获优秀次数（次）', NULL, NULL, NULL, 1010, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1011', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1011', '课堂教学质量学校随机抽查结果', NULL, NULL, NULL, 1011, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1020', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1020', '被评为优秀教师奖、优秀导师奖、优秀教学奖情况 ', NULL, NULL, NULL, 1020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1021', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1020', '参加校级以上各类教学比赛中获得奖励', NULL, NULL, NULL, 1021, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1022', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1022', '获得教学成果奖', NULL, NULL, NULL, 1022, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1030', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1030', '担任军队级以上金课主讲教师及排名', NULL, NULL, NULL, 1030, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1040', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1040', '作为主编、副主编出版全国统编教材或军队级教材', NULL, NULL, NULL, 1040, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1041', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1041', '出版本专业领域专著（编著、译著）', NULL, NULL, NULL, 1041, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1050', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1050', '指导的青年教员获得校级以上教学比赛奖励', NULL, NULL, NULL, 1050, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1051', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1051', '指导学员参加创新实践竞赛，被评为学员A类竞赛优秀指导教员', NULL, NULL, NULL, 1051, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1052', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1052', '担任本科全程导师或指导本科优异生或本科学员毕业设计（人）', NULL, NULL, NULL, 1052, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1053', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1053', '指导学员获得校级优秀毕业设计（人）', NULL, NULL, NULL, 1053, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1060', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1060', '指导硕士生或博士生数量', NULL, NULL, NULL, 1060, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1061', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1061', '指导的研究生学位论文在各级抽查中没有不合格情况', NULL, NULL, NULL, 1061, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1070', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1070', '协助指导的博士后，进站获资助情况', NULL, NULL, NULL, 1070, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1080', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1080', '参加军委、战区或军兵种组织的作战任务、军事研究等重大军事行动或全军组织的代职支援工作中奖励 ', NULL, NULL, NULL, 1080, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1081', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1081', '参加师级以上单位组织的综合演练、军事比武等活动，被表彰为先进个人', NULL, NULL, NULL, 1081, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1090', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1090', '主持或参与国家、军队级重大项目、课题任务（项）', NULL, NULL, NULL, 1090, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1091', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1091', '作为核心骨干参与国家、军队级重大项目、课题任务（项）', NULL, NULL, NULL, 1091, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1100', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1100', '参与国家、军队级重大项目 1 项以上（排名前 7），获得国家发明专利或国防专利 1 项以上，或者实用新型专利或外观设计专利 1 项以上', NULL, NULL, NULL, 1100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1110', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1110', '获得科技奖励', NULL, NULL, NULL, 1110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1120', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1120', '发表进入SCIE、EI Compendex、SSCI、A&HCI检索论文（篇）', NULL, NULL, NULL, 1120, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1121', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1121', '在学科重要刊物上论文发表（篇）', NULL, NULL, NULL, 1121, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1122', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1122', '发表进入中国科技论文核心期刊、CSSCI来源期刊、全国中文核心期刊、军事学核心期刊和中国科学引文数据库论文（篇，可含重大项目的技术报告 1 篇）', NULL, NULL, NULL, 1122, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1123', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1123', '在有CN刊号的期刊或出版物发表与本职工作紧密相关的论文或报告（篇）', NULL, NULL, NULL, 1123, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1124', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1124', '教育类核心期刊或军事学核心期刊发表论文或报告（篇）', NULL, NULL, NULL, 1124, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1130', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1130', '撰写决策咨询意见、研究论证报告，及军级以上单位领导的肯定性批示（篇）', NULL, NULL, NULL, 1130, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1140', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1140', '参与完成军队级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作 ', NULL, NULL, NULL, 1140, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1141', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1141', '提出的业务建议、保障方案、咨询报告、工作经验等被军委机关以上领导采纳、获得批示批转 ', NULL, NULL, NULL, 1141, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1142', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1142', '作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证及组织实施工作 ', NULL, NULL, NULL, 1142, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('1143', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '1143', '主笔起草重要规章制度、报告、总结、讲话等材料 （篇）', NULL, NULL, NULL, 1143, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2000', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2000', '主持国家、军队级项目（项）', NULL, NULL, NULL, 2000, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2001', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2001', '作为核心骨干，参与国家、军队级项目（项）', NULL, NULL, NULL, 2001, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2010', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2010', '发表进入SCIE、EI Compendex、SSCI、A&HCI检索论文（篇）', NULL, NULL, NULL, 2010, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2011', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2011', '在学科重要刊物上论文发表（篇）', NULL, NULL, NULL, 2011, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2012', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2012', '发表进入SCIE、EI Compendex、SSCI、A&HCI检索论文或在学科重要刊物上论文发表（篇）', NULL, NULL, NULL, 2012, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2013', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2013', '发表进入中国科技论文核心期刊、CSSCI来源期刊、全国中文核心期刊、军事学核心期刊和中国科学引文数据库论文（篇）', NULL, NULL, NULL, 2013, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2014', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2014', '在有CN刊号的期刊或出版物发表与本职工作紧密相关的论文或报告（篇）', NULL, NULL, NULL, 2014, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2020', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2020', '出版本专业领域专著（编著、译著）', NULL, NULL, NULL, 2020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2030', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2030', '主笔起草国家标准、军用标准、行业标准等正式颁布的标准或规范（项）', NULL, NULL, NULL, 2030, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2040', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2040', '第一作者，撰写决策咨询意见、研究论证报告，及军级以上单位领导的肯定性批示（篇）', NULL, NULL, NULL, 2040, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2041', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2041', '作为第一作者的咨询报告被新华社内参、解放军报内参、军队情况摘报等刊用数量（篇）', NULL, NULL, NULL, 2041, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2050', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2050', '参加国际会议，作大会邀请报告（次）', NULL, NULL, NULL, 2050, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2051', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2051', '参加国际会议，作大会分会场邀请报告（次）', NULL, NULL, NULL, 2051, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2052', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2052', '参加全国性学会举办的学术会议，作大会邀请报告（次）', NULL, NULL, NULL, 2052, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2053', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2053', '参加全国性学会举办的学术会议，作大会分会场邀请报告（次）', NULL, NULL, NULL, 2053, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2060', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2060', '担任CSSIC\\CSCD\\SCI\\SSCI\\EI\\A&HCI收录期刊', NULL, NULL, NULL, 2060, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2070', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2070', '在本专业领域知名学会、协会等军内外专业学术团体中担任过理事以上职务1届以上', NULL, NULL, NULL, 2070, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2080', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2080', '指导硕士生或博士生数量', NULL, NULL, NULL, 2080, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2081', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2081', '指导的研究生学位论文在各级抽查中没有不合格情况', NULL, NULL, NULL, 2081, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2082', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2082', '协助指导的博士后，进站或中国博士后科学基金资助', NULL, NULL, NULL, 2082, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2083', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2083', '被评为学员A类竞赛优秀指导教员', NULL, NULL, NULL, 2083, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2084', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2084', '担任本科全程导师或指导本科优异生或本科学员毕业设计（人）', NULL, NULL, NULL, 2084, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2085', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2085', '指导学员获得校级优秀毕业设计（人）', NULL, NULL, NULL, 2085, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2090', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2090', '获得教学科技奖励', NULL, NULL, NULL, 2090, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2091', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2091', '质量奖励表彰', NULL, NULL, NULL, 2091, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2100', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2100', '获得国家发明专利或国防专利 ，或者实用新型专利或外观设计专利（项）', NULL, NULL, NULL, 2100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2101', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2101', '作为团队核心骨干，技术研发成果在部队列装使用，或科技成果转化收益', NULL, NULL, NULL, 2101, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2110', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2110', '参加军委、战区或军兵种组织的作战任务、军事研究等重大军事行动或或全军组织的代职支援工作中奖励 ', NULL, NULL, NULL, 2110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2111', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2111', '参加师级以上单位组织的综合演练、军事比武等活动，被表彰为先进个人', NULL, NULL, NULL, 2111, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2120', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2120', '参与完成军队级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作 ', NULL, NULL, NULL, 2120, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2121', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2121', '作为主要起草人编写或修订科研质量管理体系文件，并颁布实施', NULL, NULL, NULL, 2121, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2122', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2122', '提出的业务建议、保障方案、咨询报告、工作经验等被军委机关以上领导采纳、获得批示批转 ', NULL, NULL, NULL, 2122, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2123', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2123', '编写工程型号项目研制过程所需的管理、技术、工艺等文件（份）', NULL, NULL, NULL, 2123, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2124', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2124', '作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证及组织实施工作 ', NULL, NULL, NULL, 2124, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2125', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2125', '主笔起草重要规章制度、报告、总结、讲话等材料 （篇）', NULL, NULL, NULL, 2125, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2126', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2126', '作为主要参加者，完成校级以上科研项目管理或标准、计量、质量、科技信息、知识产权等方面的调研、论证及组织实施工作，主笔起草相关的规章制度、报告、总结、讲话、方案、规划等材料', NULL, NULL, NULL, 2126, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2130', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2130', '积极落实军委机关相关政策及任务、推动校内外重要战略合作、促进学校科研成果转化、促进科研管理信息化建设、在学校以上范围内组织培训或技术交流会、组织学校质量管理体系审核（项）', NULL, NULL, NULL, 2130, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('2140', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '2140', '？', NULL, NULL, NULL, 2140, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3000', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3000', '主持国家、军队级项目（项）', NULL, NULL, NULL, 3000, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3010', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3010', '校级以上教育教学改革研究课题', NULL, NULL, NULL, 3010, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3020', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3020', '作为核心骨干，参与国家、军队级项目（项）', NULL, NULL, NULL, 3020, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3030', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3030', '在参加重大军事行动任务中解决重大技术难题、提出关键决策建议、推动创新成果转化运用，对提升战斗力发挥重要作用', NULL, NULL, NULL, 3030, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3040', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3040', '参加作战任务、军事演习、科研试验、重大装备试验、抢险救灾、反恐维稳等重大军事行动中，作为主要负责人在武器装备维护、勤务保障工作中，解决重大技术难题、提出关键决策建议和作出重大贡献者，受到军级以上单位表彰', NULL, NULL, NULL, 3040, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3050', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3050', '在参加重大装备试验任务中解决重大技术难题、提出关键决策建议、推动创新成果转化运用，对提升战斗力发挥重要作用', NULL, NULL, NULL, 3050, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3060', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3060', '撰写技术报告（GF报告）', NULL, NULL, NULL, 3060, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3070', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3070', '获得教学科技奖励', NULL, NULL, NULL, 3070, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3080', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3080', '获得国家发明专利或国防专利 ，或者实用新型专利或外观设计专利（项）', NULL, NULL, NULL, 3080, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3090', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3090', '作为团队核心骨干，技术研发成果在部队列装使用，或科技成果转化收益', NULL, NULL, NULL, 3090, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3100', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3100', '以第一作者撰写工程设计方案、技术标准规范（篇）', NULL, NULL, NULL, 3100, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3110', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3110', '以第一作者撰写军事需求研究、决策咨询建议、技术标准规范、技术保障决策咨询建议、试验方案、测试大纲、测试报告（篇）', NULL, NULL, NULL, 3110, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3120', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3120', '被师级以上单位采纳应用', NULL, NULL, NULL, 3120, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3130', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3130', '承担武器装备试验任务或师级以上单位武器装备发展规划、质量管理、勤务保障等任务（项）', NULL, NULL, NULL, 3130, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3140', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3140', '承担武器装备试验任务（项）', NULL, NULL, NULL, 3140, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3150', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3150', '师级以上单位武器装备发展规划、质量管理、勤务保障等任务（项）', NULL, NULL, NULL, 3150, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3160', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3160', '在有CN刊号的期刊或出版物发表与本职工作紧密相关的论文或报告（篇', NULL, NULL, NULL, 3160, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3170', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3170', '出版本专业领域专著（编著、译著）', NULL, NULL, NULL, 3170, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3180', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3180', '参与完成军队级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作', NULL, NULL, NULL, 3180, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3190', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3190', '提出的业务建议、保障方案、咨询报告、工作经验等被军委机关以上领导采纳、获得批示批转 ', NULL, NULL, NULL, 3190, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3200', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3200', '作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证级组织实施工作', NULL, NULL, NULL, 3200, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3210', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3210', '主笔起草重要规章制度、报告、总结、讲话等材料 （篇）', NULL, NULL, NULL, 3210, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_group` VALUES ('3220', '2020-03-04 14:37:56', '2020-03-04 14:37:56', '3220', '作为主要成员撰写具有较高学术水平和技术价值的技术报告、论证报告（篇）', NULL, NULL, NULL, 3220, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_base_clause_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_base_clause_item`;
CREATE TABLE `qa_base_clause_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `clause_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件项id',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标头',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别 \r\nselect 下拉框\r\nradio 单选\r\ntext 文本框\r\nselect&text 下拉框可输入\r\ndate 日期',
  `type_validate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型校验 \r\nnull 默认 \r\nselect 下拉框(relation参数校验) \r\ntext 文本 \r\nnumber 数字\r\nrecord  采集标准录入数据条数\r\nnear 采集标准录入的多条数据距离当前年份最近（年份-1开始）的**条\r\ncontains 字符包含关系',
  `location_index` int(11) NULL DEFAULT NULL COMMENT '位置下标',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `relation` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关系引用表(select) id',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '引用的base_parameter ID\r\n对应type_validate中的record',
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '包含字符串\r\n对应type_validate中的contains类型',
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '条件子项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_base_clause_item
-- ----------------------------
INSERT INTO `qa_base_clause_item` VALUES ('1000-01', '1000', '#1#', NULL, NULL, NULL, 'select', '2010005001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1000-02', '1000', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10200-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1001-01', '1001', '#1#', NULL, NULL, NULL, 'select', '2010005001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1001-02', '1001', '#2#', NULL, NULL, NULL, 'select', '2010005002', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1001-03', '1001', '#3#', NULL, NULL, NULL, 'text', 'near', 2, NULL, NULL, '10200-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1002-01', '1002', '#1#', NULL, NULL, NULL, 'select', '2010005001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1002-02', '1002', '#2#', NULL, NULL, NULL, 'radio', '1020001301', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1002-03', '1002', '#3#', NULL, NULL, NULL, 'select', '2010005002', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1003-01', '1003', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10200-014', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1010-01', '1010', '#1#', NULL, NULL, NULL, 'select', '2010005003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1010-02', '1010', '#2#', NULL, NULL, NULL, 'select', '2010005005', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1011-01', '1011', '#1#', NULL, NULL, NULL, 'select', '2010005003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1011-02', '1011', '#2#', NULL, NULL, NULL, 'select', '2010005005', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1020-01', '1020', '#1#', NULL, NULL, NULL, 'select&text', 'contains', 0, NULL, NULL, '10300-002', '2010005007', NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1020-02', '1020', '#2#', NULL, NULL, NULL, 'select', '1030000801', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1021-01', '1021', '#1#', NULL, NULL, NULL, 'select&text', '2010005008', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1022-01', '1022', '#1#', NULL, NULL, NULL, 'select', '1030000801', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1022-02', '1022', '#2#', NULL, NULL, NULL, 'select', '2010005007', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1030-01', '1030', '#1#', NULL, NULL, NULL, 'radio', '1020001301', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1030-02', '1030', '#2#', NULL, NULL, NULL, 'text', 'number', 1, NULL, NULL, '10200-015', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1040-01', '1040', '#1#', NULL, NULL, NULL, 'select', '2010006010', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1040-02', '1040', '#2#', NULL, NULL, NULL, 'select', '2010006012', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1040-03', '1040', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '11100-006', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1041-01', '1041', '#1#', NULL, NULL, NULL, 'select', '2010006011', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1041-02', '1041', '#2#', NULL, NULL, NULL, 'select', '2010006010', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1041-03', '1041', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '11000-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1041-04', '1041', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '11000-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1050-01', '1050', '#1#', NULL, NULL, NULL, 'select', '1050000201', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1051-01', '1051', '#1#', NULL, NULL, NULL, 'select', '2010005007', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1052-01', '1052', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10500-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1053-01', '1053', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10500-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1060-01', '1060', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10500-005', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1060-02', '1060', '#2#', NULL, NULL, NULL, 'select', '2010008001', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1061-01', '1061', '#1#', NULL, NULL, NULL, 'select', '2010005009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1070-01', '1070', '#1#', NULL, NULL, NULL, 'text', 'record', 0, NULL, NULL, '5-10500-003-001', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1080-01', '1080', '#1#', NULL, NULL, NULL, 'select', '2010004008', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1080-02', '1080', '#2#', NULL, NULL, NULL, 'select', '1170000601', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1081-01', '1081', '#1#', NULL, NULL, NULL, 'select', '2010004009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1081-02', '1081', '#2#', NULL, NULL, NULL, 'select', '2010004010', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1081-03', '1081', '#3#', NULL, NULL, NULL, 'select', '1170000601', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1090-01', '1090', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1090-02', '1090', '#2#', NULL, NULL, NULL, 'select', '2010006004', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1090-03', '1090', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1090-04', '1090', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1091-01', '1091', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1091-02', '1091', '#2#', NULL, NULL, NULL, 'select', '2010006004', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1091-03', '1091', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1091-04', '1091', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1100-01', '1100', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1100-02', '1100', '#2#', NULL, NULL, NULL, 'select', '2010006004', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1100-03', '1100', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1100-04', '1100', '#4#', NULL, NULL, NULL, 'select', '2010006005', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1100-05', '1100', '#5#', NULL, NULL, NULL, 'text', 'record', 4, NULL, NULL, '10700-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1100-06', '1100', '#6#', NULL, NULL, NULL, 'text', 'number', 5, NULL, NULL, '10700-009', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1110-01', '1110', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1110-02', '1110', '#2#', NULL, NULL, NULL, 'select', '2010006008', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1110-03', '1110', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10800-007', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1120-01', '1120', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1120-02', '1120', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1121-01', '1121', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1121-02', '1121', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1122-01', '1122', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1122-02', '1122', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1123-01', '1123', '#1#', NULL, NULL, NULL, 'text', 'contains', 0, NULL, NULL, '10900-006', 'CN', NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1123-02', '1123', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1124-01', '1124', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1130-01', '1130', '#1#', NULL, NULL, NULL, 'select', '2010006016', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1130-02', '1130', '#2#', NULL, NULL, NULL, 'select', '2010006017', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1130-03', '1130', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11300-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1130-04', '1130', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '11300-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1140-01', '1140', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1140-02', '1140', '#2#', NULL, NULL, NULL, 'select', '1240000501', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1140-03', '1140', '#3#', NULL, NULL, NULL, 'select', '1240000601', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1141-01', '1141', '#1#', NULL, NULL, NULL, 'select', '2010006016', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1141-02', '1141', '#2#', NULL, NULL, NULL, 'select', '2010006017', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1141-03', '1141', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11300-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1141-04', '1141', '#4#', NULL, NULL, NULL, 'text', 'number', 3, NULL, NULL, '12400-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1142-01', '1142', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1142-02', '1142', '#2#', NULL, NULL, NULL, 'select', '1240000601', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1142-03', '1142', '#3#', NULL, NULL, NULL, 'select', '1240000501', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1143-01', '1143', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('1143-02', '1143', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2000-01', '2000', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2000-02', '2000', '#2#', NULL, NULL, NULL, 'select', '2010006004', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2000-03', '2000', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2000-04', '2000', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2000-05', '2000', '#5#', NULL, NULL, NULL, 'select', '1060001201', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2001-01', '2001', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2001-02', '2001', '#2#', NULL, NULL, NULL, 'select', '2010006004', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2001-03', '2001', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2001-04', '2001', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2001-05', '2001', '#5#', NULL, NULL, NULL, 'select', '1060001201', 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2010-01', '2010', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2010-02', '2010', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2011-01', '2011', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2011-02', '2011', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2012-01', '2012', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2012-02', '2012', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2012-03', '2012', '#3#', NULL, NULL, NULL, 'select', '2010006009', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2013-01', '2013', '#1#', NULL, NULL, NULL, 'select', '2010006009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2013-02', '2013', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2014-01', '2014', '#1#', NULL, NULL, NULL, 'text', 'contains', 0, NULL, NULL, '10900-006', 'CN', NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2014-02', '2014', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10900-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2020-01', '2020', '#1#', NULL, NULL, NULL, 'select', '2010006011', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2020-02', '2020', '#2#', NULL, NULL, NULL, 'select', '2010006010', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2020-03', '2020', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '11000-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2020-04', '2020', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '11000-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2030-01', '2030', '#1#', NULL, NULL, NULL, 'text', 'record', 0, NULL, NULL, '11200-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2030-02', '2030', '#2#', NULL, NULL, NULL, 'text', 'number', 1, NULL, NULL, '11200-005', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2040-01', '2040', '#1#', NULL, NULL, NULL, 'select', '2010006016', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2040-02', '2040', '#2#', NULL, NULL, NULL, 'select', '2010006017', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2040-03', '2040', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11300-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2040-04', '2040', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '11300-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2040-05', '2040', '#5#', NULL, NULL, NULL, 'text', 'number', 4, NULL, NULL, '11300-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2041-01', '2041', '#1#', NULL, NULL, NULL, 'select', '2010006016', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2041-02', '2041', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '11300-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2041-03', '2041', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '11300-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2050-01', '2050', '#1#', NULL, NULL, NULL, 'select', '2010006018', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2050-02', '2050', '#2#', NULL, NULL, NULL, 'select', '2010006019', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2050-03', '2050', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11400-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2051-01', '2051', '#1#', NULL, NULL, NULL, 'select', '2010006018', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2051-02', '2051', '#2#', NULL, NULL, NULL, 'select', '2010006019', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2051-03', '2051', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11400-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2052-01', '2052', '#1#', NULL, NULL, NULL, 'select', '2010006018', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2052-02', '2052', '#2#', NULL, NULL, NULL, 'select', '2010006019', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2052-03', '2052', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11400-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2053-01', '2053', '#1#', NULL, NULL, NULL, 'select', '2010006018', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2053-02', '2053', '#2#', NULL, NULL, NULL, 'select', '2010006019', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2053-03', '2053', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11400-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2060-01', '2060', '#1#', NULL, NULL, NULL, 'select', '2010006021', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2060-02', '2060', '#2#', NULL, NULL, NULL, 'select', '2010006022', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2070-01', '2070', '#1#', NULL, NULL, NULL, 'select', '2010006020', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2070-02', '2070', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '11500-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2080-01', '2080', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10500-005', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2080-02', '2080', '#2#', NULL, NULL, NULL, 'select', '2010008001', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2081-01', '2081', '#1#', NULL, NULL, NULL, 'select', '2010005009', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2082-01', '2082', '#1#', NULL, NULL, NULL, 'select', '2010008001', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2082-02', '2082', '#2#', NULL, NULL, NULL, 'select', '2010008002', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2083-01', '2083', '#1#', NULL, NULL, NULL, 'select', '2010005007', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2084-01', '2084', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10500-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2085-01', '2085', '#1#', NULL, NULL, NULL, 'text', 'number', 0, NULL, NULL, '10500-004', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2090-01', '2090', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2090-02', '2090', '#2#', NULL, NULL, NULL, 'select', '2010006008', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2090-03', '2090', '#3#', NULL, NULL, NULL, 'text', 'number', 2, NULL, NULL, '10800-007', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2090-04', '2090', '#4#', NULL, NULL, NULL, 'select', '2010006007', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2100-01', '2100', '#1#', NULL, NULL, NULL, 'select', '2010006005', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2100-02', '2100', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '10700-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2101-01', '2101', '#1#', NULL, NULL, NULL, 'select', '1250000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2101-02', '2101', '#2#', NULL, NULL, NULL, 'text', 'number', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2110-01', '2110', '#1#', NULL, NULL, NULL, 'select', '2010004008', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2110-02', '2110', '#2#', NULL, NULL, NULL, 'select', '1170000601', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2111-01', '2111', '#1#', NULL, NULL, NULL, 'select', '2010004010', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2111-02', '2111', '#2#', NULL, NULL, NULL, 'select', '1170000601', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2111-03', '2111', '#3#', NULL, NULL, NULL, 'select', '2010004009', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2120-01', '2120', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2120-02', '2120', '#2#', NULL, NULL, NULL, 'select', '1240000501', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2120-03', '2120', '#3#', NULL, NULL, NULL, 'select', '1240000601', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2122-01', '2122', '#1#', NULL, NULL, NULL, 'select', '2010006016', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2122-02', '2122', '#2#', NULL, NULL, NULL, 'select', '2010006017', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2122-03', '2122', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '11300-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2122-04', '2122', '#4#', NULL, NULL, NULL, 'text', 'number', 3, NULL, NULL, '12400-003', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2123-01', '2123', '#1#', NULL, NULL, NULL, 'select', '1240000501', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2123-02', '2123', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, '12400-002', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2124-01', '2124', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2124-02', '2124', '#2#', NULL, NULL, NULL, 'select', '1240000501', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2124-03', '2124', '#3#', NULL, NULL, NULL, 'select', '1240000601', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2125-01', '2125', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2125-02', '2125', '#2#', NULL, NULL, NULL, 'text', 'record', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2126-01', '2126', '#1#', NULL, NULL, NULL, 'select', '1240000401', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2126-02', '2126', '#2#', NULL, NULL, NULL, 'select', '1240000501', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2126-03', '2126', '#3#', NULL, NULL, NULL, 'select', '1240000401', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2126-04', '2126', '#4#', NULL, NULL, NULL, 'select', '1240000601', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2130-01', '2130', '#1#', NULL, NULL, NULL, 'text', 'sum', 0, NULL, NULL, '12500-002,12500-003,12500-006,12500-007,12500-008,12500-009', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('2130-02', '2130', '#2#', NULL, NULL, NULL, 'radio', '1250001001', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3000-01', '3000', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3000-02', '3000', '#2#', NULL, NULL, NULL, 'text', 'number', 1, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3000-03', '3000', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3000-04', '3000', '#4#', NULL, NULL, NULL, 'select', '1060001201', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3010-01', '3010', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3010-02', '3010', '#2#', NULL, NULL, NULL, 'text', 'number', 1, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3010-03', '3010', '#3#', NULL, NULL, NULL, 'select', '1060001201', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3010-04', '3010', '#4#', NULL, NULL, NULL, 'text', 'record', 3, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3020-01', '3020', '#1#', NULL, NULL, NULL, 'select', '2010006003', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3020-02', '3020', '#2#', NULL, NULL, NULL, 'text', 'number', 1, NULL, NULL, '10600-011', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3020-03', '3020', '#3#', NULL, NULL, NULL, 'text', 'record', 2, NULL, NULL, '10600-008', NULL, NULL, NULL, NULL);
INSERT INTO `qa_base_clause_item` VALUES ('3020-04', '3020', '#4#', NULL, NULL, NULL, 'select', '1060001201', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_base_project
-- ----------------------------
DROP TABLE IF EXISTS `qa_base_project`;
CREATE TABLE `qa_base_project`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orders` int(11) NULL DEFAULT NULL,
  `titile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `has_list` bit(1) NULL DEFAULT NULL,
  `category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '领域',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_base_project
-- ----------------------------
INSERT INTO `qa_base_project` VALUES ('1000', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '课堂教学', 1000, '课堂教学', '时间，学员层次，班队，课程名称，课程性质，教学分工，课时量。', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1010', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学评价', 1010, '教学评价', '时间，班队，课程名称，评价方式，随机抽查结果', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1020', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学奖励', 1020, '教学奖励', '时间，奖励类别，奖励等级，成果名称，排名，证书编号', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1030', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '担任军队级以上金课主讲教师', 1030, '担任军队级以上金课主讲教师', '时间，课程名称，金课级别，授课排名', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1040', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '著作情况', 1040, '著作情况', '出版年月，著作名称，著作类型，级别，身份，字数', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1050', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学指导1', 1050, '教学指导1', '时间，对象，层次，工作，获奖情况，是否被评为A类竞赛优秀指导教员', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1060', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学指导2', 1060, '教学指导2', '对象，层次，指导形式，各级学位论文抽查是否合格', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1070', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学指导3', 1070, '教学指导3', '资助时间，博士后姓名，资助项目', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1080', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '部队一线奖励', 1080, '部队一线奖励', '奖励类型，奖励时间，任务类型，担任职务 ', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1090', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '科研项目', 1090, '科研项目', '立项时间，项目/专利名称，项目类别，项目来源/专利授权种类，编号，下达/收益经费（万），排序，是否第一发明人', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1100', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '发明专利', 1100, '发明专利', '', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1110', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '科技奖励', 1110, '科技奖励', '时间，奖励类别，奖励等级，项目名称，授予单位，排名', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1120', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '以一作或通讯作者发表论文', 1120, '以一作或通讯作者发表沦为', '发表时间，论文名称，发表刊物名称，CN /ISSN/ISBN号，排名，收录类型，期刊影响因子，进入数据库', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1130', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '咨询\n报告\n', 1130, '咨询\n报告\n', '时间，报告名称，报送单位，排名，军级以上单位领导肯定性批示内容', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('1140', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学\n保障\n业务\n工作\n', 1140, '教学\n保障\n业务\n工作\n', '时间，工作内容，任务级别,军委机关以上领导采纳、获得批示批转情况,撰写报告、总结、讲话名称', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2000', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '科研项目', 2000, '科研项目', '时间，项目名称，项目类别，项目来源，编号，下达经费（万），排序', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2010', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '以一作或通讯作者发表论文', 2010, '以一作或通讯作者发表论文', '发表时间，论文名称，发表刊物名称，CN /ISSN/ISBN号，排名，收录类型，期刊影响因子，进入数据库', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2020', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '著作情况', 2020, '著作情况', '出版年月，著作名称，著作类型，级别，参与身份，字数', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2030', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '标准规范', 2030, '标准规范', '时间，标准名称，标准类别，是否正式颁布', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2040', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '咨询报告', 2040, '咨询报告', '时间，报告名称，报送单位，排名，军级以上单位领导肯定性批示内容，刊用期刊', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2050', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '参加学术会议', 2050, '参加学术会议', '时间，会议名称，会议类别，主会场/分会场，邀请报告主题', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2060', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '担任期刊主编', 2060, '担任期刊主编', '时间，期刊名称，期刊收录类型，职务', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2070', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '参加学术团体', 2070, '参加学术团体', '时间，团体名称，担任职务，是否理事以上，是否1届以上', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2080', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学', 2080, '教学指导', '对象，层次，指导位论文抽查形式，各级学是否合格;资助时间，博士后姓名，资助项目', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2090', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学科技奖励或质量奖励', 2090, '教学科技奖励或质量奖励', '时间，对象，层次，工作，获奖情况，是否被评为A类竞赛优秀指导教员;类别，项目名称，授予单位，排名', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2100', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '成果或专利', 2100, '成果或专利', '时间，成果/专利名称，专利授权/列装授权，编号，成果转化收益经费（万），排序，是否团队核心骨干，技术研发成果列装使用情况', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2110', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '部队一线奖励', 2110, '部队一线奖励', '奖励类型，奖励时间，任务类型，担任职务 ', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2120', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学保障工作', 2120, '教学保障工作', '时间，工作内容，任务级别;文件名称，排名，是否颁布实施;军委机关以上领导采纳、获得批示批转情况;工程型号项目;工作内容，撰写报告、总结、讲话名称', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2130', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '科研与质量管理标志性成果', 2130, '科研与质量管理标志性成果', '时间，活动名称，级别, 标志性成果形式', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('2140', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '同行评价', 2140, '同行评价', '各单位组织代表作同行评价，送审专家应为教育部学科评估A以上的学科（术）带头人，且送审专家不少于2人', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3000', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '工程项目', 3000, '工程项目', '时间，项目名称，项目类别，项目来源，编号，下达经费（万），排序', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3010', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '创新成果转化运用', 3010, '创新成果转化运用', '时间，任务类别，任务名称，决策建议，解决重大技术难题，对提升战斗力发挥重要作用，武器装备维护、勤务保障工作负责人，军级以上单位表彰，技术报告名称，排名', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3020', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '教学科技奖励', 3020, '教学科技奖励', '时间，类别，项目名称，授予单位，排名', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3030', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '成果或专利', 3030, '成果或专利', '时间，成果/专利名称，专利授权/列装授权，编号，成果转化收益经费（万），排序，是否团队核心骨干，技术研发成果列装使用情况', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3040', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '任务保障', 3040, '任务保障', '时间，任务类型，工作内容，排名，采纳应用单位，是否师以上单位，承担任务名称，任务来源', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3050', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '以一作或通讯作者发表论文', 3050, '以一作或通讯作者发表论文', '发表时间，论文名称，发表刊物名称，CN /ISSN/ISBN号，排名，收录类型，期刊影响因子，进入数据库', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3060', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '著作情况', 3060, '著作情况', '出版年月，著作名称，著作类型，级别，参与身份，字数', b'1', NULL);
INSERT INTO `qa_base_project` VALUES ('3070', '2020-03-04 13:43:50', '2020-03-04 13:43:50', '保障工作', 3070, '保障工作', '时间，工作内容，任务级别，军委机关以上领导采纳、获得批示批转情况，撰写报告、总结、讲话名称，排名', b'1', NULL);

-- ----------------------------
-- Table structure for qa_category
-- ----------------------------
DROP TABLE IF EXISTS `qa_category`;
CREATE TABLE `qa_category`  (
  `id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业领域或岗位名称',
  `another_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别称',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型  \r\n0：职业领域  \r\n1：岗位类型  \r\n2：职称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级',
  `apply_num` int(11) NULL DEFAULT NULL COMMENT '岗位职称申请累计人数（有新增申请时变更申请总数）',
  `first_approve_num` int(11) NULL DEFAULT NULL COMMENT '初审通过人数',
  `approve_num` int(11) NULL DEFAULT NULL COMMENT '审核通过人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_category
-- ----------------------------
INSERT INTO `qa_category` VALUES ('1000', '教育教学类', NULL, 0, NULL, NULL, NULL, 1000, NULL, 45, 2, 0);
INSERT INTO `qa_category` VALUES ('1010', '教学为主型', NULL, 1, '*******************************************************等一线教学岗位、专门从事教学等方面工作的专业技术人员。', NULL, NULL, 1010, '1000', 15, 2, 0);
INSERT INTO `qa_category` VALUES ('101001', '教授', '正高', 2, '（一）评定教授职称应具备下列业绩条件', NULL, NULL, 101001, '1000,1010', 5, 1, 0);
INSERT INTO `qa_category` VALUES ('101002', '副教授', '副高', 2, '（二）评定副教授职称应具备下列业绩条件', NULL, NULL, 101002, '1000,1010', 5, 1, 0);
INSERT INTO `qa_category` VALUES ('101003', '讲师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 101003, '1000,1010', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('101004', '助教', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 101004, '1000,1010', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('1020', '教学科研型', NULL, 1, '主要分布 *** 等一线教学科研岗位、同时从事教学和科研两方面工作的专业技术人员。', NULL, NULL, 1020, '1000', 15, 0, 0);
INSERT INTO `qa_category` VALUES ('102001', '教授', '正高', 2, '（一）评定教授职称应具备下列业绩条件', NULL, NULL, 102001, '1000,1020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('102002', '副教授', '副高', 2, '（二）评定副教授职称应具备下列业绩条件', NULL, NULL, 102002, '1000,1020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('102003', '讲师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 102003, '1000,1020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('102004', '助教', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 102004, '1000,1020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('1030', '教学保障型', NULL, 1, '主要分布在 **** 职业教育中心和 *** 考评中心, *** 命题中心、*** 中心和 *** 教研保障室, **** 展中心相关教学管理和保障岗位、从事教学技术保障、教学评估管理和教育规划与教学研究等方面工作的专业技术人员。', NULL, NULL, 1030, '1000', 15, 0, 0);
INSERT INTO `qa_category` VALUES ('103001', '教授', '正高', 2, '（一）评定教授职称应具备下列业绩条件', NULL, NULL, 103001, '1000,1030', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('103002', '副教授', '副高', 2, '（二）评定副教授职称应具备下列业绩条件', NULL, NULL, 103002, '1000,1030', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('103003', '讲师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 103003, '1000,1030', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('103004', '助教', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 103004, '1000,1030', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('2000', '科学研究类', NULL, 0, NULL, NULL, NULL, 2000, NULL, 0, 0, 0);
INSERT INTO `qa_category` VALUES ('2010', '基础研究型', NULL, 1, NULL, NULL, NULL, 2010, '2000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('201001', '研究员', '正高', 2, NULL, NULL, NULL, 201001, '2000,2010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('201002', '副研究员', '副高', 2, NULL, NULL, NULL, 201002, '2000,2010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('201003', '助理研究员', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 201003, '2000,2010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('201004', '研究实习员', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 201004, '2000,2010', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('2020', '技术创新应用研究型', NULL, 1, NULL, NULL, NULL, 2020, '2000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('202001', '研究员', '正高', 2, NULL, NULL, NULL, 202001, '2000,2020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('202002', '副研究员', '副高', 2, NULL, NULL, NULL, 202002, '2000,2020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('202003', '助理研究员', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 202003, '2000,2020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('202004', '研究实习员', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 202004, '2000,2020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('2030', '以国家安全、军事理论和军事教育研究为主型', NULL, 1, NULL, NULL, NULL, 2030, '2000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('203001', '研究员', '正高', 2, NULL, NULL, NULL, 203001, '2000,2030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('203002', '副研究员', '副高', 2, NULL, NULL, NULL, 203002, '2000,2030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('203003', '助理研究员', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 203003, '2000,2030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('203004', '研究实习员', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 203004, '2000,2030', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('2040', '科研保障型', NULL, 1, NULL, NULL, NULL, 2040, '2000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('204001', '研究员', '正高', 2, NULL, NULL, NULL, 204001, '2000,2040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('204002', '副研究员', '副高', 2, NULL, NULL, NULL, 204002, '2000,2040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('204003', '助理研究员', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 204003, '2000,2040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('204004', '研究实习员', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 204004, '2000,2040', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('3000', '工程技术类', NULL, 0, NULL, NULL, NULL, 3000, NULL, 0, 0, 0);
INSERT INTO `qa_category` VALUES ('3010', '工程技术研发应用型', NULL, 1, NULL, NULL, NULL, 3010, '3000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('301001', '正高级工程师', '正高', 2, NULL, NULL, NULL, 301001, '3000,3010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('301002', '高级工程师', '副高', 2, NULL, NULL, NULL, 301002, '3000,3010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('301003', '工程师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 301003, '3000,3010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('301004', '助理工程师', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 301004, '3000,3010', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('3020', '装备维护和勤务保障型', NULL, 1, NULL, NULL, NULL, 3020, '3000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('302001', '正高级工程师', '正高', 2, NULL, NULL, NULL, 302001, '3000,3020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('302002', '高级工程师', '副高', 2, NULL, NULL, NULL, 302002, '3000,3020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('302003', '工程师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 302003, '3000,3020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('302004', '助理工程师', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 302004, '3000,3020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('3030', '教学科研管理型', NULL, 1, NULL, NULL, NULL, 3030, '3000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('303001', '正高级工程师', '正高', 2, NULL, NULL, NULL, 303001, '3000,3030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('303002', '高级工程师', '副高', 2, NULL, NULL, NULL, 303002, '3000,3030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('303003', '工程师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 303003, '3000,3030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('303004', '助理工程师', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 303004, '3000,3030', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('3040', '基建营房管理型', NULL, 1, NULL, NULL, NULL, 3040, '3000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('304001', '正高级工程师', '正高', 2, NULL, NULL, NULL, 304001, '3000,3040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('304002', '高级工程师', '副高', 2, NULL, NULL, NULL, 304002, '3000,3040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('304003', '工程师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 304003, '3000,3040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('304004', '助理工程师', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 304004, '3000,3040', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('4000', '实验类', NULL, 0, NULL, NULL, NULL, 4000, NULL, 0, 0, 0);
INSERT INTO `qa_category` VALUES ('4010', '专业技术员', NULL, 1, NULL, NULL, NULL, 4010, '4000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('401001', '正高级实验师', '正高', 2, NULL, NULL, NULL, 401001, '4000,4010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('401002', '高级实验师', '副高', 2, NULL, NULL, NULL, 401002, '4000,4010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('401003', '实验师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 401003, '4000,4010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('401004', '助理实验师', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 401004, '4000,4010', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('5000', '专门业务类', NULL, 0, NULL, NULL, NULL, 5000, NULL, 0, 0, 0);
INSERT INTO `qa_category` VALUES ('5010', '图书管理', NULL, 1, NULL, NULL, NULL, 5010, '5000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('501001', '', '正高', 2, NULL, NULL, NULL, 501001, '5000,5010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('501002', '', '副高', 2, NULL, NULL, NULL, 501002, '5000,5010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('501003', '管理员', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 501003, '5000,5010', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('501004', '助理管理员', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 501004, '5000,5010', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('5020', '档案', NULL, 1, NULL, NULL, NULL, 5020, '5000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('502001', '研究馆员', '正高', 2, NULL, NULL, NULL, 502001, '5000,5020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('502002', '副研究馆员', '副高', 2, NULL, NULL, NULL, 502002, '5000,5020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('502003', '馆员', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 502003, '5000,5020', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('502004', '助理馆员', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 502004, '5000,5020', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('5030', '新闻', NULL, 1, NULL, NULL, NULL, 5030, '5000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('503001', '编审', '正高', 2, NULL, NULL, NULL, 503001, '5000,5030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('503002', '副编审', '副高', 2, NULL, NULL, NULL, 503002, '5000,5030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('503003', '', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 503003, '5000,5030', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('5040', '出版', NULL, 1, NULL, NULL, NULL, 5040, '5000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('504001', '', '正高', 2, NULL, NULL, NULL, 504001, '5000,5040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('504002', '', '副高', 2, NULL, NULL, NULL, 504002, '5000,5040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('504003', '编辑', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 504003, '5000,5040', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('504004', '助理编辑', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 504004, '5000,5040', 5, 0, 0);
INSERT INTO `qa_category` VALUES ('5050', '会计', NULL, 1, NULL, NULL, NULL, 5050, '5000', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('505001', '正高级会计师', '正高', 2, NULL, NULL, NULL, 505001, '5000,5050', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('505002', '高级会计师', '副高', 2, NULL, NULL, NULL, 505002, '5000,5050', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('505003', '会计师', '中职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 505003, '5000,5050', 0, 0, 0);
INSERT INTO `qa_category` VALUES ('505004', '助理会计师', '初职', 2, '（三）评定讲师职称业绩条件由各单位研究制定。', NULL, NULL, 505004, '5000,5050', 5, 0, 0);

-- ----------------------------
-- Table structure for qa_clause
-- ----------------------------
DROP TABLE IF EXISTS `qa_clause`;
CREATE TABLE `qa_clause`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参评职位id（qa_category表主键）',
  `clause_num` int(11) NULL DEFAULT NULL COMMENT '评审条件序号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评审条件内容',
  `type` int(11) NULL DEFAULT NULL COMMENT '条件要求：\r\n0：不满足（否）  1：满足（是） 2：具备其一（是）',
  `annex` bigint(1) NULL DEFAULT NULL COMMENT '是否需要上传附件 0：否 1：是',
  `repeat_index` int(11) NULL DEFAULT NULL COMMENT '重复条件内容的repeat_index 相同',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参评岗位业绩条件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_clause
-- ----------------------------
INSERT INTO `qa_clause` VALUES ('10100111', '101001', 1, '系统讲授过2门以上课程（含MOOC课程），其中，所属单位承担本科生教育任务的，每年应为本科学员讲授1门以上课程。', 1, 0, 1200);
INSERT INTO `qa_clause` VALUES ('10100121', '101001', 2, '年均教学净课时量应达到64学时以上。', 1, 0, 2500);
INSERT INTO `qa_clause` VALUES ('10100131', '101001', 3, '参加一次以上课堂教学质量校级评价并获“优秀”结果，且在学校随机抽查结果均为“良好”以上。', 1, 0, 3300);
INSERT INTO `qa_clause` VALUES ('10100141', '101001', 4, '被评为校级以上优秀教师奖、优秀导师奖、优秀教学奖；或参加校级以上各类教学比赛中获得一等奖以上奖励；或获得校级以上教学成果奖。', 1, 0, 1100);
INSERT INTO `qa_clause` VALUES ('10100151', '101001', 5, '作为指导教师，指导的青年教员获得校级以上教学比赛一等奖以上奖励；', 2, 0, 5100);
INSERT INTO `qa_clause` VALUES ('10100152', '101001', 5, '担任本科全程导师或指导本科优异生或本科学员毕业设计5人以上；或指导学员获得校级优秀毕业设计1人以；', 2, 0, 2000);
INSERT INTO `qa_clause` VALUES ('10100153', '101001', 5, '指导学员参加创新实践活动，被评为学员A类竞赛优秀指导教员；', 2, 0, 1900);
INSERT INTO `qa_clause` VALUES ('10100154', '101001', 5, '独立指导硕士生或协助指导博士生（博士后）2名以上，且指导的研究生学位论文在各级抽查中没有不合格情况；', 2, 0, 1700);
INSERT INTO `qa_clause` VALUES ('10100155', '101001', 5, '在条件2的基础上，年均教学净课时量再增加120学时以上的；', 2, 0, 3100);
INSERT INTO `qa_clause` VALUES ('10100156', '101001', 5, '参加***或者***组织的作战任务、**演习、研试验、抢险救灾、****等****行动或****组织的代职支援工作中，个人受到嘉奖以上奖励的；', 2, 0, 3500);
INSERT INTO `qa_clause` VALUES ('10100157', '101001', 5, '参加***级以上单位组织的综合演练、****比武等活动，被表\n彰为先进个人的。', 2, 0, 3400);
INSERT INTO `qa_clause` VALUES ('10100161', '101001', 6, '担任 *** 级以上金课主讲教师（国家级排名前5, *** 级排名前3）; 或作为主编、副主编出版全国统编教材或者 *** 级教材1部（15万字以上）以上；或作为主编出版本专业领域专著（编著、译著）2部（每部10万字以上）以上。', 1, 0, 2400);
INSERT INTO `qa_clause` VALUES ('10100171', '101001', 7, ' 将科研成果、论文等辅助评价要素业绩条件，由各单位根据工作实际研究制定。其中，对承担基础教学任务的，对科研成果奖、发明专利、课题研究和论文专著不作要求。', 1, 0, 6000);
INSERT INTO `qa_clause` VALUES ('10100211', '101002', 1, '系统讲授过2门以上课程（含MOOC课程），其中，所属单位承担本科生教育任务的，应为本科学员讲授1门以上课程。', 1, 0, 1300);
INSERT INTO `qa_clause` VALUES ('10100221', '101002', 2, '年均教学净课时量应达到64学时以上。', 1, 0, 2500);
INSERT INTO `qa_clause` VALUES ('10100231', '101002', 3, '参加一次以上课堂教学质量校级评价并获“优秀”结果，且在学校随机抽查结果均为“良好”以上。', 1, 0, 3300);
INSERT INTO `qa_clause` VALUES ('10100241', '101002', 4, '被评为院级以上优秀教师奖、优秀导师奖、优秀教学奖；或参加校级以上各类教学比赛中获得三等奖以上奖励；或获得院级以上教学成果奖。', 1, 0, 1000);
INSERT INTO `qa_clause` VALUES ('10100251', '101002', 5, '作为指导教师，指导的青年教员获得校级以上教学比赛三等奖以上奖励；', 2, 0, 5000);
INSERT INTO `qa_clause` VALUES ('10100252', '101002', 5, '担任本科全程导师或指导本科优异生或本科学员毕业设计3人以上；或指导学员获得校级优秀毕业设计1人以上；', 2, 0, 2100);
INSERT INTO `qa_clause` VALUES ('10100253', '101002', 5, '指导学员参加创新实践活动，被评为学员A类竞赛优秀指导教员；', 2, 0, 1900);
INSERT INTO `qa_clause` VALUES ('10100254', '101002', 5, '协助指导硕士生或协助指导博士生2名以上，且指导的研究生学位论文在各级抽查中没有不合格情况；', 2, 0, 4200);
INSERT INTO `qa_clause` VALUES ('10100255', '101002', 5, '在条件2的基础上，年均教学净课时量再增加100学时以上的；', 2, 0, 3200);
INSERT INTO `qa_clause` VALUES ('10100256', '101002', 5, '参加 *** 或者 *** 种组织的作战任务、*** 演习、科研试验、抢险救灾、*** 等 *** 行动或 *** 组织的代职支援工作中，个人受到嘉奖以上奖励或通报表扬的；', 2, 0, 3900);
INSERT INTO `qa_clause` VALUES ('10100257', '101002', 5, '参加 *** 级以上单位组织的综合演练、***比武等活动，被表彰为先进个人的。', 2, 0, 3800);
INSERT INTO `qa_clause` VALUES ('10100261', '101002', 6, '担任***级以上金课主讲教师（国家级不限排名,军队级 排名前5）;或作为主编、副主编出版本专业领域专著（编著、 译著、教材）1部（10万字以上）以上。', 1, 0, 2300);
INSERT INTO `qa_clause` VALUES ('10100271', '101002', 7, '将科研成果、论文等辅助评价要素业绩条件，由各单位根据工作实际研究制定。其中，对承担基础教学任务的，对科研成果奖、发明专利、课题研究和论文专著不作要求。', 1, 0, 2700);
INSERT INTO `qa_clause` VALUES ('10200111', '102001', 1, '系统讲授过1门以上课程（含MOOC课程），其中，所属单位承担本科生教育任务的，每年应为本科学员讲授1门以上课程', 1, 0, 1400);
INSERT INTO `qa_clause` VALUES ('10200121', '102001', 2, '年均教学净课时量应达到32学时以上。', 1, 0, 2600);
INSERT INTO `qa_clause` VALUES ('10200131', '102001', 3, '参加一次以上课堂教学质量校级评价并获“优秀”结果，且在学校随机抽查结果均为“良好”以上。', 1, 0, 3300);
INSERT INTO `qa_clause` VALUES ('10200141', '102001', 4, '主持国家、***级重大项目、课题任务1项以上；或作为核心骨干参与国家***级重大项目、课题任务2项以上（排名前3）。', 1, 0, 5800);
INSERT INTO `qa_clause` VALUES ('10200151', '102001', 5, '作为主要贡献者，获得 *** 二等奖以上教学科技奖励（国家级特等奖排名前15、一等奖排名前10、二等奖排名前7, *** 级一等奖排名前5、二等奖排名前3）;或担任 *** 级以上金课主讲教师（国家级排名前7, *** 级排名前5 ）。', 1, 0, 5200);
INSERT INTO `qa_clause` VALUES ('10200161', '102001', 6, '作为第一作者发表进入 SCIE、El Compendex、SSCE、A&HCI 检索的论文2篇以上;或作为第一作者在学科重要刊物上发表论文3篇以上；或作为第一作者发表进入中国科技论文核心期刊、 CSSCI来源期刊、全国中文核心期刊、 ***学核心期刊和中国科学引文数据库（CSCD）核心期刊的论文6篇以上（可含重大项目的技术报告1篇）；或作为第一作者撰写决策咨询意见、研究论证报告3篇以上，并得到 *** 级以上单位领导的肯定性批示；或作为主编出版本专业领域专著（编著、译著、教材）1部（15万字以上）以上。', 1, 0, 4700);
INSERT INTO `qa_clause` VALUES ('10200171', '102001', 7, '作为指导教师，指导的青年教员获得校级以上教学比赛二等奖以上奖励；', 2, 0, 4900);
INSERT INTO `qa_clause` VALUES ('10200172', '102001', 7, '独立指导硕士生或协助指导博士生2名以上，且指导的研究生学位论文在各级抽查中没有不合格情况；', 2, 0, 1800);
INSERT INTO `qa_clause` VALUES ('10200173', '102001', 7, '担任本科全程导师或指导本科优异生或本科学员毕业设计3人以上；或指导学员获得校级优秀毕业设计1人以上；', 2, 0, 2100);
INSERT INTO `qa_clause` VALUES ('10200174', '102001', 7, '指导学员参加创新实践活动，被评为学员A类竞赛优秀指导教员；', 2, 0, 1900);
INSERT INTO `qa_clause` VALUES ('10200175', '102001', 7, '协助指导的博士后，进站后获得中国博士后科学基金资助或入选博士后国（境）外交流项目、博士后创新人才支持计划;', 2, 0, 4400);
INSERT INTO `qa_clause` VALUES ('10200176', '102001', 7, '在条件2的基础上，年均教学净课时量再增加120学时以上的；', 2, 0, 3100);
INSERT INTO `qa_clause` VALUES ('10200177', '102001', 7, '参加 ***、***区或者 *** 种组织的作战任务 *** 演习、科研试验、抢险救灾、反恐维稳等 *** 行动或 **** 组织的代职支援工作中，个人受到嘉奖以上奖励的；', 2, 0, 3700);
INSERT INTO `qa_clause` VALUES ('10200178', '102001', 7, '参加 *** 级以上单位组织的综合演练、***比武等活动，被表彰为先进个人的。', 2, 0, 3800);
INSERT INTO `qa_clause` VALUES ('10200181', '102001', 8, '将承担教学创新、课程教材等情况作为辅助评价要素，由各单位根据工作实际研究制定。', 1, 0, 2800);
INSERT INTO `qa_clause` VALUES ('10200211', '102002', 1, '系统讲授过1门以上课程（含MOOC课程），其中，所属单位承担本科生教育任务的，应协助 *** 以上金课主讲教师系统辅导1门本科课程。', 1, 0, 1500);
INSERT INTO `qa_clause` VALUES ('10200221', '102002', 2, '年均教学净课时量应达到32学时以上。', 1, 0, 2600);
INSERT INTO `qa_clause` VALUES ('10200231', '102002', 3, '参加一次以上课堂教学质量校级评价并获“优秀”结果，且在学校随机抽查结果均为“良好”以上。', 1, 0, 3300);
INSERT INTO `qa_clause` VALUES ('10200241', '102002', 4, '主持国家、*** 级重大项目、课题任务1项以上；或作为核心骨干参与国家、*** 级重大项目、课题任务2项以上（排名前7）;或作为核心骨干参与国家、***级重大项目1项以上（排名前7）且作为第一发明人，获得国家发明专利或国防专利1项以上，或者实用新型专利或外观设计专利1项以上。', 1, 0, 5900);
INSERT INTO `qa_clause` VALUES ('10200251', '102002', 5, '作为主要贡献者，获得 *** 三等奖以上教学科技奖励（国家级特等奖排名前30、一等奖排名前15、二等奖排名前10,军队级一等奖排名前7、二等奖排名前5、三等奖排名前3）;或担任以上金课主讲教师（国家级不限排名，*** 排名前5）; 或参加校级以上各类教学比赛中获得一等奖以上奖励。', 1, 0, 5400);
INSERT INTO `qa_clause` VALUES ('10200261', '102002', 6, '作为第一作者发表进入 SCIE、BI Compendex、SSCI、A&HCI 检索的论文1篇以上;或作为第一作者在学科重要刊物上发表论 文2篇以上；或作为第一作者发表进入中国科技论文核心期刊、CSSCI来源期刊、全国中文核心期刊、军事学核心期刊和中国科学引文数据库（CSCD）核心期刊的论文3篇以上（可含重大项目的技术报告1篇）；或作为第一作者撰写决策咨询意见、研究论证报告1篇以上，并得到***级以上单位领导的肯定性批示；或作为主编、副主编出版本专业领域专著（编著、译著、教材）1部（10万字以上）以上。', 1, 0, 4800);
INSERT INTO `qa_clause` VALUES ('10200271', '102002', 7, '作为指导教师，指导的青年教员获得校级以上教学比赛三等奖以上奖励；', 2, 0, 5000);
INSERT INTO `qa_clause` VALUES ('10200272', '102002', 7, '协助指导硕士生或协助指导博士生1名以上，且指导的研究生学位论文在各级抽查中没有不合格情况；', 2, 0, 4300);
INSERT INTO `qa_clause` VALUES ('10200273', '102002', 7, '担任本科全程导师或指导本科优异生或本科学员毕业设计2人以上；或指导学员获得校级优秀毕业设计1人以上；', 2, 0, 2200);
INSERT INTO `qa_clause` VALUES ('10200274', '102002', 7, '指导学员参加创新实践活动，被评为学员A类竞赛优秀指导教员；', 2, 0, 1900);
INSERT INTO `qa_clause` VALUES ('10200275', '102002', 7, '在条件2的基础上，年均教学净课时量再增加90学时以上的；', 2, 0, 3000);
INSERT INTO `qa_clause` VALUES ('10200276', '102002', 7, '参加 ***、***或者 ***种组织的作战任务、军事演习、科研试验、抢险救灾、反恐维稳等重大军事行动或全军组织的代职支援工作中，个人受到嘉奖以上奖励或通报表扬的；', 2, 0, 3600);
INSERT INTO `qa_clause` VALUES ('10200277', '102002', 7, '参加 *** 级以上单位组织的综合演练、***比武等活动，被表彰为先进个人的。', 2, 0, 3800);
INSERT INTO `qa_clause` VALUES ('10200281', '102002', 8, '将承担教学创新、课程教材等情况作为辅助评价要素，由各单位根据工作实际研究制定。', 1, 0, 2800);
INSERT INTO `qa_clause` VALUES ('10300111', '103001', 1, '系统讲授过1门以上课程（含MOOC课程），其中，所属单位承担本科生教育任务的，每年应为本科学员讲授1门以上课程。', 1, 0, 1400);
INSERT INTO `qa_clause` VALUES ('10300121', '103001', 2, '年均教学净课时量应达到32学时以上。', 1, 0, 2600);
INSERT INTO `qa_clause` VALUES ('10300131', '103001', 3, '参加一次以上课堂教学质量校级评价并获“优秀”结果，且在学校随机抽查结果均为“良好”以上。', 1, 0, 3300);
INSERT INTO `qa_clause` VALUES ('10300141', '103001', 4, '作为第一作者在有CN刊号（ISSN号、ISBN号）的期刊或者出版物上发表与本职工作紧密相关的论文或交流报告4篇以上，其中1篇是在教育类核心期刊或军事学核心期刊上发表。', 1, 0, 4500);
INSERT INTO `qa_clause` VALUES ('10300151', '103001', 5, '参与完成 *** 级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作；或提出的业务建议、保障方案、咨询报告、工作经验等被 *** 机关以上领导采纳、获得批示批转；或作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证及组织实施工作，主笔起草重要规章制度、报告、总结、讲话等材料8篇以上。', 1, 0, 4000);
INSERT INTO `qa_clause` VALUES ('10300161', '103001', 6, '作为主要贡献者，获得 *** 二等奖以上教学科技奖励（国家级特等奖排名前15、一等奖排名前10、二等奖排名前7, *** 级一等奖排名前5、二等奖排名前3）;或主持国家级项目1项以上或 *** 级研究项目2项以上。', 1, 0, 5300);
INSERT INTO `qa_clause` VALUES ('10300171', '103001', 7, '作为主编出版本专业领域专著（编著、译著、教材）1部（10万字以上）以上；或在条件4的基础上再发表论文4篇以上。', 1, 0, 5600);
INSERT INTO `qa_clause` VALUES ('10300181', '103001', 8, '将发明专利等情况作为辅助评价要素，由各单位根据工作实际研究制定。', 1, 0, 2900);
INSERT INTO `qa_clause` VALUES ('10300211', '103002', 1, '系统讲授过1门以上课程（含MOOC课程），其中，所属单位承担本科生教育任务的，应为本科学员讲授1门以上课程。', 1, 0, 1600);
INSERT INTO `qa_clause` VALUES ('10300221', '103002', 2, '年均教学净课时量应达到32学时以上。', 1, 0, 2600);
INSERT INTO `qa_clause` VALUES ('10300231', '103002', 3, '参加一次以上课堂教学质量校级评价并获“优秀”结果，且在学校随机抽查结果均为“良好”以上。', 1, 0, 3300);
INSERT INTO `qa_clause` VALUES ('10300241', '103002', 4, '作为第一作者在有CN刊号（ISSN号、ISBN号）的期刊或者出版物上发表与本职工作紧密相关的论文或交流报告2篇以上。', 1, 0, 4600);
INSERT INTO `qa_clause` VALUES ('10300251', '103002', 5, '参与完成 *** 级以上政策法规、制度办法、重大技术保障规范、标准、软件等的制定、论证工作；或提出的业务建议、保障方案、咨询报告、工作经验等被 *** 机关以上领导采纳、获得批示批转；或作为主要参加者，完成院级以上重大学科建设、教学改革、科研发展、人才队伍建设等方面规划计划、政策法规、制度办法的调研、论证及组织实施工作，主笔起草重要规章制度、 报告、总结、讲话等材料6篇以上。', 1, 0, 4100);
INSERT INTO `qa_clause` VALUES ('10300261', '103002', 6, '作为主要贡献者，获得 *** 三等奖以上教学科技奖励（国家级特等奖排名前30、一等奖排名前15、二等奖排名前10, ***队级一等奖排名前7、二等奖排名前5、三等奖排名前3）;或主持 *** 级研究项目1项以上或校级研究项目2项以上。', 1, 0, 5500);
INSERT INTO `qa_clause` VALUES ('10300271', '103002', 7, '作为主编、副主编出版本专业领域专著（编著、译著、教材）1部（8万字以上）以上；或在条件4的基础上再发表论文2篇以上。', 1, 0, 5700);
INSERT INTO `qa_clause` VALUES ('10300281', '103002', 8, '将发明专利等情况作为辅助评价要素，由各单位根据工作实际研究制定。', 1, 0, 2900);

-- ----------------------------
-- Table structure for qa_info_record
-- ----------------------------
DROP TABLE IF EXISTS `qa_info_record`;
CREATE TABLE `qa_info_record`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `final_category_child_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终审评职称id',
  `final_category_child_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最终审评职称名称(全称 格式为：领域--岗位--职称)',
  `final_category_tree_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称treepath路径',
  `first_approve_status` int(11) NULL DEFAULT NULL COMMENT '初次审核状态\r\n0：未通过\r\n1：通过 ',
  `first_approve_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '初次审核时间',
  `first_approve_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初次审核人',
  `first_approve_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初次审核人id',
  `first_approve_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初次审核意见',
  `first_category_child_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初审符合岗位（qa_category表主键）',
  `first_category_tree_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初审符合岗位树路径',
  `second_approve_status` int(11) NULL DEFAULT NULL COMMENT '复审审核状态\r\n0：未通过\r\n1：通过 \r\n2：审批中',
  `second_approve_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '复审时间',
  `second_approve_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复审人',
  `second_approve_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复审人id',
  `second_approve_opinion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复审意见',
  `second_category_child_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复审给定岗位id',
  `second_category_tree_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复审给定岗位路径',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态\r\n0： 默认保存\r\n10：待电脑审核\r\n11：电脑审核未通过\r\n12：电脑审核通过\r\n20：待人工审核\r\n21：人工审核未通过\r\n22：人工审核通过',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '申请记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa_info_record_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_info_record_item`;
CREATE TABLE `qa_info_record_item`  (
  `record_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '录入信息记录项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa_notice
-- ----------------------------
DROP TABLE IF EXISTS `qa_notice`;
CREATE TABLE `qa_notice`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `begin_date` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `has_list` bit(1) NULL DEFAULT b'0' COMMENT '是否列出 0：否  1：是',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `has_top` bit(1) NULL DEFAULT b'0' COMMENT '是否置顶 0 默认 1 置顶',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_notice
-- ----------------------------
INSERT INTO `qa_notice` VALUES ('1', '2020-02-20 17:35:45', '2020-02-20 17:35:48', '2020-02-20 18:35:52', '2020-07-11 20:35:54', '个人填报功能上线了', '个人填报功能上线了', b'1', 0, b'0');

-- ----------------------------
-- Table structure for qa_relation_clause_category
-- ----------------------------
DROP TABLE IF EXISTS `qa_relation_clause_category`;
CREATE TABLE `qa_relation_clause_category`  (
  `clause_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qa_clause表主键',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'qa_category表主键',
  `clause_item_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关系条件'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '条件项与职称关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_relation_clause_category
-- ----------------------------
INSERT INTO `qa_relation_clause_category` VALUES ('1000', '101001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1000', '101002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1000', '102001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1000', '102002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1000', '103001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1000', '103002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1001', '101001', '(A1||A2)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1001', '101002', '(A1||A2)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1001', '102001', '(A1||A2)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1001', '103001', '(A1||A2)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1001', '103002', '(A1||A2)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1002', '102002', 'A1&&B1&&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1003', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1003', '101002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1003', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1003', '102002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1003', '103001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1003', '103002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1010', '101001', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1010', '101002', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1010', '102001', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1010', '102002', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1010', '103001', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1010', '103002', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1011', '101001', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1011', '101002', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1011', '102001', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1011', '102002', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1011', '103001', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1011', '103002', '(A1& C1)||(A2&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1020', '101001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1020', '101002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1021', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1021', '101002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1021', '102002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1022', '101002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1022', '102002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1030', '101001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1030', '101002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1030', '102001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1030', '102002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1040', '101001', '(A1 || A2) & (B1 || B2) & C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1040', '102001', '(A1 || A2) & (B1 || B2) & C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1040', '102002', '(A1 || A2) & (B1 || B2) & C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1041', '101001', '(A1 ||A2) & B1 & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1041', '101002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1041', '102001', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1041', '102002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1041', '103001', '(A1 ||A2) & B1 & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1041', '103002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1050', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1050', '101002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1050', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1050', '102002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1051', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1051', '101002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1051', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1051', '102002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1052', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1052', '101002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1052', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1052', '102002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1053', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1053', '101002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1053', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1053', '102002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1060', '101001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1060', '101002', 'A1 & (B1 || B2)');
INSERT INTO `qa_relation_clause_category` VALUES ('1060', '102001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1060', '102002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1070', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1080', '101001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1080', '101002', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1080', '102001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('1080', '102002', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1080', '103001', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1080', '103002', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1081', '101001', 'A1&&B1&&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1081', '101002', 'A1&&B1&&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1081', '102001', 'A1&&B1&&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1081', '102002', 'A1&&B1&&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1081', '103001', 'A1&&B1&&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1090', '102002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1090', '103001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1090', '102001', '((A1||A2)&B1&C1&D1)||((A1||A2)&B1&C1&D2)');
INSERT INTO `qa_relation_clause_category` VALUES ('1091', '101001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1091', '101002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1091', '102001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1091', '102002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1100', '102002', '(A1||A2)&C1&(D1||D2||D3||D4)&E1&F1');
INSERT INTO `qa_relation_clause_category` VALUES ('1100', '103001', '(A1||A2)&C1&(D1||D2||D3||D4)&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('1100', '103002', '(A1||A2)&C1&(D1||D2||D3||D4)&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('1110', '101001', '(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))');
INSERT INTO `qa_relation_clause_category` VALUES ('1110', '101002', '(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))');
INSERT INTO `qa_relation_clause_category` VALUES ('1110', '102001', '(A1&((B1&C2)||(B2&C3)||(B3&C4)))||(A2&((B2&C5)||(B3&C6)))');
INSERT INTO `qa_relation_clause_category` VALUES ('1110', '102002', '(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))');
INSERT INTO `qa_relation_clause_category` VALUES ('1110', '103001', '(A1&((B1&C2)||(B2&C3)||(B3&C4)))||(A2&((B2&C5)||(B3&C6)))');
INSERT INTO `qa_relation_clause_category` VALUES ('1110', '103002', '(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))');
INSERT INTO `qa_relation_clause_category` VALUES ('1120', '101001', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1120', '101002', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1120', '102001', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1120', '102002', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1121', '101001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1121', '101002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1121', '102001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1121', '102002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1122', '101001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1122', '101002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1122', '102001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1122', '102002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1123', '103001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1123', '103002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1124', '103001', 'A1||A2');
INSERT INTO `qa_relation_clause_category` VALUES ('1130', '102001', '((A1||A2)&B1&D1)&((A1||A2)&B1&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1130', '102002', '((A1||A2)&B1&D1)&((A1||A2)&B1&C1)');
INSERT INTO `qa_relation_clause_category` VALUES ('1140', '103001', '(A1||A2||A3||A4||A5)&(B1||B2)&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1140', '103002', '(A1||A2||A3||A4||A5)&(B1||B2)&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('1141', '103001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1141', '103002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('1142', '103001', '(A1||A2||A3||A4)&B1&(C1||C2||C3)');
INSERT INTO `qa_relation_clause_category` VALUES ('1142', '103002', '(A1||A2||A3||A4)&B1&(C1||C2||C3)');
INSERT INTO `qa_relation_clause_category` VALUES ('1143', '103001', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('1143', '103002', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '201001', '(A1||A2)&C1&D1&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '202001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '202002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '203001', '((A1&C1&D1&E1)||(A2&C1&D2&E1))');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '203002', '((A1&C1&D2&E1)||(A2&C1&D1&E1))');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '204001', '(A1||A2)&C1&D1&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('2000', '204002', '(A1||A2)&C1&D1&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('2001', '201001', '(A1||A2)&C1&D1&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('2001', '201002', '(A1||A2)&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2001', '202001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2001', '202002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2001', '204001', '(A1||A2)&C1&D1&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('2001', '204002', '(A1||A2)&C1&D1&E1');
INSERT INTO `qa_relation_clause_category` VALUES ('2010', '201001', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2010', '201002', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2010', '203001', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2010', '203002', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2011', '201001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2011', '201002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2012', '202001', '((A1||A2||A3||A4)&B1)||((C1||C2||C3||C4||C5)&B1)');
INSERT INTO `qa_relation_clause_category` VALUES ('2012', '202002', '((A1||A2||A3||A4)&B1)||((C1||C2||C3||C4||C5)&B1)');
INSERT INTO `qa_relation_clause_category` VALUES ('2013', '201001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2013', '201002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2013', '202001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2013', '202002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2013', '203001', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2013', '203002', '(A1||A2||A3||A4||A5)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2014', '204001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2014', '204002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '201001', '(A1 ||A2) & B1 & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '201002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '202001', '(A1 ||A2) & B1 & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '202002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '203001', '(A1 ||A2) & B1 & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '203002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '204001', '(A1 ||A2) & B1 & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2020', '204002', '(A1 ||A2) & (B1 || B2)  & C1 & D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2030', '204001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2030', '204002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2040', '202001', '((A1||A2)&B1&D1&E1)&((A1||A2)&B1&C1&E1)');
INSERT INTO `qa_relation_clause_category` VALUES ('2040', '202002', '((A1||A2)&B1&D1&E1)&((A1||A2)&B1&C1&E1)');
INSERT INTO `qa_relation_clause_category` VALUES ('2040', '204001', '((A1||A2)&B1&D1&E1)&((A1||A2)&B1&C1&E1)');
INSERT INTO `qa_relation_clause_category` VALUES ('2040', '204002', '((A1||A2)&B1&D1&E1)&((A1||A2)&B1&C1&E1)');
INSERT INTO `qa_relation_clause_category` VALUES ('2041', '203001', '(A1||A2||A3)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2041', '203002', '(A1||A2||A3)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2050', '201001', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2050', '201002', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2051', '201001', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2051', '201002', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2052', '201001', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2052', '201002', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2053', '201001', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2053', '201002', 'A1&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2060', '201001', '(A1||A2||A3||A4||A5)&(B1||B2)');
INSERT INTO `qa_relation_clause_category` VALUES ('2060', '201002', '(A1||A2||A3||A4||A5)&(B1||B2||B3)');
INSERT INTO `qa_relation_clause_category` VALUES ('2070', '201001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2070', '201002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2080', '201001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2080', '201002', 'A1 & (B1 || B2)');
INSERT INTO `qa_relation_clause_category` VALUES ('2080', '202001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2080', '202002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2080', '203001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2080', '203002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2081', '201001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2081', '201002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2081', '202001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2081', '202002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2081', '203001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2081', '203002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2082', '201001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2083', '201001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2083', '201002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2084', '202001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2084', '202002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2084', '203001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2084', '203002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2085', '202001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2085', '202002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2085', '203001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2085', '203002', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '201001', '(A1&(B1&C1)||(B2&C2)||(B3&C3))||(A2&(B2&C4)||(B3&C5)||(B4&C6))');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '201002', '(A1&(B1&C1)||(B2&C2)||(B3&C3))||(A2&(B2&C4)||(B3&C5)||(B4&C6))||(A3&B2&C7)');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '202001', '(A1&(B1&C2)||(B2&C3)||(B3&C4))||(A2&(B2&C5)||(B3&C6))');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '202002', '(A1&(B1&C1)||(B2&C2)||(B3&C3))||(A2&(B2&C4)||(B3&C5)||(B4&C6))');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '203001', '(A1&(B1&C1)||(B2&C2))||(A2&(B2&C5)||(B3&C6))||(A3&B2&C7)');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '203002', '(A2&D1)||(A3&(B2&C6)||(B3&C7))');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '204001', '(A1&(B1&C1)||(B2&C2)||(B3&C3))||(A2&(B2&C4)||(B3&C5)||(B4&C6))||(A3&B2&C7)');
INSERT INTO `qa_relation_clause_category` VALUES ('2090', '204002', '(A2&D1)||(A3&(B2&C6)||(B3&C7))');
INSERT INTO `qa_relation_clause_category` VALUES ('2100', '201001', '(A1||A2||A2||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2100', '201002', '(A1||A2||A2||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2100', '202001', '(A1||A2||A2||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2100', '202002', '(A1||A2||A2||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2100', '203001', '(A1||A2||A2||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2100', '203002', '(A1||A2||A2||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2101', '202001', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2101', '202002', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2110', '202001', 'A1');
INSERT INTO `qa_relation_clause_category` VALUES ('2110', '202002', 'A1||B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2111', '202001', '(A1||A2)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2111', '202002', '(A1||A2)&B1&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2120', '203001', '(A1||A2||A3||A4||A5)&(B1||B2)&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2120', '203002', '(A1||A2||A3||A4||A5)&(B1||B2)&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2122', '203001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2122', '203002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2123', '204001', '(A1||A2||A3)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2123', '204002', '(A1||A2||A3)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2124', '203001', '(A1||A2||A3||A4)&(B1||B2||B3)&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2124', '203002', '(A1||A2||A3||A4)&(B1||B2||B3)&C1');
INSERT INTO `qa_relation_clause_category` VALUES ('2125', '203001', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2125', '203002', '(A1||A2||A3||A4)&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2126', '204001', '((A1||A2|A3||A4||A5)&(B1||B2||B3))&(C1||C2||C3||C4||C5)&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2126', '204002', '((A1||A2|A3||A4||A5)&(B1||B2||B3))&(C1||C2||C3||C4||C5)&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('2130', '204001', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('2130', '204002', 'A1&B1');
INSERT INTO `qa_relation_clause_category` VALUES ('3000', '301001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('3000', '301002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('3000', '302001', '(A1||A2)&B1&C1&(D1||D2||D3)');
INSERT INTO `qa_relation_clause_category` VALUES ('3010', '303001', 'A1&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('3010', '303002', 'A1&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('3020', '301001', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('3020', '301002', '(A1||A2)&B1&C1&D1');
INSERT INTO `qa_relation_clause_category` VALUES ('3020', '302001', '(A1||A2)&B1&C1&(D1||D2||D3)');
INSERT INTO `qa_relation_clause_category` VALUES ('3020', '302002', '(A1||A2)&B1&C1&(D1||D2||D3)');

-- ----------------------------
-- Table structure for qa_relation_clause_group
-- ----------------------------
DROP TABLE IF EXISTS `qa_relation_clause_group`;
CREATE TABLE `qa_relation_clause_group`  (
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组id',
  `clause_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件项id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '条件分组与条件项关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_relation_clause_group
-- ----------------------------
INSERT INTO `qa_relation_clause_group` VALUES ('1000', '1000');
INSERT INTO `qa_relation_clause_group` VALUES ('1001', '1001');
INSERT INTO `qa_relation_clause_group` VALUES ('1002', '1002');
INSERT INTO `qa_relation_clause_group` VALUES ('1003', '1003');
INSERT INTO `qa_relation_clause_group` VALUES ('1010', '1010');
INSERT INTO `qa_relation_clause_group` VALUES ('1011', '1011');
INSERT INTO `qa_relation_clause_group` VALUES ('1020', '1020');
INSERT INTO `qa_relation_clause_group` VALUES ('1020', '1020');
INSERT INTO `qa_relation_clause_group` VALUES ('1022', '1022');
INSERT INTO `qa_relation_clause_group` VALUES ('1030', '1030');
INSERT INTO `qa_relation_clause_group` VALUES ('1040', '1040');
INSERT INTO `qa_relation_clause_group` VALUES ('1041', '1041');
INSERT INTO `qa_relation_clause_group` VALUES ('1050', '1050');
INSERT INTO `qa_relation_clause_group` VALUES ('1051', '1051');
INSERT INTO `qa_relation_clause_group` VALUES ('1052', '1052');
INSERT INTO `qa_relation_clause_group` VALUES ('1053', '1053');
INSERT INTO `qa_relation_clause_group` VALUES ('1060', '1060');
INSERT INTO `qa_relation_clause_group` VALUES ('1061', '1061');
INSERT INTO `qa_relation_clause_group` VALUES ('1070', '1070');
INSERT INTO `qa_relation_clause_group` VALUES ('1080', '1080');
INSERT INTO `qa_relation_clause_group` VALUES ('1081', '1081');
INSERT INTO `qa_relation_clause_group` VALUES ('1090', '1090');
INSERT INTO `qa_relation_clause_group` VALUES ('1091', '1091');
INSERT INTO `qa_relation_clause_group` VALUES ('1100', '1100');
INSERT INTO `qa_relation_clause_group` VALUES ('1110', '1110');
INSERT INTO `qa_relation_clause_group` VALUES ('1120', '1120');
INSERT INTO `qa_relation_clause_group` VALUES ('1121', '1121');
INSERT INTO `qa_relation_clause_group` VALUES ('1122', '1122');
INSERT INTO `qa_relation_clause_group` VALUES ('1123', '1123');
INSERT INTO `qa_relation_clause_group` VALUES ('1124', '1124');
INSERT INTO `qa_relation_clause_group` VALUES ('1130', '1130');
INSERT INTO `qa_relation_clause_group` VALUES ('1140', '1140');
INSERT INTO `qa_relation_clause_group` VALUES ('1141', '1141');
INSERT INTO `qa_relation_clause_group` VALUES ('1142', '1142');
INSERT INTO `qa_relation_clause_group` VALUES ('1143', '1143');
INSERT INTO `qa_relation_clause_group` VALUES ('2000', '2000');
INSERT INTO `qa_relation_clause_group` VALUES ('2001', '2001');
INSERT INTO `qa_relation_clause_group` VALUES ('2010', '2010');
INSERT INTO `qa_relation_clause_group` VALUES ('2011', '2011');
INSERT INTO `qa_relation_clause_group` VALUES ('2012', '2012');
INSERT INTO `qa_relation_clause_group` VALUES ('2013', '2013');
INSERT INTO `qa_relation_clause_group` VALUES ('2014', '2014');
INSERT INTO `qa_relation_clause_group` VALUES ('2020', '2020');
INSERT INTO `qa_relation_clause_group` VALUES ('2030', '2030');
INSERT INTO `qa_relation_clause_group` VALUES ('2040', '2040');
INSERT INTO `qa_relation_clause_group` VALUES ('2041', '2041');
INSERT INTO `qa_relation_clause_group` VALUES ('2050', '2050');
INSERT INTO `qa_relation_clause_group` VALUES ('2051', '2051');
INSERT INTO `qa_relation_clause_group` VALUES ('2052', '2052');
INSERT INTO `qa_relation_clause_group` VALUES ('2053', '2053');
INSERT INTO `qa_relation_clause_group` VALUES ('2060', '2060');
INSERT INTO `qa_relation_clause_group` VALUES ('2070', '2070');
INSERT INTO `qa_relation_clause_group` VALUES ('2080', '2080');
INSERT INTO `qa_relation_clause_group` VALUES ('2081', '2081');
INSERT INTO `qa_relation_clause_group` VALUES ('2082', '2082');
INSERT INTO `qa_relation_clause_group` VALUES ('2083', '2083');
INSERT INTO `qa_relation_clause_group` VALUES ('2084', '2084');
INSERT INTO `qa_relation_clause_group` VALUES ('2085', '2085');
INSERT INTO `qa_relation_clause_group` VALUES ('2090', '2090');
INSERT INTO `qa_relation_clause_group` VALUES ('2091', '2091');
INSERT INTO `qa_relation_clause_group` VALUES ('2100', '2100');
INSERT INTO `qa_relation_clause_group` VALUES ('2101', '2101');
INSERT INTO `qa_relation_clause_group` VALUES ('2110', '2110');
INSERT INTO `qa_relation_clause_group` VALUES ('2111', '2111');
INSERT INTO `qa_relation_clause_group` VALUES ('2120', '2120');
INSERT INTO `qa_relation_clause_group` VALUES ('2121', '2121');
INSERT INTO `qa_relation_clause_group` VALUES ('2122', '2122');
INSERT INTO `qa_relation_clause_group` VALUES ('2123', '2123');
INSERT INTO `qa_relation_clause_group` VALUES ('2124', '2124');
INSERT INTO `qa_relation_clause_group` VALUES ('2125', '2125');
INSERT INTO `qa_relation_clause_group` VALUES ('2126', '2126');
INSERT INTO `qa_relation_clause_group` VALUES ('2130', '2130');
INSERT INTO `qa_relation_clause_group` VALUES ('2140', '2140');
INSERT INTO `qa_relation_clause_group` VALUES ('3000', '3000');
INSERT INTO `qa_relation_clause_group` VALUES ('3010', '3010');
INSERT INTO `qa_relation_clause_group` VALUES ('3020', '3020');
INSERT INTO `qa_relation_clause_group` VALUES ('3030', '3030');
INSERT INTO `qa_relation_clause_group` VALUES ('3040', '3040');
INSERT INTO `qa_relation_clause_group` VALUES ('3050', '3050');
INSERT INTO `qa_relation_clause_group` VALUES ('3060', '3060');
INSERT INTO `qa_relation_clause_group` VALUES ('3070', '3070');
INSERT INTO `qa_relation_clause_group` VALUES ('3080', '3080');
INSERT INTO `qa_relation_clause_group` VALUES ('3090', '3090');
INSERT INTO `qa_relation_clause_group` VALUES ('3100', '3100');
INSERT INTO `qa_relation_clause_group` VALUES ('3110', '3110');
INSERT INTO `qa_relation_clause_group` VALUES ('3120', '3120');
INSERT INTO `qa_relation_clause_group` VALUES ('3130', '3130');
INSERT INTO `qa_relation_clause_group` VALUES ('3140', '3140');
INSERT INTO `qa_relation_clause_group` VALUES ('3150', '3150');
INSERT INTO `qa_relation_clause_group` VALUES ('3160', '3160');
INSERT INTO `qa_relation_clause_group` VALUES ('3170', '3170');
INSERT INTO `qa_relation_clause_group` VALUES ('3180', '3180');
INSERT INTO `qa_relation_clause_group` VALUES ('3190', '3190');
INSERT INTO `qa_relation_clause_group` VALUES ('3200', '3200');
INSERT INTO `qa_relation_clause_group` VALUES ('3210', '3210');
INSERT INTO `qa_relation_clause_group` VALUES ('3220', '3220');

-- ----------------------------
-- Table structure for qa_relation_clause_group_category
-- ----------------------------
DROP TABLE IF EXISTS `qa_relation_clause_group_category`;
CREATE TABLE `qa_relation_clause_group_category`  (
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件分组id',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称id',
  `type` int(11) NULL DEFAULT 0 COMMENT '类型 0 与关系 1 组内或关系',
  `inner_group` int(11) NULL DEFAULT 0 COMMENT '组内分组编号(用于组内或关系)',
  `clause_condition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '同一group下所有clause之间关系'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '条件分组与类别关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_relation_clause_group_category
-- ----------------------------
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1000', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1001', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1001', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1001', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1001', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1001', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1002', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1003', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1003', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1003', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1003', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1003', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1003', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1010', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1010', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1010', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1010', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1010', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1010', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1011', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1011', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1011', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1011', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1011', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1011', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1020', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1020', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1021', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1021', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1021', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1022', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1022', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1030', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1030', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1030', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1030', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1040', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1040', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1040', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1041', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1041', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1041', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1041', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1041', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1041', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1050', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1050', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1050', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1050', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1051', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1051', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1051', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1051', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1052', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1052', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1052', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1052', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1053', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1053', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1053', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1053', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1060', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1060', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1060', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1060', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1061', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1061', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1061', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1061', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1070', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1080', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1080', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1080', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1080', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1080', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1080', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1081', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1081', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1081', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1081', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1081', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1090', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1090', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1090', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1091', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1091', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1091', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1091', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1100', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1100', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1100', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1110', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1110', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1110', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1110', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1110', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1110', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1120', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1120', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1120', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1120', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1121', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1121', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1121', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1121', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1122', '101001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1122', '101002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1122', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1122', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1123', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1123', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1124', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1130', '102001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1130', '102002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1140', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1140', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1141', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1141', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1142', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1142', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1143', '103001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('1143', '103002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2000', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2001', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2001', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2001', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2001', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2001', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2001', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2010', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2010', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2010', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2010', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2011', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2011', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2012', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2012', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2013', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2013', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2013', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2013', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2013', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2013', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2014', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2014', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2020', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2030', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2030', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2040', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2040', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2040', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2040', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2041', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2041', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2050', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2050', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2051', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2051', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2052', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2052', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2053', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2053', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2060', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2060', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2070', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2070', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2080', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2080', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2080', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2080', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2080', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2080', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2081', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2081', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2081', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2081', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2081', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2081', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2082', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2083', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2083', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2084', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2084', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2084', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2084', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2085', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2085', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2085', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2085', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2090', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2091', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2091', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2100', '201001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2100', '201002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2100', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2100', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2100', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2100', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2101', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2101', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2110', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2110', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2111', '202001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2111', '202002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2120', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2120', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2121', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2121', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2122', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2122', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2123', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2123', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2124', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2124', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2125', '203001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2125', '203002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2126', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2126', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2130', '204001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('2130', '204002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3000', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3000', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3000', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3000', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3010', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3010', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3020', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3020', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3020', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3020', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3030', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3030', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3040', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3040', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3050', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3050', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3060', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3060', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3070', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3070', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3070', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3070', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3070', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3070', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3080', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3080', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3080', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3080', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3090', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3090', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3100', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3100', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3110', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3110', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3120', '301001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3120', '301002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3120', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3120', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3130', '302001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3140', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3150', '302002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3160', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3160', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3160', '304002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3170', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3170', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3180', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3180', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3190', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3190', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3200', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3200', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3210', '303001', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3210', '303002', 0, 0, NULL);
INSERT INTO `qa_relation_clause_group_category` VALUES ('3220', '304002', 0, 0, NULL);

-- ----------------------------
-- Table structure for qa_relation_clause_group_project
-- ----------------------------
DROP TABLE IF EXISTS `qa_relation_clause_group_project`;
CREATE TABLE `qa_relation_clause_group_project`  (
  `project_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目id',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '条件组id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '条件分组与项目关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qa_relation_clause_item_category
-- ----------------------------
DROP TABLE IF EXISTS `qa_relation_clause_item_category`;
CREATE TABLE `qa_relation_clause_item_category`  (
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '条件子项id',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类id',
  `relation_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联id',
  `relation_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联值',
  `relation` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联选项(select) id',
  `express` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表达式',
  `clause_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'qa_base_clause表主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '条件子项与分类关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_relation_clause_item_category
-- ----------------------------
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-01', '101001', '2010-319', NULL, NULL, NULL, '1000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-01', '101002', '2010-319', NULL, NULL, NULL, '1000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-01', '102001', '2010-319', NULL, NULL, NULL, '1000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-01', '102002', '2010-319', NULL, NULL, NULL, '1000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-01', '103001', '2010-319', NULL, NULL, NULL, '1000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-01', '103002', '2010-319', NULL, NULL, NULL, '1000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-02', '101001', NULL, '2', NULL, '>=', '1000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-02', '101002', NULL, '2', NULL, '>=', '1000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-02', '102001', NULL, '1', NULL, '>=', '1000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-02', '102002', NULL, '1', NULL, '>=', '1000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-02', '103001', NULL, '1', NULL, '>=', '1000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1000-02', '103002', NULL, '1', NULL, '>=', '1000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '101001', '2010-319', NULL, NULL, NULL, '1001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '101002', '2010-319', NULL, NULL, NULL, '1001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '102001', '2010-319', NULL, NULL, NULL, '1001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '103001', '2010-319', NULL, NULL, NULL, '1001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '103002', '2010-319', NULL, NULL, NULL, '1001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '101001', '2010-317', NULL, NULL, NULL, '1001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '101002', '2010-317', NULL, NULL, NULL, '1001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '102001', '2010-317', NULL, NULL, NULL, '1001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '103001', '2010-317', NULL, NULL, NULL, '1001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-01', '103002', '2010-317', NULL, NULL, NULL, '1001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-02', '101001', '2010-320', NULL, NULL, NULL, '1001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-02', '101002', '2010-320', NULL, NULL, NULL, '1001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-02', '102001', '2010-320', NULL, NULL, NULL, '1001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-02', '103001', '2010-320', NULL, NULL, NULL, '1001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-02', '103002', '2010-320', NULL, NULL, NULL, '1001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-03', '101001', NULL, '5', NULL, '>=', '1001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-03', '101002', NULL, '5', NULL, '>=', '1001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-03', '102001', NULL, '5', NULL, '>=', '1001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1001-03', '103001', NULL, '5', NULL, '>=', '1001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1002-01', '102002', '2010-318', NULL, NULL, NULL, '1002', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1002-02', '102002', '2010-661', NULL, NULL, NULL, '1002', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1002-03', '102002', '2010-320', NULL, NULL, NULL, '1002', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1003-01', '101001', NULL, '64', NULL, '>=', '1003', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1003-01', '101002', NULL, '64', NULL, '>=', '1003', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1003-01', '102001', NULL, '32', NULL, '>=', '1003', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1003-01', '102002', NULL, '32', NULL, '>=', '1003', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1003-01', '103001', NULL, '32', NULL, '>=', '1003', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1003-01', '103002', NULL, '32', NULL, '>=', '1003', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '101001', '2010-325', NULL, NULL, NULL, '1010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '101002', '2010-325', NULL, NULL, NULL, '1010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '102001', '2010-325', NULL, NULL, NULL, '1010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '102002', '2010-325', NULL, NULL, NULL, '1010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '103001', '2010-325', NULL, NULL, NULL, '1010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '103002', '2010-325', NULL, NULL, NULL, '1010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '101001', '2010-326', NULL, NULL, NULL, '1010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '101002', '2010-326', NULL, NULL, NULL, '1010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '102001', '2010-326', NULL, NULL, NULL, '1010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '102002', '2010-326', NULL, NULL, NULL, '1010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '103001', '2010-326', NULL, NULL, NULL, '1010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-01', '103002', '2010-326', NULL, NULL, NULL, '1010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-02', '101001', '2010-329', NULL, NULL, NULL, '1010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-02', '101002', '2010-329', NULL, NULL, NULL, '1010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-02', '102001', '2010-329', NULL, NULL, NULL, '1010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-02', '102002', '2010-329', NULL, NULL, NULL, '1010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-02', '103001', '2010-329', NULL, NULL, NULL, '1010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1010-02', '103002', '2010-329', NULL, NULL, NULL, '1010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '101001', '2010-325', NULL, NULL, NULL, '1011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '101002', '2010-325', NULL, NULL, NULL, '1011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '102001', '2010-325', NULL, NULL, NULL, '1011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '102002', '2010-325', NULL, NULL, NULL, '1011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '103001', '2010-325', NULL, NULL, NULL, '1011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '103002', '2010-325', NULL, NULL, NULL, '1011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '101001', '2010-326', NULL, NULL, NULL, '1011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '101002', '2010-326', NULL, NULL, NULL, '1011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '102001', '2010-326', NULL, NULL, NULL, '1011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '102002', '2010-326', NULL, NULL, NULL, '1011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '103001', '2010-326', NULL, NULL, NULL, '1011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-01', '103002', '2010-326', NULL, NULL, NULL, '1011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-02', '101001', '2010-330', NULL, NULL, '>=', '1011', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-02', '101002', '2010-330', NULL, NULL, '>=', '1011', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-02', '102001', '2010-330', NULL, NULL, '>=', '1011', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-02', '102002', '2010-330', NULL, NULL, '>=', '1011', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-02', '103001', '2010-330', NULL, NULL, '>=', '1011', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1011-02', '103002', '2010-330', NULL, NULL, '>=', '1011', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1020-01', '101001', NULL, '优秀&教师奖##优秀&导师奖##优秀&教学奖', NULL, '[]', '1020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1020-01', '101002', NULL, '优秀&教师奖##优秀&导师奖##优秀&教学奖', NULL, '[]', '1020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1020-02', '101001', '2010-666', NULL, NULL, '>=', '1020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1020-02', '101002', '2010-667', NULL, NULL, '>=', '1020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1021-01', '101001', '2010-346', NULL, NULL, '>=', '1021', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1021-01', '101002', '2010-348', NULL, NULL, '>=', '1021', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1021-01', '102002', '2010-346', NULL, NULL, '>=', '1021', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1022-01', '101001', '2010-666', NULL, NULL, '>=', '1022', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1022-01', '101002', '2010-667', NULL, NULL, '>=', '1022', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1022-02', '101001', '2010-333', NULL, NULL, NULL, '1022', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1022-02', '101002', '2010-333', NULL, NULL, NULL, '1022', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-01', '101001', '2010-661', NULL, NULL, NULL, '1030', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-01', '101002', '2010-661', NULL, NULL, NULL, '1030', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-01', '102001', '2010-661', NULL, NULL, NULL, '1030', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-01', '102002', '2010-661', NULL, NULL, NULL, '1030', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-02', '101001', NULL, '3', NULL, '>=', '1030', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-02', '101002', NULL, '5', NULL, '>=', '1030', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-02', '102001', NULL, '5', NULL, '>=', '1030', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1030-02', '102002', NULL, '5', NULL, '>=', '1030', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-01', '101001', '2010-442', NULL, NULL, NULL, '1040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-01', '102001', '2010-442', NULL, NULL, NULL, '1040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-01', '102002', '2010-442', NULL, NULL, NULL, '1040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-01', '101001', '2010-443', NULL, NULL, NULL, '1040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-01', '102001', '2010-443', NULL, NULL, NULL, '1040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-01', '102002', '2010-443', NULL, NULL, NULL, '1040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-02', '101001', '2010-449', NULL, NULL, NULL, '1040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-02', '102001', '2010-449', NULL, NULL, NULL, '1040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-02', '102002', '2010-449', NULL, NULL, NULL, '1040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-02', '101001', '2010-450', NULL, NULL, NULL, '1040', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-02', '102001', '2010-450', NULL, NULL, NULL, '1040', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-02', '102002', '2010-450', NULL, NULL, NULL, '1040', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-03', '101001', NULL, '15', NULL, '>=', '1040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-03', '102001', NULL, '15', NULL, '>=', '1040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1040-03', '102002', NULL, '10', NULL, '>=', '1040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '101001', '2010-446', NULL, NULL, NULL, '1041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '101002', '2010-446', NULL, NULL, NULL, '1041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '102001', '2010-446', NULL, NULL, NULL, '1041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '102002', '2010-446', NULL, NULL, NULL, '1041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '103001', '2010-446', NULL, NULL, NULL, '1041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '103002', '2010-446', NULL, NULL, NULL, '1041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '101001', '2010-447', NULL, NULL, NULL, '1041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '101002', '2010-447', NULL, NULL, NULL, '1041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '102001', '2010-447', NULL, NULL, NULL, '1041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '102002', '2010-447', NULL, NULL, NULL, '1041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '103001', '2010-447', NULL, NULL, NULL, '1041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-01', '103002', '2010-447', NULL, NULL, NULL, '1041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '101001', '2010-442', NULL, NULL, NULL, '1041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '101002', '2010-442', NULL, NULL, NULL, '1041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '102001', '2010-442', NULL, NULL, NULL, '1041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '102002', '2010-442', NULL, NULL, NULL, '1041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '103001', '2010-442', NULL, NULL, NULL, '1041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '103002', '2010-442', NULL, NULL, NULL, '1041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '101002', '2010-443', NULL, NULL, NULL, '1041', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '102001', '2010-443', NULL, NULL, NULL, '1041', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '102002', '2010-443', NULL, NULL, NULL, '1041', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-02', '103002', '2010-443', NULL, NULL, NULL, '1041', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-03', '101001', NULL, '10', NULL, '>=', '1041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-03', '101002', NULL, '10', NULL, '>=', '1041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-03', '102001', NULL, '15', NULL, '>=', '1041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-03', '102002', NULL, '10', NULL, '>=', '1041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-03', '103001', NULL, '10', NULL, '>=', '1041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-03', '103002', NULL, '8', NULL, '>=', '1041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-04', '101001', NULL, '2', NULL, '>=', '1041', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-04', '101002', NULL, '1', NULL, '>=', '1041', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-04', '102001', NULL, '1', NULL, '>=', '1041', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-04', '102002', NULL, '1', NULL, '>=', '1041', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-04', '103001', NULL, '1', NULL, '>=', '1041', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1041-04', '103002', NULL, '1', NULL, '>=', '1041', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1050-01', '101001', '2010-669', NULL, NULL, '>=', '1050', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1050-01', '101002', '2010-671', NULL, NULL, '>=', '1050', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1050-01', '102001', '2010-670', NULL, NULL, '>=', '1050', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1050-01', '102002', '2010-671', NULL, NULL, '>=', '1050', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1051-01', '101001', '2010-342', NULL, NULL, NULL, '1051', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1051-01', '101002', '2010-342', NULL, NULL, NULL, '1051', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1051-01', '102001', '2010-342', NULL, NULL, NULL, '1051', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1051-01', '102002', '2010-342', NULL, NULL, NULL, '1051', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1052-01', '101001', NULL, '5', NULL, '>=', '1052', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1052-01', '101002', NULL, '3', NULL, '>=', '1052', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1052-01', '102001', NULL, '3', NULL, '>=', '1052', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1052-01', '102002', NULL, '2', NULL, '>=', '1052', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1053-01', '101001', NULL, '1', NULL, '>=', '1053', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1053-01', '101002', NULL, '1', NULL, '>=', '1053', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1053-01', '102001', NULL, '1', NULL, '>=', '1053', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1053-01', '102002', NULL, '1', NULL, '>=', '1053', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-01', '101001', NULL, '2', NULL, '>=', '1060', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-01', '101002', NULL, '2', NULL, '>=', '1060', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-01', '102001', NULL, '2', NULL, '>=', '1060', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-01', '102002', NULL, '1', NULL, '>=', '1060', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-02', '101001', '2010-605', NULL, NULL, NULL, '1060', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-02', '101002', '2010-605', NULL, NULL, NULL, '1060', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-02', '102001', '2010-605', NULL, NULL, NULL, '1060', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-02', '102002', '2010-606', NULL, NULL, NULL, '1060', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1060-02', '101002', '2010-606', NULL, NULL, NULL, '1060', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1061-01', '101001', '2010-365', NULL, NULL, NULL, '1061', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1061-01', '101002', '2010-365', NULL, NULL, NULL, '1061', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1061-01', '102001', '2010-365', NULL, NULL, NULL, '1061', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1061-01', '102002', '2010-365', NULL, NULL, NULL, '1061', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1070-01', '102001', NULL, '1', NULL, '>=', '1070', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-01', '101001', '2010-261', NULL, NULL, '>=', '1080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-01', '101002', '2010-261', NULL, NULL, '>=', '1080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-01', '102001', '2010-261', NULL, NULL, '>=', '1080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-01', '102002', '2010-261', NULL, NULL, '>=', '1080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-01', '103001', '2010-261', NULL, NULL, '>=', '1080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-01', '103002', '2010-261', NULL, NULL, '>=', '1080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-02', '101002', '2010-674', NULL, NULL, NULL, '1080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-02', '102002', '2010-674', NULL, NULL, NULL, '1080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-02', '103001', '2010-674', NULL, NULL, NULL, '1080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1080-02', '103002', '2010-674', NULL, NULL, NULL, '1080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-01', '101001', '2010-268', NULL, NULL, '>=', '1081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-01', '101002', '2010-268', NULL, NULL, '>=', '1081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-01', '102001', '2010-268', NULL, NULL, '>=', '1081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-01', '102002', '2010-268', NULL, NULL, '>=', '1081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-01', '103001', '2010-268', NULL, NULL, '>=', '1081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '101001', '2010-269', NULL, NULL, NULL, '1081', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '101002', '2010-269', NULL, NULL, NULL, '1081', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '102001', '2010-269', NULL, NULL, NULL, '1081', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '102002', '2010-269', NULL, NULL, NULL, '1081', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '103001', '2010-269', NULL, NULL, NULL, '1081', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '101001', '2010-270', NULL, NULL, NULL, '1081', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '101002', '2010-270', NULL, NULL, NULL, '1081', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '102001', '2010-270', NULL, NULL, NULL, '1081', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '102002', '2010-270', NULL, NULL, NULL, '1081', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-02', '103001', '2010-270', NULL, NULL, NULL, '1081', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-03', '101001', '2010-672', NULL, NULL, NULL, '1081', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-03', '101002', '2010-672', NULL, NULL, NULL, '1081', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-03', '102001', '2010-672', NULL, NULL, NULL, '1081', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-03', '102002', '2010-672', NULL, NULL, NULL, '1081', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1081-03', '103001', '2010-672', NULL, NULL, NULL, '1081', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-01', '102001', '2010-407', NULL, NULL, NULL, '1090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-01', '102002', '2010-407', NULL, NULL, NULL, '1090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-01', '103001', '2010-407', NULL, NULL, NULL, '1090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-01', '102001', '2010-408', NULL, NULL, NULL, '1090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-01', '102002', '2010-408', NULL, NULL, NULL, '1090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-01', '103001', '2010-408', NULL, NULL, NULL, '1090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-02', '102001', '2010-411', NULL, NULL, NULL, '1090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-02', '102002', '2010-411', NULL, NULL, NULL, '1090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-02', '103001', '2010-411', NULL, NULL, NULL, '1090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-03', '102001', NULL, '1', NULL, '=', '1090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-03', '102002', NULL, '1', NULL, '=', '1090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-03', '103001', NULL, '1', NULL, '=', '1090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-04', '102001', NULL, '1', NULL, '>=', '1090', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-04', '102002', NULL, '1', NULL, '>=', '1090', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-04', '103001', NULL, '1', NULL, '>=', '1090', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1090-04', '103001', NULL, '2', NULL, '>=', '1090', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '101001', '2010-407', NULL, NULL, NULL, '1091', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '101002', '2010-407', NULL, NULL, NULL, '1091', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '102001', '2010-407', NULL, NULL, NULL, '1091', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '102002', '2010-407', NULL, NULL, NULL, '1091', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '101001', '2010-408', NULL, NULL, NULL, '1091', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '101002', '2010-408', NULL, NULL, NULL, '1091', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '102001', '2010-408', NULL, NULL, NULL, '1091', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-01', '102002', '2010-408', NULL, NULL, NULL, '1091', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-02', '101001', '2010-411', NULL, NULL, NULL, '1091', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-02', '101002', '2010-411', NULL, NULL, NULL, '1091', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-02', '102001', '2010-411', NULL, NULL, NULL, '1091', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-02', '102002', '2010-411', NULL, NULL, NULL, '1091', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-03', '101001', NULL, '7', NULL, '<=', '1091', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-03', '101002', NULL, '7', NULL, '<=', '1091', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-03', '102001', NULL, '3', NULL, '<=', '1091', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-03', '102002', NULL, '7', NULL, '<=', '1091', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-04', '101001', NULL, '2', NULL, '>=', '1091', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-04', '101002', NULL, '1', NULL, '>=', '1091', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-04', '102001', NULL, '2', NULL, '>=', '1091', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1091-04', '102002', NULL, '2', NULL, '>=', '1091', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-01', '102002', '2010-407', NULL, NULL, NULL, '1100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-01', '103001', '2010-407', NULL, NULL, NULL, '1100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-01', '103002', '2010-407', NULL, NULL, NULL, '1100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-01', '102002', '2010-408', NULL, NULL, NULL, '1100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-01', '103001', '2010-408', NULL, NULL, NULL, '1100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-01', '103002', '2010-408', NULL, NULL, NULL, '1100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-02', '102002', '2010-411', NULL, NULL, NULL, '1100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-02', '103001', '2010-411', NULL, NULL, NULL, '1100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-02', '103002', '2010-411', NULL, NULL, NULL, '1100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-03', '102002', NULL, '7', NULL, '<=', '1100', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-03', '103001', NULL, '7', NULL, '<=', '1100', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-03', '103002', NULL, '7', NULL, '<=', '1100', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '102002', '2010-417', NULL, NULL, NULL, '1100', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103001', '2010-417', NULL, NULL, NULL, '1100', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103002', '2010-417', NULL, NULL, NULL, '1100', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '102002', '2010-418', NULL, NULL, NULL, '1100', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103001', '2010-418', NULL, NULL, NULL, '1100', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103002', '2010-418', NULL, NULL, NULL, '1100', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '102002', '2010-419', NULL, NULL, NULL, '1100', 'D3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103001', '2010-419', NULL, NULL, NULL, '1100', 'D3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103002', '2010-419', NULL, NULL, NULL, '1100', 'D3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '102002', '2010-420', NULL, NULL, NULL, '1100', 'D4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103001', '2010-420', NULL, NULL, NULL, '1100', 'D4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-04', '103002', '2010-420', NULL, NULL, NULL, '1100', 'D4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-05', '102002', NULL, '1', NULL, '>=', '1100', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-05', '103001', NULL, '1', NULL, '>=', '1100', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-05', '103002', NULL, '1', NULL, '>=', '1100', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1100-06', '102002', NULL, '1', NULL, '=', '1100', 'F1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '101001', '2010-407', NULL, NULL, NULL, '1110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '101002', '2010-407', NULL, NULL, NULL, '1110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '102001', '2010-407', NULL, NULL, NULL, '1110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '102002', '2010-407', NULL, NULL, NULL, '1110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '103001', '2010-407', NULL, NULL, NULL, '1110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '103002', '2010-407', NULL, NULL, NULL, '1110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '101001', '2010-408', NULL, NULL, NULL, '1110', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '101002', '2010-408', NULL, NULL, NULL, '1110', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '102001', '2010-408', NULL, NULL, NULL, '1110', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '102002', '2010-408', NULL, NULL, NULL, '1110', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '103001', '2010-408', NULL, NULL, NULL, '1110', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-01', '103002', '2010-408', NULL, NULL, NULL, '1110', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101001', '2010-431', NULL, NULL, NULL, '1110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101002', '2010-431', NULL, NULL, NULL, '1110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102001', '2010-431', NULL, NULL, NULL, '1110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102002', '2010-431', NULL, NULL, NULL, '1110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103001', '2010-431', NULL, NULL, NULL, '1110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103002', '2010-431', NULL, NULL, NULL, '1110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101001', '2010-432', NULL, NULL, NULL, '1110', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101002', '2010-432', NULL, NULL, NULL, '1110', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102001', '2010-432', NULL, NULL, NULL, '1110', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102002', '2010-432', NULL, NULL, NULL, '1110', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103001', '2010-432', NULL, NULL, NULL, '1110', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103002', '2010-432', NULL, NULL, NULL, '1110', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101001', '2010-433', NULL, NULL, NULL, '1110', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101002', '2010-433', NULL, NULL, NULL, '1110', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102001', '2010-433', NULL, NULL, NULL, '1110', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102002', '2010-433', NULL, NULL, NULL, '1110', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103001', '2010-433', NULL, NULL, NULL, '1110', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103002', '2010-433', NULL, NULL, NULL, '1110', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101001', '2010-434', NULL, NULL, NULL, '1110', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '101002', '2010-434', NULL, NULL, NULL, '1110', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102001', '2010-434', NULL, NULL, NULL, '1110', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '102002', '2010-434', NULL, NULL, NULL, '1110', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103001', '2010-434', NULL, NULL, NULL, '1110', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-02', '103002', '2010-434', NULL, NULL, NULL, '1110', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101001', NULL, '30', NULL, '<=', '1110', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101002', NULL, '30', NULL, '<=', '1110', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102001', NULL, '30', NULL, '<=', '1110', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102002', NULL, '30', NULL, '<=', '1110', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103001', NULL, '30', NULL, '<=', '1110', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103002', NULL, '30', NULL, '<=', '1110', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101001', NULL, '15', NULL, '<=', '1110', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101002', NULL, '15', NULL, '<=', '1110', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102001', NULL, '15', NULL, '<=', '1110', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102002', NULL, '15', NULL, '<=', '1110', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103001', NULL, '15', NULL, '<=', '1110', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103002', NULL, '15', NULL, '<=', '1110', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101001', NULL, '10', NULL, '<=', '1110', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101002', NULL, '10', NULL, '<=', '1110', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102001', NULL, '10', NULL, '<=', '1110', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102002', NULL, '10', NULL, '<=', '1110', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103001', NULL, '10', NULL, '<=', '1110', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103002', NULL, '10', NULL, '<=', '1110', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101001', NULL, '7', NULL, '<=', '1110', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101002', NULL, '7', NULL, '<=', '1110', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102001', NULL, '7', NULL, '<=', '1110', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102002', NULL, '7', NULL, '<=', '1110', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103001', NULL, '7', NULL, '<=', '1110', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103002', NULL, '7', NULL, '<=', '1110', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101001', NULL, '5', NULL, '<=', '1110', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101002', NULL, '5', NULL, '<=', '1110', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102001', NULL, '5', NULL, '<=', '1110', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102002', NULL, '5', NULL, '<=', '1110', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103001', NULL, '5', NULL, '<=', '1110', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103002', NULL, '5', NULL, '<=', '1110', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101001', NULL, '3', NULL, '<=', '1110', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '101002', NULL, '3', NULL, '<=', '1110', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102001', NULL, '3', NULL, '<=', '1110', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '102002', NULL, '3', NULL, '<=', '1110', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103001', NULL, '3', NULL, '<=', '1110', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1110-03', '103002', NULL, '3', NULL, '<=', '1110', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101001', '2010-437', NULL, NULL, NULL, '1120', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101002', '2010-437', NULL, NULL, NULL, '1120', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102001', '2010-437', NULL, NULL, NULL, '1120', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102002', '2010-437', NULL, NULL, NULL, '1120', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101001', '2010-436', NULL, NULL, NULL, '1120', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101002', '2010-436', NULL, NULL, NULL, '1120', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102001', '2010-436', NULL, NULL, NULL, '1120', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102002', '2010-436', NULL, NULL, NULL, '1120', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101001', '2010-438', NULL, NULL, NULL, '1120', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101002', '2010-438', NULL, NULL, NULL, '1120', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102001', '2010-438', NULL, NULL, NULL, '1120', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102002', '2010-438', NULL, NULL, NULL, '1120', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101001', '2010-620', NULL, NULL, NULL, '1120', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '101002', '2010-620', NULL, NULL, NULL, '1120', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102001', '2010-620', NULL, NULL, NULL, '1120', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-01', '102002', '2010-620', NULL, NULL, NULL, '1120', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-02', '101001', NULL, '1', NULL, '>=', '1120', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-02', '101002', NULL, '1', NULL, '>=', '1120', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-02', '102001', NULL, '2', NULL, '>=', '1120', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1120-02', '102002', NULL, '1', NULL, '>=', '1120', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101001', '2010-439', NULL, NULL, NULL, '1121', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101002', '2010-439', NULL, NULL, NULL, '1121', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102001', '2010-439', NULL, NULL, NULL, '1121', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102002', '2010-439', NULL, NULL, NULL, '1121', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101001', '2010-440', NULL, NULL, NULL, '1121', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101002', '2010-440', NULL, NULL, NULL, '1121', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102001', '2010-440', NULL, NULL, NULL, '1121', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102002', '2010-440', NULL, NULL, NULL, '1121', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101001', '2010-617', NULL, NULL, NULL, '1121', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101002', '2010-617', NULL, NULL, NULL, '1121', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102001', '2010-617', NULL, NULL, NULL, '1121', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102002', '2010-617', NULL, NULL, NULL, '1121', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101001', '2010-618', NULL, NULL, NULL, '1121', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101002', '2010-618', NULL, NULL, NULL, '1121', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102001', '2010-618', NULL, NULL, NULL, '1121', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102002', '2010-618', NULL, NULL, NULL, '1121', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101001', '2010-678', NULL, NULL, NULL, '1121', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '101002', '2010-678', NULL, NULL, NULL, '1121', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102001', '2010-678', NULL, NULL, NULL, '1121', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-01', '102002', '2010-678', NULL, NULL, NULL, '1121', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-02', '101001', NULL, '2', NULL, '>=', '1121', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-02', '101002', NULL, '2', NULL, '>=', '1121', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-02', '102001', NULL, '3', NULL, '>=', '1121', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1121-02', '102002', NULL, '2', NULL, '>=', '1121', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101001', '2010-439', NULL, NULL, NULL, '1122', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101002', '2010-439', NULL, NULL, NULL, '1122', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102001', '2010-439', NULL, NULL, NULL, '1122', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102002', '2010-439', NULL, NULL, NULL, '1122', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101001', '2010-440', NULL, NULL, NULL, '1122', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101002', '2010-440', NULL, NULL, NULL, '1122', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102001', '2010-440', NULL, NULL, NULL, '1122', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102002', '2010-440', NULL, NULL, NULL, '1122', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101001', '2010-617', NULL, NULL, NULL, '1122', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101002', '2010-617', NULL, NULL, NULL, '1122', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102001', '2010-617', NULL, NULL, NULL, '1122', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102002', '2010-617', NULL, NULL, NULL, '1122', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101001', '2010-618', NULL, NULL, NULL, '1122', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101002', '2010-618', NULL, NULL, NULL, '1122', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102001', '2010-618', NULL, NULL, NULL, '1122', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102002', '2010-618', NULL, NULL, NULL, '1122', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101001', '2010-619', NULL, NULL, NULL, '1122', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '101002', '2010-619', NULL, NULL, NULL, '1122', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102001', '2010-619', NULL, NULL, NULL, '1122', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-01', '102002', '2010-619', NULL, NULL, NULL, '1122', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-02', '101001', NULL, '3', NULL, '>=', '1122', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-02', '101002', NULL, '3', NULL, '>=', '1122', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-02', '102001', NULL, '6', NULL, '>=', '1122', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1122-02', '102002', NULL, '3', NULL, '>=', '1122', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1123-01', '103001', NULL, 'CN', NULL, '[]', '1123', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1123-01', '103002', NULL, 'CN', NULL, '[]', '1123', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1123-02', '103001', NULL, '4', NULL, '>=', '1123', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1123-02', '103002', NULL, '2', NULL, '>=', '1123', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1124-01', '103001', '2010-678', NULL, NULL, NULL, '1124', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1124-01', '103001', '2010-440', NULL, NULL, NULL, '1124', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-01', '102001', '2010-459', NULL, NULL, NULL, '1130', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-01', '102002', '2010-459', NULL, NULL, NULL, '1130', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-01', '102001', '2010-460', NULL, NULL, NULL, '1130', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-01', '102002', '2010-460', NULL, NULL, NULL, '1130', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-02', '102001', '2010-468', NULL, NULL, '>=', '1130', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-02', '102002', '2010-468', NULL, NULL, '>=', '1130', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-03', '102001', NULL, '1', NULL, '>=', '1130', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-03', '102002', NULL, '1', NULL, '>=', '1130', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-04', '102001', NULL, '3', NULL, '>=', '1130', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1130-04', '102002', NULL, '1', NULL, '>=', '1130', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103001', '2010-622', NULL, NULL, NULL, '1140', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103002', '2010-622', NULL, NULL, NULL, '1140', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103001', '2010-623', NULL, NULL, NULL, '1140', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103002', '2010-623', NULL, NULL, NULL, '1140', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103001', '2010-624', NULL, NULL, NULL, '1140', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103002', '2010-624', NULL, NULL, NULL, '1140', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103001', '2010-625', NULL, NULL, NULL, '1140', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103002', '2010-625', NULL, NULL, NULL, '1140', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103001', '2010-626', NULL, NULL, NULL, '1140', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-01', '103002', '2010-626', NULL, NULL, NULL, '1140', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-02', '103001', '2010-643', NULL, NULL, NULL, '1140', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-02', '103002', '2010-643', NULL, NULL, NULL, '1140', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-02', '103001', '2010-644', NULL, NULL, NULL, '1140', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-02', '103002', '2010-644', NULL, NULL, NULL, '1140', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-03', '103001', '2010-652', NULL, NULL, '>=', '1140', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1140-03', '103002', '2010-652', NULL, NULL, '>=', '1140', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-01', '103001', '2010-459', NULL, NULL, NULL, '1141', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-01', '103002', '2010-459', NULL, NULL, NULL, '1141', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-01', '103001', '2010-460', NULL, NULL, NULL, '1141', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-01', '103002', '2010-460', NULL, NULL, NULL, '1141', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-02', '103001', '2010-468', NULL, NULL, '>=', '1141', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-02', '103002', '2010-468', NULL, NULL, '>=', '1141', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-03', '103001', NULL, '1', NULL, '>=', '1141', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-03', '103002', NULL, '1', NULL, '>=', '1141', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-04', '103001', NULL, '1', NULL, '>=', '1141', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1141-04', '103002', NULL, '1', NULL, '>=', '1141', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103001', '2010-627', NULL, NULL, NULL, '1142', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103002', '2010-627', NULL, NULL, NULL, '1142', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103001', '2010-628', NULL, NULL, NULL, '1142', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103002', '2010-628', NULL, NULL, NULL, '1142', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103001', '2010-629', NULL, NULL, NULL, '1142', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103002', '2010-629', NULL, NULL, NULL, '1142', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103001', '2010-630', NULL, NULL, NULL, '1142', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-01', '103002', '2010-630', NULL, NULL, NULL, '1142', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-02', '103001', '2010-654', NULL, NULL, '>=', '1142', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-02', '103002', '2010-654', NULL, NULL, '>=', '1142', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-03', '103001', '2010-645', NULL, NULL, NULL, '1142', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-03', '103002', '2010-645', NULL, NULL, NULL, '1142', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-03', '103001', '2010-644', NULL, NULL, NULL, '1142', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-03', '103002', '2010-644', NULL, NULL, NULL, '1142', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-03', '103001', '2010-646', NULL, NULL, NULL, '1142', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1142-03', '103002', '2010-646', NULL, NULL, NULL, '1142', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103001', '2010-632', NULL, NULL, NULL, '1143', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103002', '2010-632', NULL, NULL, NULL, '1143', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103001', '2010-633', NULL, NULL, NULL, '1143', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103002', '2010-633', NULL, NULL, NULL, '1143', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103001', '2010-634', NULL, NULL, NULL, '1143', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103002', '2010-634', NULL, NULL, NULL, '1143', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103001', '2010-635', NULL, NULL, NULL, '1143', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-01', '103002', '2010-635', NULL, NULL, NULL, '1143', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-02', '103001', NULL, '8', NULL, '>=', '1143', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('1143-02', '103002', NULL, '6', NULL, '>=', '1143', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '201001', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '202001', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '202002', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '203001', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '203002', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '204001', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '204002', '2010-407', NULL, NULL, NULL, '2000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '201001', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '202001', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '202002', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '203001', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '203002', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '204001', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-01', '204002', '2010-408', NULL, NULL, NULL, '2000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-02', '202001', '2010-411', NULL, NULL, NULL, '2000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-02', '202002', '2010-411', NULL, NULL, NULL, '2000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '201001', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '202001', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '202002', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '203001', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '203002', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '204001', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-03', '204002', NULL, '1', NULL, '=', '2000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '201001', NULL, '1', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '202001', NULL, '2', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '202002', NULL, '1', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '203001', NULL, '1', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '203002', NULL, '1', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '204001', NULL, '2', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '204002', NULL, '1', NULL, '>=', '2000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '203001', NULL, '2', NULL, '>=', '2000', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-04', '203002', NULL, '2', NULL, '>=', '2000', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '201001', '2010-610', NULL, NULL, NULL, '2000', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '203001', '2010-611', NULL, NULL, NULL, '2000', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '203002', '2010-611', NULL, NULL, NULL, '2000', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '204001', '2010-612', NULL, NULL, NULL, '2000', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '204002', '2010-612', NULL, NULL, NULL, '2000', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '201001', '2010-407', NULL, NULL, NULL, '2001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '201002', '2010-407', NULL, NULL, NULL, '2001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '202001', '2010-407', NULL, NULL, NULL, '2001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '202002', '2010-407', NULL, NULL, NULL, '2001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '204001', '2010-407', NULL, NULL, NULL, '2001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '204002', '2010-407', NULL, NULL, NULL, '2001', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '201001', '2010-408', NULL, NULL, NULL, '2001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '201002', '2010-408', NULL, NULL, NULL, '2001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '202001', '2010-408', NULL, NULL, NULL, '2001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '202002', '2010-408', NULL, NULL, NULL, '2001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '204001', '2010-408', NULL, NULL, NULL, '2001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-01', '204002', '2010-408', NULL, NULL, NULL, '2001', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-02', '202001', '2010-411', NULL, NULL, NULL, '2001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-02', '202002', '2010-411', NULL, NULL, NULL, '2001', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-03', '201001', NULL, '3', NULL, '<=', '2001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-03', '201002', NULL, '5', NULL, '<=', '2001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-03', '202001', NULL, '3', NULL, '<=', '2001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-03', '202002', NULL, '7', NULL, '<=', '2001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-03', '204001', NULL, '3', NULL, '<=', '2001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-03', '204002', NULL, '5', NULL, '<=', '2001', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-04', '201001', NULL, '2', NULL, '>=', '2001', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-04', '201002', NULL, '2', NULL, '>=', '2001', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-04', '202001', NULL, '3', NULL, '>=', '2001', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-04', '202002', NULL, '2', NULL, '>=', '2001', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-04', '204001', NULL, '3', NULL, '>=', '2001', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2001-04', '204002', NULL, '2', NULL, '>=', '2001', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '201001', '2010-610', NULL, NULL, NULL, '2001', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '204001', '2010-612', NULL, NULL, NULL, '2001', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2000-05', '204002', '2010-612', NULL, NULL, NULL, '2001', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201001', '2010-437', NULL, NULL, NULL, '2010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201002', '2010-437', NULL, NULL, NULL, '2010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203001', '2010-437', NULL, NULL, NULL, '2010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203002', '2010-437', NULL, NULL, NULL, '2010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201001', '2010-436', NULL, NULL, NULL, '2010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201002', '2010-436', NULL, NULL, NULL, '2010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203001', '2010-436', NULL, NULL, NULL, '2010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203002', '2010-436', NULL, NULL, NULL, '2010', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201001', '2010-438', NULL, NULL, NULL, '2010', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201002', '2010-438', NULL, NULL, NULL, '2010', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203001', '2010-438', NULL, NULL, NULL, '2010', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203002', '2010-438', NULL, NULL, NULL, '2010', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201001', '2010-620', NULL, NULL, NULL, '2010', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '201002', '2010-620', NULL, NULL, NULL, '2010', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203001', '2010-620', NULL, NULL, NULL, '2010', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-01', '203002', '2010-620', NULL, NULL, NULL, '2010', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-02', '201001', NULL, '4', NULL, '>=', '2010', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-02', '201002', NULL, '2', NULL, '>=', '2010', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-02', '203001', NULL, '4', NULL, '>=', '2010', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2010-02', '203002', NULL, '2', NULL, '>=', '2010', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201001', '2010-439', NULL, NULL, NULL, '2011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201002', '2010-439', NULL, NULL, NULL, '2011', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201001', '2010-440', NULL, NULL, NULL, '2011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201002', '2010-440', NULL, NULL, NULL, '2011', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201001', '2010-617', NULL, NULL, NULL, '2011', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201002', '2010-617', NULL, NULL, NULL, '2011', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201001', '2010-618', NULL, NULL, NULL, '2011', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201002', '2010-618', NULL, NULL, NULL, '2011', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201001', '2010-678', NULL, NULL, NULL, '2011', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-01', '201002', '2010-678', NULL, NULL, NULL, '2011', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-02', '201001', NULL, '6', NULL, '>=', '2011', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2011-02', '201002', NULL, '3', NULL, '>=', '2011', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202001', '2010-437', NULL, NULL, NULL, '2012', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202002', '2010-437', NULL, NULL, NULL, '2012', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202001', '2010-436', NULL, NULL, NULL, '2012', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202002', '2010-436', NULL, NULL, NULL, '2012', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202001', '2010-438', NULL, NULL, NULL, '2012', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202002', '2010-438', NULL, NULL, NULL, '2012', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202001', '2010-620', NULL, NULL, NULL, '2012', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-01', '202002', '2010-620', NULL, NULL, NULL, '2012', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-02', '202001', NULL, '2', NULL, '>=', '2012', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-02', '202002', NULL, '1', NULL, '>=', '2012', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202001', '2010-439', NULL, NULL, NULL, '2012', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202002', '2010-439', NULL, NULL, NULL, '2012', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202001', '2010-440', NULL, NULL, NULL, '2012', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202002', '2010-440', NULL, NULL, NULL, '2012', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202001', '2010-617', NULL, NULL, NULL, '2012', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202002', '2010-617', NULL, NULL, NULL, '2012', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202001', '2010-618', NULL, NULL, NULL, '2012', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202002', '2010-618', NULL, NULL, NULL, '2012', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202001', '2010-678', NULL, NULL, NULL, '2012', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2012-03', '202002', '2010-678', NULL, NULL, NULL, '2012', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201001', '2010-439', NULL, NULL, NULL, '2013', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201002', '2010-439', NULL, NULL, NULL, '2013', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202001', '2010-439', NULL, NULL, NULL, '2013', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202002', '2010-439', NULL, NULL, NULL, '2013', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203001', '2010-439', NULL, NULL, NULL, '2013', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203002', '2010-439', NULL, NULL, NULL, '2013', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201001', '2010-440', NULL, NULL, NULL, '2013', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201002', '2010-440', NULL, NULL, NULL, '2013', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202001', '2010-440', NULL, NULL, NULL, '2013', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202002', '2010-440', NULL, NULL, NULL, '2013', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203001', '2010-440', NULL, NULL, NULL, '2013', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203002', '2010-440', NULL, NULL, NULL, '2013', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201001', '2010-617', NULL, NULL, NULL, '2013', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201002', '2010-617', NULL, NULL, NULL, '2013', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202001', '2010-617', NULL, NULL, NULL, '2013', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202002', '2010-617', NULL, NULL, NULL, '2013', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203001', '2010-617', NULL, NULL, NULL, '2013', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203002', '2010-617', NULL, NULL, NULL, '2013', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201001', '2010-618', NULL, NULL, NULL, '2013', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201002', '2010-618', NULL, NULL, NULL, '2013', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202001', '2010-618', NULL, NULL, NULL, '2013', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202002', '2010-618', NULL, NULL, NULL, '2013', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203001', '2010-618', NULL, NULL, NULL, '2013', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203002', '2010-618', NULL, NULL, NULL, '2013', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201001', '2010-619', NULL, NULL, NULL, '2013', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '201002', '2010-619', NULL, NULL, NULL, '2013', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202001', '2010-619', NULL, NULL, NULL, '2013', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '202002', '2010-619', NULL, NULL, NULL, '2013', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203001', '2010-619', NULL, NULL, NULL, '2013', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-01', '203002', '2010-619', NULL, NULL, NULL, '2013', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-02', '201001', NULL, '10', NULL, '>=', '2013', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-02', '201002', NULL, '5', NULL, '>=', '2013', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-02', '202001', NULL, '3', NULL, '>=', '2013', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-02', '202002', NULL, '2', NULL, '>=', '2013', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-02', '203001', NULL, '10', NULL, '>=', '2013', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2013-02', '203002', NULL, '8', NULL, '>=', '2013', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2014-01', '204001', NULL, 'CN', NULL, '[]', '2014', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2014-01', '204002', NULL, 'CN', NULL, '[]', '2014', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2014-02', '204001', NULL, '4', NULL, '>=', '2014', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2014-02', '204002', NULL, '2', NULL, '>=', '2014', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '201001', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '201002', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '202001', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '202002', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '203001', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '203002', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '204001', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '204002', '2010-446', NULL, NULL, NULL, '2020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '201001', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '201002', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '202001', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '202002', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '203001', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '203002', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '204001', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-01', '204002', '2010-447', NULL, NULL, NULL, '2020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '201001', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '201002', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '202001', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '202002', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '203001', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '203002', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '204001', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '204002', '2010-442', NULL, NULL, NULL, '2020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '201002', '2010-443', NULL, NULL, NULL, '2020', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '202002', '2010-443', NULL, NULL, NULL, '2020', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '203002', '2010-443', NULL, NULL, NULL, '2020', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-02', '204002', '2010-443', NULL, NULL, NULL, '2020', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '201001', NULL, '15', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '201002', NULL, '10', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '202001', NULL, '15', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '202002', NULL, '10', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '203001', NULL, '15', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '203002', NULL, '10', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '204001', NULL, '10', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-03', '204002', NULL, '8', NULL, '>=', '2020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '201001', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '201002', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '202001', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '202002', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '203001', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '203002', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '204001', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2020-04', '204002', NULL, '1', NULL, '>=', '2020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2030-01', '204001', NULL, '2', NULL, '>=', '2030', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2030-01', '204002', NULL, '1', NULL, '>=', '2030', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2030-02', '204001', NULL, '1', NULL, '=', '2030', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2030-02', '204002', NULL, '1', NULL, '=', '2030', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '202001', '2010-459', NULL, NULL, NULL, '2040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '202002', '2010-459', NULL, NULL, NULL, '2040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '204001', '2010-459', NULL, NULL, NULL, '2040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '204002', '2010-459', NULL, NULL, NULL, '2040', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '202001', '2010-460', NULL, NULL, NULL, '2040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '202002', '2010-460', NULL, NULL, NULL, '2040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '204001', '2010-460', NULL, NULL, NULL, '2040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-01', '204002', '2010-460', NULL, NULL, NULL, '2040', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-02', '202001', '2010-468', NULL, NULL, '>=', '2040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-02', '202002', '2010-468', NULL, NULL, '>=', '2040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-02', '204001', '2010-468', NULL, NULL, '>=', '2040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-02', '204002', '2010-468', NULL, NULL, '>=', '2040', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-03', '202001', NULL, '1', NULL, '>=', '2040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-03', '202002', NULL, '1', NULL, '>=', '2040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-03', '204001', NULL, '1', NULL, '>=', '2040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-03', '204002', NULL, '1', NULL, '>=', '2040', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-04', '202001', NULL, '3', NULL, '>=', '2040', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-04', '202002', NULL, '1', NULL, '>=', '2040', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-04', '204001', NULL, '3', NULL, '>=', '2040', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-04', '204002', NULL, '1', NULL, '>=', '2040', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-05', '202001', NULL, '1', NULL, '=', '2040', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-05', '202002', NULL, '1', NULL, '=', '2040', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-05', '204001', NULL, '1', NULL, '=', '2040', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2040-05', '204002', NULL, '1', NULL, '=', '2040', 'E1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-01', '203001', '2010-461', NULL, NULL, NULL, '2041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-01', '203002', '2010-461', NULL, NULL, NULL, '2041', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-01', '203001', '2010-462', NULL, NULL, NULL, '2041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-01', '203002', '2010-462', NULL, NULL, NULL, '2041', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-01', '203001', '2010-463', NULL, NULL, NULL, '2041', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-01', '203002', '2010-463', NULL, NULL, NULL, '2041', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-02', '203001', NULL, '2', NULL, '>=', '2041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-02', '203002', NULL, '1', NULL, '>=', '2041', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-03', '203001', NULL, '1', NULL, '=', '2041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2041-03', '203002', NULL, '1', NULL, '=', '2041', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2050-01', '201001', '2010-469', NULL, NULL, NULL, '2050', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2050-01', '201002', '2010-469', NULL, NULL, NULL, '2050', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2050-02', '201001', '2010-473', NULL, NULL, NULL, '2050', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2050-02', '201002', '2010-473', NULL, NULL, NULL, '2050', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2050-03', '201001', NULL, '1', NULL, '>=', '2050', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2050-03', '201002', NULL, '1', NULL, '>=', '2050', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2051-01', '201001', '2010-469', NULL, NULL, NULL, '2051', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2051-01', '201002', '2010-469', NULL, NULL, NULL, '2051', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2051-02', '201001', '2010-474', NULL, NULL, NULL, '2051', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2051-02', '201002', '2010-474', NULL, NULL, NULL, '2051', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2051-03', '201001', NULL, '2', NULL, '>=', '2051', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2051-03', '201002', NULL, '1', NULL, '>=', '2051', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2052-01', '201001', '2010-470', NULL, NULL, NULL, '2052', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2052-01', '201002', '2010-470', NULL, NULL, NULL, '2052', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2052-02', '201001', '2010-473', NULL, NULL, NULL, '2052', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2052-02', '201002', '2010-473', NULL, NULL, NULL, '2052', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2052-03', '201001', NULL, '2', NULL, '>=', '2052', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2052-03', '201002', NULL, '1', NULL, '>=', '2052', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2053-01', '201001', '2010-470', NULL, NULL, NULL, '2053', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2053-01', '201002', '2010-470', NULL, NULL, NULL, '2053', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2053-02', '201001', '2010-474', NULL, NULL, NULL, '2053', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2053-02', '201002', '2010-474', NULL, NULL, NULL, '2053', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2053-03', '201001', NULL, '2', NULL, '>=', '2053', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2053-03', '201002', NULL, '1', NULL, '>=', '2053', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201001', '2010-477', NULL, NULL, NULL, '2060', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201002', '2010-477', NULL, NULL, NULL, '2060', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201001', '2010-478', NULL, NULL, NULL, '2060', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201002', '2010-478', NULL, NULL, NULL, '2060', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201001', '2010-479', NULL, NULL, NULL, '2060', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201002', '2010-479', NULL, NULL, NULL, '2060', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201001', '2010-480', NULL, NULL, NULL, '2060', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201002', '2010-480', NULL, NULL, NULL, '2060', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201001', '2010-481', NULL, NULL, NULL, '2060', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201002', '2010-481', NULL, NULL, NULL, '2060', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201001', '2010-482', NULL, NULL, NULL, '2060', 'A6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-01', '201002', '2010-482', NULL, NULL, NULL, '2060', 'A6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-02', '201001', '2010-483', NULL, NULL, NULL, '2060', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-02', '201002', '2010-485', NULL, NULL, NULL, '2060', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-02', '201001', '2010-484', NULL, NULL, NULL, '2060', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-02', '201002', '2010-486', NULL, NULL, NULL, '2060', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2060-02', '201002', '2010-487', NULL, NULL, NULL, '2060', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2070-01', '201002', '2010-621', NULL, NULL, NULL, '2070', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2070-01', '201002', '2010-621', NULL, NULL, NULL, '2070', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2070-01', '201002', NULL, '1', NULL, '>=', '2070', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2070-01', '201002', NULL, '1', NULL, '>=', '2070', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-01', '201001', NULL, '3', NULL, '>=', '2080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-01', '201002', NULL, '2', NULL, '>=', '2080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-01', '202001', NULL, '1', NULL, '>=', '2080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-01', '202002', NULL, '1', NULL, '>=', '2080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-01', '203001', NULL, '1', NULL, '>=', '2080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-01', '203002', NULL, '1', NULL, '>=', '2080', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '201001', '2010-605', NULL, NULL, NULL, '2080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '201002', '2010-606', NULL, NULL, NULL, '2080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '202001', '2010-606', NULL, NULL, NULL, '2080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '202002', '2010-606', NULL, NULL, NULL, '2080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '203001', '2010-606', NULL, NULL, NULL, '2080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '203002', '2010-606', NULL, NULL, NULL, '2080', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2080-02', '201002', '2010-605', NULL, NULL, NULL, '2080', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2081-01', '201001', '2010-365', NULL, NULL, NULL, '2081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2081-01', '201002', '2010-365', NULL, NULL, NULL, '2081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2081-01', '202001', '2010-365', NULL, NULL, NULL, '2081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2081-01', '202002', '2010-365', NULL, NULL, NULL, '2081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2081-01', '203001', '2010-365', NULL, NULL, NULL, '2081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2081-01', '203002', '2010-365', NULL, NULL, NULL, '2081', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2082-01', '201001', '2010-606', NULL, NULL, NULL, '2082', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2082-02', '201001', '2010-607', NULL, NULL, NULL, '2082', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2083-01', '201001', '2010-342', NULL, NULL, NULL, '2083', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2083-01', '201002', '2010-342', NULL, NULL, NULL, '2083', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2084-01', '202001', NULL, '2', NULL, '>=', '2084', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2084-01', '202002', NULL, '1', NULL, '>=', '2084', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2084-01', '203001', NULL, '2', NULL, '>=', '2084', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2084-01', '203002', NULL, '1', NULL, '>=', '2084', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2085-01', '202001', NULL, '1', NULL, '>=', '2085', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2085-01', '202002', NULL, '1', NULL, '>=', '2085', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2085-01', '203001', NULL, '1', NULL, '>=', '2085', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2085-01', '203002', NULL, '1', NULL, '>=', '2085', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '201001', '2010-407', NULL, NULL, NULL, '2090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '201002', '2010-407', NULL, NULL, NULL, '2090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '202001', '2010-407', NULL, NULL, NULL, '2090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '202002', '2010-407', NULL, NULL, NULL, '2090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '203001', '2010-407', NULL, NULL, NULL, '2090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '204001', '2010-407', NULL, NULL, NULL, '2090', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '201001', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '201002', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '202001', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '202002', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '203001', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '203002', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '204001', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '204002', '2010-408', NULL, NULL, NULL, '2090', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '201002', '2010-603', NULL, NULL, NULL, '2090', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '203001', '2010-603', NULL, NULL, NULL, '2090', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '203002', '2010-603', NULL, NULL, NULL, '2090', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '204001', '2010-603', NULL, NULL, NULL, '2090', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-01', '204002', '2010-603', NULL, NULL, NULL, '2090', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201001', '2010-431', NULL, NULL, NULL, '2090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201002', '2010-431', NULL, NULL, NULL, '2090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202001', '2010-431', NULL, NULL, NULL, '2090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202002', '2010-431', NULL, NULL, NULL, '2090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '203001', '2010-431', NULL, NULL, NULL, '2090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '204001', '2010-431', NULL, NULL, NULL, '2090', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201001', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201002', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202001', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202002', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '203001', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '203002', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '204001', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '204002', '2010-432', NULL, NULL, NULL, '2090', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201001', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201002', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202001', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202002', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '203001', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '203002', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '204001', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '204002', '2010-433', NULL, NULL, NULL, '2090', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201001', '2010-434', NULL, NULL, NULL, '2090', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '201002', '2010-434', NULL, NULL, NULL, '2090', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '202002', '2010-434', NULL, NULL, NULL, '2090', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '203001', '2010-434', NULL, NULL, NULL, '2090', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-02', '204001', '2010-434', NULL, NULL, NULL, '2090', 'B4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '30', NULL, '<=', '2090', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '15', NULL, '<=', '2090', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '10', NULL, '<=', '2090', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '7', NULL, '<=', '2090', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '5', NULL, '<=', '2090', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '3', NULL, '<=', '2090', 'C6');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201001', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '201002', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202001', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '202002', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203001', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '203002', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204001', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-03', '204002', NULL, '1', NULL, '<=', '2090', 'C7');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-04', '203002', '2010-428', NULL, NULL, NULL, '2090', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2090-04', '204002', '2010-428', NULL, NULL, NULL, '2090', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201001', '2010-417', NULL, NULL, NULL, '2100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201002', '2010-417', NULL, NULL, NULL, '2100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202001', '2010-417', NULL, NULL, NULL, '2100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202002', '2010-417', NULL, NULL, NULL, '2100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203001', '2010-417', NULL, NULL, NULL, '2100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203002', '2010-417', NULL, NULL, NULL, '2100', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201001', '2010-418', NULL, NULL, NULL, '2100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201002', '2010-418', NULL, NULL, NULL, '2100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202001', '2010-418', NULL, NULL, NULL, '2100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202002', '2010-418', NULL, NULL, NULL, '2100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203001', '2010-418', NULL, NULL, NULL, '2100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203002', '2010-418', NULL, NULL, NULL, '2100', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201001', '2010-419', NULL, NULL, NULL, '2100', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201002', '2010-419', NULL, NULL, NULL, '2100', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202001', '2010-419', NULL, NULL, NULL, '2100', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202002', '2010-419', NULL, NULL, NULL, '2100', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203001', '2010-419', NULL, NULL, NULL, '2100', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203002', '2010-419', NULL, NULL, NULL, '2100', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201001', '2010-420', NULL, NULL, NULL, '2100', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '201002', '2010-420', NULL, NULL, NULL, '2100', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202001', '2010-420', NULL, NULL, NULL, '2100', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '202002', '2010-420', NULL, NULL, NULL, '2100', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203001', '2010-420', NULL, NULL, NULL, '2100', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-01', '203002', '2010-420', NULL, NULL, NULL, '2100', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-02', '201001', NULL, '1', NULL, '>=', '2100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-02', '201002', NULL, '1', NULL, '>=', '2100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-02', '202001', NULL, '2', NULL, '>=', '2100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-02', '202002', NULL, '1', NULL, '>=', '2100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-02', '203001', NULL, '1', NULL, '>=', '2100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2100-02', '203002', NULL, '1', NULL, '>=', '2100', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2101-01', '202001', '2010-657', NULL, NULL, NULL, '2101', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2101-01', '202002', '2010-657', NULL, NULL, NULL, '2101', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2101-02', '202001', NULL, '500', NULL, '>=', '2101', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2101-02', '202002', NULL, '100', NULL, '>=', '2101', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2110-01', '202001', '2010-261', NULL, NULL, '>=', '2110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2110-01', '202002', '2010-261', NULL, NULL, '>=', '2110', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2110-02', '202001', '2010-674', NULL, NULL, NULL, '2110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2110-02', '202002', '2010-674', NULL, NULL, NULL, '2110', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-01', '202001', '2010-269', NULL, NULL, NULL, '2111', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-01', '202002', '2010-269', NULL, NULL, NULL, '2111', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-01', '202001', '2010-270', NULL, NULL, NULL, '2111', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-01', '202002', '2010-270', NULL, NULL, NULL, '2111', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-02', '202001', '2010-672', NULL, NULL, NULL, '2111', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-02', '202002', '2010-672', NULL, NULL, NULL, '2111', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-03', '202001', '2010-268', NULL, NULL, '>=', '2111', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2111-03', '202002', '2010-268', NULL, NULL, '>=', '2111', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203001', '2010-622', NULL, NULL, NULL, '2120', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203002', '2010-622', NULL, NULL, NULL, '2120', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203001', '2010-623', NULL, NULL, NULL, '2120', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203002', '2010-623', NULL, NULL, NULL, '2120', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203001', '2010-624', NULL, NULL, NULL, '2120', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203002', '2010-624', NULL, NULL, NULL, '2120', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203001', '2010-625', NULL, NULL, NULL, '2120', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203002', '2010-625', NULL, NULL, NULL, '2120', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203001', '2010-626', NULL, NULL, NULL, '2120', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-01', '203002', '2010-626', NULL, NULL, NULL, '2120', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-02', '203001', '2010-643', NULL, NULL, NULL, '2120', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-02', '203002', '2010-643', NULL, NULL, NULL, '2120', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-02', '203001', '2010-644', NULL, NULL, NULL, '2120', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-02', '203002', '2010-644', NULL, NULL, NULL, '2120', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-03', '203001', '2010-652', NULL, NULL, '>=', '2120', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2120-03', '203002', '2010-652', NULL, NULL, '>=', '2120', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-01', '203001', '2010-459', NULL, NULL, NULL, '2122', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-01', '203002', '2010-459', NULL, NULL, NULL, '2122', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-01', '203001', '2010-460', NULL, NULL, NULL, '2122', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-01', '203002', '2010-460', NULL, NULL, NULL, '2122', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-02', '203001', '2010-468', NULL, NULL, '>=', '2122', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-02', '203002', '2010-468', NULL, NULL, '>=', '2122', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-03', '203001', NULL, '1', NULL, '>=', '2122', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-03', '203002', NULL, '1', NULL, '>=', '2122', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-04', '203001', NULL, '1', NULL, '>=', '2122', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2122-04', '203002', NULL, '1', NULL, '>=', '2122', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-01', '204001', '2010-649', NULL, NULL, NULL, '2123', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-01', '204002', '2010-649', NULL, NULL, NULL, '2123', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-01', '204001', '2010-650', NULL, NULL, NULL, '2123', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-01', '204002', '2010-650', NULL, NULL, NULL, '2123', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-01', '204001', '2010-651', NULL, NULL, NULL, '2123', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-01', '204002', '2010-651', NULL, NULL, NULL, '2123', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-02', '204001', NULL, '10', NULL, '>=', '2123', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2123-02', '204002', NULL, '6', NULL, '>=', '2123', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203001', '2010-627', NULL, NULL, NULL, '2124', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203002', '2010-627', NULL, NULL, NULL, '2124', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203001', '2010-628', NULL, NULL, NULL, '2124', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203002', '2010-628', NULL, NULL, NULL, '2124', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203001', '2010-629', NULL, NULL, NULL, '2124', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203002', '2010-629', NULL, NULL, NULL, '2124', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203001', '2010-630', NULL, NULL, NULL, '2124', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-01', '203002', '2010-630', NULL, NULL, NULL, '2124', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-02', '203001', '2010-644', NULL, NULL, NULL, '2124', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-02', '203002', '2010-644', NULL, NULL, NULL, '2124', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-02', '203001', '2010-645', NULL, NULL, NULL, '2124', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-02', '203002', '2010-645', NULL, NULL, NULL, '2124', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-02', '203001', '2010-646', NULL, NULL, NULL, '2124', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-02', '203002', '2010-646', NULL, NULL, NULL, '2124', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-03', '203001', '2010-654', NULL, NULL, '>=', '2124', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2124-03', '203002', '2010-654', NULL, NULL, '>=', '2124', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203001', '2010-632', NULL, NULL, NULL, '2125', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203002', '2010-632', NULL, NULL, NULL, '2125', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203001', '2010-633', NULL, NULL, NULL, '2125', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203002', '2010-633', NULL, NULL, NULL, '2125', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203001', '2010-634', NULL, NULL, NULL, '2125', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203002', '2010-634', NULL, NULL, NULL, '2125', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203001', '2010-635', NULL, NULL, NULL, '2125', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-01', '203002', '2010-635', NULL, NULL, NULL, '2125', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-02', '203001', NULL, '8', NULL, '>=', '2125', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2125-02', '203002', NULL, '6', NULL, '>=', '2125', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204001', '2010-638', NULL, NULL, NULL, '2126', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204002', '2010-638', NULL, NULL, NULL, '2126', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204001', '2010-639', NULL, NULL, NULL, '2126', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204002', '2010-639', NULL, NULL, NULL, '2126', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204001', '2010-640', NULL, NULL, NULL, '2126', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204002', '2010-640', NULL, NULL, NULL, '2126', 'A3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204001', '2010-641', NULL, NULL, NULL, '2126', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204002', '2010-641', NULL, NULL, NULL, '2126', 'A4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204001', '2010-642', NULL, NULL, NULL, '2126', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-01', '204002', '2010-642', NULL, NULL, NULL, '2126', 'A5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-02', '204001', '2010-644', NULL, NULL, NULL, '2126', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-02', '204002', '2010-644', NULL, NULL, NULL, '2126', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-02', '204001', '2010-645', NULL, NULL, NULL, '2126', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-02', '204002', '2010-645', NULL, NULL, NULL, '2126', 'B2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-02', '204001', '2010-646', NULL, NULL, NULL, '2126', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-02', '204002', '2010-646', NULL, NULL, NULL, '2126', 'B3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204001', '2010-632', NULL, NULL, NULL, '2126', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204002', '2010-632', NULL, NULL, NULL, '2126', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204001', '2010-633', NULL, NULL, NULL, '2126', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204002', '2010-633', NULL, NULL, NULL, '2126', 'C2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204001', '2010-634', NULL, NULL, NULL, '2126', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204002', '2010-634', NULL, NULL, NULL, '2126', 'C3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204001', '2010-635', NULL, NULL, NULL, '2126', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204002', '2010-635', NULL, NULL, NULL, '2126', 'C4');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204001', '2010-631', NULL, NULL, NULL, '2126', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-03', '204002', '2010-631', NULL, NULL, NULL, '2126', 'C5');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-04', '204001', '2010-653', NULL, NULL, NULL, '2126', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2126-04', '204002', '2010-653', NULL, NULL, NULL, '2126', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2130-01', '204001', NULL, '4', NULL, '>=', '2130', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2130-01', '204002', NULL, '3', NULL, '>=', '2130', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2130-02', '204001', '2010-679', NULL, NULL, NULL, '2130', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('2130-02', '204002', '2010-679', NULL, NULL, NULL, '2130', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-01', '301001', '2010-407', NULL, NULL, NULL, '3000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-01', '301002', '2010-407', NULL, NULL, NULL, '3000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-01', '302001', '2010-407', NULL, NULL, NULL, '3000', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-01', '301001', '2010-408', NULL, NULL, NULL, '3000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-01', '301002', '2010-408', NULL, NULL, NULL, '3000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-01', '302001', '2010-408', NULL, NULL, NULL, '3000', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-02', '301001', NULL, '1', NULL, '=', '3000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-02', '301002', NULL, '1', NULL, '=', '3000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-02', '302001', NULL, '1', NULL, '=', '3000', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-03', '301001', NULL, '1', NULL, '>=', '3000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-03', '301002', NULL, '1', NULL, '>=', '3000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-03', '302001', NULL, '1', NULL, '>=', '3000', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-04', '301001', '2010-613', NULL, NULL, NULL, '3000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-04', '301002', '2010-613', NULL, NULL, NULL, '3000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-04', '302001', '2010-614', NULL, NULL, NULL, '3000', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-04', '302001', '2010-615', NULL, NULL, NULL, '3000', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3000-04', '302001', '2010-616', NULL, NULL, NULL, '3000', 'D3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-01', '303001', '2010-603', NULL, NULL, NULL, '3010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-01', '303002', '2010-603', NULL, NULL, NULL, '3010', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-02', '303001', NULL, '1', NULL, '=', '3010', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-02', '303002', NULL, '1', NULL, '>', '3010', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-03', '303001', '2010-611', NULL, NULL, NULL, '3010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-03', '303002', '2010-611', NULL, NULL, NULL, '3010', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-04', '303001', NULL, '1', NULL, '>=', '3010', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3010-04', '303002', NULL, '1', NULL, '>=', '3010', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '301001', '2010-407', NULL, NULL, NULL, '3020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '301002', '2010-407', NULL, NULL, NULL, '3020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '302001', '2010-407', NULL, NULL, NULL, '3020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '302002', '2010-407', NULL, NULL, NULL, '3020', 'A1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '301001', '2010-408', NULL, NULL, NULL, '3020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '301002', '2010-408', NULL, NULL, NULL, '3020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '302001', '2010-408', NULL, NULL, NULL, '3020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-01', '302002', '2010-408', NULL, NULL, NULL, '3020', 'A2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-02', '301001', NULL, '3', NULL, '<=', '3020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-02', '301002', NULL, '5', NULL, '<=', '3020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-02', '302001', NULL, '3', NULL, '<=', '3020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-02', '302002', NULL, '1', NULL, '>=', '3020', 'B1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-03', '301001', NULL, '3', NULL, '>=', '3020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-03', '301002', NULL, '2', NULL, '>=', '3020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-03', '302001', NULL, '3', NULL, '>=', '3020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-03', '302002', NULL, '1', NULL, '>=', '3020', 'C1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '301001', '2010-613', NULL, NULL, NULL, '3020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '301002', '2010-613', NULL, NULL, NULL, '3020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '302001', '2010-614', NULL, NULL, NULL, '3020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '302002', '2010-614', NULL, NULL, NULL, '3020', 'D1');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '301001', '2010-615', NULL, NULL, NULL, '3020', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '301002', '2010-615', NULL, NULL, NULL, '3020', 'D2');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '302001', '2010-616', NULL, NULL, NULL, '3020', 'D3');
INSERT INTO `qa_relation_clause_item_category` VALUES ('3020-04', '302002', '2010-616', NULL, NULL, NULL, '3020', 'D3');

-- ----------------------------
-- Table structure for qa_resume_army_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_resume_army_item`;
CREATE TABLE `qa_resume_army_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `begin_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始时间',
  `end_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '终止时间',
  `parent_unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大单位\r\n\r\n陆军\r\n海军\r\n空军\r\n火箭军\r\n战略支援部队\r\n东部战区\r\n南部战区\r\n西部战区\r\n北部战区\r\n中部战区\r\n院内\r\n',
  `unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位\r\n\r\nXX旅\r\n一大队\r\n二大队\r\n',
  `post` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职务\r\n\r\n副旅长\r\n四队队长',
  `category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别\r\n\r\n任职\r\n代职\r\n院内代职',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部队任(代)职经历信息采集标准' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_resume_army_item
-- ----------------------------
INSERT INTO `qa_resume_army_item` VALUES ('1506abf2-5cf3-4885-b2b5-745316da9e04', '2020-02-26 17:15:21', '2020-02-26 17:15:47', '1', '2020-09-09 00:00:00', '2020-09-12 00:00:00', '大单位1', '单位1', '职务1', '类别1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_army_item` VALUES ('2bda8c4d-6f1d-4e08-8cd7-c735b12e6c0f', '2020-02-27 19:44:10', '2020-02-27 21:03:05', 'H7uMs5OW', '2020-02-11 00:00:00', '2020-02-11 00:00:00', '空军', '一大队', '副旅长', '代职', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_army_item` VALUES ('9bc917a9-644c-47c9-ab8b-61aaf9875527', '2020-02-27 19:46:42', '2020-02-27 19:46:42', 'H7uMs5OW', '2020-02-11 00:00:00', NULL, '', '', '副旅长', '院内代职', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_army_item` VALUES ('b9215794-d4ac-4794-beca-fc270d7801f6', '2020-02-27 19:23:11', '2020-02-27 19:23:11', 'H7uMs5OW', '2020-02-18 00:00:00', NULL, '火箭军', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_resume_army_train_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_resume_army_train_item`;
CREATE TABLE `qa_resume_army_train_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `begin_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始时间',
  `end_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '终止时间',
  `arms` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '军兵种\r\n\r\n陆军\r\n海军\r\n空军\r\n火箭军\r\n战略支援部队\r\n',
  `train_school` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训院校\r\n\r\nXX 旅',
  `train_major` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训专业\r\n\r\n副旅长\r\n',
  `train_levl` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训层次\r\n\r\n本科\r\n硕士研究生\r\n博士研究生\r\n大专\r\n任职\r\n',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '军兵种培训经历信息采集标准表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_resume_army_train_item
-- ----------------------------
INSERT INTO `qa_resume_army_train_item` VALUES ('76b6c114-7784-40f1-8036-9b2f43f13214', '2020-02-27 20:42:56', '2020-02-27 20:42:56', 'H7uMs5OW', '2020-02-19 00:00:00', '2020-02-04 00:00:00', '海军', 'XX旅', '副旅长', '硕士研究生', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_army_train_item` VALUES ('9e84c715-66b7-43ac-9000-53f609cd6d69', '2020-02-26 16:55:27', '2020-02-26 16:55:57', '1', '2020-12-12 00:00:00', '2020-12-16 00:00:00', '军兵种1', '培训院校1', '培训专业1', '培训层次1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_resume_peace_keep_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_resume_peace_keep_item`;
CREATE TABLE `qa_resume_peace_keep_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `begin_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始时间',
  `end_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '终止时间',
  `dispatch_unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '派遣单位\r\n军委联合参谋部\r\n',
  `country` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '\r\n执行任务国家和地区\r\n南苏丹\r\n',
  `task_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称\r\n\r\n维和\r\n',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维和援外任务经历信息采集标准' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_resume_peace_keep_item
-- ----------------------------
INSERT INTO `qa_resume_peace_keep_item` VALUES ('672247ea-a7d3-4cb4-a19a-c272d88af0a8', '2020-02-26 16:36:05', '2020-02-26 16:36:47', NULL, '2020-09-09 00:00:00', '2020-12-12 00:00:00', '派遣单位11', '执行任务国家和地区1', '任务名称11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_peace_keep_item` VALUES ('e5b18a28-8b56-4c39-abd3-0b181b0fe41d', '2020-02-26 16:36:13', '2020-02-26 16:36:13', NULL, '2020-09-09 00:00:00', '2020-12-12 00:00:00', '派遣单位', '执行任务国家和地区', '任务名称', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_peace_keep_item` VALUES ('e80cf46b-c00f-49a9-b250-f440fed6dbf7', '2020-02-27 20:53:04', '2020-02-27 20:53:04', 'H7uMs5OW', '2020-02-11 00:00:00', '2020-02-14 00:00:00', '派遣单位', '执行任务国家和地区', '任务名称', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_resume_study_abroad_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_resume_study_abroad_item`;
CREATE TABLE `qa_resume_study_abroad_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `begin_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '起始时间',
  `end_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '终止时间',
  `country` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家\r\n俄罗斯\r\n',
  `unit` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训院校或单位\r\n',
  `train_major` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训专业\r\n电子对抗\r\n',
  `train_category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '培训类别\r\n',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '出国留学经历信息采集标准' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_resume_study_abroad_item
-- ----------------------------
INSERT INTO `qa_resume_study_abroad_item` VALUES ('1d95bfa3-42f3-43dd-9cf9-197242025650', '2020-02-26 16:18:34', '2020-02-26 16:18:34', NULL, '2020-11-12 00:00:00', '2020-11-23 00:00:00', '俄罗斯123', '培训院校或单位123', '培训专业123', '培训类别123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_study_abroad_item` VALUES ('552c0899-0fdd-4873-99c4-17331cba2e3a', '2020-02-26 16:16:44', '2020-02-26 16:17:59', NULL, '2020-11-12 00:00:00', '2020-11-23 00:00:00', '俄罗斯123', '培训院校或单位123', '培训专业123', '培训类别123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_study_abroad_item` VALUES ('db45f5c3-9d87-4456-95e0-5f32e89f217c', '2020-03-03 10:43:23', '2020-03-03 10:43:23', '5a5lHOJT', NULL, NULL, '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_resume_study_abroad_item` VALUES ('e63a31db-aeba-4313-9a64-738cfc4cf68d', '2020-02-27 20:53:32', '2020-02-27 20:53:32', 'H7uMs5OW', '2020-02-05 00:00:00', '2020-02-06 00:00:00', '中国', '培训院或单位', '培训专业', '培训类别', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_standard_record
-- ----------------------------
DROP TABLE IF EXISTS `qa_standard_record`;
CREATE TABLE `qa_standard_record`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` bigint(1) NULL DEFAULT NULL COMMENT '性别 0：女  1：男',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `personnel_category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员类别\r\n\r\n军人干部\r\n文职人员',
  `birth` date NULL DEFAULT NULL COMMENT '出生日期',
  `enlistment_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '入伍时间（军人干部）',
  `enter_employment_time` datetime(0) NULL DEFAULT NULL COMMENT '参加工作时间（文职人员）',
  `political_affiliation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌\r\n\r\n党员',
  `caucus_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '党团时间',
  `last_education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历\r\n\r\n大学\r\n硕士研究生\r\n博士研究生\r\n',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业（最高学历）',
  `highest_degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学位\r\n\r\n学士\r\n硕士\r\n博士\r\n',
  `highest_degree_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '学位时间',
  `last_school_major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后毕业学校',
  `graduate_time` datetime(0) NULL DEFAULT NULL COMMENT '毕业时间',
  `highest_school_major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学位授予学校、专业',
  `highest_get_time` datetime(0) NULL DEFAULT NULL COMMENT '最高学位授予时间',
  `school_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院校类别 \r\n\r\n211\r\n985\r\n普通全日制\r\n军内院校\r\n',
  `technology_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现任专业技术职务\r\n\r\n助教\r\n讲师\r\n副教授\r\n教授\r\n研究实习员\r\n助理研究员\r\n副研究员\r\n研究员\r\n实验员\r\n助理实验师\r\n实验师\r\n高级实验师\r\n正高级实验师\r\n助理工程师\r\n技术员\r\n工程师\r\n高级工程师\r\n正高级工程师\r\n会计员\r\n助理会计师\r\n会计师\r\n高级会计师\r\n正高级会计师\r\n助理编辑\r\n编辑\r\n副编审\r\n编审\r\n管理员\r\n助理馆员\r\n馆员\r\n副研究馆员\r\n研究馆员\r\n',
  `appoint_time` datetime(0) NULL DEFAULT NULL COMMENT '聘任时间（现专业技术职务时间）',
  `appoint_annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聘任相关附件地址',
  `technology_level` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现技术等级\r\n\r\n专业技术一级\r\n专业技术二级\r\n专业技术三级\r\n专业技术四级\r\n专业技术五级\r\n专业技术六级\r\n专业技术七级\r\n专业技术八级\r\n专业技术九级\r\n专业技术十级\r\n专业技术十一级\r\n专业技术十二级\r\n专业技术十三级\r\n',
  `technology_level_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '现技术等级时间',
  `military_rank` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现军衔/级别\r\n\r\n大校\r\n上将\r\n中将\r\n少将\r\n上校\r\n中校\r\n少校\r\n上尉\r\n中尉\r\n少尉\r\n转改特级\r\n转改1级\r\n转改2级\r\n转改3级\r\n转改4级\r\n转改5级\r\n转改6级\r\n转改7级\r\n转改8级\r\n特级\r\n1级\r\n2级\r\n3级\r\n4级\r\n……\r\n25级\r\n',
  `military_rank_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '现军衔/级别时间',
  `technology_category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现技职类别\r\n\r\n初职\r\n中职\r\n副高职\r\n正高职\r\n',
  `technology_category_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '现技职类别时间',
  `official_rank` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现部级别\r\n\r\n训练管理系助教',
  `academic_title` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请职称，存储下拉框的选择值',
  `break_rule` bigint(1) NULL DEFAULT NULL COMMENT '是否破格 0：否  1：是',
  `break_rule_annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '破格相关附件地址',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作部门',
  `get_qualifications_time` datetime(0) NULL DEFAULT NULL COMMENT '获得资格时间',
  `get_qualifications_annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获得资格相关附件地址',
  `tech_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现所属技职领域',
  `category_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现所属技职领域类型',
  `category_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现所属技职职称',
  `category_title_date` datetime(0) NULL DEFAULT NULL COMMENT '现职称获取时间',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际工作单位',
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commit_status` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已提交 0 未提交 1已提交',
  `last_technology_category_time` datetime(0) NULL DEFAULT NULL,
  `user_want_category_title_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id_index`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户标准信息记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_standard_record
-- ----------------------------
INSERT INTO `qa_standard_record` VALUES ('1584611571668', '2020-03-19 17:52:52', '2020-03-19 17:53:15', '0MCzxPpA', NULL, '法撒旦', 1, '152824199012250817', '军人干部', '2020-02-01', '2020-02-01 00:00:00', NULL, '党员', '2020-02-01 00:00:00', '大学', NULL, '硕士', '2020-02-01 00:00:00', '法撒旦', '2020-02-01 00:00:00', '风速达到', '2020-02-01 00:00:00', '211', '副教授', '2020-02-01 00:00:00', NULL, '专业技术一级', '2020-02-01 00:00:00', '中将', '2020-02-01 00:00:00', '中职', '2020-02-01 00:00:00', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学科研型', NULL, NULL, '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584611654387', '2020-03-19 17:54:14', '2020-03-19 17:54:14', 'o9RJ34Zu', NULL, '测试员1', 1, '123456789012345678', '军人干部', '2020-07-01', '2020-07-01 00:00:00', NULL, '党员', '2020-07-01 00:00:00', '大学', NULL, '学士', '2020-07-01 00:00:00', '啊是嘎洒提问', '2000-07-01 00:00:00', '阿松大司徒', '2020-07-01 00:00:00', '211', '教授', '2020-07-01 00:00:00', NULL, '专业技术一级', '2020-07-01 00:00:00', '上将', '2020-11-01 00:00:00', '初职', '2020-11-01 00:00:00', '的复合', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学为主型', NULL, NULL, '指挥控制对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584611710892', '2020-03-19 17:55:11', '2020-03-20 09:14:58', '1', NULL, 'fdsa', 1, 'fdsafd', '文职人员', '2020-04-01', '2020-01-01 00:00:00', NULL, '党员', '2020-02-01 00:00:00', '博士研究生', NULL, '博士', '2020-01-01 00:00:00', 'fdafda', '2020-01-01 00:00:00', 'fdsa', '2020-02-01 00:00:00', '985', '研究实习员', '2020-10-01 00:00:00', NULL, '专业技术二级', '2020-01-01 00:00:00', '大校', '2020-09-01 00:00:00', '副高职', '2020-07-01 00:00:00', 'fdasfds', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '科学研究类', '基础研究型', NULL, NULL, '导航和制导对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584611781059', '2020-03-19 17:56:21', '2020-03-19 17:56:21', 'urcB3iLZ', NULL, '测试员1', 1, '123456789012345678', '军人干部', '2020-07-01', '2020-07-01 00:00:00', NULL, '党员', '2020-07-01 00:00:00', '大学', NULL, '学士', '2020-07-01 00:00:00', '啊是嘎洒提问', '2000-07-01 00:00:00', '阿松大司徒', '2020-07-01 00:00:00', '211', '教授', '2020-07-01 00:00:00', NULL, '专业技术一级', '2020-07-01 00:00:00', '上将', '2020-11-01 00:00:00', '初职', '2020-11-01 00:00:00', '的复合', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学为主型', NULL, NULL, '指挥控制对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584611827243', '2020-03-19 17:57:07', '2020-03-19 18:39:50', 'Al66Y75j', NULL, '噶大发噶', 1, '阿迪斯发士大夫', '军人干部', '1981-02-01', '1999-02-01 00:00:00', NULL, '党员', '2000-02-01 00:00:00', '博士研究生', NULL, '博士', '2006-08-01 00:00:00', '啊手动阀', '2006-08-01 00:00:00', '爱的方式', '2006-08-01 00:00:00', '军内院校', '教授', '2019-02-01 00:00:00', NULL, '专业技术一级', '2019-02-01 00:00:00', '大校', '2019-02-01 00:00:00', '正高职', '2019-02-01 00:00:00', '啊打发', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学科研型', NULL, NULL, '指挥控制对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584611865589', '2020-03-19 17:57:46', '2020-03-19 17:57:46', 'keMKRcnc', NULL, '朱哲', 1, '1212121212121', '军人干部', '2020-01-01', '2020-01-01 00:00:00', NULL, '群众', '2020-01-01 00:00:00', '硕士研究生', NULL, '硕士', '2020-01-01 00:00:00', '1212', '2020-01-01 00:00:00', '1212', '2020-01-01 00:00:00', '211', '助教', '2020-02-01 00:00:00', NULL, '专业技术一级', '2020-01-01 00:00:00', '上将', '2020-01-01 00:00:00', '初职', '2020-02-01 00:00:00', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学科研型', NULL, NULL, '指挥控制对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584612027021', '2020-03-19 18:00:27', '2020-03-19 18:11:25', 'tsMuMM47', NULL, 'siri', 1, '1245', '军人干部', '2000-07-01', '2020-02-01 00:00:00', NULL, '党员', '2020-04-01 00:00:00', '硕士研究生', NULL, '硕士', '2020-06-01 00:00:00', 'd', '2000-07-01 00:00:00', '对对对', '2020-04-01 00:00:00', '985', '助理研究员', '2020-10-01 00:00:00', NULL, '专业技术二级', '2020-06-01 00:00:00', '中将', '2020-01-01 00:00:00', '副高职', '2020-02-01 00:00:00', '33', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '科学研究类', '基础研究型', NULL, NULL, '导航和制导对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584614722793', '2020-03-19 18:45:23', '2020-03-19 18:45:36', 'sxD8lF9l', NULL, '法撒旦', 1, '152824199012250817', '军人干部', '2020-02-01', '2020-02-01 00:00:00', NULL, '党员', '2020-02-01 00:00:00', '大学', NULL, '硕士', '2020-02-01 00:00:00', '法撒旦', '2020-02-01 00:00:00', '风速达到', '2020-02-01 00:00:00', '211', '副教授', '2020-02-01 00:00:00', NULL, '专业技术一级', '2020-02-01 00:00:00', '中将', '2020-02-01 00:00:00', '中职', '2020-02-01 00:00:00', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学科研型', NULL, NULL, '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584616356692', '2020-03-19 19:12:37', '2020-03-19 19:13:02', 'Jt9TcU4h', NULL, '杨盼盼', 1, '610582199001012027', '军人干部', '2019-02-01', '2020-02-01 00:00:00', NULL, '党员', '2019-01-01 00:00:00', '大学', NULL, '学士', '2019-01-01 00:00:00', '的发送到发送到', '2020-01-01 00:00:00', '发的发生的', '2019-01-01 00:00:00', '211', '助理研究员', '2020-09-01 00:00:00', NULL, '专业技术一级', '2020-01-01 00:00:00', '上将', '2020-01-01 00:00:00', '中职', '2020-01-01 00:00:00', '大幅度', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '科学研究类', '基础研究型', NULL, NULL, '网电作战目标侦查系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'1', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584667188642', '2020-03-20 09:19:49', '2020-03-20 09:19:58', '4n9cdaPb', NULL, '法撒旦2', 1, '152824199012250817', '军人干部', '2020-02-01', '2020-02-01 00:00:00', NULL, '党员', '2020-02-01 00:00:00', '大学', NULL, '硕士', '2020-02-01 00:00:00', '法撒旦', '2020-02-01 00:00:00', '风速达到', '2020-02-01 00:00:00', '211', '副教授', '2020-02-01 00:00:00', NULL, '专业技术一级', '2020-02-01 00:00:00', '中将', '2020-02-01 00:00:00', '中职', '2020-02-01 00:00:00', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学科研型', NULL, NULL, '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584667319071', '2020-03-20 09:21:59', '2020-03-20 09:23:56', 'wZZltOyr', NULL, '范德萨', 1, '1552465456465133156', '军人干部', '2020-02-01', '2020-02-01 00:00:00', NULL, '党员', '2020-02-01 00:00:00', '硕士研究生', NULL, '学士', '2020-02-01 00:00:00', '发送到发送', '2020-02-01 00:00:00', '发范德萨', '2020-02-01 00:00:00', '985', '副研究员', '2020-02-01 00:00:00', NULL, '专业技术一级', '2020-02-01 00:00:00', '中将', '2020-02-01 00:00:00', '中职', '2020-02-01 00:00:00', '发送到发送', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '科学研究类', '技术创新应用研究型', NULL, NULL, '导航和制导对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584667595338', '2020-03-20 09:26:35', '2020-03-20 09:31:39', 'r4JGAkqJ', NULL, '防守打法', 0, '1528241999005045012', '文职人员', '2020-02-01', '2020-02-01 00:00:00', NULL, '群众', '2020-02-01 00:00:00', '硕士研究生', NULL, '学士', '2020-02-01 00:00:00', '防守打法撒', '2020-02-01 00:00:00', 'f发送端的', '2020-02-01 00:00:00', '普通全日制', '讲师', '2020-02-01 00:00:00', NULL, '专业技术二级', '2020-02-01 00:00:00', '上将', '2020-01-01 00:00:00', '初职', '2020-02-01 00:00:00', '收到收到 的', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学科研型', NULL, NULL, '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);
INSERT INTO `qa_standard_record` VALUES ('1584667602179', '2020-03-20 09:26:42', '2020-03-20 09:28:18', '5GKou3Xx', NULL, '杨盼', 1, '610582199001012027', '文职人员', '2019-02-01', '2020-01-01 00:00:00', NULL, '党员', '2020-01-01 00:00:00', '硕士研究生', NULL, '学士', '2019-01-01 00:00:00', '大幅度发多少', '2019-01-01 00:00:00', '发的发的顺丰', '2019-02-01 00:00:00', '211', '副教授', '2019-01-01 00:00:00', NULL, '专业技术二级', '2019-01-01 00:00:00', '中将', '2017-01-01 00:00:00', '中职', '2019-01-01 00:00:00', '大幅度发多少', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '教育教学类', '教学为主型', NULL, NULL, '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, b'0', NULL, NULL);

-- ----------------------------
-- Table structure for qa_standard_record_item
-- ----------------------------
DROP TABLE IF EXISTS `qa_standard_record_item`;
CREATE TABLE `qa_standard_record_item`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `group_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据组',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `record_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采集标准申请记录id （qa_standard_record表id）',
  `parameter_group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'base_parameter_group表id',
  `parameter_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'base_parameter表id',
  `parameter_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `parameter_annex_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件地址',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `record_id_index`(`record_id`) USING BTREE,
  INDEX `parameter_id_index`(`parameter_id`) USING BTREE,
  INDEX `parameter_group_id_index`(`parameter_group_id`) USING BTREE,
  INDEX `user_item_index`(`record_id`, `parameter_id`, `parameter_group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户标准信息记录项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_standard_record_item
-- ----------------------------
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_100', NULL, 0, '1584611571668', '11700', '11700-003', '地方撒', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_101', NULL, 0, '1584611571668', '11700', '11700-004', '发送端', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_102', NULL, 0, '1584611571668', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_103', NULL, 0, '1584611571668', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_104', NULL, 0, '1584611571668', '11700', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/11700/0/2020.3.19数据库修改.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_105', NULL, 1, '1584611571668', '11700', '11700-001', '2020.02-2020.03', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_106', NULL, 1, '1584611571668', '11700', '11700-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_107', NULL, 1, '1584611571668', '11700', '11700-003', '防守打法', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_108', NULL, 1, '1584611571668', '11700', '11700-004', '防守打法', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_109', NULL, 1, '1584611571668', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_110', NULL, 1, '1584611571668', '11700', '11700-006', '嘉奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_111', NULL, 1, '1584611571668', '11700', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/11700/1/gl_v1.3.0.apk', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_112', NULL, 0, '1584611571668', '5-10500-003', '5-10500-003-001', '发送端', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_113', NULL, 0, '1584611571668', '5-10500-003', '5-10500-003-002', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_114', NULL, 0, '1584611571668', '5-10500-003', '5-10500-003-003', '独立指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_115', NULL, 0, '1584611571668', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_116', NULL, 0, '1584611571668', '5-10500-003', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/5-10500-003/0/2.3 - ？工作日志.xls', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_117', NULL, 1, '1584611571668', '5-10500-003', '5-10500-003-001', '范德萨', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_118', NULL, 1, '1584611571668', '5-10500-003', '5-10500-003-002', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_119', NULL, 1, '1584611571668', '5-10500-003', '5-10500-003-003', '独立指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_120', NULL, 1, '1584611571668', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_121', NULL, 1, '1584611571668', '5-10500-003', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/5-10500-003/1/admin.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_122', NULL, 0, '1584611571668', '5-10500-002', '5-10500-002-001', '发送端', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_123', NULL, 0, '1584611571668', '5-10500-002', '5-10500-002-002', '地方撒', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_124', NULL, 0, '1584611571668', '5-10500-002', '5-10500-002-003', '2020', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_125', NULL, 0, '1584611571668', '5-10500-002', '5-10500-002-004', '湖南省', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_126', NULL, 0, '1584611571668', '5-10500-002', '5-10500-002-005', '合格', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_127', NULL, 0, '1584611571668', '5-10500-002', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/5-10500-002/0/qa_clause.sql', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_128', NULL, 1, '1584611571668', '5-10500-002', '5-10500-002-001', '发送端的', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_129', NULL, 1, '1584611571668', '5-10500-002', '5-10500-002-002', '范德萨', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_130', NULL, 1, '1584611571668', '5-10500-002', '5-10500-002-003', '2020', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_131', NULL, 1, '1584611571668', '5-10500-002', '5-10500-002-004', '安徽省', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_132', NULL, 1, '1584611571668', '5-10500-002', '5-10500-002-005', '合格', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_133', NULL, 1, '1584611571668', '5-10500-002', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_134', NULL, 0, '1584611571668', '5-10500-001', '5-10500-001-001', '发送端', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_135', NULL, 0, '1584611571668', '5-10500-001', '5-10500-001-002', '发送端', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_136', NULL, 0, '1584611571668', '5-10500-001', '5-10500-001-003', '2020', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_137', NULL, 0, '1584611571668', '5-10500-001', '5-10500-001-004', '湖南省', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_138', NULL, 0, '1584611571668', '5-10500-001', '5-10500-001-005', '优秀博士论文', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_139', NULL, 0, '1584611571668', '5-10500-001', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/5-10500-001/0/code码.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_140', NULL, 1, '1584611571668', '5-10500-001', '5-10500-001-001', '很舒服', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_141', NULL, 1, '1584611571668', '5-10500-001', '5-10500-001-002', '发额', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_142', NULL, 1, '1584611571668', '5-10500-001', '5-10500-001-003', '2020', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_143', NULL, 1, '1584611571668', '5-10500-001', '5-10500-001-004', '安徽省', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_144', NULL, 1, '1584611571668', '5-10500-001', '5-10500-001-005', '优秀硕士论文', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_145', NULL, 1, '1584611571668', '5-10500-001', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_28', NULL, 1, '1584611571668', '10100', '10100-001', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_29', NULL, 1, '1584611571668', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_30', NULL, 1, '1584611571668', '10100', '10100-003', '152824199012250817', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_31', NULL, 1, '1584611571668', '10100', '10100-005', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_32', NULL, 1, '1584611571668', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_33', NULL, 1, '1584611571668', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_34', NULL, 1, '1584611571668', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_35', NULL, 1, '1584611571668', '10100', '10100-009', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_36', NULL, 1, '1584611571668', '10100', '10100-010', '大学', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_37', NULL, 1, '1584611571668', '10100', '10100-011', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_38', NULL, 1, '1584611571668', '10100', '10100-012', '硕士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_39', NULL, 1, '1584611571668', '10100', '10100-013', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_40', NULL, 1, '1584611571668', '10100', '10100-014', '风速达到', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_41', NULL, 1, '1584611571668', '10100', '10100-015', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_42', NULL, 1, '1584611571668', '10100', '10100-016', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_43', NULL, 1, '1584611571668', '10100', '10100-017', '211', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_44', NULL, 1, '1584611571668', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_45', NULL, 1, '1584611571668', '10100', '10100-021', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_46', NULL, 1, '1584611571668', '10100', '10100-022', '中将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_47', NULL, 1, '1584611571668', '10100', '10100-023', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_48', NULL, 1, '1584611571668', '10100', '10100-024', '中职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_49', NULL, 1, '1584611571668', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_50', NULL, 1, '1584611571668', '10100', '10100-026', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_51', NULL, 1, '1584611571668', '10100', '10100-018', '副教授', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_52', NULL, 1, '1584611571668', '10100', '10100-019', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_53', NULL, 1, '1584611571668', '10100', '10100-028', '教学科研型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_54', NULL, 1, '1584611571668', '10100', '10100-029', '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_55', NULL, 1, '1584611571668', '10100', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/10100/1/eladmin-master.zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_73', NULL, 0, '1584611571668', '10500', '10500-001', '范德萨的', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_74', NULL, 0, '1584611571668', '10500', '10500-002', '一等奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_75', NULL, 0, '1584611571668', '10500', '10500-003', '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_76', NULL, 0, '1584611571668', '10500', '10500-004', '3', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_77', NULL, 0, '1584611571668', '10500', '10500-005', '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_83', NULL, 0, '1584611571668', '10500', '10500-006', '有', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_90', NULL, 0, '1584611571668', '10500', '10500-007', '有', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_97', NULL, 0, '1584611571668', '10500', '10000', '/root/www/statics/userUploadFiles/0MCzxPpA/10500/0/admin.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_98', NULL, 0, '1584611571668', '11700', '11700-001', '2020.02-2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611571668_99', NULL, 0, '1584611571668', '11700', '11700-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_01', '', 1, '1584611654387', '10100', '10100-001', '测试员1', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_02', '', 1, '1584611654387', '10100', '10100-002', '男', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_03', '', 1, '1584611654387', '10100', '10100-003', '123456789012345678', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_04', '', 1, '1584611654387', '10100', '10100-004', '军人干部', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_05', '', 1, '1584611654387', '10100', '10100-005', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_06', '', 1, '1584611654387', '10100', '10100-006', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_07', '', 1, '1584611654387', '10100', '10100-008', '党员', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_08', '', 1, '1584611654387', '10100', '10100-009', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_09', '', 1, '1584611654387', '10100', '10100-010', '大学', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_10', '', 1, '1584611654387', '10100', '10100-011', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_100', '', 10, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_101', '', 11, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_102', '', 11, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_103', '', 11, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_104', '', 11, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_105', '', 11, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_106', '', 12, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_107', '', 12, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_108', '', 12, '1584611654387', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_109', '', 12, '1584611654387', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_11', '', 1, '1584611654387', '10100', '10100-012', '学士', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_110', '', 12, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_111', '', 13, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_112', '', 13, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_113', '', 13, '1584611654387', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_114', '', 13, '1584611654387', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_115', '', 13, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_116', '', 14, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_117', '', 14, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_118', '', 14, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_119', '', 14, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_12', '', 1, '1584611654387', '10100', '10100-013', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_120', '', 14, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_121', '', 15, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_122', '', 15, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_123', '', 15, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_124', '', 15, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_125', '', 15, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_126', '', 16, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_127', '', 16, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_128', '', 16, '1584611654387', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_129', '', 16, '1584611654387', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_13', '', 1, '1584611654387', '10100', '10100-014', '阿松大司徒', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_130', '', 16, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_131', '', 17, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_132', '', 17, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_133', '', 17, '1584611654387', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_134', '', 17, '1584611654387', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_135', '', 17, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_136', '', 18, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_137', '', 18, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_138', '', 18, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_139', '', 18, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_14', '', 1, '1584611654387', '10100', '10100-015', '啊是嘎洒提问', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_140', '', 18, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_141', '', 19, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_142', '', 19, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_143', '', 19, '1584611654387', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_144', '', 19, '1584611654387', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_145', '', 19, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_15', '', 1, '1584611654387', '10100', '10100-016', '2000.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_16', '', 1, '1584611654387', '10100', '10100-017', '211', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_164', NULL, 0, '1584611654387', '11700', '11700-001', '2020.06-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_165', NULL, 0, '1584611654387', '11700', '11700-002', '陆军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_166', NULL, 0, '1584611654387', '11700', '11700-003', '啊扣税的给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_167', NULL, 0, '1584611654387', '11700', '11700-004', '啊速度过快', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_168', NULL, 0, '1584611654387', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_169', NULL, 0, '1584611654387', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_17', '', 1, '1584611654387', '10100', '10100-020', '专业技术一级', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_170', NULL, 0, '1584611654387', '11700', '10000', '/root/www/statics/userUploadFiles/o9RJ34Zu/11700/0/教务工作业绩-模块.zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_171', NULL, 1, '1584611654387', '11700', '11700-001', '2020.07-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_172', NULL, 1, '1584611654387', '11700', '11700-002', '陆军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_173', NULL, 1, '1584611654387', '11700', '11700-003', '啊扣税的给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_174', NULL, 1, '1584611654387', '11700', '11700-004', '啊速度过快', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_175', NULL, 1, '1584611654387', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_176', NULL, 1, '1584611654387', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_177', NULL, 0, '1584611654387', '11700', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_178', NULL, 2, '1584611654387', '11700', '11700-001', '2020.08-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_179', NULL, 2, '1584611654387', '11700', '11700-002', '陆军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_18', '', 1, '1584611654387', '10100', '10100-021', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_180', NULL, 2, '1584611654387', '11700', '11700-003', '啊扣税的给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_181', NULL, 2, '1584611654387', '11700', '11700-004', '啊速度过快', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_182', NULL, 2, '1584611654387', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_183', NULL, 2, '1584611654387', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_184', NULL, 0, '1584611654387', '11700', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_19', '', 1, '1584611654387', '10100', '10100-022', '上将', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_20', '', 1, '1584611654387', '10100', '10100-023', '2020.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_21', '', 1, '1584611654387', '10100', '10100-024', '初职', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_22', '', 1, '1584611654387', '10100', '10100-025', '2020.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_23', '', 1, '1584611654387', '10100', '10100-026', '的复合', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_24', '', 1, '1584611654387', '10100', '10100-018', '教授', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_25', '', 1, '1584611654387', '10100', '10100-019', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_26', '', 1, '1584611654387', '10100', '10100-028', '教学为主型', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_27', '', 1, '1584611654387', '10100', '10100-029', '指挥控制对抗系', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_28', '', 1, '1584611654387', '10500', '10500-001', '阿瑟东个了', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_29', '', 1, '1584611654387', '10500', '10500-002', '特等奖', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_30', '', 1, '1584611654387', '10500', '10500-003', '2', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_31', '', 1, '1584611654387', '10500', '10500-004', '2', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_32', '', 1, '1584611654387', '10500', '10500-005', '2', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_33', '', 1, '1584611654387', '10500', '10500-006', '有', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_34', '', 1, '1584611654387', '10500', '10500-007', '无', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_35', '', 1, '1584611654387', '5-10500-003', '5-10500-003-001', '撒啊', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_36', '', 1, '1584611654387', '5-10500-003', '5-10500-003-002', '2000.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_37', '', 1, '1584611654387', '5-10500-003', '5-10500-003-003', '独立指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_38', '', 1, '1584611654387', '5-10500-003', '5-10500-003-004', '中国博士后科学基金', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_39', '', 2, '1584611654387', '5-10500-003', '5-10500-003-001', '阿松改', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_40', '', 2, '1584611654387', '5-10500-003', '5-10500-003-002', '2366.10', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_41', '', 2, '1584611654387', '5-10500-003', '5-10500-003-003', '协助指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_42', '', 2, '1584611654387', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_43', '', 3, '1584611654387', '5-10500-003', '5-10500-003-001', '撒啊', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_44', '', 3, '1584611654387', '5-10500-003', '5-10500-003-002', '2000.12', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_45', '', 3, '1584611654387', '5-10500-003', '5-10500-003-003', '独立指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_46', '', 3, '1584611654387', '5-10500-003', '5-10500-003-004', '中国博士后科学基金', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_47', '', 4, '1584611654387', '5-10500-003', '5-10500-003-001', '阿松改', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_48', '', 4, '1584611654387', '5-10500-003', '5-10500-003-002', '2366.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_49', '', 4, '1584611654387', '5-10500-003', '5-10500-003-003', '协助指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_50', '', 4, '1584611654387', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_51', '', 1, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_52', '', 1, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_53', '', 1, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_54', '', 1, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_55', '', 1, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_56', '', 2, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_57', '', 2, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_58', '', 2, '1584611654387', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_59', '', 2, '1584611654387', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_60', '', 2, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_61', '', 3, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_62', '', 3, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_63', '', 3, '1584611654387', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_64', '', 3, '1584611654387', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_65', '', 3, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_66', '', 4, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_67', '', 4, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_68', '', 4, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_69', '', 4, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_70', '', 4, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_71', '', 5, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_72', '', 5, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_73', '', 5, '1584611654387', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_74', '', 5, '1584611654387', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_75', '', 5, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_76', '', 6, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_77', '', 6, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_78', '', 6, '1584611654387', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_79', '', 6, '1584611654387', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_80', '', 6, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_81', '', 7, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_82', '', 7, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_83', '', 7, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_84', '', 7, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_85', '', 7, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_86', '', 8, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_87', '', 8, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_88', '', 8, '1584611654387', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_89', '', 8, '1584611654387', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_90', '', 8, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_91', '', 9, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_92', '', 9, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_93', '', 9, '1584611654387', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_94', '', 9, '1584611654387', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_95', '', 9, '1584611654387', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_96', '', 10, '1584611654387', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_97', '', 10, '1584611654387', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_98', '', 10, '1584611654387', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611654387_99', '', 10, '1584611654387', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_100', NULL, 0, '1584611710892', '10500', '10500-005', '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_101', NULL, 1, '1584611710892', '5-10500-003', '5-10500-003-001', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_102', NULL, 1, '1584611710892', '5-10500-003', '5-10500-003-002', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_103', NULL, 1, '1584611710892', '5-10500-003', '5-10500-003-003', '协助指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_104', NULL, 1, '1584611710892', '5-10500-003', '5-10500-003-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_105', NULL, 0, '1584611710892', '5-10500-003', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_106', NULL, 2, '1584611710892', '5-10500-003', '5-10500-003-001', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_107', NULL, 2, '1584611710892', '5-10500-003', '5-10500-003-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_108', NULL, 2, '1584611710892', '5-10500-003', '5-10500-003-003', '独立指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_109', NULL, 2, '1584611710892', '5-10500-003', '5-10500-003-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_110', NULL, 1, '1584611710892', '5-10500-003', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_111', NULL, 0, '1584611710892', '10500', '10500-006', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_112', NULL, 0, '1584611710892', '10500', '10500-007', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_113', NULL, 0, '1584611710892', '10500', '10000', '/root/www/statics/userUploadFiles/1/10500/0/指导硕士生或博士生数量（人）.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_128', NULL, 0, '1584611710892', '10400', '10400-001', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_129', NULL, 0, '1584611710892', '10400', '10400-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_130', NULL, 0, '1584611710892', '10400', '10400-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_131', NULL, 0, '1584611710892', '10400', '10400-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_132', NULL, 0, '1584611710892', '10400', '10400-005', '良好', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_133', NULL, 0, '1584611710892', '10400', '10400-006', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_134', NULL, 0, '1584611710892', '10400', '10000', '/root/www/statics/userUploadFiles/1/10400/0/指导硕士生或博士生数量（人）.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_135', NULL, 1, '1584611710892', '10400', '10400-001', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_136', NULL, 1, '1584611710892', '10400', '10400-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_137', NULL, 1, '1584611710892', '10400', '10400-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_138', NULL, 1, '1584611710892', '10400', '10400-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_139', NULL, 1, '1584611710892', '10400', '10400-005', '优秀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_140', NULL, 1, '1584611710892', '10400', '10400-006', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_141', NULL, 1, '1584611710892', '10400', '10000', '/root/www/statics/userUploadFiles/1/10400/1/download (1).zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_170', NULL, 1, '1584611710892', '11700', '11700-001', '2020.06-2020.06', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_171', NULL, 1, '1584611710892', '11700', '11700-002', '战略支援部队', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_172', NULL, 1, '1584611710892', '11700', '11700-003', 'fdasfdsaf', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_173', NULL, 1, '1584611710892', '11700', '11700-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_174', NULL, 1, '1584611710892', '11700', '11700-005', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_175', NULL, 1, '1584611710892', '11700', '11700-006', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_176', NULL, 0, '1584611710892', '11700', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_177', NULL, 2, '1584611710892', '11700', '11700-001', '-2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_178', NULL, 2, '1584611710892', '11700', '11700-002', '火箭军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_179', NULL, 2, '1584611710892', '11700', '11700-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_180', NULL, 2, '1584611710892', '11700', '11700-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_181', NULL, 2, '1584611710892', '11700', '11700-005', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_182', NULL, 2, '1584611710892', '11700', '11700-006', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_183', NULL, 2, '1584611710892', '11700', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_268', NULL, 0, '1584611710892', '10100', '10100-001', 'fdsa', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_269', NULL, 0, '1584611710892', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_270', NULL, 0, '1584611710892', '10100', '10100-003', 'fdsafd', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_271', NULL, 0, '1584611710892', '10100', '10100-005', '2020.04', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_272', NULL, 0, '1584611710892', '10100', '10100-004', '文职人员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_273', NULL, 0, '1584611710892', '10100', '10100-006', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_274', NULL, 0, '1584611710892', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_275', NULL, 0, '1584611710892', '10100', '10100-009', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_276', NULL, 0, '1584611710892', '10100', '10100-010', '博士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_277', NULL, 0, '1584611710892', '10100', '10100-011', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_278', NULL, 0, '1584611710892', '10100', '10100-012', '博士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_279', NULL, 0, '1584611710892', '10100', '10100-013', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_280', NULL, 0, '1584611710892', '10100', '10100-014', 'fdsa', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_281', NULL, 0, '1584611710892', '10100', '10100-015', 'fdafda', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_282', NULL, 0, '1584611710892', '10100', '10100-016', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_283', NULL, 0, '1584611710892', '10100', '10100-017', '985', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_284', NULL, 0, '1584611710892', '10100', '10100-020', '专业技术二级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_285', NULL, 0, '1584611710892', '10100', '10100-021', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_286', NULL, 0, '1584611710892', '10100', '10100-022', '大校', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_287', NULL, 0, '1584611710892', '10100', '10100-023', '2020.09', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_288', NULL, 0, '1584611710892', '10100', '10100-024', '副高职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_289', NULL, 0, '1584611710892', '10100', '10100-025', '2020.07', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_290', NULL, 0, '1584611710892', '10100', '10100-026', 'fdasfds', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_291', NULL, 0, '1584611710892', '10100', '10100-018', '研究实习员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_292', NULL, 0, '1584611710892', '10100', '10100-019', '2020.10', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_293', NULL, 0, '1584611710892', '10100', '10100-028', '基础研究型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_294', NULL, 0, '1584611710892', '10100', '10100-029', '导航和制导对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_295', NULL, 0, '1584611710892', '10100', '10000', '/root/www/statics/userUploadFiles/1/10100/0/admin.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_96', NULL, 0, '1584611710892', '10500', '10500-001', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_97', NULL, 0, '1584611710892', '10500', '10500-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_98', NULL, 0, '1584611710892', '10500', '10500-003', '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611710892_99', NULL, 0, '1584611710892', '10500', '10500-004', '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_01', '', 1, '1584611781059', '10100', '10100-001', '测试员1', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_02', '', 1, '1584611781059', '10100', '10100-002', '男', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_03', '', 1, '1584611781059', '10100', '10100-003', '123456789012345678', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_04', '', 1, '1584611781059', '10100', '10100-004', '军人干部', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_05', '', 1, '1584611781059', '10100', '10100-005', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_06', '', 1, '1584611781059', '10100', '10100-006', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_07', '', 1, '1584611781059', '10100', '10100-008', '党员', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_08', '', 1, '1584611781059', '10100', '10100-009', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_09', '', 1, '1584611781059', '10100', '10100-010', '大学', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_10', '', 1, '1584611781059', '10100', '10100-011', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_100', '', 10, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_101', '', 11, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_102', '', 11, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_103', '', 11, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_104', '', 11, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_105', '', 11, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_106', '', 12, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_107', '', 12, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_108', '', 12, '1584611781059', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_109', '', 12, '1584611781059', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_11', '', 1, '1584611781059', '10100', '10100-012', '学士', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_110', '', 12, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_111', '', 13, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_112', '', 13, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_113', '', 13, '1584611781059', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_114', '', 13, '1584611781059', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_115', '', 13, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_116', '', 14, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_117', '', 14, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_118', '', 14, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_119', '', 14, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_12', '', 1, '1584611781059', '10100', '10100-013', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_120', '', 14, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_121', '', 15, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_122', '', 15, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_123', '', 15, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_124', '', 15, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_125', '', 15, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_126', '', 16, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_127', '', 16, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_128', '', 16, '1584611781059', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_129', '', 16, '1584611781059', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_13', '', 1, '1584611781059', '10100', '10100-014', '阿松大司徒', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_130', '', 16, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_131', '', 17, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_132', '', 17, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_133', '', 17, '1584611781059', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_134', '', 17, '1584611781059', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_135', '', 17, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_136', '', 18, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_137', '', 18, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_138', '', 18, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_139', '', 18, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_14', '', 1, '1584611781059', '10100', '10100-015', '啊是嘎洒提问', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_140', '', 18, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_141', '', 19, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_142', '', 19, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_143', '', 19, '1584611781059', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_144', '', 19, '1584611781059', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_145', '', 19, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_15', '', 1, '1584611781059', '10100', '10100-016', '2000.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_16', '', 1, '1584611781059', '10100', '10100-017', '211', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_17', '', 1, '1584611781059', '10100', '10100-020', '专业技术一级', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_18', '', 1, '1584611781059', '10100', '10100-021', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_19', '', 1, '1584611781059', '10100', '10100-022', '上将', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_20', '', 1, '1584611781059', '10100', '10100-023', '2020.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_21', '', 1, '1584611781059', '10100', '10100-024', '初职', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_22', '', 1, '1584611781059', '10100', '10100-025', '2020.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_23', '', 1, '1584611781059', '10100', '10100-026', '的复合', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_24', '', 1, '1584611781059', '10100', '10100-018', '教授', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_25', '', 1, '1584611781059', '10100', '10100-019', '2020.07', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_26', '', 1, '1584611781059', '10100', '10100-028', '教学为主型', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_27', '', 1, '1584611781059', '10100', '10100-029', '指挥控制对抗系', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_28', '', 1, '1584611781059', '10500', '10500-001', '阿瑟东个了', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_29', '', 1, '1584611781059', '10500', '10500-002', '特等奖', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_30', '', 1, '1584611781059', '10500', '10500-003', '2', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_31', '', 1, '1584611781059', '10500', '10500-004', '2', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_32', '', 1, '1584611781059', '10500', '10500-005', '2', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_33', '', 1, '1584611781059', '10500', '10500-006', '有', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_34', '', 1, '1584611781059', '10500', '10500-007', '无', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_35', '', 1, '1584611781059', '5-10500-003', '5-10500-003-001', '撒啊', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_36', '', 1, '1584611781059', '5-10500-003', '5-10500-003-002', '2000.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_37', '', 1, '1584611781059', '5-10500-003', '5-10500-003-003', '独立指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_38', '', 1, '1584611781059', '5-10500-003', '5-10500-003-004', '中国博士后科学基金', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_39', '', 2, '1584611781059', '5-10500-003', '5-10500-003-001', '阿松改', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_40', '', 2, '1584611781059', '5-10500-003', '5-10500-003-002', '2366.10', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_41', '', 2, '1584611781059', '5-10500-003', '5-10500-003-003', '协助指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_42', '', 2, '1584611781059', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_43', '', 3, '1584611781059', '5-10500-003', '5-10500-003-001', '撒啊', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_44', '', 3, '1584611781059', '5-10500-003', '5-10500-003-002', '2000.12', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_45', '', 3, '1584611781059', '5-10500-003', '5-10500-003-003', '独立指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_46', '', 3, '1584611781059', '5-10500-003', '5-10500-003-004', '中国博士后科学基金', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_47', '', 4, '1584611781059', '5-10500-003', '5-10500-003-001', '阿松改', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_48', '', 4, '1584611781059', '5-10500-003', '5-10500-003-002', '2366.11', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_49', '', 4, '1584611781059', '5-10500-003', '5-10500-003-003', '协助指导', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_50', '', 4, '1584611781059', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_51', '', 1, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_52', '', 1, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_53', '', 1, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_54', '', 1, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_55', '', 1, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_56', '', 2, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_57', '', 2, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_58', '', 2, '1584611781059', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_59', '', 2, '1584611781059', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_60', '', 2, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_61', '', 3, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_62', '', 3, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_63', '', 3, '1584611781059', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_64', '', 3, '1584611781059', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_65', '', 3, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_66', '', 4, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_67', '', 4, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_68', '', 4, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_69', '', 4, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_70', '', 4, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_71', '', 5, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_72', '', 5, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_73', '', 5, '1584611781059', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_74', '', 5, '1584611781059', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_75', '', 5, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_76', '', 6, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_77', '', 6, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_78', '', 6, '1584611781059', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_79', '', 6, '1584611781059', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_80', '', 6, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_81', '', 7, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_82', '', 7, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_83', '', 7, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_84', '', 7, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_85', '', 7, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_86', '', 8, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_87', '', 8, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_88', '', 8, '1584611781059', '5-10500-001', '5-10500-001-003', '6541', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_89', '', 8, '1584611781059', '5-10500-001', '5-10500-001-004', '湖南省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_90', '', 8, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀博士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_91', '', 9, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_92', '', 9, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_93', '', 9, '1584611781059', '5-10500-001', '5-10500-001-003', '6542', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_94', '', 9, '1584611781059', '5-10500-001', '5-10500-001-004', '安徽省', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_95', '', 9, '1584611781059', '5-10500-001', '5-10500-001-005', '优秀硕士论文', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_96', '', 10, '1584611781059', '5-10500-001', '5-10500-001-001', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_97', '', 10, '1584611781059', '5-10500-001', '5-10500-001-002', '阿斯顿噶', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_98', '', 10, '1584611781059', '5-10500-001', '5-10500-001-003', '1543', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611781059_99', '', 10, '1584611781059', '5-10500-001', '5-10500-001-004', '全军', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_113', NULL, 0, '1584611827243', '10500', '10500-001', '从v表现出v吧', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_114', NULL, 0, '1584611827243', '10500', '10500-002', '特等奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_115', NULL, 0, '1584611827243', '10500', '10500-003', '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_116', NULL, 0, '1584611827243', '10500', '10500-004', '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_117', NULL, 0, '1584611827243', '10500', '10500-005', '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_118', NULL, 0, '1584611827243', '5-10500-003', '5-10500-003-001', '现场v表现出v吧', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_119', NULL, 0, '1584611827243', '5-10500-003', '5-10500-003-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_120', NULL, 0, '1584611827243', '5-10500-003', '5-10500-003-003', '独立指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_121', NULL, 0, '1584611827243', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_122', NULL, 0, '1584611827243', '5-10500-003', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/5-10500-003/0/sqlite3.h', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_123', NULL, 1, '1584611827243', '5-10500-003', '5-10500-003-001', '自行车v表现出v吧', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_124', NULL, 1, '1584611827243', '5-10500-003', '5-10500-003-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_125', NULL, 1, '1584611827243', '5-10500-003', '5-10500-003-003', '协助指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_126', NULL, 1, '1584611827243', '5-10500-003', '5-10500-003-004', '中国博士后科学基金', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_127', NULL, 1, '1584611827243', '5-10500-003', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/5-10500-003/1/sqlite3ext.h', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_128', NULL, 2, '1584611827243', '5-10500-003', '5-10500-003-001', '这地方不存在v吧', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_129', NULL, 2, '1584611827243', '5-10500-003', '5-10500-003-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_130', NULL, 2, '1584611827243', '5-10500-003', '5-10500-003-003', '独立指导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_131', NULL, 2, '1584611827243', '5-10500-003', '5-10500-003-004', '入选博士后国（境）外交流项目', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_132', NULL, 2, '1584611827243', '5-10500-003', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/5-10500-003/2/shell.c', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_133', NULL, 0, '1584611827243', '10500', '10500-006', '有', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_134', NULL, 0, '1584611827243', '10500', '10500-007', '有', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_135', NULL, 0, '1584611827243', '10500', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/10500/0/sqlite3.c', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_136', NULL, 0, '1584611827243', '5-10500-001', '5-10500-001-001', 'zxcvxcv', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_137', NULL, 0, '1584611827243', '5-10500-001', '5-10500-001-002', 'zxcvzxcv', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_138', NULL, 0, '1584611827243', '5-10500-001', '5-10500-001-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_139', NULL, 0, '1584611827243', '5-10500-001', '5-10500-001-004', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_140', NULL, 0, '1584611827243', '5-10500-001', '5-10500-001-005', '优秀硕士论文', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_141', NULL, 0, '1584611827243', '5-10500-001', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/5-10500-001/0/sqlite3.h', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_142', NULL, 0, '1584611827243', '11900', '11900-001', '-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_143', NULL, 0, '1584611827243', '11900', '11900-002', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_144', NULL, 0, '1584611827243', '11900', '11900-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_145', NULL, 0, '1584611827243', '11900', '11900-004', '军事观察员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_146', NULL, 0, '1584611827243', '11900', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/11900/0/Android应用开发课程设计.docx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_159', NULL, 0, '1584611827243', '12000', '12000-001', '-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_160', NULL, 0, '1584611827243', '12000', '12000-002', '博士后项目', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_161', NULL, 0, '1584611827243', '12000', '12000-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_162', NULL, 0, '1584611827243', '12000', '12000-004', 'safdg', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_163', NULL, 0, '1584611827243', '12000', '12000-005', '岁的法国士大夫给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_164', NULL, 0, '1584611827243', '12000', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/12000/0/谷歌瓦片下载工具20180807.zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_165', NULL, 1, '1584611827243', '12000', '12000-001', '-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_166', NULL, 1, '1584611827243', '12000', '12000-002', '研究学者', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_167', NULL, 1, '1584611827243', '12000', '12000-003', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_168', NULL, 1, '1584611827243', '12000', '12000-004', '双方都不是大风', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_169', NULL, 1, '1584611827243', '12000', '12000-005', '岁的法国士大夫给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_170', NULL, 1, '1584611827243', '12000', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/12000/1/SQLlite.rar', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_199', NULL, 0, '1584611827243', '10100', '10100-001', '噶大发噶', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_200', NULL, 0, '1584611827243', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_201', NULL, 0, '1584611827243', '10100', '10100-003', '阿迪斯发士大夫', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_202', NULL, 0, '1584611827243', '10100', '10100-005', '1981.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_203', NULL, 0, '1584611827243', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_204', NULL, 0, '1584611827243', '10100', '10100-006', '1999.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_205', NULL, 0, '1584611827243', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_206', NULL, 0, '1584611827243', '10100', '10100-009', '2000.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_207', NULL, 0, '1584611827243', '10100', '10100-010', '博士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_208', NULL, 0, '1584611827243', '10100', '10100-011', '2006.08', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_209', NULL, 0, '1584611827243', '10100', '10100-012', '博士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_210', NULL, 0, '1584611827243', '10100', '10100-013', '2006.08', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_211', NULL, 0, '1584611827243', '10100', '10100-014', '爱的方式', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_212', NULL, 0, '1584611827243', '10100', '10100-015', '啊手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_213', NULL, 0, '1584611827243', '10100', '10100-016', '2006.08', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_214', NULL, 0, '1584611827243', '10100', '10100-017', '军内院校', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_215', NULL, 0, '1584611827243', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_216', NULL, 0, '1584611827243', '10100', '10100-021', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_217', NULL, 0, '1584611827243', '10100', '10100-022', '大校', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_218', NULL, 0, '1584611827243', '10100', '10100-023', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_219', NULL, 0, '1584611827243', '10100', '10100-024', '正高职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_220', NULL, 0, '1584611827243', '10100', '10100-025', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_221', NULL, 0, '1584611827243', '10100', '10100-026', '啊打发', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_222', NULL, 0, '1584611827243', '10100', '10100-018', '教授', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_223', NULL, 0, '1584611827243', '10100', '10100-019', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_224', NULL, 0, '1584611827243', '10100', '10100-028', '教学科研型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_225', NULL, 0, '1584611827243', '10100', '10100-029', '指挥控制对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_226', NULL, 0, '1584611827243', '10100', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/10100/0/谷歌瓦片下载工具20180807.zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_29', NULL, 0, '1584611827243', '11700', '11700-001', '2006.01-2010.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_30', NULL, 0, '1584611827243', '11700', '11700-002', '院内', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_31', NULL, 0, '1584611827243', '11700', '11700-003', '啊实打实的发', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_32', NULL, 0, '1584611827243', '11700', '11700-004', '啊手动阀手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_33', NULL, 0, '1584611827243', '11700', '11700-005', '代职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_34', NULL, 0, '1584611827243', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_35', NULL, 0, '1584611827243', '11700', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/11700/0/SQLlite.rar', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_36', NULL, 1, '1584611827243', '11700', '11700-001', '2018.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_37', NULL, 1, '1584611827243', '11700', '11700-002', '火箭军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_38', NULL, 1, '1584611827243', '11700', '11700-003', '啊手动阀手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_39', NULL, 1, '1584611827243', '11700', '11700-004', 'a\'s\'fa\'s\'d\'f', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_40', NULL, 1, '1584611827243', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_41', NULL, 1, '1584611827243', '11700', '11700-006', '嘉奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_42', NULL, 1, '1584611827243', '11700', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/11700/1/谷歌瓦片下载工具20180807.zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_50', NULL, 0, '1584611827243', '11800', '11800-001', '2020.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_51', NULL, 0, '1584611827243', '11800', '11800-002', '火箭军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_52', NULL, 0, '1584611827243', '11800', '11800-003', '啊手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_53', NULL, 0, '1584611827243', '11800', '11800-004', '啊手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_54', NULL, 0, '1584611827243', '11800', '11800-005', '啊手动阀手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_55', NULL, 0, '1584611827243', '11800', '11800-006', '学历教育', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_56', NULL, 0, '1584611827243', '11800', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/11800/0/sqlite-amalgamation-3071000.zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_57', NULL, 1, '1584611827243', '11800', '11800-001', '2020.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_58', NULL, 1, '1584611827243', '11800', '11800-002', '陆军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_59', NULL, 1, '1584611827243', '11800', '11800-003', '啊手动阀手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_60', NULL, 1, '1584611827243', '11800', '11800-004', '啊手动阀手动阀', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_61', NULL, 1, '1584611827243', '11800', '11800-005', '全日制本科', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_62', NULL, 1, '1584611827243', '11800', '11800-006', '学历教育', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_63', NULL, 1, '1584611827243', '11800', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/11800/1/SQLlite.rar', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_64', NULL, 0, '1584611827243', '12100', '12100-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_65', NULL, 0, '1584611827243', '12100', '12100-002', '三方公司股份和', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_66', NULL, 0, '1584611827243', '12100', '12100-003', '儿童很是反感和', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_67', NULL, 0, '1584611827243', '12100', '12100-004', '东部战区', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_68', NULL, 0, '1584611827243', '12100', '12100-005', '军事演习', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_69', NULL, 0, '1584611827243', '12100', '12100-006', '嘉奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_70', NULL, 0, '1584611827243', '12100', '12100-007', '是否更好傻瓜和', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_71', NULL, 0, '1584611827243', '12100', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/12100/0/Android应用开发课程设计.docx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_72', NULL, 1, '1584611827243', '12100', '12100-001', '2018.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_73', NULL, 1, '1584611827243', '12100', '12100-002', '书法国画书法', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_74', NULL, 1, '1584611827243', '12100', '12100-003', '飞过海的风格和', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_75', NULL, 1, '1584611827243', '12100', '12100-004', '军委', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_76', NULL, 1, '1584611827243', '12100', '12100-005', '军事演习', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_77', NULL, 1, '1584611827243', '12100', '12100-006', '嘉奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_78', NULL, 1, '1584611827243', '12100', '12100-007', '的和高度风格化', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_79', NULL, 1, '1584611827243', '12100', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/12100/1/InstallSQLiteStudio-3.2.1.exe', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_80', NULL, 0, '1584611827243', '12200', '12200-001', '的风格不能续保', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_81', NULL, 0, '1584611827243', '12200', '12200-007', '师', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_82', NULL, 0, '1584611827243', '12200', '12200-002', '岁的法国士大夫给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_83', NULL, 0, '1584611827243', '12200', '12200-003', '2020.03.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_84', NULL, 0, '1584611827243', '12200', '12200-004', '岁的法国士大夫给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_85', NULL, 0, '1584611827243', '12200', '12200-005', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_86', NULL, 0, '1584611827243', '12200', '12200-006', '史蒂夫噶士大夫', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_87', NULL, 0, '1584611827243', '12200', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/12200/0/策划案数值部分.doc', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_88', NULL, 0, '1584611827243', '12600', '12600-010', '军事演习', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_89', NULL, 0, '1584611827243', '12600', '12600-020', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_90', NULL, 0, '1584611827243', '12600', '12600-030', '军事需求研究', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_91', NULL, 0, '1584611827243', '12600', '12600-040', '科研处', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_92', NULL, 0, '1584611827243', '12600', '12600-050', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_93', NULL, 0, '1584611827243', '12600', '12600-060', 'sdfgsdfg ', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_94', NULL, 0, '1584611827243', '12600', '12600-070', '岁的法国士大夫给', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_95', NULL, 0, '1584611827243', '12600', '12600-080', '科研实验', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_96', NULL, 0, '1584611827243', '12600', '12600-090', '师以上', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611827243_97', NULL, 0, '1584611827243', '12600', '10000', '/root/www/statics/userUploadFiles/Al66Y75j/12600/0/SQLlite.rar', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_01', NULL, 0, '1584611865589', '10100', '10100-001', '朱哲', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_02', NULL, 0, '1584611865589', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_03', NULL, 0, '1584611865589', '10100', '10100-003', '1212121212121', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_04', NULL, 0, '1584611865589', '10100', '10100-005', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_05', NULL, 0, '1584611865589', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_06', NULL, 0, '1584611865589', '10100', '10100-006', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_07', NULL, 0, '1584611865589', '10100', '10100-008', '群众', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_08', NULL, 0, '1584611865589', '10100', '10100-009', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_09', NULL, 0, '1584611865589', '10100', '10100-010', '硕士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_10', NULL, 0, '1584611865589', '10100', '10100-011', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_11', NULL, 0, '1584611865589', '10100', '10100-012', '硕士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_12', NULL, 0, '1584611865589', '10100', '10100-013', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_13', NULL, 0, '1584611865589', '10100', '10100-014', '1212', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_14', NULL, 0, '1584611865589', '10100', '10100-015', '1212', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_15', NULL, 0, '1584611865589', '10100', '10100-016', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_16', NULL, 0, '1584611865589', '10100', '10100-017', '211', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_17', NULL, 0, '1584611865589', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_18', NULL, 0, '1584611865589', '10100', '10100-021', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_19', NULL, 0, '1584611865589', '10100', '10100-022', '上将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_20', NULL, 0, '1584611865589', '10100', '10100-023', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_21', NULL, 0, '1584611865589', '10100', '10100-024', '初职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_22', NULL, 0, '1584611865589', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_23', NULL, 0, '1584611865589', '10100', '10100-026', '12', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_24', NULL, 0, '1584611865589', '10100', '10100-018', '助教', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_25', NULL, 0, '1584611865589', '10100', '10100-019', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_26', NULL, 0, '1584611865589', '10100', '10100-028', '教学科研型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_27', NULL, 0, '1584611865589', '10100', '10100-029', '指挥控制对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_28', NULL, 0, '1584611865589', '10100', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_29', NULL, 0, '1584611865589', '11700', '11700-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_30', NULL, 0, '1584611865589', '11700', '11700-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_31', NULL, 0, '1584611865589', '11700', '11700-003', '121', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_32', NULL, 0, '1584611865589', '11700', '11700-004', '1212', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_33', NULL, 0, '1584611865589', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_34', NULL, 0, '1584611865589', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_35', NULL, 0, '1584611865589', '11700', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_36', NULL, 0, '1584611865589', '11800', '11800-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_37', NULL, 0, '1584611865589', '11800', '11800-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_38', NULL, 0, '1584611865589', '11800', '11800-003', '111', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_39', NULL, 0, '1584611865589', '11800', '11800-004', '121212', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_40', NULL, 0, '1584611865589', '11800', '11800-005', '全日制本科', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_41', NULL, 0, '1584611865589', '11800', '11800-006', '学历教育', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_42', NULL, 0, '1584611865589', '11800', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_43', NULL, 0, '1584611865589', '11900', '11900-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_44', NULL, 0, '1584611865589', '11900', '11900-002', '121', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_45', NULL, 0, '1584611865589', '11900', '11900-003', '211312', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_46', NULL, 0, '1584611865589', '11900', '11900-004', '军事观察员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_47', NULL, 0, '1584611865589', '11900', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_48', NULL, 0, '1584611865589', '12000', '12000-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_49', NULL, 0, '1584611865589', '12000', '12000-002', '博士后项目', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_50', NULL, 0, '1584611865589', '12000', '12000-003', 'のの诶诶ののの', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_51', NULL, 0, '1584611865589', '12000', '12000-004', 'ののののe', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_52', NULL, 0, '1584611865589', '12000', '12000-005', '额鹅鹅鹅', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_53', NULL, 0, '1584611865589', '12000', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_54', NULL, 0, '1584611865589', '12100', '12100-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_55', NULL, 0, '1584611865589', '12100', '12100-002', '222', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_56', NULL, 0, '1584611865589', '12100', '12100-003', '222', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_57', NULL, 0, '1584611865589', '12100', '12100-004', '东部战区', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_58', NULL, 0, '1584611865589', '12100', '12100-005', '作战任务', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_59', NULL, 0, '1584611865589', '12100', '12100-006', '三等功', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_60', NULL, 0, '1584611865589', '12100', '12100-007', 'のののののe', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_61', NULL, 0, '1584611865589', '12100', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_62', NULL, 0, '1584611865589', '12200', '12200-001', '2222', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_63', NULL, 0, '1584611865589', '12200', '12200-007', '团', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_64', NULL, 0, '1584611865589', '12200', '12200-002', '综合演练', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_65', NULL, 0, '1584611865589', '12200', '12200-003', '2020.03.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_66', NULL, 0, '1584611865589', '12200', '12200-004', '222', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_67', NULL, 0, '1584611865589', '12200', '12200-005', '嘉奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_68', NULL, 0, '1584611865589', '12200', '12200-006', '1111', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_69', NULL, 0, '1584611865589', '12200', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_70', NULL, 0, '1584611865589', '12300', '12300-001', '国家“万人计划”', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_71', NULL, 0, '1584611865589', '12300', '12300-002', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_72', NULL, 0, '1584611865589', '12300', '12300-003', '中国青年女科学家奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_73', NULL, 0, '1584611865589', '12300', '12300-004', '2020.06', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584611865589_74', NULL, 0, '1584611865589', '12300', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_161', NULL, 0, '1584612027021', '10100', '10100-001', 'siri', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_162', NULL, 0, '1584612027021', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_163', NULL, 0, '1584612027021', '10100', '10100-003', '1245', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_164', NULL, 0, '1584612027021', '10100', '10100-005', '2000.07', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_165', NULL, 0, '1584612027021', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_166', NULL, 0, '1584612027021', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_167', NULL, 0, '1584612027021', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_168', NULL, 0, '1584612027021', '10100', '10100-009', '2020.04', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_169', NULL, 0, '1584612027021', '10100', '10100-010', '硕士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_170', NULL, 0, '1584612027021', '10100', '10100-011', '2020.04', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_171', NULL, 0, '1584612027021', '10100', '10100-012', '硕士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_172', NULL, 0, '1584612027021', '10100', '10100-013', '2020.06', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_173', NULL, 0, '1584612027021', '10100', '10100-014', '对对对', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_174', NULL, 0, '1584612027021', '10100', '10100-015', 'd', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_175', NULL, 0, '1584612027021', '10100', '10100-016', '2000.07', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_176', NULL, 0, '1584612027021', '10100', '10100-017', '985', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_177', NULL, 0, '1584612027021', '10100', '10100-020', '专业技术二级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_178', NULL, 0, '1584612027021', '10100', '10100-021', '2020.06', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_179', NULL, 0, '1584612027021', '10100', '10100-022', '中将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_180', NULL, 0, '1584612027021', '10100', '10100-023', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_181', NULL, 0, '1584612027021', '10100', '10100-024', '副高职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_182', NULL, 0, '1584612027021', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_183', NULL, 0, '1584612027021', '10100', '10100-026', '33', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_184', NULL, 0, '1584612027021', '10100', '10100-018', '助理研究员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_185', NULL, 0, '1584612027021', '10100', '10100-019', '2020.10', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_186', NULL, 0, '1584612027021', '10100', '10100-028', '基础研究型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_187', NULL, 0, '1584612027021', '10100', '10100-029', '导航和制导对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_188', NULL, 0, '1584612027021', '10100', '10000', '/root/www/statics/userUploadFiles/tsMuMM47/10100/0/我是中文.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_199', NULL, 0, '1584612027021', '11900', '11900-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_200', NULL, 0, '1584612027021', '11900', '11900-002', 'h', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_201', NULL, 0, '1584612027021', '11900', '11900-003', 'h', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_202', NULL, 0, '1584612027021', '11900', '11900-004', '驻外武官', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_203', NULL, 0, '1584612027021', '11900', '10000', '/root/www/statics/userUploadFiles/tsMuMM47/11900/0/eladmin-master (3).zip', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_44', '', 0, '1584612027021', '10500', '10500-001', '1', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_45', '', 0, '1584612027021', '10500', '10500-002', '特等奖', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_46', '', 0, '1584612027021', '10500', '10500-003', '1', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_47', '', 0, '1584612027021', '10500', '10500-004', '1', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_48', '', 0, '1584612027021', '10500', '10500-005', '1', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_49', '', 0, '1584612027021', '10500', '10500-006', '有', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584612027021_50', '', 0, '1584612027021', '10500', '10500-007', '无', '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_56', NULL, 0, '1584614722793', '10100', '10100-001', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_57', NULL, 0, '1584614722793', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_58', NULL, 0, '1584614722793', '10100', '10100-003', '152824199012250817', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_59', NULL, 0, '1584614722793', '10100', '10100-005', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_60', NULL, 0, '1584614722793', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_61', NULL, 0, '1584614722793', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_62', NULL, 0, '1584614722793', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_63', NULL, 0, '1584614722793', '10100', '10100-009', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_64', NULL, 0, '1584614722793', '10100', '10100-010', '大学', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_65', NULL, 0, '1584614722793', '10100', '10100-011', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_66', NULL, 0, '1584614722793', '10100', '10100-012', '硕士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_67', NULL, 0, '1584614722793', '10100', '10100-013', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_68', NULL, 0, '1584614722793', '10100', '10100-014', '风速达到', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_69', NULL, 0, '1584614722793', '10100', '10100-015', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_70', NULL, 0, '1584614722793', '10100', '10100-016', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_71', NULL, 0, '1584614722793', '10100', '10100-017', '211', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_72', NULL, 0, '1584614722793', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_73', NULL, 0, '1584614722793', '10100', '10100-021', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_74', NULL, 0, '1584614722793', '10100', '10100-022', '中将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_75', NULL, 0, '1584614722793', '10100', '10100-023', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_76', NULL, 0, '1584614722793', '10100', '10100-024', '中职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_77', NULL, 0, '1584614722793', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_78', NULL, 0, '1584614722793', '10100', '10100-026', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_79', NULL, 0, '1584614722793', '10100', '10100-018', '副教授', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_80', NULL, 0, '1584614722793', '10100', '10100-019', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_81', NULL, 0, '1584614722793', '10100', '10100-028', '教学科研型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_82', NULL, 0, '1584614722793', '10100', '10100-029', '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584614722793_83', NULL, 0, '1584614722793', '10100', '10000', '/root/www/statics/userUploadFiles/sxD8lF9l/10100/0/admin.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_100', NULL, 0, '1584616356692', '11900', '11900-004', '军事观察员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_101', NULL, 0, '1584616356692', '11900', '10000', '/root/www/statics/userUploadFiles/Jt9TcU4h/11900/0/专利信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_138', NULL, 0, '1584616356692', '12000', '12000-001', '2020.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_139', NULL, 0, '1584616356692', '12000', '12000-002', '访问学者', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_140', NULL, 0, '1584616356692', '12000', '12000-003', '他让他让他 ', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_141', NULL, 0, '1584616356692', '12000', '12000-004', '打发打发', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_142', NULL, 0, '1584616356692', '12000', '12000-005', '的辅导辅导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_143', NULL, 0, '1584616356692', '12000', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_144', NULL, 1, '1584616356692', '12000', '12000-001', '2020.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_145', NULL, 1, '1584616356692', '12000', '12000-002', '访问学者', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_146', NULL, 1, '1584616356692', '12000', '12000-003', '冠福股份', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_147', NULL, 1, '1584616356692', '12000', '12000-004', '非官方个', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_148', NULL, 1, '1584616356692', '12000', '12000-005', '非官方个', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_149', NULL, 1, '1584616356692', '12000', '10000', '/root/www/statics/userUploadFiles/Jt9TcU4h/12000/1/担任收录期刊审稿职务信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_150', NULL, 0, '1584616356692', '11700', '11700-001', '2020.03-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_151', NULL, 0, '1584616356692', '11700', '11700-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_152', NULL, 0, '1584616356692', '11700', '11700-003', '打发打发', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_153', NULL, 0, '1584616356692', '11700', '11700-004', '打发打发', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_154', NULL, 0, '1584616356692', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_155', NULL, 0, '1584616356692', '11700', '11700-006', '嘉奖', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_156', NULL, 0, '1584616356692', '11700', '10000', '', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_57', NULL, 0, '1584616356692', '10100', '10100-001', '杨盼盼', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_58', NULL, 0, '1584616356692', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_59', NULL, 0, '1584616356692', '10100', '10100-003', '610582199001012027', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_60', NULL, 0, '1584616356692', '10100', '10100-005', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_61', NULL, 0, '1584616356692', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_62', NULL, 0, '1584616356692', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_63', NULL, 0, '1584616356692', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_64', NULL, 0, '1584616356692', '10100', '10100-009', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_65', NULL, 0, '1584616356692', '10100', '10100-010', '大学', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_66', NULL, 0, '1584616356692', '10100', '10100-011', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_67', NULL, 0, '1584616356692', '10100', '10100-012', '学士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_68', NULL, 0, '1584616356692', '10100', '10100-013', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_69', NULL, 0, '1584616356692', '10100', '10100-014', '发的发生的', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_70', NULL, 0, '1584616356692', '10100', '10100-015', '的发送到发送到', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_71', NULL, 0, '1584616356692', '10100', '10100-016', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_72', NULL, 0, '1584616356692', '10100', '10100-017', '211', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_73', NULL, 0, '1584616356692', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_74', NULL, 0, '1584616356692', '10100', '10100-021', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_75', NULL, 0, '1584616356692', '10100', '10100-022', '上将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_76', NULL, 0, '1584616356692', '10100', '10100-023', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_77', NULL, 0, '1584616356692', '10100', '10100-024', '中职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_78', NULL, 0, '1584616356692', '10100', '10100-025', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_79', NULL, 0, '1584616356692', '10100', '10100-026', '大幅度', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_80', NULL, 0, '1584616356692', '10100', '10100-018', '助理研究员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_81', NULL, 0, '1584616356692', '10100', '10100-019', '2020.09', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_82', NULL, 0, '1584616356692', '10100', '10100-028', '基础研究型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_83', NULL, 0, '1584616356692', '10100', '10100-029', '网电作战目标侦查系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_84', NULL, 0, '1584616356692', '10100', '10000', '/root/www/statics/userUploadFiles/Jt9TcU4h/10100/0/科研项目信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_97', NULL, 0, '1584616356692', '11900', '11900-001', '2020.02-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_98', NULL, 0, '1584616356692', '11900', '11900-002', '如果梵蒂冈梵蒂冈', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584616356692_99', NULL, 0, '1584616356692', '11900', '11900-003', '分隔符股份', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_28', NULL, 0, '1584667188642', '10100', '10100-001', '法撒旦2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_29', NULL, 0, '1584667188642', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_30', NULL, 0, '1584667188642', '10100', '10100-003', '152824199012250817', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_31', NULL, 0, '1584667188642', '10100', '10100-005', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_32', NULL, 0, '1584667188642', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_33', NULL, 0, '1584667188642', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_34', NULL, 0, '1584667188642', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_35', NULL, 0, '1584667188642', '10100', '10100-009', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_36', NULL, 0, '1584667188642', '10100', '10100-010', '大学', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_37', NULL, 0, '1584667188642', '10100', '10100-011', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_38', NULL, 0, '1584667188642', '10100', '10100-012', '硕士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_39', NULL, 0, '1584667188642', '10100', '10100-013', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_40', NULL, 0, '1584667188642', '10100', '10100-014', '风速达到', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_41', NULL, 0, '1584667188642', '10100', '10100-015', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_42', NULL, 0, '1584667188642', '10100', '10100-016', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_43', NULL, 0, '1584667188642', '10100', '10100-017', '211', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_44', NULL, 0, '1584667188642', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_45', NULL, 0, '1584667188642', '10100', '10100-021', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_46', NULL, 0, '1584667188642', '10100', '10100-022', '中将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_47', NULL, 0, '1584667188642', '10100', '10100-023', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_48', NULL, 0, '1584667188642', '10100', '10100-024', '中职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_49', NULL, 0, '1584667188642', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_50', NULL, 0, '1584667188642', '10100', '10100-026', '法撒旦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_51', NULL, 0, '1584667188642', '10100', '10100-018', '副教授', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_52', NULL, 0, '1584667188642', '10100', '10100-019', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_53', NULL, 0, '1584667188642', '10100', '10100-028', '教学科研型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_54', NULL, 0, '1584667188642', '10100', '10100-029', '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667188642_55', NULL, 0, '1584667188642', '10100', '10000', '/root/www/statics/userUploadFiles/4n9cdaPb/10100/0/code码.txt', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_100', NULL, 0, '1584667319071', '10100', '10100-017', '985', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_101', NULL, 0, '1584667319071', '10100', '10100-020', '专业技术一级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_102', NULL, 0, '1584667319071', '10100', '10100-021', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_103', NULL, 0, '1584667319071', '10100', '10100-022', '中将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_104', NULL, 0, '1584667319071', '10100', '10100-023', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_105', NULL, 0, '1584667319071', '10100', '10100-024', '中职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_106', NULL, 0, '1584667319071', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_107', NULL, 0, '1584667319071', '10100', '10100-026', '发送到发送', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_108', NULL, 0, '1584667319071', '10100', '10100-018', '副研究员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_109', NULL, 0, '1584667319071', '10100', '10100-019', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_110', NULL, 0, '1584667319071', '10100', '10100-028', '技术创新应用研究型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_111', NULL, 0, '1584667319071', '10100', '10100-029', '导航和制导对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_112', NULL, 0, '1584667319071', '10100', '10000', '/root/www/statics/userUploadFiles/wZZltOyr/10100/0/个人基本信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_85', NULL, 0, '1584667319071', '10100', '10100-001', '范德萨', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_86', NULL, 0, '1584667319071', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_87', NULL, 0, '1584667319071', '10100', '10100-003', '1552465456465133156', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_88', NULL, 0, '1584667319071', '10100', '10100-005', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_89', NULL, 0, '1584667319071', '10100', '10100-004', '军人干部', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_90', NULL, 0, '1584667319071', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_91', NULL, 0, '1584667319071', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_92', NULL, 0, '1584667319071', '10100', '10100-009', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_93', NULL, 0, '1584667319071', '10100', '10100-010', '硕士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_94', NULL, 0, '1584667319071', '10100', '10100-011', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_95', NULL, 0, '1584667319071', '10100', '10100-012', '学士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_96', NULL, 0, '1584667319071', '10100', '10100-013', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_97', NULL, 0, '1584667319071', '10100', '10100-014', '发范德萨', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_98', NULL, 0, '1584667319071', '10100', '10100-015', '发送到发送', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667319071_99', NULL, 0, '1584667319071', '10100', '10100-016', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_169', NULL, 0, '1584667595338', '10100', '10100-001', '防守打法', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_170', NULL, 0, '1584667595338', '10100', '10100-002', '女', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_171', NULL, 0, '1584667595338', '10100', '10100-003', '1528241999005045012', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_172', NULL, 0, '1584667595338', '10100', '10100-005', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_173', NULL, 0, '1584667595338', '10100', '10100-004', '文职人员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_174', NULL, 0, '1584667595338', '10100', '10100-006', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_175', NULL, 0, '1584667595338', '10100', '10100-008', '群众', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_176', NULL, 0, '1584667595338', '10100', '10100-009', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_177', NULL, 0, '1584667595338', '10100', '10100-010', '硕士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_178', NULL, 0, '1584667595338', '10100', '10100-011', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_179', NULL, 0, '1584667595338', '10100', '10100-012', '学士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_180', NULL, 0, '1584667595338', '10100', '10100-013', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_181', NULL, 0, '1584667595338', '10100', '10100-014', 'f发送端的', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_182', NULL, 0, '1584667595338', '10100', '10100-015', '防守打法撒', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_183', NULL, 0, '1584667595338', '10100', '10100-016', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_184', NULL, 0, '1584667595338', '10100', '10100-017', '普通全日制', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_185', NULL, 0, '1584667595338', '10100', '10100-020', '专业技术二级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_186', NULL, 0, '1584667595338', '10100', '10100-021', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_187', NULL, 0, '1584667595338', '10100', '10100-022', '上将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_188', NULL, 0, '1584667595338', '10100', '10100-023', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_189', NULL, 0, '1584667595338', '10100', '10100-024', '初职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_190', NULL, 0, '1584667595338', '10100', '10100-025', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_191', NULL, 0, '1584667595338', '10100', '10100-026', '收到收到 的', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_192', NULL, 0, '1584667595338', '10100', '10100-018', '讲师', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_193', NULL, 0, '1584667595338', '10100', '10100-019', '2020.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_194', NULL, 0, '1584667595338', '10100', '10100-028', '教学科研型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_195', NULL, 0, '1584667595338', '10100', '10100-029', '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667595338_196', NULL, 0, '1584667595338', '10100', '10000', '/root/www/statics/userUploadFiles/r4JGAkqJ/10100/0/数据说明.doc', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_100', NULL, 0, '1584667602179', '10100', '10100-002', '男', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_101', NULL, 0, '1584667602179', '10100', '10100-003', '610582199001012027', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_102', NULL, 0, '1584667602179', '10100', '10100-005', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_103', NULL, 0, '1584667602179', '10100', '10100-004', '文职人员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_104', NULL, 0, '1584667602179', '10100', '10100-006', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_105', NULL, 0, '1584667602179', '10100', '10100-008', '党员', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_106', NULL, 0, '1584667602179', '10100', '10100-009', '2020.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_107', NULL, 0, '1584667602179', '10100', '10100-010', '硕士研究生', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_108', NULL, 0, '1584667602179', '10100', '10100-011', '2019.02', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_109', NULL, 0, '1584667602179', '10100', '10100-012', '学士', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_110', NULL, 0, '1584667602179', '10100', '10100-013', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_111', NULL, 0, '1584667602179', '10100', '10100-014', '发的发的顺丰', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_112', NULL, 0, '1584667602179', '10100', '10100-015', '大幅度发多少', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_113', NULL, 0, '1584667602179', '10100', '10100-016', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_114', NULL, 0, '1584667602179', '10100', '10100-017', '211', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_115', NULL, 0, '1584667602179', '10100', '10100-020', '专业技术二级', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_116', NULL, 0, '1584667602179', '10100', '10100-021', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_117', NULL, 0, '1584667602179', '10100', '10100-022', '中将', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_118', NULL, 0, '1584667602179', '10100', '10100-023', '2017.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_119', NULL, 0, '1584667602179', '10100', '10100-024', '中职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_120', NULL, 0, '1584667602179', '10100', '10100-025', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_121', NULL, 0, '1584667602179', '10100', '10100-026', '大幅度发多少', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_122', NULL, 0, '1584667602179', '10100', '10100-018', '副教授', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_123', NULL, 0, '1584667602179', '10100', '10100-019', '2019.01', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_124', NULL, 0, '1584667602179', '10100', '10100-028', '教学为主型', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_125', NULL, 0, '1584667602179', '10100', '10100-029', '预警探测对抗系', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_126', NULL, 0, '1584667602179', '10100', '10000', '/root/www/statics/userUploadFiles/5GKou3Xx/10100/0/科技奖励信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_29', NULL, 0, '1584667602179', '11700', '11700-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_30', NULL, 0, '1584667602179', '11700', '11700-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_31', NULL, 0, '1584667602179', '11700', '11700-003', '发的发的', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_32', NULL, 0, '1584667602179', '11700', '11700-004', ' 大幅度', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_33', NULL, 0, '1584667602179', '11700', '11700-005', '任职', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_34', NULL, 0, '1584667602179', '11700', '11700-006', '先进个人', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_35', NULL, 0, '1584667602179', '11700', '10000', '/root/www/statics/userUploadFiles/5GKou3Xx/11700/0/参加学术团体信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_36', NULL, 0, '1584667602179', '11800', '11800-001', '2020.01-至今', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_37', NULL, 0, '1584667602179', '11800', '11800-002', '海军', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_38', NULL, 0, '1584667602179', '11800', '11800-003', '打发打发', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_39', NULL, 0, '1584667602179', '11800', '11800-004', '的辅导辅导', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_40', NULL, 0, '1584667602179', '11800', '11800-005', '全日制本科', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_41', NULL, 0, '1584667602179', '11800', '11800-006', '学历教育', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_42', NULL, 0, '1584667602179', '11800', '10000', '/root/www/statics/userUploadFiles/5GKou3Xx/11800/0/科技奖励信息.xlsx', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_standard_record_item` VALUES ('1584667602179_99', NULL, 0, '1584667602179', '10100', '10100-001', '杨盼', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for qa_user_info
-- ----------------------------
DROP TABLE IF EXISTS `qa_user_info`;
CREATE TABLE `qa_user_info`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` bigint(1) NULL DEFAULT NULL COMMENT '性别 0：女  1：男',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `personnel_category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '人员类别\r\n\r\n军人干部\r\n文职人员',
  `birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `enlistment_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '入伍时间（军人干部）',
  `enter_employment_time` datetime(0) NULL DEFAULT NULL COMMENT '参加工作时间（文职人员）',
  `political_affiliation` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌\r\n\r\n党员',
  `caucus_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '党团时间',
  `last_education` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学历\r\n\r\n大学\r\n硕士研究生\r\n博士研究生\r\n',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业（最高学历）',
  `highest_degree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学位\r\n\r\n学士\r\n硕士\r\n博士\r\n',
  `highest_degree_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '学位时间',
  `last_school_major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后毕业学校',
  `graduate_time` datetime(0) NULL DEFAULT NULL COMMENT '毕业时间',
  `highest_school_major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最高学位授予学校、专业',
  `highest_get_time` datetime(0) NULL DEFAULT NULL COMMENT '最高学位授予时间',
  `school_category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院校类别 \r\n\r\n211\r\n985\r\n普通全日制\r\n军内院校\r\n',
  `technology_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现任专业技术职务\r\n\r\n助教\r\n讲师\r\n副教授\r\n教授\r\n研究实习员\r\n助理研究员\r\n副研究员\r\n研究员\r\n实验员\r\n助理实验师\r\n实验师\r\n高级实验师\r\n正高级实验师\r\n助理工程师\r\n技术员\r\n工程师\r\n高级工程师\r\n正高级工程师\r\n会计员\r\n助理会计师\r\n会计师\r\n高级会计师\r\n正高级会计师\r\n助理编辑\r\n编辑\r\n副编审\r\n编审\r\n管理员\r\n助理馆员\r\n馆员\r\n副研究馆员\r\n研究馆员\r\n',
  `appoint_time` datetime(0) NULL DEFAULT NULL COMMENT '聘任时间（现专业技术职务时间）',
  `appoint_annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '聘任相关附件地址',
  `technology_level` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现技术等级\r\n\r\n专业技术一级\r\n专业技术二级\r\n专业技术三级\r\n专业技术四级\r\n专业技术五级\r\n专业技术六级\r\n专业技术七级\r\n专业技术八级\r\n专业技术九级\r\n专业技术十级\r\n专业技术十一级\r\n专业技术十二级\r\n专业技术十三级\r\n',
  `technology_level_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '现技术等级时间',
  `military_rank` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现军衔/级别\r\n\r\n大校\r\n上将\r\n中将\r\n少将\r\n上校\r\n中校\r\n少校\r\n上尉\r\n中尉\r\n少尉\r\n转改特级\r\n转改1级\r\n转改2级\r\n转改3级\r\n转改4级\r\n转改5级\r\n转改6级\r\n转改7级\r\n转改8级\r\n特级\r\n1级\r\n2级\r\n3级\r\n4级\r\n……\r\n25级\r\n',
  `military_rank_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '现军衔/级别时间',
  `technology_category` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现技职类别\r\n\r\n初职\r\n中职\r\n副高职\r\n正高职\r\n',
  `technology_category_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '现技职类别时间',
  `official_rank` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '现部级别\r\n\r\n训练管理系助教',
  `academic_title` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请职称，存储下拉框的选择值',
  `break_rule` bigint(1) NULL DEFAULT NULL COMMENT '是否破格 0：否  1：是',
  `break_rule_annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '破格相关附件地址',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作部门',
  `get_qualifications_time` datetime(0) NULL DEFAULT NULL COMMENT '获得资格时间',
  `get_qualifications_annex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '获得资格相关附件地址',
  `tech_num` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
  `attr0` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr6` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr7` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr8` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `attr9` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '申请用户基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qa_user_info
-- ----------------------------
INSERT INTO `qa_user_info` VALUES ('0MCzxPpA', '2020-03-13 15:49:16', '2020-03-13 15:49:16', '15529396626', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('0o5Cmrta', '2020-03-13 14:24:21', '2020-03-13 14:24:21', '15009282659', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('1', '2020-02-27 14:48:36', '2020-02-27 18:53:25', '13121655144', '测试', 1, '411081199507177890', '军人干部', '2020-02-03 00:00:00', '2020-02-27 18:44:09', '2020-01-27 00:00:00', '团员', '2020-02-27 18:44:09', '硕士研究生', NULL, '硕士', '2020-02-27 18:44:09', '最后毕业院校', NULL, '最高学位专业', '2020-02-18 00:00:00', '', '', '2020-01-27 00:00:00', NULL, '', '2020-02-27 18:44:09', '', '2020-02-27 18:44:09', '', '2020-02-27 18:44:09', '', '正高', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('2co0zSVC', '2020-03-19 11:52:42', '2020-03-19 11:52:42', '13322222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('4n9cdaPb', '2020-03-20 09:15:56', '2020-03-20 09:15:56', '15523456789', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('5a5lHOJT', '2020-03-03 10:19:26', '2020-03-03 10:19:26', '15891485658', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('5GKou3Xx', '2020-03-20 09:24:32', '2020-03-20 09:24:32', '13333333334', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('6M29wmD9', '2020-03-13 10:31:20', '2020-03-13 10:31:20', '13344444444', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('7FfCFppB', '2020-03-13 16:50:36', '2020-03-13 16:50:36', '15891485658', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('8OGORnPI', '2020-03-05 17:52:24', '2020-03-05 17:52:24', '13402970419', '朱哲', NULL, NULL, NULL, NULL, '2020-03-13 10:04:38', NULL, NULL, '2020-03-13 10:04:38', NULL, NULL, NULL, '2020-03-13 10:04:38', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2020-03-13 10:04:38', NULL, '2020-03-13 10:04:38', NULL, '2020-03-13 10:04:38', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '110022200', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('9pHMjGI4', '2020-03-16 09:45:36', '2020-03-16 09:45:36', '18829013486', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('Al66Y75j', '2020-03-19 11:21:57', '2020-03-19 11:21:57', '15902954423', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('b04702c1-ebbc-438b-b11d-730c261a9d7a', NULL, '2020-02-21 11:56:32', '', '非官方个', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, '', '', '', NULL, '', NULL, '', NULL, NULL, 'aqaqa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('cbiZGSkm', '2020-03-12 09:59:28', '2020-03-12 09:59:28', '13345678901', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('D4uIu7zP', '2020-03-13 09:51:14', '2020-03-13 09:51:14', '13689193133', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('DbzrH0sc', '2020-03-20 09:21:21', '2020-03-20 09:21:21', '18629687994', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('DUxDJCAi', '2020-03-20 09:33:19', '2020-03-20 09:33:19', '9001912345', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('F01oLnxP', '2020-03-11 13:21:55', '2020-03-11 13:21:55', '17736453673', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('fC0A4GRD', '2020-02-27 11:31:13', '2020-02-27 11:31:13', '18629687995', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'YANGWW', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('FGFWoOtO', '2020-03-13 14:05:31', '2020-03-13 14:05:31', '13333333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('FIsYzPTf', '2020-03-11 14:28:22', '2020-03-11 14:28:22', '13324597128', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('g6CPYl8S', '2020-03-12 13:23:14', '2020-03-12 13:23:14', '13311111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('GJzl3yLJ', '2020-03-20 09:22:39', '2020-03-20 09:22:39', '18629647483', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('GlB0zYD3', '2020-03-13 09:31:33', '2020-03-13 09:31:33', '18729320050', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('Gw80K9f9', '2020-03-13 09:44:19', '2020-03-13 09:44:19', '18691836060', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('H7uMs5OW', '2020-02-27 14:48:36', '2020-02-27 18:28:04', '13121655144', '测试', 1, '411081199507177890', '军人干部', '2020-02-03 00:00:00', '2020-02-27 18:44:09', '2020-01-27 00:00:00', '团员', '2020-02-27 18:44:09', '硕士研究生', NULL, '硕士', '2020-02-27 18:44:09', '最后毕业院校', NULL, '最高学位专业', '2020-02-18 00:00:00', '', '', NULL, NULL, '', '2020-02-27 18:44:09', '', '2020-02-27 18:44:09', '', '2020-02-27 18:44:09', '', '正高', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('HRitmsHb', '2020-03-12 13:41:18', '2020-03-12 13:41:18', '13474663426', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('jNjGJNG0', '2020-03-19 14:46:27', '2020-03-19 14:46:27', '13344444444', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('Jt9TcU4h', '2020-03-19 19:01:57', '2020-03-19 19:01:57', '13402941409', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('kdJCM6CW', '2020-03-19 18:46:51', '2020-03-19 18:46:51', '18629687995', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('keMKRcnc', '2020-03-19 15:34:52', '2020-03-19 15:34:52', '18181818181', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('KGe0WNhd', '2020-03-12 10:45:55', '2020-03-12 10:45:55', '13312345678', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('L1hrunAC', '2020-03-16 09:58:05', '2020-03-16 09:58:05', '13402970419', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('lYq8Oqcf', '2020-03-19 14:27:08', '2020-03-19 14:27:08', '13333333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('NCwp2GLQ', '2020-03-13 15:38:40', '2020-03-13 15:38:40', '13311111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('NnqPMOn9', '2020-03-13 09:09:15', '2020-03-13 09:09:15', '13333333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('o9RJ34Zu', '2020-03-19 17:07:00', '2020-03-19 17:07:00', '13366666666', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('r1TMuiME', '2020-03-13 09:34:54', '2020-03-13 09:34:54', '13173616208', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('r4JGAkqJ', '2020-03-20 09:24:46', '2020-03-20 09:24:46', '15511111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('SCFI87zs', '2020-03-11 13:33:49', '2020-03-11 13:33:49', '13402941409', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('sxD8lF9l', '2020-03-19 18:44:58', '2020-03-19 18:44:58', '15512345678', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('TMPZOd3t', '2020-03-11 14:28:18', '2020-03-11 14:28:18', '15902954423', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('tsMuMM47', '2020-03-13 16:16:35', '2020-03-13 16:16:35', '15009282659', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('urcB3iLZ', '2020-03-19 15:15:33', '2020-03-19 15:15:33', '13355555555', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('WbMBmYik', '2020-03-13 16:52:26', '2020-03-13 16:52:26', '13121655144', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('Wpsy2CDf', '2020-03-12 13:39:05', '2020-03-12 13:39:05', '13322222222', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('wZZltOyr', '2020-03-20 09:20:26', '2020-03-20 09:20:26', '15545678912', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('XZofQdDM', '2020-03-12 13:40:24', '2020-03-12 13:40:24', '13474663425', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('yg5aibSQ', '2020-03-19 15:33:26', '2020-03-19 15:33:26', '17171717171', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('ZcqwPAoW', '2020-03-11 16:40:10', '2020-03-11 16:40:10', '18745464457', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('ZmmaBhJV', '2020-03-11 14:53:16', '2020-03-11 14:53:16', '18829013486', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `qa_user_info` VALUES ('ZUmgLpoK', '2020-03-13 11:45:14', '2020-03-13 11:45:14', '13344444444', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门（负责业务）',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `is_enabled` bit(1) NULL DEFAULT b'1' COMMENT '是否可用',
  `is_locked` bit(1) NULL DEFAULT b'0' COMMENT '是否锁定',
  `locked_date` datetime(0) NULL DEFAULT NULL COMMENT '锁定日期',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '登录日期',
  `login_failure_count` int(11) NULL DEFAULT 0 COMMENT '登录失败次数',
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  `roles` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '角色项',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('0MCzxPpA', '2020-03-13 15:49:16', '2020-03-13 15:49:16', NULL, '15529396626', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '张三', '3c928bf98162167d4d7d8c1f52ef98d0', '15529396626', NULL, ',9000,');
INSERT INTO `sys_admin` VALUES ('1', '2019-11-29 15:06:18', '2019-11-29 15:06:18', '顶级管理员', NULL, NULL, b'1', b'0', NULL, NULL, NULL, NULL, '管理员', '0564607e6132b89a9e63d8d6135eef10', 'admin', '顶级管理', ',9000,');
INSERT INTO `sys_admin` VALUES ('2co0zSVC', '2020-03-19 11:52:42', '2020-03-19 11:52:42', NULL, '13322222222', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '测试员2', '3c928bf98162167d4d7d8c1f52ef98d0', '13322222222', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('4n9cdaPb', '2020-03-20 09:15:56', '2020-03-20 09:15:56', NULL, '15523456789', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '王五', '3c928bf98162167d4d7d8c1f52ef98d0', '15523456789', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('5GKou3Xx', '2020-03-20 09:24:32', '2020-03-20 09:24:32', NULL, '13333333334', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '哈哈哈', '3c928bf98162167d4d7d8c1f52ef98d0', '13333333334', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('7FfCFppB', '2020-03-13 16:50:36', '2020-03-13 16:50:36', NULL, '15891485658', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '诺维', '602503da0c6305f058444397efa50e39', '15891485658', NULL, ',9000,');
INSERT INTO `sys_admin` VALUES ('9pHMjGI4', '2020-03-16 09:45:36', '2020-03-16 09:45:36', NULL, '18829013486', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'zhenzhen', '211cbcdda29e9ae0afdfb6659770bfd8', '18829013486', NULL, ',1,');
INSERT INTO `sys_admin` VALUES ('Al66Y75j', '2020-03-19 11:21:57', '2020-03-19 11:21:57', NULL, '15902954423', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '玄易', '84d56d3ccae208a07ad6ddadac193a73', '15902954423', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('DbzrH0sc', '2020-03-20 09:21:21', '2020-03-20 09:21:21', NULL, '18629687994', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'fasf', '0564607e6132b89a9e63d8d6135eef10', '18629687994', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('DUxDJCAi', '2020-03-20 09:33:19', '2020-03-20 09:33:19', NULL, '9001912345', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '1234', '0564607e6132b89a9e63d8d6135eef10', '9001912345', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('GJzl3yLJ', '2020-03-20 09:22:39', '2020-03-20 09:22:39', NULL, '18629647483', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'fdsafdsf', '0564607e6132b89a9e63d8d6135eef10', '18629647483', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('jNjGJNG0', '2020-03-19 14:46:27', '2020-03-19 14:46:27', NULL, '13344444444', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '测试员4', '3c928bf98162167d4d7d8c1f52ef98d0', '13344444444', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('Jt9TcU4h', '2020-03-19 19:01:57', '2020-03-19 19:01:57', NULL, '13402941409', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '杨盼盼', '3c928bf98162167d4d7d8c1f52ef98d0', '13402941409', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('kdJCM6CW', '2020-03-19 18:46:51', '2020-03-19 18:46:51', NULL, '18629687995', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'testsfdasffdsafdafdaf', '0564607e6132b89a9e63d8d6135eef10', '18629687995', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('keMKRcnc', '2020-03-19 15:34:52', '2020-03-19 15:34:52', NULL, '18181818181', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'qaz', '19db21514bf3de62bc12d5838eff386b', '18181818181', NULL, ',9000,');
INSERT INTO `sys_admin` VALUES ('L1hrunAC', '2020-03-16 09:58:05', '2020-03-16 09:58:05', NULL, '13402970419', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '朱哲', '3c928bf98162167d4d7d8c1f52ef98d0', '13402970419', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('lYq8Oqcf', '2020-03-19 14:27:08', '2020-03-19 14:27:08', NULL, '13333333333', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '测试员3', '3c928bf98162167d4d7d8c1f52ef98d0', '13333333333', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('NCwp2GLQ', '2020-03-13 15:38:40', '2020-03-13 15:38:40', NULL, '13311111111', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '测试员1', '3c928bf98162167d4d7d8c1f52ef98d0', '13311111111', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('o9RJ34Zu', '2020-03-19 17:07:00', '2020-03-19 17:07:00', NULL, '13366666666', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '测试员6', '3c928bf98162167d4d7d8c1f52ef98d0', '13366666666', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('r4JGAkqJ', '2020-03-20 09:24:46', '2020-03-20 09:24:46', NULL, '15511111111', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '刘泽', '3c928bf98162167d4d7d8c1f52ef98d0', '15511111111', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('sxD8lF9l', '2020-03-19 18:44:58', '2020-03-19 18:44:58', NULL, '15512345678', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '李四', '3c928bf98162167d4d7d8c1f52ef98d0', '15512345678', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('tsMuMM47', '2020-03-13 16:16:35', '2020-03-13 16:16:35', NULL, '15009282659', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '宋泽堃', '0564607e6132b89a9e63d8d6135eef10', '15009282659', NULL, ',9000,');
INSERT INTO `sys_admin` VALUES ('urcB3iLZ', '2020-03-19 15:15:33', '2020-03-19 15:15:33', NULL, '13355555555', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '测试员5', '3c928bf98162167d4d7d8c1f52ef98d0', '13355555555', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('WbMBmYik', '2020-03-13 16:52:26', '2020-03-13 16:52:26', NULL, '13121655144', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'liu', 'a1d83431229cbda302fb33f24276c446', '13121655144', NULL, ',9000,');
INSERT INTO `sys_admin` VALUES ('wZZltOyr', '2020-03-20 09:20:26', '2020-03-20 09:20:26', NULL, '15545678912', NULL, b'1', b'0', NULL, NULL, NULL, NULL, '马六', '0441ca88897ed41d108e05416cb7a8cb', '15545678912', NULL, ',0,');
INSERT INTO `sys_admin` VALUES ('yg5aibSQ', '2020-03-19 15:33:26', '2020-03-19 15:33:26', NULL, '17171717171', NULL, b'1', b'0', NULL, NULL, NULL, NULL, 'hahah', '3c928bf98162167d4d7d8c1f52ef98d0', '17171717171', NULL, ',0,');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role`  (
  `admin_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主键id',
  `roles_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `type` int(11) NOT NULL COMMENT '类型\r\n0: admin 1: user',
  INDEX `roles_id_foreign`(`roles_id`) USING BTREE,
  INDEX `partner_id_index`(`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES ('1', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('fC0A4GRD', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('LBaSUAFD', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('H7uMs5OW', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('8OGORnPI', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('5a5lHOJT', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('F01oLnxP', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('SCFI87zs', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('TMPZOd3t', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('FIsYzPTf', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('ZmmaBhJV', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('ZcqwPAoW', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('cbiZGSkm', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('KGe0WNhd', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('g6CPYl8S', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('Wpsy2CDf', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('XZofQdDM', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('HRitmsHb', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('NnqPMOn9', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('GlB0zYD3', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('r1TMuiME', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('Gw80K9f9', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('D4uIu7zP', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('6M29wmD9', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('ZUmgLpoK', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('FGFWoOtO', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('0o5Cmrta', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('NCwp2GLQ', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('7FfCFppB', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('WbMBmYik', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('L1hrunAC', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('9pHMjGI4', '1', 0);
INSERT INTO `sys_admin_role` VALUES ('Al66Y75j', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('2co0zSVC', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('lYq8Oqcf', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('jNjGJNG0', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('urcB3iLZ', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('tsMuMM47', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('yg5aibSQ', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('0MCzxPpA', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('o9RJ34Zu', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('keMKRcnc', '9000', 0);
INSERT INTO `sys_admin_role` VALUES ('sxD8lF9l', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('kdJCM6CW', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('Jt9TcU4h', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('4n9cdaPb', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('wZZltOyr', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('DbzrH0sc', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('GJzl3yLJ', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('5GKou3Xx', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('r4JGAkqJ', '0', 0);
INSERT INTO `sys_admin_role` VALUES ('DUxDJCAi', '0', 0);

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `authority` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限值',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `type` int(11) NOT NULL COMMENT '类型\r\n\r\n1: 移动端 \r\n0: 后台管理',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限使用url',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'HTTP 访问method （GET POST PUT DELETE）',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标（显示使用）',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态 1 启用 0 禁用',
  `orders` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级id',
  `tree_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '树路径',
  `grade` int(11) NULL DEFAULT NULL COMMENT '层级',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接(点击跳转)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('100', '信息录入', 'ROLE_BASE_INFO', '', 0, '/admin/**', 'ALL', NULL, b'1', 0, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('110', '资料录入', 'ROLE_INFO_WRITE', '', 0, '/admin/qaApplyRecord/**,/admin/qaUserInfo/**,/admin/qaClause/**', 'ALL', NULL, b'0', 10, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('120', '个人首页', 'ROLE_INFO_CENTER', '', 0, '/admin/qaApplyRecord/**,/admin/qaApplyLog/**,/admin/qaNotice/**,/admin/qaClause/**', 'ALL', NULL, b'1', 20, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('130', '人员信息', 'ROLE_INFO_VIEW', '', 0, '/admin/qaApplyRecord/**,/admin/qaUserInfo/**', 'ALL', NULL, b'1', 30, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('210', '数据总览', 'ROLE_DATA_CENTER', '', 0, '/admin/qaCategory/**,/admin/qaApplyLog/**,/admin/qaNotice/**,/admin/qaClause/**', 'ALL', NULL, b'1', 40, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('211', '人员职称表', 'ROLE_INFO_LIST', ' ', 0, '/admin/qaCategory/**,/admin/qaUserInfo/**', 'ALL', NULL, b'1', 50, ',', NULL, 0, NULL);
INSERT INTO `sys_authority` VALUES ('220', '已审核人员', 'ROLE_CHECKED', '', 0, '/admin/qaApplyRecord/**,/admin/qaClause/**', 'ALL', NULL, b'1', 60, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('230', '待审核人员', 'ROLE_CHECKING', '', 0, '/admin/qaApplyRecord/**,/admin/qaClause/**', 'ALL', NULL, b'1', 70, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('310', '系统设置', 'ROLE_SETTING', '', 0, '/admin/setting/**', 'ALL', NULL, b'1', 100, NULL, ',', 0, NULL);
INSERT INTO `sys_authority` VALUES ('410', '权限设置', 'ROLE_ACCOUNT_MANAGER', '', 0, '/admin/account/**', 'ALL', NULL, b'1', 110, NULL, ',', 0, NULL);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `status` bit(1) NULL DEFAULT NULL COMMENT '状态\r\n0 禁用\r\n1 启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES ('HUA_WEI_OBS', '华为obs', '华为obs', NULL, b'1');
INSERT INTO `sys_config` VALUES ('SMS_API_KEY', '短信发送api-key', '短信发送api-key', '4138e5bf2cdae7f2d9cbbb392b8fa477', b'1');
INSERT INTO `sys_config` VALUES ('SMS_MOBILE_CAPTCHA_REGISTER', '手机号验证码注册', '手机号验证码注册配置', '【北斗智慧行】您的验证码是#code。如非本人操作，请忽略本短信', b'1');
INSERT INTO `sys_config` VALUES ('SMS_MOBILE_RESET_PASSWORD', '密码重置', NULL, '【北斗智慧行】您的密码已重置为#code，请牢记。', b'0');

-- ----------------------------
-- Table structure for sys_config_attributes
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_attributes`;
CREATE TABLE `sys_config_attributes`  (
  `config_id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config_attributes
-- ----------------------------
INSERT INTO `sys_config_attributes` VALUES ('HUA_WEI_OBS', 'accessKey', 'CJB1CAKCXJPQNAVRLMXI');
INSERT INTO `sys_config_attributes` VALUES ('HUA_WEI_OBS', 'securityKey', '5Ni7nVMybVuNyrQuBo6Yhe1rTZ9NQKka3pAQKxFE');
INSERT INTO `sys_config_attributes` VALUES ('HUA_WEI_OBS', 'endPoint', 'https://obs.myhuaweicloud.com');
INSERT INTO `sys_config_attributes` VALUES ('HUA_WEI_OBS', 'OBS-2224', 'obs-2224');
INSERT INTO `sys_config_attributes` VALUES ('HUA_WEI_OBS', 'HUA_WEI_VIEW_URL_PREFIX', 'https://obs-2224.obs.cn-north-1.myhuaweicloud.com/');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `operator_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `memo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `relation_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `before_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `after_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('09c92c73-5a51-4075-a230-977aedaca409', '2020-03-19 16:02:04', NULL, NULL, '变更管理员角色', NULL, 8, '15529396626', NULL, '[{\"createDate\":1534576991000,\"description\":\"超级管理\",\"id\":\"9000\",\"isSystem\":true,\"modifyDate\":1534576994000,\"name\":\"超级管理员\",\"type\":0}]');
INSERT INTO `sys_log` VALUES ('33780d72-371e-4451-992c-33aced35f45d', '2020-03-19 17:35:39', NULL, NULL, '变更管理员角色', NULL, 8, '18181818181', NULL, '[{\"createDate\":1534576991000,\"description\":\"超级管理\",\"id\":\"9000\",\"isSystem\":true,\"modifyDate\":1534576994000,\"name\":\"超级管理员\",\"type\":0}]');
INSERT INTO `sys_log` VALUES ('3407feff-9d99-4d67-8279-927bafd48653', '2020-03-19 15:16:05', NULL, NULL, '变更管理员角色', NULL, 8, '15009282659', NULL, '[{\"createDate\":1534576991000,\"description\":\"超级管理\",\"id\":\"9000\",\"isSystem\":true,\"modifyDate\":1534576994000,\"name\":\"超级管理员\",\"type\":0}]');
INSERT INTO `sys_log` VALUES ('a6441428-b5c5-4657-9de4-a3ae6f304874', '2020-03-18 09:09:26', NULL, NULL, '变更管理员角色', NULL, 8, '18829013486', NULL, '[{\"createDate\":1534576991000,\"description\":\"超级管理\",\"id\":\"9000\",\"isSystem\":true,\"modifyDate\":1534576994000,\"name\":\"超级管理员\",\"type\":0}]');
INSERT INTO `sys_log` VALUES ('af6cd98b-cde5-41b1-8d9e-4d419446276e', '2020-03-18 09:09:56', NULL, NULL, '变更管理员角色', NULL, 8, '18829013486', NULL, '[{\"createDate\":1582204825000,\"description\":\"审核管理员\",\"id\":\"1\",\"isSystem\":true,\"modifyDate\":1582204825000,\"name\":\"审核管理员\",\"type\":0}]');
INSERT INTO `sys_log` VALUES ('d30fd20b-eef3-41a2-8ca9-7eba0191ed90', '2020-03-13 16:50:58', NULL, NULL, '变更管理员角色', NULL, 8, '15891485658', NULL, '[{\"createDate\":1534576991000,\"description\":\"超级管理\",\"id\":\"9000\",\"isSystem\":true,\"modifyDate\":1534576994000,\"name\":\"超级管理员\",\"type\":0}]');
INSERT INTO `sys_log` VALUES ('f86c78a7-bdd5-47c7-95d2-fd6240cbf292', '2020-03-13 16:52:54', NULL, NULL, '变更管理员角色', NULL, 8, '13121655144', NULL, '[{\"createDate\":1534576991000,\"description\":\"超级管理\",\"id\":\"9000\",\"isSystem\":true,\"modifyDate\":1534576994000,\"name\":\"超级管理员\",\"type\":0}]');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `create_date` datetime(0) NOT NULL COMMENT '创建日期',
  `modify_date` datetime(0) NOT NULL COMMENT '修改日期',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `is_system` bit(1) NOT NULL COMMENT '是否系统角色',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型 0：admin 1：user',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0', '2020-02-20 21:20:25', '2020-02-20 21:20:25', '默认注册用户', b'1', '普通用户', 0);
INSERT INTO `sys_role` VALUES ('1', '2020-02-20 21:20:25', '2020-02-20 21:20:25', '审核管理员', b'1', '审核管理员', 0);
INSERT INTO `sys_role` VALUES ('9000', '2018-08-18 15:23:11', '2018-08-18 15:23:14', '超级管理', b'1', '超级管理员', 0);

-- ----------------------------
-- Table structure for sys_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_authority`;
CREATE TABLE `sys_role_authority`  (
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `authority_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  INDEX `role_id_index`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_authority
-- ----------------------------
INSERT INTO `sys_role_authority` VALUES ('9000', '110');
INSERT INTO `sys_role_authority` VALUES ('9000', '120');
INSERT INTO `sys_role_authority` VALUES ('9000', '210');
INSERT INTO `sys_role_authority` VALUES ('9000', '220');
INSERT INTO `sys_role_authority` VALUES ('9000', '230');
INSERT INTO `sys_role_authority` VALUES ('9000', '310');
INSERT INTO `sys_role_authority` VALUES ('9000', '410');
INSERT INTO `sys_role_authority` VALUES ('1', '110');
INSERT INTO `sys_role_authority` VALUES ('1', '120');
INSERT INTO `sys_role_authority` VALUES ('1', '210');
INSERT INTO `sys_role_authority` VALUES ('1', '220');
INSERT INTO `sys_role_authority` VALUES ('1', '230');
INSERT INTO `sys_role_authority` VALUES ('1', '310');
INSERT INTO `sys_role_authority` VALUES ('0', '110');
INSERT INTO `sys_role_authority` VALUES ('0', '120');
INSERT INTO `sys_role_authority` VALUES ('0', '310');
INSERT INTO `sys_role_authority` VALUES ('9000', '100');
INSERT INTO `sys_role_authority` VALUES ('9000', '130');
INSERT INTO `sys_role_authority` VALUES ('9000', '211');
INSERT INTO `sys_role_authority` VALUES ('1', '100');
INSERT INTO `sys_role_authority` VALUES ('0', '100');
INSERT INTO `sys_role_authority` VALUES ('1', '211');
INSERT INTO `sys_role_authority` VALUES ('1', '130');

SET FOREIGN_KEY_CHECKS = 1;
