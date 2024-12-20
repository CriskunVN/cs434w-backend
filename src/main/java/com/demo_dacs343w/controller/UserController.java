package com.demo_dacs343w.controller;

import com.demo_dacs343w.dtos.request.UserDTO;
import com.demo_dacs343w.dtos.request.UserLoginDTO;
import com.demo_dacs343w.dtos.response.ResponseData;
import com.demo_dacs343w.dtos.response.ResponseError;
import com.demo_dacs343w.exception.ResourceNotFoundException;
import com.demo_dacs343w.service.UserService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @CrossOrigin
    public ResponseData<?> addUser(@Validated @RequestBody UserDTO user) {

        try {
            log.info("DOB user: {}", user.getDateOfBirth());
            //check confirm password
            if(!user.getConfirmPassword().equals(user.getPassword())) {
                log.error("errorMessage={}", "Password does not mach");
                return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Password does not mach");
            }
            // save user
            long userId = userService.saveUser(user);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully");
        } catch (Exception e) {
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value() ,e.getMessage());
        }

        }

    @PostMapping("/login")
    @CrossOrigin
    public ResponseData<?> login(@Validated @RequestBody UserLoginDTO user ) {
        try {
            Boolean token = userService.loginUser(user.getPhoneNumber(),user.getPassword());
            if(!token) {
                return new ResponseError(HttpStatus.BAD_REQUEST.value(),"Wrong Password or Phone Number");
            }
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value() ,e.getMessage());
        }
        return new ResponseData(HttpStatus.OK.value(), "Login Successful");
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseData<?> getUser(@PathVariable @Min(1) long id) {
        log.info("Request get user detail, userId={},id");
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "user",userService.findUserById(id));
        }
        catch(ResourceNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(),e.getMessage());
        }
    }


    }
