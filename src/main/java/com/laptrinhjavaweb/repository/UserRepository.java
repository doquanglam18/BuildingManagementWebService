package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserNameAndStatus(String name, int status);
    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status,
                                                                                                  Pageable pageable);
    Page<UserEntity> findByStatusNot(int status, Pageable pageable);
    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseAndStatusNot(String userName, String fullName, int status);
    long countByStatusNot(int status);
    UserEntity findOneByUserName(String userName);
    UserEntity findOneByFullName(String fullName);
    List<UserEntity> findByStatusAndRoles_Code(Integer status, String roleCode);

    @Query(value ="SELECT * FROM users\n" +
            "WHERE  id IN (SELECT users.id FROM users WHERE users.id IN (\n" +
            "SELECT user_role.user_id FROM user_role WHERE user_role.role_id IN (\n" +
            "SELECT role.id FROM role WHERE role.code = 'STAFF')))", nativeQuery = true)
    List<UserEntity> findAllUser();
}
