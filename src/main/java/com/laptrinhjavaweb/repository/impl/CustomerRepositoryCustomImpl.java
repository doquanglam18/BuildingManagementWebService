package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.request.CustomerRequestDTO;
import com.laptrinhjavaweb.dto.response.CustomerResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import com.laptrinhjavaweb.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryCustomImpl extends JdbcDaoImpl<CustomerEntity> implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerConverter customerConverter;

    public StringBuilder buildNativeQuery(CustomerRequestDTO customerRequestDTO) {
        StringBuilder finalQuery = new StringBuilder();
        String fullName = customerRequestDTO.getFullName();
        String phone = customerRequestDTO.getPhone();
        String email = customerRequestDTO.getEmail();
        String nameOfStaff = customerRequestDTO.getNameOfStaff();
        finalQuery.append(SystemConstant.ONE_EQUAL_ONE + "\n");

        if(!StringUtils.isNullOrEmpty(nameOfStaff)){
            finalQuery.append("and c.id IN (\n" +
                              "SELECT customer_id FROM assignmentcustomer WHERE staff_id =" + nameOfStaff + ")");
        }
//        if (!StringUtils.isNullOrEmpty(fullName)) {
//            finalQuery.append(" and fullname LIKE '%" + fullName + "%'");
//        }
//        if (!StringUtils.isNullOrEmpty(phone)) {
//            finalQuery.append(" and phone LIKE '%" + phone + "%'");
//        }
//        if (!StringUtils.isNullOrEmpty(email)) {
//            finalQuery.append(" and email LIKE '%" + email + "%'");
//        }
        return finalQuery;
    }

    public String customerNativeQuery(CustomerRequestDTO customerRequestDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerRequestDTO);
        StringBuilder nativeQuery = buildNativeQuery(customerRequestDTO);
        StringBuilder reflectionQuery = sqlSearch(customerEntity);
        StringBuilder finalQuery = new StringBuilder("SELECT * FROM `customer` c \n");
        finalQuery.append(nativeQuery)
                  .append(reflectionQuery)
                  .append(" \nGROUP BY c.id");
        return finalQuery.toString();
    }

    @Override
    public List<CustomerEntity> getAllCustomer(Pageable pageable, CustomerRequestDTO customerRequestDTO) {
        StringBuilder sql = new StringBuilder(customerNativeQuery(customerRequestDTO));
        sql.append(" LIMIT ").append(pageable.getPageSize()).append("\n")
                .append(" OFFSET ").append(pageable.getOffset());
        System.out.println("Final query: " + sql);
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    public int countTotalItem(CustomerRequestDTO customerRequestDTO) {
        String sql = customerNativeQuery(customerRequestDTO);
//        String sql = buildQueryFilter();
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

}
