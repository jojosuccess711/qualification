package com.bdjbd.service.impl;

import com.bdjbd.bo.ScheduleVO;
import com.bdjbd.bo.TicketVO;
import com.bdjbd.bo.TimeResVO;
import com.bdjbd.bo.TimeVO;
import com.bdjbd.dao.entity.*;
import com.bdjbd.dao.mapper.*;
import com.bdjbd.service.QaIntoService;
import com.bdjbd.util.JsonUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 9:40 AM 2020/9/8
 */
@Service
public class QaIntoServiceImpl implements QaIntoService {

    @Autowired
    private QaReviewProfessionalEvaluationMapper evaluationMapper;

    @Autowired
    private SysConfigAttributesMapper mapper;

    @Autowired
    private BaseParameterMapper parameterMapper;

    @Autowired
    private QaStandardRecordItemMapper itemMapper;


    @Autowired
    private QaStandardRecordMapper standardRecordMapper;

    @Autowired
    private QaIntoParticipantsMapper participantsMapper;

    @Autowired
    private QaReviewCfgMapper qaReviewCfgMapper;

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private QaExpertCommitMapper expertCommitMapper;

    @Override
    public Map<String, Object> findList(String userId, String rounds, String typeStatus, String lastCategory, String type) {

        final String ts = typeStatus;
        Example ex = new Example(QaReviewCfg.class);
        Example.Criteria criteria1 = ex.createCriteria();
        criteria1.andEqualTo("typeStatus", typeStatus).andEqualTo("rounds", rounds + "").andEqualTo("type", type);
        List<QaReviewCfg> cfgs = qaReviewCfgMapper.selectByExample(ex);

        if (StringUtils.equals("1", typeStatus))
            typeStatus = "续任正高";
        else if (StringUtils.equals("2", typeStatus))
            typeStatus = "晋升正高";
        else if (StringUtils.equals("3", typeStatus))
            typeStatus = "续任副高";
        else if (StringUtils.equals("4", typeStatus))
            typeStatus = "晋升副高";
        else if (StringUtils.equals("5", typeStatus))
            typeStatus = "续任中职";
        else typeStatus = "晋升中职";

        final String type1 = typeStatus;

        TicketVO ticketVO = new TicketVO();
        ticketVO.setUserId(userId);
        String[] split = cfgs.get(0).getExpertsNum().split("-");
        if ("高教".equals(lastCategory))
            ticketVO.setExpertsNum(Integer.parseInt(split[0]));
        else if ("科研".equals(lastCategory))
            ticketVO.setExpertsNum(Integer.parseInt(split[1]));
        else if ("工程".equals(lastCategory))
            ticketVO.setExpertsNum(Integer.parseInt(split[2]));
        else
            ticketVO.setExpertsNum(Integer.parseInt(split[3]));
        ticketVO.setTypeStatus(ts);
        ticketVO.setRounds(rounds);
        Example example = new Example(QaIntoParticipants.class);
        example.orderBy("createTime").asc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("lastCategory", lastCategory).andEqualTo("rounds", rounds).andEqualTo("finallyPost", typeStatus).andEqualTo("type", type);
        List<QaIntoParticipants> list = participantsMapper.selectByExample(example);
        List<QaIntoParticipants> list2 = new ArrayList<>();
        List<QaIntoParticipants> list3 = participantsMapper.selectAll();
        for (QaIntoParticipants qaIntoParticipants : list3) {
            if (type.equals(qaIntoParticipants.getType()))
                list2.add(qaIntoParticipants);
        }

        if (CollectionUtils.isEmpty(list2)) {
            if ("高教".equals(lastCategory))
                ticketVO.setRemaining(Integer.parseInt(split[0]));
            else if ("科研".equals(lastCategory))
                ticketVO.setRemaining(Integer.parseInt(split[1]));
            else if ("工程".equals(lastCategory))
                ticketVO.setRemaining(Integer.parseInt(split[2]));
            else
                ticketVO.setRemaining(Integer.parseInt(split[3]));
        } else {
            List<QaIntoParticipants> collect = list2.stream().filter(s -> s.getRounds().equals(rounds) && type1.equals(s.getFinallyPost()) && lastCategory.equals(s.getLastCategory())).collect(Collectors.toList());
            int count = 0;
            for (QaIntoParticipants qaIntoParticipants : collect) {
                String ticketIds = qaIntoParticipants.getTicketIds();
                if (StringUtils.isNotBlank(ticketIds)) {
                    List<String> list1 = JsonUtils.jsonToList(ticketIds, String.class);
                    if (list1.contains(userId)) {
                        count++;
                    }
                } else {
                    if ("高教".equals(lastCategory))
                        ticketVO.setRemaining(Integer.parseInt(split[0]));
                    else if ("科研".equals(lastCategory))
                        ticketVO.setRemaining(Integer.parseInt(split[1]));
                    else if ("工程".equals(lastCategory))
                        ticketVO.setRemaining(Integer.parseInt(split[2]));
                    else
                        ticketVO.setRemaining(Integer.parseInt(split[3]));
//                    ticketVO.setRemaining(cfgs.get(0).getExpertsNum());
                }
            }
            if ("高教".equals(lastCategory))
                ticketVO.setRemaining(Integer.parseInt(split[0]) - count);
            else if ("科研".equals(lastCategory))
                ticketVO.setRemaining(Integer.parseInt(split[1]) - count);
            else if ("工程".equals(lastCategory))
                ticketVO.setRemaining(Integer.parseInt(split[2]) - count);
            else
                ticketVO.setRemaining(Integer.parseInt(split[3]) - count);
//            ticketVO.setRemaining(cfgs.get(0).getExpertsNum() - count);
        }

        Example exc = new Example(QaExpertCommit.class);

        Example.Criteria excCriteria = exc.createCriteria();

        excCriteria.andEqualTo("typeStatus", ts).andEqualTo("rounds", rounds).andEqualTo("type", type).andEqualTo("userId", userId);

        List<QaExpertCommit> qaExpertCommits = expertCommitMapper.selectByExample(exc);
        if (CollectionUtils.isEmpty(qaExpertCommits))
            ticketVO.setCommit("0");
        else ticketVO.setCommit("1");

        Map<String, Object> map = new HashMap<>();
        if ("高教".equals(lastCategory) && "2".equals(ts))
            map.put("number", participantsMapper.selectNumber().getNumber());
        else map.put("number", "0");

        map.put("time", cfgs.get(0));
        map.put("data", list);
        map.put("ticket", ticketVO);
        return map;
    }

    @Override
    public int vote(String id, String userId) {

        QaIntoParticipants qaIntoParticipants = participantsMapper.selectByPrimaryKey(id);
        String ticketIds = qaIntoParticipants.getTicketIds();
        if (StringUtils.isBlank(ticketIds)) {
            List<String> list = new ArrayList<>();
            list.add(userId);
            qaIntoParticipants.setTicketIds(JsonUtils.objectToJson(list));
        } else {
            List<String> list = JsonUtils.jsonToList(ticketIds, String.class);
            list.add(userId);
            qaIntoParticipants.setTicketIds(JsonUtils.objectToJson(list));
        }
        qaIntoParticipants.setTickets(qaIntoParticipants.getTickets() + 1);


        return participantsMapper.updateByPrimaryKeySelective(qaIntoParticipants);
    }

    @Override
    public int unVote(String id, String userId) {
        QaIntoParticipants qaIntoParticipants = participantsMapper.selectByPrimaryKey(id);
        String ticketIds = qaIntoParticipants.getTicketIds();
        List<String> list = JsonUtils.jsonToList(ticketIds, String.class);
        for (String s : list) {
            if (StringUtils.equals(userId, s)) {
                list.remove(s);
                break;
            }
        }
        qaIntoParticipants.setTicketIds(JsonUtils.objectToJson(list));
        qaIntoParticipants.setTickets(qaIntoParticipants.getTickets() - 1);
        return participantsMapper.updateByPrimaryKeySelective(qaIntoParticipants);
    }

    @Override
    public int commitVote(String userId, String rounds, String typeStatus, String lastCategory, String pic, String type) {

        QaExpertCommit qaExpertCommit = new QaExpertCommit();
        qaExpertCommit.setId(RandomStringUtils.randomAlphanumeric(32));
        qaExpertCommit.setUserId(userId);
        qaExpertCommit.setRounds(rounds);
        qaExpertCommit.setTypeStatus(typeStatus);
        qaExpertCommit.setCommit("1");
        qaExpertCommit.setLastCategory(lastCategory);
        qaExpertCommit.setPic(pic);
        qaExpertCommit.setCreateTime(new Date());
        qaExpertCommit.setUpdateTime(new Date());
        qaExpertCommit.setType(type);

        return expertCommitMapper.insertSelective(qaExpertCommit);
    }

    @Override
    public List<QaIntoParticipants> findResult(String userId, String rounds, String typeStatus, String lastCategory, String type) {

        if (StringUtils.equals("1", typeStatus))
            typeStatus = "续任正高";
        else if (StringUtils.equals("2", typeStatus))
            typeStatus = "晋升正高";
        else if (StringUtils.equals("3", typeStatus))
            typeStatus = "续任副高";
        else if (StringUtils.equals("4", typeStatus))
            typeStatus = "晋升副高";
        else if (StringUtils.equals("5", typeStatus))
            typeStatus = "续任中职";
        else typeStatus = "晋升中职";


        Example example = new Example(QaIntoParticipants.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("lastCategory", lastCategory).andEqualTo("rounds", rounds).andEqualTo("finallyPost", typeStatus);
        List<QaIntoParticipants> list = participantsMapper.selectByExample(example);
        List<QaIntoParticipants> result = new ArrayList<>();
        if (type.equals("1") && !CollectionUtils.isEmpty(list)) {
            for (QaIntoParticipants qaParticipant : list) {

                QaStandardRecord qaStandardRecords = standardRecordMapper.findOne(qaParticipant.getUserId());
                if (qaStandardRecords.getPersonnelCategory().equals("军人干部"))
                    result.add(qaParticipant);

            }
        } else {
            if (type.equals("2") && !CollectionUtils.isEmpty(list)) {
                for (QaIntoParticipants qaParticipant : list) {

                    QaStandardRecord qaStandardRecords = standardRecordMapper.findOne(qaParticipant.getUserId());
                    if (qaStandardRecords.getPersonnelCategory().equals("文职人员"))
                        result.add(qaParticipant);
                }
            }
        }
        for (QaIntoParticipants qaIntoParticipants : result) {
            List<String> jsonToList = JsonUtils.jsonToList(qaIntoParticipants.getTicketIds(), String.class);
            if (CollectionUtils.isEmpty(jsonToList))
                qaIntoParticipants.setTotal(0);
            else qaIntoParticipants.setTotal(jsonToList.size());
            Example ple = new Example(QaIntoParticipants.class);
            Example.Criteria cri = ple.createCriteria();
            String fRds = rounds + "-1";
            cri.andEqualTo("lastCategory", lastCategory).andEqualTo("finallyPost", typeStatus).andEqualTo("userId", qaIntoParticipants.getUserId()).andEqualTo("rounds", fRds);
            ;

            List<QaIntoParticipants> participants = participantsMapper.selectByExample(ple);
            if (CollectionUtils.isEmpty(participants))
                qaIntoParticipants.setNext("false");
            else qaIntoParticipants.setNext("true");
            Example ple1 = new Example(QaIntoParticipants.class);
            Example.Criteria cri1 = ple1.createCriteria();
            cri1.andEqualTo("lastCategory", lastCategory).andEqualTo("finallyPost", typeStatus).andEqualTo("userId", qaIntoParticipants.getUserId()).andEqualTo("rounds", "2");

            List<QaIntoParticipants> participant = participantsMapper.selectByExample(ple1);
            if (CollectionUtils.isEmpty(participant))
                qaIntoParticipants.setNextTwo("false");
            else qaIntoParticipants.setNextTwo("true");
        }

        if (!CollectionUtils.isEmpty(result)) {
            List<QaIntoParticipants> collect = result.stream().sorted(Comparator.comparing(QaIntoParticipants::getTickets).reversed()).collect(Collectors.toList());
            return collect;
        }
        return result;
    }

    @Override
    public List<ScheduleVO> findSchedule(String userId, String rounds, String typeStatus) {

        Example ex = new Example(QaReviewCfg.class);

        Example.Criteria criteria1 = ex.createCriteria();

        criteria1.andEqualTo("typeStatus", typeStatus).andEqualTo("rounds", rounds);
        List<QaReviewCfg> cfgs = qaReviewCfgMapper.selectByExample(ex);

        List<String> list = JsonUtils.jsonToList(cfgs.get(0).getExperts(), String.class);
        List<ScheduleVO> resultList = new ArrayList<>();
        for (String s : list) {
            ScheduleVO vo = new ScheduleVO();
            SysAdmin sysAdmin = sysAdminMapper.selectByPrimaryKey(s);
            vo.setName(sysAdmin.getName());
            Example example = new Example(QaExpertCommit.class);
            example.setOrderByClause("`create_time` DESC");
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("typeStatus", typeStatus).andEqualTo("rounds", rounds).andEqualTo("userId", sysAdmin.getId());
            List<QaExpertCommit> qaExpertCommits = expertCommitMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(qaExpertCommits)) {
                vo.setResult("1");
                vo.setEndTime(qaExpertCommits.get(0).getCreateTime());
            } else vo.setResult("0");

            resultList.add(vo);

        }
        if (!CollectionUtils.isEmpty(resultList)) {
            List<ScheduleVO> collect = new ArrayList<>();
            List<ScheduleVO> collect2 = new ArrayList<>();
            List<ScheduleVO> result = new ArrayList<>();

            for (ScheduleVO vo : resultList) {
                if (vo.getEndTime() != null)
                    collect.add(vo);
                else
                    collect2.add(vo);
            }
            if (!CollectionUtils.isEmpty(collect)){
                List<ScheduleVO> collect1 = collect.stream().sorted(Comparator.comparing(ScheduleVO::getEndTime).reversed()).collect(Collectors.toList());
                result.addAll(collect1);
            }

            result.addAll(collect2);
            return result;
        }
        return resultList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void intoAgain(String userId, String rounds, String userIds, String typeStatus) {

        Example ex = new Example(QaReviewCfg.class);

        Example.Criteria criteria1 = ex.createCriteria();

        criteria1.andEqualTo("typeStatus", typeStatus).andEqualTo("rounds", rounds.substring(0, 1));
        List<QaReviewCfg> cfgs = qaReviewCfgMapper.selectByExample(ex);
        if (rounds.length() > 1) {
            QaReviewCfg qaReviewCfg = new QaReviewCfg();
            BeanUtils.copyProperties(cfgs.get(0), qaReviewCfg);
            qaReviewCfg.setId(RandomStringUtils.randomAlphanumeric(32));
            qaReviewCfg.setExpertsNum("1,1,1,1");
            qaReviewCfg.setRounds(rounds);
            qaReviewCfg.setCreateTime(new Date());
            qaReviewCfg.setUpdateTime(new Date());
            qaReviewCfgMapper.insert(qaReviewCfg);
        }

        List<String> list = JsonUtils.jsonToList(userIds, String.class);
        for (String s : list) {
            QaIntoParticipants qaIntoParticipants1 = participantsMapper.selectByPrimaryKey(s);

            QaIntoParticipants qaIntoParticipants = new QaIntoParticipants();
            BeanUtils.copyProperties(qaIntoParticipants1, qaIntoParticipants);
            qaIntoParticipants.setId(RandomStringUtils.randomAlphanumeric(32));
            qaIntoParticipants.setRounds(rounds);
            qaIntoParticipants.setTicketIds("");
            qaIntoParticipants.setTickets(0);
            qaIntoParticipants.setCreateTime(new Date());
            qaIntoParticipants.setUpdateTime(new Date());
            participantsMapper.insertSelective(qaIntoParticipants);
        }

    }

    @Override
    public List<QaIntoParticipants> exportInfo(String userId, String userIds) {
        List<String> list = JsonUtils.jsonToList(userIds, String.class);
        List<QaIntoParticipants> result = new ArrayList<>();
        for (String s : list) {
            result.add(participantsMapper.selectByPrimaryKey(s));
        }
        return result;
    }

    @Override
    public void repairData(String userId) {

        List<QaStandardRecord> qaStandardRecords = standardRecordMapper.selectAll();
        if (!CollectionUtils.isEmpty(qaStandardRecords)) {
            QaStandardRecord record = null;
            record = qaStandardRecords.stream().filter(o -> userId.equals(o.getUserId())).collect(Collectors.toList()).get(0);

            List<QaStandardRecordItem> list = itemMapper.selectRepairData(record.getId());
            if (!CollectionUtils.isEmpty(list)) {

                Map<String, String> map = Maps.newHashMap();

                List<BaseParameter> baseParameters = parameterMapper.selectAll();
                for (BaseParameter baseParameter : baseParameters) {
                    map.put(baseParameter.getId(), baseParameter.getTypeValidate());
                }

                for (QaStandardRecordItem qaStandardRecordItem : list) {
                    System.out.println(map.get(qaStandardRecordItem.getParameterId()));
                    qaStandardRecordItem.setAttr2(map.get(qaStandardRecordItem.getParameterId()));
                    itemMapper.updateByPrimaryKeySelective(qaStandardRecordItem);
                }
            }
        }
    }

    @Override
    public Map<String, TimeVO> timeData() {

        Map<String, TimeVO> map = Maps.newHashMap();


        List<SysConfigAttributes> sysConfigAttributes = mapper.selectAll();
        TimeVO vo = null;
        TimeVO vo1 = null;
        TimeVO vo2 = null;

        for (SysConfigAttributes sysConfigAttribute : sysConfigAttributes) {

            if (map.get("INPUT_OPEN") == null) {
                vo = new TimeVO();
                map.put("INPUT_OPEN", vo);
            } else vo = map.get("INPUT_OPEN");


            if (map.get("COMMIT_ACADEMIC_CATEGORY") == null) {
                vo1 = new TimeVO();
                map.put("COMMIT_ACADEMIC_CATEGORY", vo1);
            } else vo1 = map.get("COMMIT_ACADEMIC_CATEGORY");

            if (map.get("ASSESSORS_OPEN") == null) {
                vo2 = new TimeVO();
                map.put("ASSESSORS_OPEN", vo2);
            } else vo2 = map.get("ASSESSORS_OPEN");


            if (sysConfigAttribute.getConfigId().equals("INPUT_OPEN")) {

                if (sysConfigAttribute.getName().equals("beginDate"))
                    vo.setStartTime(sysConfigAttribute.getValue());
                else
                    vo.setEndTime(sysConfigAttribute.getValue());
            }

            if (sysConfigAttribute.getConfigId().equals("COMMIT_ACADEMIC_CATEGORY")) {

                if (sysConfigAttribute.getName().equals("beginDate"))
                    vo1.setStartTime(sysConfigAttribute.getValue());
                else vo1.setEndTime(sysConfigAttribute.getValue());
            }

            if (sysConfigAttribute.getConfigId().equals("ASSESSORS_OPEN")) {

                if (sysConfigAttribute.getName().equals("beginDate"))
                    vo2.setStartTime(sysConfigAttribute.getValue());
                else vo2.setEndTime(sysConfigAttribute.getValue());

            }

        }

        map.put("INPUT_OPEN", vo);
        map.put("COMMIT_ACADEMIC_CATEGORY", vo1);
        map.put("ASSESSORS_OPEN", vo2);
        return map;
    }

    @Override
    public QaReviewProfessionalEvaluationEntity findDataByUserId(String userId) {
        return evaluationMapper.findByUserId(userId);
    }

    @Override
    public void updateDataByUserId(QaReviewProfessionalEvaluationEntity entity) {
        evaluationMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public QaExpertCommit findImg(String userId, String rounds, String type, String typeStatus) {

        Example example = new Example(QaExpertCommit.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId).andEqualTo("rounds", rounds).andEqualTo("type", type).andEqualTo("typeStatus", typeStatus);
        List<QaExpertCommit> qaExpertCommits = expertCommitMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(qaExpertCommits))
            return qaExpertCommits.get(0);
        return null;
    }
}
