package com.imusicstudio.service.impl;

import com.imusicstudio.entities.User;
import com.imusicstudio.repository.UserRepository;
import com.imusicstudio.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveInfor(User user) {
        return null;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findUserByUserName(username) ;

    }
}
