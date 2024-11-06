package com.mocktest.controllers;
import com.mocktest.dto.UserDto;
import com.mocktest.entities.Role;
import com.mocktest.services.RoleService;
import com.mocktest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    @PostMapping("/login")
    public ResponseEntity<Object> LoginUser(@RequestBody UserDto userDto){
        UserDto loggedInUser = userService.login(userDto);
        Object responseObject = new String[]{loggedInUser.getUsername(), (loggedInUser.getRole().getRoleName())};
        return new ResponseEntity<>(responseObject, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto request){
        Role role = roleService.getById(2L);
        request.setRole(role);
        UserDto response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/employee")
    public ResponseEntity<UserDto> saveEmployee(@RequestBody UserDto request){
        Role role = roleService.getById(3L);
        request.setRole(role);
        UserDto response = userService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/employee/booking/{username}")
    public ResponseEntity<UserDto> findUser(@PathVariable("username") String username) {
        return new ResponseEntity<>(userService.getByUserName(username), HttpStatus.OK);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.getByUserName(username), HttpStatus.OK);
    }
    @GetMapping("/employee")
    public ResponseEntity<List<UserDto>> listEmployee(){
        List<UserDto> response = userService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/profile")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request){
        userService.updateById(request);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long request){
        userService.deleteById(request);
        return new ResponseEntity<>(String.valueOf(request), HttpStatus.OK);
    }
}
