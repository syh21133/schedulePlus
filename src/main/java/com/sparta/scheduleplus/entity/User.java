package com.sparta.scheduleplus.entity;

import java.util.ArrayList;
import java.util.List;

import com.sparta.scheduleplus.dto.UserRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Schedule> scheduleList = new ArrayList<>();

	@ManyToMany(mappedBy = "authorList")
	private List<Schedule> authList = new ArrayList<>();

	public User(String username, String email, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public User(UserRequestDto dto) {
		this.username = dto.getUsername();
		this.email = dto.getEmail();
		this.password = dto.getPassword();

	}

	public void update(String username, String password) {
		this.username = username;
		this.password = password;
	}
}