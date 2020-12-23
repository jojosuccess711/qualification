package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.QaIntoParticipants;
import com.bdjbd.dao.entity.QaParticipants;
import com.bdjbd.dao.entity.QaReviewCfg;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.mapper.QaIntoParticipantsMapper;
import com.bdjbd.dao.mapper.QaParticipantsMapper;
import com.bdjbd.dao.mapper.QaReviewCfgMapper;
import com.bdjbd.dao.mapper.QaStandardRecordMapper;
import com.bdjbd.service.ParticipantsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 4:45 PM 2020/8/26
 */
@Service
public class ParticipantsServiceImpl implements ParticipantsService {

    @Autowired
    private QaParticipantsMapper participantsMapper;

    @Autowired
    private QaIntoParticipantsMapper intoParticipantsMapper;

    @Autowired
    private QaReviewCfgMapper qaReviewCfgMapper;

    @Autowired
    private QaStandardRecordMapper standardRecordMapper;


    @Override
    public PageInfo<QaParticipants> findList(String categoryName, String category, String type,Integer pageNum, Integer pageSize) {
        pageSize = 100;
        if (StringUtils.isBlank(categoryName) || StringUtils.isBlank(category))
            return new PageInfo<>(new ArrayList<>());

        Example example = new Example(QaParticipants.class);
        example.setOrderByClause("`status` DESC, `update_time` ASC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("finallyPost", categoryName).andEqualTo("lastCategory", category);
        PageHelper.startPage(pageNum, pageSize);
        List<QaParticipants> qaParticipants = participantsMapper.selectByExample(example);
        List<QaParticipants> result = new ArrayList<>();
        if (type.equals("1") && !CollectionUtils.isEmpty(qaParticipants)){
            for (QaParticipants qaParticipant : qaParticipants) {

                QaStandardRecord qaStandardRecords = standardRecordMapper.findOne(qaParticipant.getUserId());
                if(qaStandardRecords.getPersonnelCategory().equals("军人干部"))
                    result.add(qaParticipant);

            }
        }else {
            if (type.equals("2") && !CollectionUtils.isEmpty(qaParticipants)) {
                for (QaParticipants qaParticipant : qaParticipants) {

                    QaStandardRecord qaStandardRecords = standardRecordMapper.findOne(qaParticipant.getUserId());
                    if (qaStandardRecords.getPersonnelCategory().equals("文职人员"))
                        result.add(qaParticipant);
                }
            }
        }

        PageInfo<QaParticipants> info = new PageInfo<>(result);
        return info;
    }

    @Override
    public int move(String id) {
        QaParticipants qaParticipants = participantsMapper.selectByPrimaryKey(id);
        QaIntoParticipants qaIntoParticipants = intoParticipantsMapper.selectByUserId(qaParticipants.getUserId());

        if (StringUtils.equals(qaParticipants.getCategory(), qaParticipants.getLastCategory())) {
            qaParticipants.setLastCategory("其他");
            if (qaIntoParticipants != null)
                qaIntoParticipants.setLastCategory("其他");
        } else {
            qaParticipants.setLastCategory(qaParticipants.getCategory());
            if (qaIntoParticipants != null)
                qaIntoParticipants.setLastCategory(qaParticipants.getCategory());
        }
        intoParticipantsMapper.updateByPrimaryKeySelective(qaIntoParticipants);
        return participantsMapper.updateByPrimaryKeySelective(qaParticipants);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int participation(String id, String status,String type) {
        QaParticipants qaParticipants = new QaParticipants();
        qaParticipants.setId(id);
        qaParticipants.setStatus(status);
        qaParticipants.setUpdateTime(new Date());
        QaParticipants participants = participantsMapper.selectByPrimaryKey(id);
        if (StringUtils.equals("1", status)) {
            QaIntoParticipants qaIntoParticipants = new QaIntoParticipants();
            BeanUtils.copyProperties(participants, qaIntoParticipants);
            qaIntoParticipants.setId(RandomStringUtils.randomAlphanumeric(32));
            qaIntoParticipants.setRounds("1");
            qaIntoParticipants.setType(type);
            qaIntoParticipants.setCreateTime(new Date());
            qaIntoParticipants.setUpdateTime(new Date());
            intoParticipantsMapper.insertSelective(qaIntoParticipants);
        } else if (StringUtils.equals("0", status)) {
            intoParticipantsMapper.deleteByParticipants(participants.getUserId());
        }

        return participantsMapper.updateByPrimaryKeySelective(qaParticipants);
    }

    @Override
    public String findTime(String categoryName, String category) {
        String typeStatus = "";
        if (StringUtils.equals("续任正高", categoryName))
            typeStatus = "1";
        else if (StringUtils.equals("晋升正高", categoryName))
            typeStatus = "2";
        else if (StringUtils.equals("续任副高", categoryName))
            typeStatus = "3";
        else if (StringUtils.equals("晋升副高", categoryName))
            typeStatus = "4";
        if (StringUtils.equals("", typeStatus))
            return "";
        Example ex = new Example(QaReviewCfg.class);

        Example.Criteria criteria1 = ex.createCriteria();

        criteria1.andEqualTo("typeStatus", typeStatus).andEqualTo("rounds", "1");
        List<QaReviewCfg> cfgs = qaReviewCfgMapper.selectByExample(ex);
        if (CollectionUtils.isEmpty(cfgs))
            return "";
        else return cfgs.get(0).getStartTime();

    }
}
