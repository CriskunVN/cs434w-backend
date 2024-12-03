package com.demo_dacs343w.service.Impl;

import com.demo_dacs343w.dtos.request.UserDTO;
import com.demo_dacs343w.exception.ResourceNotFoundException;
import com.demo_dacs343w.models.UserEntity;
import com.demo_dacs343w.repository.UserRepository;
import com.demo_dacs343w.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public long saveUser(UserDTO userDTO) {
        UserEntity user = UserEntity.builder()
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhone_number())
                .fullName(userDTO.getFullName())
                .dob(userDTO.getDateOfBirth())
                .build();

        userRepository.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public Boolean loginUser(String phoneNumber , String password) {

        UserEntity userE = userRepository.findByPhoneNumber(phoneNumber);
        if(userE == null) {
            return false;
        }

        if(!userE.getPassword().equals(password)) {
            return false;
        }
        return true;
    }

    @Override
    public UserEntity findUserById(long id) {
      return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
