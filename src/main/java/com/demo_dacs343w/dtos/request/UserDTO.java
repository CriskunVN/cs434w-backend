package com.demo_dacs343w.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @JsonProperty("confirm_password")
    private String confirmPassword;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Phone Number is blank")
    private String phone_number;


    @JsonProperty("fullname")
    private String fullName;

    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    @JsonProperty("dob")
    private Date dateOfBirth;


}
