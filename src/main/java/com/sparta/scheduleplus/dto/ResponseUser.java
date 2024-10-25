package com.sparta.scheduleplus.dto;

import com.sparta.scheduleplus.converter.DateTimeFormatConverter;
import com.sparta.scheduleplus.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseUser {
	private Long id;

	private String username;

	private String email;

	private String createdAt;

	public static ResponseUser make(User user) {
		return ResponseUser.builder()
			.id(user.getId())
			.username(user.getUsername())
			.email(user.getEmail())
			.createdAt(DateTimeFormatConverter.convertDateTimeFormat(user.getCreatedAt()))
			.build();
	}
}
