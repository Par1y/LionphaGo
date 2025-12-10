package com.lionphago.backend.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ListLongJsonTypeHandler extends BaseTypeHandler<List<Long>> {


    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = mapper.writeValueAsString(parameter);

            PGobject jsonObject = new PGobject();
            jsonObject.setType("jsonb");
            jsonObject.setValue(json);

            ps.setObject(i,jsonObject);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("序列化失败");
        }
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parsejson(rs.getString(columnName));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parsejson(rs.getString(columnIndex));
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parsejson(cs.getString(columnIndex));
    }

    private List<Long> parsejson(String jsonString){
        if(!StringUtils.hasText(jsonString)){
            // 空数据就返回空列表
            return Collections.emptyList();
        }
        // 非空
        try {
            return mapper.readValue(jsonString, new TypeReference<List<Long>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("反序列化 JSON 失败: " + jsonString, e);
        }
    }
}
