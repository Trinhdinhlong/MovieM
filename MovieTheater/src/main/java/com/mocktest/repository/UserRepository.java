package com.mocktest.repository;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.Gender;
import com.mocktest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByIdentityCard(String identityCard);
    boolean existsByPhone(String phone);

    @Override
    @Query ("FROM User u WHERE u.role.roleName = 'Employee' AND u.Active = 'true'")
    List<User> findAll();

}
