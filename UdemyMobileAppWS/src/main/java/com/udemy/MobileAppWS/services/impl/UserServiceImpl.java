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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author fredr
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    Utils utils;
    
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {
        
        if(userRepository.findUserByEmail(user.getEmail()) != null) throw new RuntimeException("Email already exists!");
        
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        
        UserEntity storedValue = userRepository.save(userEntity);
        
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedValue, returnValue);
        
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
