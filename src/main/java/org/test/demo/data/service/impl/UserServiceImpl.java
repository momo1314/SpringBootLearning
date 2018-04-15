package org.test.demo.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.demo.data.dao.UserMapper;
import org.test.demo.data.domain.User;
import org.test.demo.data.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * UserServive的实体类
 */
@Service
public class UserServiceImpl implements UserService{

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public User add(User user) {
        String passwordHash = passwordToHash(user.getPassword());
        user.setPassword(passwordHash);
        userMapper.add(user);
        return user;
    }


    @Override
    public User findById(int id) {
        User temp = new User();
        temp.setId(id);
        return userMapper.findOne(temp);
    }

    @Override
    public User findByName(String username) {
        User temp = new User();
        temp.setUsername(username);
        return userMapper.findOne(temp);
    }

    @Override
    public boolean comparePassword(User user, User userInDataBase) {
        return passwordToHash(user.getPassword()).equals(userInDataBase.getPassword());
    }

    @Override
    public String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }
}
