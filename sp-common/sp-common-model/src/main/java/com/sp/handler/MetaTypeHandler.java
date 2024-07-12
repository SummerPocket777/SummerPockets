package com.sp.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sp.model.domain.MenuMeta;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MetaTypeHandler extends BaseTypeHandler<MenuMeta> {

    ObjectMapper om = new ObjectMapper();
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MenuMeta parameter, JdbcType jdbcType) throws SQLException {
        String json = null;
        try {
            json = om.writeValueAsString(parameter);
            if(json != null) {
                ps.setString(i, json);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MenuMeta getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        MenuMeta menuMeta = null;
        try {
            if(json != null) {
                menuMeta = om.readValue(json, MenuMeta.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return menuMeta;
    }

    @Override
    public MenuMeta getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        MenuMeta menuMeta = null;
        try {
            if(json != null) {
                menuMeta = om.readValue(json, MenuMeta.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return menuMeta;
    }

    @Override
    public MenuMeta getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        MenuMeta menuMeta = null;
        try {
            if(json != null) {
                menuMeta = om.readValue(json, MenuMeta.class);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return menuMeta;
    }
}
