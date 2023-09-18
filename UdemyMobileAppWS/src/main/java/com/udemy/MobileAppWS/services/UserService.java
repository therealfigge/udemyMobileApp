/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udemy.MobileAppWS.services;

import com.udemy.MobileAppWS.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author fredr
 */
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
}
