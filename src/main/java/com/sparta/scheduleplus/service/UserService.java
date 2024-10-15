package com.sparta.scheduleplus.service;

import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUser(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 유저입니다.")
        );
    }
}
