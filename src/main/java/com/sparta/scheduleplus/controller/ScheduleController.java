package com.sparta.scheduleplus.controller;

import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;




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

    @GetMapping("/pageList")
    public Page<ScheduleResponseDto> pageSchedules(@RequestParam(required = false, defaultValue = "5") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        return scheduleService.pageSchedules(page-1,size).map(ScheduleResponseDto::new);
    }


}

