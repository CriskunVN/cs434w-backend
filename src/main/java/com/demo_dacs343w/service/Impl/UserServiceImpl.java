package com.demo_dacs343w.service.Impl;

import com.demo_dacs343w.dtos.request.UserDTO;
import com.demo_dacs343w.models.UserEntity;
import com.demo_dacs343w.repository.UserRepository;
import com.demo_dacs343w.service.UserService;
import lombok.RequiredArgsConstructor;
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
        userRepository.save(user);
        return user.getId();
    }
}
