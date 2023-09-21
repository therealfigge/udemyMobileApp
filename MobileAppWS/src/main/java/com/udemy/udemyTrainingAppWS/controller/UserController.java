/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemyTrainingAppWS.controller;

import com.udemy.udemyTrainingAppWS.service.UserService;
import com.udemy.udemyTrainingAppWS.shared.dto.UserDto;
import com.udemy.udemyTrainingAppWS.ui.model.request.UserDetailsRequestModel;
import com.udemy.udemyTrainingAppWS.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fredr
 */
@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping
    public String getUser() {
        return null;
    }
    
    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        
        BeanUtils.copyProperties(userDetails, userDto);
        
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        
        return returnValue;
    }
    
    @PutMapping
    public String updateUser() {
        return "Called update user";
    }
    
    @DeleteMapping
    public String deleteUser() {
        return "Called deleteUser";
    }
}