/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.MobileAppWS.ui.controller;

import com.udemy.MobileAppWS.model.request.UserDetailsRequestModel;
import com.udemy.MobileAppWS.model.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserResponse userResponse = new UserResponse();
        
        return new ResponseEntity(userResponse, HttpStatus.CREATED);
    }
    
    @GetMapping
    public String getUserById() {
        return "Called get user by id";
    }
    
    @PutMapping
    public String updateUser() {
        return "Called update user";
    }
    
    @DeleteMapping
    public String deleteUser() {
        return "Called delete user";
    }
}
