/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemyTrainingAppWS.service.impl;

import com.udemy.udemyTrainingAppWS.io.entity.UserEntity;
import com.udemy.udemyTrainingAppWS.repository.UserRepository;
import com.udemy.udemyTrainingAppWS.service.UserService;
import com.udemy.udemyTrainingAppWS.shared.Utils;
import com.udemy.udemyTrainingAppWS.shared.dto.UserDto;
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
    UserRepository userRepository;
    
    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {
        
        UserEntity storedEntityDetails = userRepository.findByEmail(user.getEmail());
        
        if(storedEntityDetails != null) throw new RuntimeException("Email already stored in record!");
        
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword("test");
        
        UserEntity storedUserDetails = userRepository.save(userEntity);
        
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        
        return returnValue;
    }
    
}
