/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.udemy.MobileAppWS.repositories;

import com.udemy.MobileAppWS.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fredr
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);
}
