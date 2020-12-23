package com.bdjbd.bo;

import com.bdjbd.common.util.DateUtil;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * {"parameterId":"10100-021","type":"after","param":"start"}
 *
 * @author zhuzhe
 * @date 2020/4/10 16:42
 * @email zhuzhe_mail@163.com
 */
@Data
public class DateCheck {

    /**
     * 参数id
     */
    private String parameterId;

    /**
     * after  取之后时间
     * before 取之前时间
     */
    private String type;

    /**
     * start 取起始时间
     * end   取结束时间
     */
    private String param;

    private String level;

    public final static String AFTER = "after";
    public final static String BEFORE = "before";

    public final static String START = "start";
    public final static String END = "end";

    public static boolean start(String param) {
        return START.equals(param);
    }

    public static boolean end(String param) {
        return END.equals(param);
    }

    /**
     * v1比v2
     *
     * @param v1
     * @param v2
     * @return
     */
    public static boolean compare(String v1, String v2, String type) {
        Date v1date = DateUtil.dateParse("yyyy.MM", v1);
        Date v2date = DateUtil.dateParse("yyyy.MM", v2);
        if (AFTER.equals(type)) {
            return v1date.after(v2date);
        }
        if (BEFORE.equals(type)) {
            return v1date.before(v2date);
        }
        return false;
    }

    public static void main(String[] args) {


        //System.out.println(DateCheck.start(null));

//        boolean before = compare("2019.09", "2019.04", "after");
//        System.out.println(before);
    }
}
