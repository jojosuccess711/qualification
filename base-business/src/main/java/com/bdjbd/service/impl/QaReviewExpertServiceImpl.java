package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.QaReviewExpertEntity;
import com.bdjbd.dao.mapper.QaReviewExpertMapper;
import com.bdjbd.service.sys.QaReviewExpertService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * @author 83746
 */
@Service("qaReviewExpertService")
public class QaReviewExpertServiceImpl extends BaseServiceImpl<QaReviewExpertEntity, String> implements QaReviewExpertService {

    @Autowired
    private QaReviewExpertMapper qaReviewExpertMapper;

    /**
     * 专家管理列表
     * @param pageNum
     * @param pageSize
     * @param entity
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public PageInfo<QaReviewExpertEntity> findExpertList(Integer pageNum, Integer pageSize, QaReviewExpertEntity entity) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(QaReviewExpertEntity.class);
        example.createCriteria().andLike("expertName", "%" + entity.getExpertName() + "%")
                .andLike("company", "%" + entity.getCompany() + "%");
        List<QaReviewExpertEntity> list = qaReviewExpertMapper.selectByExample(example);
        PageInfo<QaReviewExpertEntity> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 新增专家
     * @param entity
     * @return
     */
    @Override
    public int addExpert(QaReviewExpertEntity entity) {
        return qaReviewExpertMapper.addExpert(entity);
    }

    /**
     * 查询专家
     * @param id
     * @return
     */
    @Override
    public QaReviewExpertEntity findById(Integer id) {
        return qaReviewExpertMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改专家
     * @param entity
     * @return
     */
    @Override
    public int updateExpert(QaReviewExpertEntity entity) {
        return qaReviewExpertMapper.updateExpert(entity);
    }

    /**
     * 删除专家
     * @param id
     * @return
     */
    @Override
    public int deleteExpert(Integer id) {
        return qaReviewExpertMapper.deleteExpert(id);
    }

}