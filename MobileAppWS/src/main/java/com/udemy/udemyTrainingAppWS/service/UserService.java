/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udemy.udemyTrainingAppWS.service;

import com.udemy.udemyTrainingAppWS.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author fredr
 */
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
}
