package com.demo_dacs343w.controller;

import com.demo_dacs343w.dtos.request.UserDTO;
import com.demo_dacs343w.dtos.response.ResponseData;
import com.demo_dacs343w.dtos.response.ResponseError;
import com.demo_dacs343w.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseData<?> addUser(@Validated @RequestBody UserDTO user) {

        try {
            long userId = userService.saveUser(user);
            log.info("Request add user, {} {}", userId,user.getUserName());
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value() ,e.getMessage());
        }

        }

    }
