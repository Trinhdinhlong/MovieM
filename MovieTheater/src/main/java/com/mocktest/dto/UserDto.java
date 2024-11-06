package com.mocktest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mocktest.entities.Gender;
import com.mocktest.entities.Role;
import com.mocktest.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    private Long userId;
    private String username;
    private String password;
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
    private String address;
    private String phone;
    private String identityCard;
    private String imageURL;
    private Role role;
    public UserDto(User entity) {
        if (entity != null) {
            BeanUtils.copyProperties(entity, this);
        }
    }


}
