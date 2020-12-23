package com.bdjbd.service.matches.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className Relation
 * @description TODO
 * @date 2020/3/3
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relation implements Serializable {

     enum Type {
        AND("&"),
        OR("||");

        private String key;

        Type(String key){
            this.key = key;
        }

        public String getKey(){
            return this.key;
        }
    }

    /** 关系 */
    private String relation;

    private String key;

    /** 关系子项 */
    private List<Relation> children;

    /** 关系项 */
    private List<String> items;
}
