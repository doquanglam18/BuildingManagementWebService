package com.laptrinhjavaweb.repository.custom;

import java.util.List;

public interface JdbcDaoCustom<T> {
    List<T> findByCondition(String sql);
    public StringBuilder sqlSearch(Object object);
    void update(Object object);
    StringBuilder sqlReflectionSearch(Object object);
}
