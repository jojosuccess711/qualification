package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.QaAcademicRecordItem;
import com.bdjbd.dao.mapper.QaAcademicRecordItemMapper;
import com.bdjbd.service.QaAcademicRecordItemService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhuzhe
 */
@Slf4j
@Service
public class QaAcademicRecordItemServiceImpl extends BaseServiceImpl<QaAcademicRecordItem, String> implements QaAcademicRecordItemService {

    private QaAcademicRecordItemMapper mapper;

    @Autowired
    public void setMapper(QaAcademicRecordItemMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setBaseDao(QaAcademicRecordItemMapper mapper) {
        super.setBaseDao(mapper);
    }

    @Override
    public int batchInsert(List<QaAcademicRecordItem> items) {
        return mapper.batchInsert(items);
    }

    @Override
    public int deleteByUser(String userId) {
        return mapper.deleteByUser(userId);
    }

    /**
     * 获取拟参评不符合人员条件详情
     * @return
     */
    @Override
    public List<QaAcademicRecordItem> findNotPassDetails(String recordId) {
        return mapper.findNotPassDetails(recordId);
    }

    /**
     * 通过groupid和通过状态获取recordid的列表
     * @return
     */
    @Override
    public List<String> findRecordList(String groupId, String status) {
        return mapper.findRecordList(groupId, status);
    }
}
