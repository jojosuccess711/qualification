package com.bdjbd.bo;

import lombok.Data;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 11:58 AM 2020/9/9
 */
@Data
public class TicketVO {

    private String userId;

    private Integer expertsNum;

    private Integer remaining;

    private String rounds;

    private String typeStatus;

    private String commit;

}
