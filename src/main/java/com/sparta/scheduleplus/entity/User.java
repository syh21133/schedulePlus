package com.sparta.scheduleplus.entity;


import com.sparta.scheduleplus.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(UserRequestDto dto) {
        this.email = dto.getEmail();
        this.username = dto.getUsername();
        this.password = dto.getPassword();

    }

    public void update(String username, String password) {
        this.username = username;
        this.password = password;
    }
}