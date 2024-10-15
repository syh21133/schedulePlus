package com.sparta.scheduleplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @Size(min = 8, max = 20)
    private String password;

}
