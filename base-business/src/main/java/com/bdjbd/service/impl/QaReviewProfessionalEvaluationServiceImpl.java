package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.*;
import com.bdjbd.dao.mapper.*;
import com.bdjbd.service.QaAcademicRecordService;
import com.bdjbd.service.QaReviewProfessionalEvaluationService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("qaReviewProfessionalEvaluationService")
public class QaReviewProfessionalEvaluationServiceImpl extends BaseServiceImpl<QaReviewProfessionalEvaluationEntity, String> implements QaReviewProfessionalEvaluationService {

    @Autowired
    private QaCategoryMapper categoryMapper;

    @Autowired
    private QaAcademicRecordMapper academicRecordMapper;

    @Autowired
    private QaStandardRecordMapper standardRecordMapper;

    @Autowired
    private QaParticipantsMapper participantsMapper;

    @Autowired
    private QaIntoParticipantsMapper intoParticipantsMapper;

    @Autowired
    private QaReviewProfessionalEvaluationMapper qaReviewProfessionalEvaluationMapper;

    @Autowired
    private SysAdminMapper adminMapper;

    @Override
    public int saveProfessionalEvaluation(String userId, QaReviewProfessionalEvaluationEntity entity) {
        //这个人是否已经录入保存了数据
        QaReviewProfessionalEvaluationEntity qaReviewProfessionalEvaluationEntity = findByUserId(userId);
        entity.setUserId(userId);
        if (qaReviewProfessionalEvaluationEntity != null) {
            //修改
            entity.setId(qaReviewProfessionalEvaluationEntity.getId());
            return qaReviewProfessionalEvaluationMapper.updateByPrimaryKeySelective(entity);
//            return this.updateByUserId(entity);
        }
        return this.saveProfessionalEvaluation(entity);
    }

    public int updateByUserId(QaReviewProfessionalEvaluationEntity entity) {
        return qaReviewProfessionalEvaluationMapper.updateByUserId(entity);
    }

    public int saveProfessionalEvaluation(QaReviewProfessionalEvaluationEntity entity) {
        return qaReviewProfessionalEvaluationMapper.saveProfessionalEvaluation(entity);
    }

    @Override
    public QaReviewProfessionalEvaluationEntity findByUserId(String userId) {
        return qaReviewProfessionalEvaluationMapper.findByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int submitProfessionalEvaluation(String userId, QaReviewProfessionalEvaluationEntity entity) {
        //这个人是否已经录入保存了数据
        QaReviewProfessionalEvaluationEntity qaReviewProfessionalEvaluationEntity = findByUserId(userId);
        entity.setUserId(userId);
        if (qaReviewProfessionalEvaluationEntity != null) {
            entity.setSubmitStatus(1);

            Example example4 = new Example(QaParticipants.class);
            Example.Criteria criteria4 = example4.createCriteria();
            criteria4.andEqualTo("userId", userId);

            List<QaParticipants> qaParticipants1 = participantsMapper.selectByExample(example4);
            if (CollectionUtils.isEmpty(qaParticipants1)) {
                String titleType = entity.getTitleType();
                String realTitleType = titleType.substring(2);
                Example example = new Example(QaCategory.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("name", realTitleType);
                List<QaCategory> qaCategories = categoryMapper.selectByExample(example);
                entity.setAnotherName(qaCategories.get(0).getAnotherName());

                Example example1 = new Example(QaStandardRecord.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("userId", userId);
                List<QaStandardRecord> qaStandardRecords = standardRecordMapper.selectByExample(example1);
                //插入参评人员的数据
                QaParticipants qaParticipants = new QaParticipants();
                qaParticipants.setId(RandomStringUtils.randomAlphanumeric(32));
                qaParticipants.setName(qaStandardRecords.get(0).getName());
                qaParticipants.setIdCard(qaStandardRecords.get(0).getIdCard());
                qaParticipants.setUserId(userId);
                String postType = qaReviewProfessionalEvaluationEntity.getPostType();
                Example example2 = new Example(QaCategory.class);
                Example.Criteria criteria2 = example2.createCriteria();
                criteria2.andEqualTo("name", postType);
                List<QaCategory> categories = categoryMapper.selectByExample(example);
                if (categories.get(0).getId().startsWith("1")) {
                    qaParticipants.setCategory("高教");
                    qaParticipants.setLastCategory("高教");
                } else if (categories.get(0).getId().startsWith("2")) {
                    qaParticipants.setCategory("科研");
                    qaParticipants.setLastCategory("科研");
                } else if (categories.get(0).getId().startsWith("3")) {
                    qaParticipants.setCategory("工程");
                    qaParticipants.setLastCategory("工程");
                } else {
                    qaParticipants.setCategory("其他");
                    qaParticipants.setLastCategory("其他");
                }
                qaParticipants.setCurrentTitle(qaStandardRecords.get(0).getTechnologyCategory());
                qaParticipants.setParticipatingTitle(qaReviewProfessionalEvaluationEntity.getTitleType());
                qaParticipants.setPostType(postType);
                String attr3 = qaStandardRecords.get(0).getAttr3();
                Example example3 = new Example(QaAcademicRecord.class);
                Example.Criteria criteria3 = example3.createCriteria();
                criteria3.andEqualTo("categoryAcademicId", attr3).andEqualTo("userId", userId);
                List<QaAcademicRecord> qaAcademicRecords = academicRecordMapper.selectByExample(example3);
                if (!CollectionUtils.isEmpty(qaAcademicRecords))
                    qaParticipants.setPostResult(qaAcademicRecords.get(0).getStatus() + "");
                else qaParticipants.setPostResult("21");
                qaParticipants.setFinallyPost(entity.getTitleType().substring(0, 2) + qaCategories.get(0).getAnotherName());
                qaParticipants.setStatus("0");
                qaParticipants.setCreateTime(new Date());
                qaParticipants.setUpdateTime(new Date());
                participantsMapper.insert(qaParticipants);
            }
        }
        //修改
        return this.updateByUserId(entity);
    }

    /**
     * 首次参评人员管理
     *
     * @param anotherName 参评职称
     * @param category    五大类型
     * @return
     */
    @Override
    public List<Map<String, Object>> firstParticipateManagement(String anotherName, String category) {
        return qaReviewProfessionalEvaluationMapper.firstParticipateManagement(anotherName, category);
    }

    @Override
    public Integer getPersonStatus(String userId) {
        Example example1 = new Example(QaStandardRecord.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("userId", userId);
        List<QaStandardRecord> qaStandardRecords = standardRecordMapper.selectByExample(example1);
        String attr3 = qaStandardRecords.get(0).getAttr3();
//        Example example3 = new Example(QaAcademicRecord.class);
//        Example.Criteria criteria3 = example3.createCriteria();
//        criteria3.andEqualTo("categoryAcademicId", attr3).andEqualTo("userId", userId);
//        List<QaAcademicRecord> qaAcademicRecords = academicRecordMapper.selectByExample(example3);
//        if (CollectionUtils.isEmpty(qaAcademicRecords))
//            return 0;
//        else return 1;
        if (qaStandardRecords.get(0).getCommitStatus())
            return 1;
        else return 0;
    }

    @Override
    public int unSubmit(String userId) {


        QaIntoParticipants qaIntoParticipants = intoParticipantsMapper.selectByUserId(userId);

        if (qaIntoParticipants != null)
            return 0;

        Example example1 = new Example(QaReviewProfessionalEvaluationEntity.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("userId", userId);


        List<QaReviewProfessionalEvaluationEntity> list = qaReviewProfessionalEvaluationMapper.selectByExample(example1);
        if (!CollectionUtils.isEmpty(list)) {
            QaReviewProfessionalEvaluationEntity entity = list.get(0);
            entity.setSubmitStatus(0);

            qaReviewProfessionalEvaluationMapper.updateByUserId(entity);

            participantsMapper.delByUserId(userId);


        }
        return 1;
    }

    @Override
    public int adminSubmit(String userId, Integer id) {

        SysAdmin sysAdmin = adminMapper.selectByPrimaryKey(userId);
        QaReviewProfessionalEvaluationEntity entity = qaReviewProfessionalEvaluationMapper.selectByPrimaryKey(id);
        if (sysAdmin.getUsername().substring(sysAdmin.getUsername().length() - 1).equals("1"))
            entity.setEducation(1);
        else if (sysAdmin.getUsername().substring(sysAdmin.getUsername().length() - 1).equals("2"))
            entity.setKyobo(1);
        else if (sysAdmin.getUsername().substring(sysAdmin.getUsername().length() - 1).equals("3"))
            entity.setResearch(1);
        else if (sysAdmin.getUsername().substring(sysAdmin.getUsername().length() - 1).equals("4"))
            entity.setPolitics(1);
        else System.out.println("账号有误");

            return qaReviewProfessionalEvaluationMapper.updateByUserId(entity);
    }

    @Override
    public QaReviewProfessionalEvaluationEntity adminFind(String userId, Integer id) {

//        QaReviewProfessionalEvaluationEntity

        QaReviewProfessionalEvaluationEntity key = qaReviewProfessionalEvaluationMapper.selectByPrimaryKey(id);
        QaReviewProfessionalEvaluationEntity byUserId = qaReviewProfessionalEvaluationMapper.findByUserId(key.getUserId());

        return byUserId;
    }
}