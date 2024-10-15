package com.sparta.scheduleplus.controller;

import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.dto.UserRequestDto;
import com.sparta.scheduleplus.dto.UserResponseDto;
import com.sparta.scheduleplus.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public String createUSer(@Valid UserRequestDto dto) {

        return userService.createUser(dto);
    }

    @GetMapping("/list")
    public List<UserResponseDto> listUser() {
        return userService.listUser().stream().map(UserResponseDto::new).toList();
    }

    @PutMapping("/update/{id}")
    public String updateUser(@RequestAttribute("userId") Long userId,@Valid UserRequestDto dto,
                                 @PathVariable Long id) {

        return userService.updateUser(userId,dto,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@RequestAttribute("userId") Long userId,@PathVariable Long id) {

        return userService.deleteUser(userId,id);
    }
}
