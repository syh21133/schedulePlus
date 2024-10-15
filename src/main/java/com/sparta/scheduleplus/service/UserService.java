package com.sparta.scheduleplus.service;

import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.dto.UserRequestDto;
import com.sparta.scheduleplus.entity.Schedule;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserService userService;

    public User findUser(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 유저입니다.")
        );
    }

    public String createUser(UserRequestDto dto) {

        User user = new User(dto);

        userRepository.save(user);

        return "유저생성";
    }

    public List<User> listUser() {
        return userRepository.findAllByOrderByModifiedAtDesc();
    }

    public String updateUser(Long userId, UserRequestDto dto, Long id) {
        User user = userService.findUser(userId);
        User user1 = userService.findUser(id);

        if(!user.equals(user1)) return "일치하지 않습니다.";

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userRepository.save(user);

        return "유저 수정";
    }


    public String deleteUser(Long userId, Long id) {
        User user = userService.findUser(userId);
        User user1 = userService.findUser(id);

        if(!user.equals(user1)) return "일치하지 않습니다.";
        userRepository.deleteById(id);

        return "유저 삭제";
    }
}
