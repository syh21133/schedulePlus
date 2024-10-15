package com.sparta.scheduleplus.controller;

import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;


    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ScheduleResponseDto createSchedule(@RequestAttribute("userId") Long userId,@Valid ScheduleRequestDto dto) {

        return scheduleService.createSchedule(userId,dto);
    }

    @GetMapping("/list")
    public List<ScheduleResponseDto> listSchedule() {
        return scheduleService.listSchedule().stream().map(ScheduleResponseDto::new).toList();
    }

    @PutMapping("/update/{id}")
    public String updateSchedule(@RequestAttribute("userId") Long userId,@Valid ScheduleRequestDto dto,
                                              @PathVariable Long id) {

        return scheduleService.updateSchedule(userId,dto,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSchedule(@RequestAttribute("userId") Long userId,@PathVariable Long id) {

        return scheduleService.deleteSchedule(userId,id);
    }


}

