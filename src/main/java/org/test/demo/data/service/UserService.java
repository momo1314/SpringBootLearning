package org.test.demo.data.service;


import org.springframework.stereotype.Service;
import org.test.demo.data.domain.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * UserService接口类
 */
public interface UserService {

    User add(User user);

    String passwordToHash(String password);


    User findById(int id);

    User findByName(String username);

    boolean comparePassword(User user , User userInDataBase);


}
