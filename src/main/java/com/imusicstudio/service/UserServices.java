package com.imusicstudio.service;

import com.imusicstudio.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {
    User saveInfor(User user);
    User findByUserName(String username);

}
