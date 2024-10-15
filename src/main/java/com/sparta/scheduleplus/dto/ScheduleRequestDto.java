package com.sparta.scheduleplus.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {

    @Size(min = 1, max = 50)
    private String title;
    @Size(min = 1, max = 200)
    private String todo;


}
