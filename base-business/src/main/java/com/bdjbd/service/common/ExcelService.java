package com.bdjbd.service.common;

import java.io.IOException;
import java.util.List;

import com.bdjbd.dao.entity.QaStandardRecord;

/**
 * @author shujukuss
 * @Date: 2020/12/22
 * @Version: 1.0
 * @Description:
 */
public interface ExcelService {

	List<QaStandardRecord> deal(byte[] bytes) throws IOException;

}
