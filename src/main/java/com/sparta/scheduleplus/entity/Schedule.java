package com.sparta.scheduleplus.entity;

import com.sparta.scheduleplus.dto.ScheduleRequestDto;
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
@Table(name = "schedule")
public class Schedule extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String todo;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "userSchedule",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> authorList = new ArrayList<>();

    public Schedule(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }

    public Schedule(ScheduleRequestDto dto, User user) {
        this.title = dto.getTitle();
        this.todo = dto.getTodo();
        this.user = user;
    }
    public Schedule(ScheduleRequestDto dto) {
        this.title = dto.getTitle();
        this.todo = dto.getTodo();
    }


    public void update(String title, String todo) {
        this.title = title;
        this.todo = todo;
    }
}
