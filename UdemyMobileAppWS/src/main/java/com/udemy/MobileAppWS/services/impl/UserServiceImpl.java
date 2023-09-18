/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.MobileAppWS.services.impl;

import com.udemy.MobileAppWS.dto.UserDto;
import com.udemy.MobileAppWS.entity.UserEntity;
import com.udemy.MobileAppWS.repositories.UserRepository;
import com.udemy.MobileAppWS.services.UserService;
import com.udemy.MobileAppWS.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fredr
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    Utils utils;
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        
        if(userRepository.findUserByEmail(user.getEmail()) != null) throw new RuntimeException("Email already exists!");
        
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        
        // TODO - Remove the following two lines
        userEntity.setEncryptedPassword("test");
        
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        
        UserEntity storedValue = userRepository.save(userEntity);
        
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedValue, returnValue);
        
        return returnValue;
    }
    
}
