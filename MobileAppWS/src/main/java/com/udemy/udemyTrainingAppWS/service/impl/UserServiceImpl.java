/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udemy.udemyTrainingAppWS.service.impl;

import com.udemy.udemyTrainingAppWS.exceptions.UserServiceException;
import com.udemy.udemyTrainingAppWS.io.entity.UserEntity;
import com.udemy.udemyTrainingAppWS.repository.UserRepository;
import com.udemy.udemyTrainingAppWS.service.UserService;
import com.udemy.udemyTrainingAppWS.shared.Utils;
import com.udemy.udemyTrainingAppWS.shared.dto.UserDto;
import java.util.ArrayList;
import java.util.List;

import com.udemy.udemyTrainingAppWS.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
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
    UserRepository userRepository;
    
    @Autowired
    Utils utils;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {
        
        UserEntity storedEntityDetails = userRepository.findByEmail(user.getEmail());
        
        if(storedEntityDetails != null) throw new RuntimeException("Email already stored in record!");
        
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        
        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        UserEntity storedUserDetails = userRepository.save(userEntity);
        
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);
        
        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);

        if(userEntity == null)
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updateUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        
        if(userEntity == null) throw new UsernameNotFoundException(username);
        
        return new User(username, userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto returnValue = new UserDto();
        
        UserEntity userEntity = userRepository.findByUserId(userId);
        
        if(userEntity==null) throw new UsernameNotFoundException(userId);
        BeanUtils.copyProperties(userEntity, returnValue);
        
        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
        List<UserEntity> users = usersPage.getContent();

        for (UserEntity userEntity : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userEntity, userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null)
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userRepository.delete(userEntity);
    }
}










