package com.sparta.scheduleplus.service;

import static com.sparta.scheduleplus.exception.enums.ExceptionCode.*;
import static com.sparta.scheduleplus.jwt.jwt.JwtUtil.*;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sparta.scheduleplus.dto.LoginRequestDto;
import com.sparta.scheduleplus.dto.SignupRequestDto;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.exception.customException.DuplicateEmailException;
import com.sparta.scheduleplus.exception.customException.NotFoundEntityException;
import com.sparta.scheduleplus.exception.customException.NotMatchPasswordException;
import com.sparta.scheduleplus.jwt.config.PasswordEncoder;
import com.sparta.scheduleplus.jwt.jwt.JwtUtil;
import com.sparta.scheduleplus.repository.UserRepository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public User signup(@Valid SignupRequestDto signupRequestDto) {
		String username = signupRequestDto.getUsername();
		String email = signupRequestDto.getEmail();
		String password = passwordEncoder.encode(signupRequestDto.getPassword());

		// email 중복확인
		Optional<User> checkEmail = userRepository.findByEmail(email);
		if (checkEmail.isPresent()) {
			throw new DuplicateEmailException(DUPLICATE_EMAIL);
		}

		//비밀번호 요구사항 체크는 SignRequestDto에서 설정

		// 사용자 생성 및 저장
		User user = new User(username, email, password);
		userRepository.save(user);

		return user;
	}

	public void login(LoginRequestDto requestDto, HttpServletResponse res) {
		String email = requestDto.getEmail();
		String password = requestDto.getPassword();

		//사용자 확인
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new NotFoundEntityException(NOT_FOUND_MEMBER));

		//비밀번호 확인
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new NotMatchPasswordException(NOT_MATCH_PASSWORD);
		}

		// JWT 생성 및 쿠기 저장 후 Response객체에 추가
		String token = jwtUtil.createToken(user.getEmail());
		jwtUtil.addJwtToCookie(token, res);
	}

	public void logout(HttpServletResponse res) {
		Cookie cookie = new Cookie(AUTHORIZATION_HEADER, null); //쿠키 생성 후 null -> 삭제
		cookie.setHttpOnly(true); // JavaScript에 의해 접근되지 않도록
		cookie.setMaxAge(0); // 유효 시간 0
		cookie.setPath("/"); // 모든 경로에서 유효
		res.addCookie(cookie);
	}
}
