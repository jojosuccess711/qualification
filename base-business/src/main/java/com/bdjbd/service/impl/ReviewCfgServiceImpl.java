package com.bdjbd.service.impl;

import com.bdjbd.bo.TimeVO;
import com.bdjbd.dao.entity.QaExpertCommit;
import com.bdjbd.dao.entity.QaReviewCfg;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.dao.mapper.QaExpertCommitMapper;
import com.bdjbd.dao.mapper.QaReviewCfgMapper;
import com.bdjbd.dao.mapper.SysAdminMapper;
import com.bdjbd.service.ReviewCfgService;
import com.bdjbd.util.JsonUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 5:02 PM 2020/8/27
 */
@Service
public class ReviewCfgServiceImpl implements ReviewCfgService {

    @Autowired
    private QaReviewCfgMapper cfgMapper;

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private QaExpertCommitMapper commitMapper;

    @Override
    public int addCfg(QaReviewCfg cfg) {
        cfg.setId(RandomStringUtils.randomAlphanumeric(32));
        cfg.setCreateTime(new Date());
        cfg.setUpdateTime(new Date());
        return cfgMapper.insert(cfg);
    }

    @Override
    public List<SysAdmin> findExperts() {
        Example example = new Example(SysAdmin.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roles", ",1000,");
        List<SysAdmin> sysAdmins = sysAdminMapper.selectByExample(example);
        return sysAdmins;
    }

    @Override
    public int updateCfg(QaReviewCfg cfg) {
        cfg.setUpdateTime(new Date());
        return cfgMapper.updateByPrimaryKeySelective(cfg);
    }

    @Override
    public TreeMap<Integer, Object> findCfg(QaReviewCfg cfg) {

        Example example = new Example(QaReviewCfg.class);
        example.setOrderByClause("rounds ASC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", cfg.getType());
        List<QaReviewCfg> cfgs = cfgMapper.selectByExample(example);

        List<QaReviewCfg> one = new ArrayList<>();
        List<QaReviewCfg> two = new ArrayList<>();
        List<QaReviewCfg> three = new ArrayList<>();
        List<QaReviewCfg> four = new ArrayList<>();
        List<QaReviewCfg> five = new ArrayList<>();
        List<QaReviewCfg> six = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cfgs)) {
            for (QaReviewCfg qaReviewCfg : cfgs) {
                if (StringUtils.equals("1", qaReviewCfg.getTypeStatus()) && !qaReviewCfg.getRounds().startsWith("1-") && !qaReviewCfg.getRounds().startsWith("2-"))
                    one.add(qaReviewCfg);
                else if (StringUtils.equals("2", qaReviewCfg.getTypeStatus()) && !qaReviewCfg.getRounds().startsWith("1-") && !qaReviewCfg.getRounds().startsWith("2-"))
                    two.add(qaReviewCfg);
                else if (StringUtils.equals("3", qaReviewCfg.getTypeStatus()) && !qaReviewCfg.getRounds().startsWith("1-") && !qaReviewCfg.getRounds().startsWith("2-"))
                    three.add(qaReviewCfg);
                else if (StringUtils.equals("4", qaReviewCfg.getTypeStatus()) && !qaReviewCfg.getRounds().startsWith("1-") && !qaReviewCfg.getRounds().startsWith("2-"))
                    four.add(qaReviewCfg);
                else if (StringUtils.equals("5", qaReviewCfg.getTypeStatus()) && !qaReviewCfg.getRounds().startsWith("1-") && !qaReviewCfg.getRounds().startsWith("2-"))
                    five.add(qaReviewCfg);
                else if (StringUtils.equals("6", qaReviewCfg.getTypeStatus()) && !qaReviewCfg.getRounds().startsWith("1-") && !qaReviewCfg.getRounds().startsWith("2-"))
                    six.add(qaReviewCfg);
            }
        }
        TreeMap<Integer, Object> result = new TreeMap<>();
        result.put(1, one);
        result.put(2, two);
        result.put(3, three);
        result.put(4, four);
        result.put(5, five);
        result.put(6, six);
        return result;
    }

    @Override
    public List<QaReviewCfg> findExpertTask(String userId) {

        List<QaReviewCfg> cfgs = cfgMapper.selectAll();

        List<QaReviewCfg> list2 = new ArrayList<>();


        if (CollectionUtils.isEmpty(cfgs))
            return new ArrayList<>();

        for (QaReviewCfg cfg : cfgs) {
            String experts = cfg.getExperts();
            List<String> list = JsonUtils.jsonToList(experts, String.class);
            if (!CollectionUtils.isEmpty(list) && list.contains(userId)) {
                list2.add(cfg);
            }
            list2.sort((o1, o2) -> {
                DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                return (int) (LocalDateTime.from(LocalDateTime.parse(o1.getStartTime(), ftf)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - LocalDateTime.from(LocalDateTime.parse(o2.getStartTime(), ftf)).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            });
        }

        for (QaReviewCfg qaReviewCfg : list2) {
            Example example = new Example(QaExpertCommit.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",userId).andEqualTo("rounds",qaReviewCfg.getRounds()).andEqualTo("typeStatus",qaReviewCfg.getTypeStatus()).andEqualTo("type",qaReviewCfg.getType());

            List<QaExpertCommit> qaExpertCommits = commitMapper.selectByExample(example);
            if (!CollectionUtils.isEmpty(qaExpertCommits))
                qaReviewCfg.setCommit("1");
            else qaReviewCfg.setCommit("0");
        }




        return list2;
    }

    @Override
    public int deleteCfg(QaReviewCfg cfg) {
        return cfgMapper.deleteByPrimaryKey(cfg.getId());
    }

    @Override
    public TimeVO findCfgTime(QaReviewCfg cfg) {

        Example example = new Example(QaReviewCfg.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.equals("1", cfg.getRounds()))
            criteria.andEqualTo("typeStatus", cfg.getTypeStatus()).andEqualTo("rounds", "2").andEqualTo("type", cfg.getType());
        else
            criteria.andEqualTo("typeStatus", cfg.getTypeStatus()).andEqualTo("rounds", "1").andEqualTo("type", cfg.getType());
        List<QaReviewCfg> cfgs = cfgMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(cfgs))
            return new TimeVO();
        TimeVO vo = new TimeVO();
        vo.setStartTime(cfgs.get(0).getStartTime());
        vo.setEndTime(cfgs.get(0).getEndTime());
        return vo;
    }
}
