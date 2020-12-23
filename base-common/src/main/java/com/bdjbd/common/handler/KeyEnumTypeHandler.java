package com.bdjbd.common.handler;

import com.bdjbd.common.common.BaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
  * @className KeyEnumTypeHandler
  * @description 枚举处理器
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
public class KeyEnumTypeHandler<E extends Enum<?> & BaseEnum> extends BaseTypeHandler<BaseEnum> {

    private Class<E> type;

    public KeyEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setObject(i, parameter.getKey());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object key = rs.getObject(columnName);
        return rs.wasNull() ? null : codeOf(key);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object code = rs.getObject(columnIndex);
        return rs.wasNull() ? null : codeOf(code);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Object code = cs.getObject(columnIndex);
        return cs.wasNull() ? null : codeOf(code);
    }

    private E codeOf(Object key){
        try {
            return codeOf(type, key);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + key + " to " + type.getSimpleName() + " by key value.", ex);
        }
    }

    public static <E extends Enum<?> & BaseEnum> E codeOf(Class<E> enumClass, Object key) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if(e.getKey().equals(key)){
                return e;
            }
        }
        return null;
    }
}
