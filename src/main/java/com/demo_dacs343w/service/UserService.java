package com.demo_dacs343w.service;

import com.demo_dacs343w.dtos.request.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    long saveUser(UserDTO user);
    Boolean loginUser(String phoneNumber, String password);
}
