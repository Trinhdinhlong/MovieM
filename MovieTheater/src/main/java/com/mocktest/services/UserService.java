package com.mocktest.services;

import com.mocktest.dto.UserDto;
import com.mocktest.entities.ShowTime;
import com.mocktest.entities.User;
import com.mocktest.exceptions.BadRequestException;
import com.mocktest.exceptions.ErrorCode;
import com.mocktest.exceptions.NotFoundException;
import com.mocktest.repository.UserRepository;
import com.mocktest.until.PasswordEncoderExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService{

    private final String REGEX_PHONE = "\\d{10}";
    private final String REGEX_IDENTITY_CARD = "[0-9]{9,12}";
    private final String REGEX_EMAIL = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";

    @Autowired
    private UserRepository userRepository;
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException(ErrorCode.ERROR_DB_NOT_FOUND);
        }
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
    public User getById(Long id){
            User requests = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_USER_NOT_FOUND));
            return  requests;
    }
    public UserDto create(UserDto request){
        if (request.getUsername() == null
                && request.getPassword() == null
                && request.getEmail() == null
                && request.getFullName() == null
                && request.getDateOfBirth() == null
                && request.getGender() == null
                && request.getPhone() == null
                && request.getIdentityCard() == null
        ) throw new BadRequestException(ErrorCode.ERROR_DATA_NOT_MATCH);

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException(ErrorCode.ERROR_ACCOUNT_EXIST);
        }

        if (request.getEmail().matches(REGEX_EMAIL)) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new BadRequestException(ErrorCode.ERROR_EMAIL_EXISTED);
            }
        } else throw new BadRequestException(ErrorCode.ERROR_FORMAT_EMAIL);

        if(!PasswordEncoderExample.isValidPassword(request.getPassword())){
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_PASSWORD);
        }
        if (request.getPhone().matches(REGEX_PHONE)) {
            if (userRepository.existsByPhone(request.getPhone())) {
                throw new BadRequestException(ErrorCode.ERROR_PHONE_EXISTED);
            }
        } else throw new BadRequestException(ErrorCode.ERROR_FORMAT_PHONE);

        if (request.getIdentityCard().matches(REGEX_IDENTITY_CARD)) {
            if (userRepository.existsByIdentityCard(request.getIdentityCard())) {
                throw new BadRequestException(ErrorCode.ERROR_IDENTITY_CARD_EXISTED);
            }
        } else throw new BadRequestException(ErrorCode.ERROR_FORMAT_IDENTITY_CARD);

        if (request.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new BadRequestException(ErrorCode.ERROR_DATE_NOT_MATCH);
        }

        User requests = new User();
        BeanUtils.copyProperties(request, requests);
        requests.setPassword(PasswordEncoderExample.encode(requests.getPassword()));
        return new UserDto(userRepository.save(requests));
    }
    public UserDto updateById(UserDto request){
        if (request.getUsername() == null
                && request.getPassword() == null
                && request.getEmail() == null
                && request.getFullName() == null
                && request.getDateOfBirth() == null
                && request.getGender() == null
                && request.getPhone() == null
                && request.getIdentityCard() == null
        ) throw new BadRequestException(ErrorCode.ERROR_DATA_NOT_MATCH);

        if (!request.getEmail().matches(REGEX_EMAIL)) {
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_EMAIL);
        }
        if(!PasswordEncoderExample.isValidPassword(request.getPassword())){
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_PASSWORD);
        }
        if (!request.getPhone().matches(REGEX_PHONE)) {
            throw new BadRequestException(ErrorCode.ERROR_FORMAT_PHONE);
        }
        if (request.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new BadRequestException(ErrorCode.ERROR_DATE_NOT_MATCH);
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.ERROR_USER_NOT_FOUND));
        user.setPassword(PasswordEncoderExample.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setIdentityCard(request.getIdentityCard());
        user.setImageURL(request.getImageURL());
        return new UserDto(userRepository.save(user));
    }
    public void deleteById(Long request) {
        if (userRepository.existsById(request)) {
            User user = userRepository.getById(request);
            user.setActive("false");
            userRepository.save(user);
        }else {
            throw new NotFoundException(ErrorCode.ERROR_USER_NOT_FOUND);
        }
    }
    public UserDto getByUserName(String request) {
        if(request != null){
           return new UserDto(userRepository.findByUsername(request));

        }else {
            throw new NotFoundException(ErrorCode.ERROR_NOT_FOUND_USERNAME);
        }
    }
    public UserDto login(UserDto request){
        if (request.getUsername() == null && request.getPassword() == null) {
            throw new NotFoundException(ErrorCode.ERROR_DATA_NOT_MATCH);
        }

        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            throw new NotFoundException(ErrorCode.ERROR_NOT_FOUND_USERNAME);
        }

        if (user.getPassword() == null && !PasswordEncoderExample.checkpw(request.getPassword(), user.getPassword())) {
            throw new BadRequestException(ErrorCode.ERROR_PASSWORD_NOT_MATCH);
        }

        UserDto response = new UserDto(user);
        return response;
    }


}
