package com.bdjbd.service;

import java.util.Map;

public interface ReviewApplyParticipateService {
    /**
     * 进入申请参评
     *
     * @param userId
     */
    Map<String, Object> applyGoInto(String userId, String categoryNameType, String intfaceType);

    Map<String, Object> preview(String userId, String categoryNameType, String intfaceType);

    String get(String userId);
}
