package com.bdjbd.bo;

import com.bdjbd.dao.entity.QaStandardRecord;
import lombok.Data;

import java.util.List;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 5:01 PM 2020/4/27
 */
@Data
public class QaStandardRecordVO extends QaStandardRecord {

    private List<String> approverType;
}
