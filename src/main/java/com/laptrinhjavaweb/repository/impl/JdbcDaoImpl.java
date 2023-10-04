package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.repository.custom.JdbcDaoCustom;
import com.laptrinhjavaweb.utils.ConnectionUtils;
import com.laptrinhjavaweb.utils.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JdbcDaoImpl<T> implements JdbcDaoCustom<T> {

    private Class<T> tClass;

    public JdbcDaoImpl() {
        //construct khởi tại mặc định tClass là class generic để nhận được các class entity khác
        tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> findByCondition(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionUtils.getCon();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
            return resultSetMapper.mapRow(rs, tClass);
        }
        catch( SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
                rs.close();
            }
            catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public StringBuilder sqlSearch(Object object) {
        String tableName = "";
        StringBuilder fields = new StringBuilder("");
        try{
            if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            for(Field field : tClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object valueField = field.get(object);
                if(Objects.nonNull(valueField)){
                    if(field.getType() == String.class){
                        String valueField1 = (String)field.get(object);
                        if(field.isAnnotationPresent(Column.class) && !StringUtils.isNullOrEmpty(valueField1)) {
                            Column column = field.getAnnotation(Column.class);
                            fields.append(" and " + column.name() + " like '%" + valueField1 + "%' " );
                        }
                    }
                    if(field.getType() == Integer.class){
                        Integer valueField1 = (Integer)field.get(object);
                        if(field.isAnnotationPresent(Column.class) && valueField1 != null) {
                            Column column = field.getAnnotation(Column.class);
                            fields.append(" and " + column.name() + " = '" + valueField1 + "' " );
                        }
                    }
                }


            }
        }catch ( IllegalAccessException e){
            e.printStackTrace();
        }

//        StringBuilder sql = new StringBuilder("Select * from  "+ tableName + " where 1 = 1 " + fields.toString() );
        StringBuilder sql = new StringBuilder(fields.toString());
        return sql;
    }

    @Override
    public void update(Object object) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionUtils.getCon();
            String  sql = updateSQL(object).toString();
            stmt = conn.prepareStatement(sql);
            //set parameter
            Class<?> zClass = object.getClass();
            //lấy ra các field của zClass
            Field[] fields = zClass.getDeclaredFields();
            int parameterIndex = 1;
            for(Field field : fields) {
                Column column1 = field.getAnnotation(Column.class);
                if (field.isAnnotationPresent(Column.class)){
                    if(column1.name().equals("id")) {
                        continue;
                    }else {
                        field.setAccessible(true);
                        if(Objects.nonNull(field.get(object))){
                            field.setAccessible(true);
                            stmt.setObject(parameterIndex, field.get(object));
                            parameterIndex++;
                        }

                    }
                }
            }
            // lấy class cha của class zClass
            Class<?> parentClass = zClass.getSuperclass();
            int indexParameterParent = parameterIndex ;
            while(parentClass != null) {
                for(Field field : parentClass.getDeclaredFields()) {
                    Column column1 = field.getAnnotation(Column.class);
                    if (field.isAnnotationPresent(Column.class)){
                        if(column1.name().equals("id")) {
                            field.setAccessible(true);
                            stmt.setObject(indexParameterParent, field.get(object));
                            indexParameterParent++;
                            break;
                        }
                    }
                }
                parentClass = parentClass.getSuperclass();
            }
            stmt.executeUpdate();
        }catch(SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public StringBuilder sqlReflectionSearch(Object object) {
        String tableName = "";
        StringBuilder fields = new StringBuilder("");
        try{
            if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            for(Field field : tClass.getDeclaredFields()) {
                field.setAccessible(true);
                Object valueField = field.get(object);
                if(Objects.nonNull(valueField)){
                    if(field.getType() == String.class){
                        String valueField1 = (String)field.get(object);
                        if(field.isAnnotationPresent(Column.class) && !StringUtils.isNullOrEmpty(valueField1)) {
                            Column column = field.getAnnotation(Column.class);
                            fields.append(" and " + column.name() + " like '%" + valueField1 + "%' " );
                        }
                    }
                }
            }
        }catch ( IllegalAccessException e){
            e.printStackTrace();
        }
        StringBuilder sql = new StringBuilder(fields.toString());
        return sql;
    }

    private StringBuilder updateSQL(Object object) {
		String tableName = "";
        StringBuilder fields = new StringBuilder("");
        StringBuilder sql = new StringBuilder();
        try{
            if(tClass.isAnnotationPresent(Entity.class) && tClass.isAnnotationPresent(Table.class)) {
                Table table = tClass.getAnnotation(Table.class);
                tableName = table.name();
            }
            sql.append("update "+ tableName + " set ");
            for(Field field : tClass.getDeclaredFields()) {
                field.setAccessible(true);
                if(Objects.nonNull(field.get(object)) ) {
                    if (field.get(object) instanceof Collection) {
                        Collection<?> collection = (Collection<?>) field.get(object);
                        if (collection.isEmpty()) {
                            continue;
                        }
                    } else if (field.get(object) instanceof Object[]) {
                        Object[] array = (Object[]) field.get(object);
                        if (array.length == 0) {
                            continue;
                        }
                    }
                    if (field.getAnnotation(Column.class).name().equals("id")) {
                        continue;
                    } else {
                        if (fields.length() > 1) {
                            fields.append(",");
                        }
                        if (field.isAnnotationPresent(Column.class)) {
                            Column column = field.getAnnotation(Column.class);
                            fields.append(column.name() + " = ? ");
                        }
                    }
                }
            }
            Class<?> parentClass = tClass.getSuperclass();
            while(parentClass != null) {
                for(Field field : parentClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    if(Objects.nonNull(field.get(object)) ) {
                        if (field.get(object) instanceof Collection) {
                            Collection<?> collection = (Collection<?>) field.get(object);
                            if (collection.isEmpty()) {
                                continue;
                            } else if (field.get(object) instanceof Object[]) {
                                Object[] array = (Object[]) field.get(object);
                                if (array.length == 0) {
                                    continue;
                                }
                            }
                            if (field.getAnnotation(Column.class).name().equals("id")) {
                                continue;
                            } else {
                                if (fields.length() > 1) {
                                    fields.append(",");
                                }
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field.getAnnotation(Column.class);
                                    fields.append(column.name() + " = ? ");
                                }
                            }
                        }
                    }
                }
                parentClass = parentClass.getSuperclass();
            }
        }
        catch(IllegalAccessException e){
            e.printStackTrace();
        }

		fields = fields.append("where id = ?");
		return sql.append(" " + fields + " ");
	}
}
